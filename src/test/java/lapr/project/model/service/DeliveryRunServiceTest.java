package lapr.project.model.service;

import javafx.util.Pair;
import lapr.project.data.DeliveryDB;
import lapr.project.data.DeliveryRunDB;
import lapr.project.model.Address;
import lapr.project.model.Courier;
import lapr.project.model.DeliveryRun;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
    void calculateMostEfficientPath() {
    when(m_oDeliveryDB.calculateMostEfficientPath(new Address(), new Address(), new LinkedList<>())).thenReturn(new Pair<>(null, -1.0));
    Pair<LinkedList<Address>, Double> result = m_oDeliveryRunService.calculateMostEfficientPath(new Address(), new Address(), new LinkedList<>());
    assertEquals(-1, result.getValue());
    }

    @Test
    void getMaxPayload() {
    when(m_oDeliveryDB.getMaxPayload("email")).thenReturn(10f);
    float result = m_oDeliveryRunService.getMaxPayload("email");
    assertEquals(result, 10f);

    }
}