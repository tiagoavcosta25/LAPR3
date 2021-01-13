package lapr.project.model.service;

import lapr.project.data.DeliveryRunDB;
import lapr.project.model.Courier;
import lapr.project.model.DeliveryRun;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;

class DeliveryRunServiceTest {

    @InjectMocks
    private DeliveryRunService m_oDeliveryRunService;

    @Mock
    private DeliveryRunDB m_oDeliveryRunDB;

    @BeforeEach
    void setUp() {
        this.m_oDeliveryRunService = new DeliveryRunService();
        this.m_oDeliveryRunDB = Mockito.mock(DeliveryRunDB.class);
        initMocks(this);
    }


    @Test
    void newDeliveryRun() {
        DeliveryRun expected = new DeliveryRun();
        DeliveryRun real = m_oDeliveryRunService.newDeliveryRun(new Courier(),new ArrayList<>());
        assertEquals(expected,real);
    }

    @Test
    void addNewDeliveryRun() {
        when(m_oDeliveryRunDB.addNewDeliveryRun(new DeliveryRun())).thenReturn(true);
        boolean real = m_oDeliveryRunService.addNewDeliveryRun(new DeliveryRun());
        assertTrue(real);
    }
}