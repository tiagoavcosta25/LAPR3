package lapr.project.model.service;

import lapr.project.data.DeliveryDB;
import lapr.project.data.DeliveryRunDB;
import lapr.project.model.Address;
import lapr.project.model.Courier;
import lapr.project.model.DeliveryRun;
import lapr.project.model.Drone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

import java.util.*;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;

class DeliveryRunServiceTest {

    @InjectMocks
    private DeliveryRunService m_oDeliveryRunService;

    @Mock
    private DeliveryRunDB m_oDeliveryRunDB;
    @Mock
    private DeliveryDB m_oDeliveryDB;

    @BeforeEach
    void setUp() {
        this.m_oDeliveryRunService = new DeliveryRunService();
        this.m_oDeliveryRunDB = Mockito.mock(DeliveryRunDB.class);
        this.m_oDeliveryDB = Mockito.mock(DeliveryDB.class);
        initMocks(this);
    }


    @Test
    void newDeliveryRun() {
        DeliveryRun expected = new DeliveryRun();
        DeliveryRun real = m_oDeliveryRunService.newDeliveryRun(new Courier(), new ArrayList<>());
        assertEquals(expected, real);
    }

    @Test
    void addNewDeliveryRun() {
        when(m_oDeliveryRunDB.addNewDeliveryRun(new DeliveryRun())).thenReturn(true);
        boolean real = m_oDeliveryRunService.addNewDeliveryRun(new DeliveryRun());
        assertTrue(real);
    }

    @Test
    void getAddressesByDeliveryRunId() {
        when(m_oDeliveryRunDB.getAddressesByDeliveryRunId("email")).thenReturn(new ArrayList<>());
        List<Address> result = m_oDeliveryRunService.getAddressesByDeliveryRunId("email");
        assertEquals(result, new ArrayList<>());
    }


    @Test
    void getMaxPayload() {
    when(m_oDeliveryDB.getMaxPayload("email")).thenReturn(10f);
    float result = m_oDeliveryRunService.getMaxPayload("email");
    assertEquals(result, 10f);

    }

    @Test
    void startDeliveryRun() {
        Map<String,String> lst = new TreeMap<>();
        when(m_oDeliveryDB.startDeliveryRun(new Drone(),"123")).thenReturn(lst);
        Map<String,String> result = m_oDeliveryRunService.startDeliveryRun(new Drone(),"123");
        assertEquals(lst, result);

    }

    @Test
    void sendsEmail() {
        Map<String,String> lst = new TreeMap<>();
        boolean result = m_oDeliveryRunService.sendsEmail(lst);
        assertTrue(result);
    }
}