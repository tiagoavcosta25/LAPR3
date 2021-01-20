package lapr.project.controller;


import lapr.project.model.Courier;
import lapr.project.model.DeliveryRun;
import lapr.project.model.UserSession;
import lapr.project.model.service.DeliveryRunService;
import lapr.project.model.service.OrderService;
import lapr.project.model.service.PharmacyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class RegisterDeliveryRunControllerTest {

    @InjectMocks
    private RegisterDeliveryRunController m_ctrl;

    @Mock
    private DeliveryRunService m_mockDeliveryRunService;
    @Mock
    private PharmacyService m_mockPharmacyService;
    @Mock
    private OrderService m_mockOrderService;

    RegisterDeliveryRunControllerTest() {

    }

    @BeforeEach
    void setUp() {
        this.m_ctrl = new RegisterDeliveryRunController();
        this.m_mockDeliveryRunService = Mockito.mock(DeliveryRunService.class);
        this.m_mockOrderService = Mockito.mock(OrderService.class);
        this.m_mockPharmacyService = Mockito.mock(PharmacyService.class);
        initMocks(this);
    }

    @Test
    void registerDeliveryRun() {
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("", 2));
        when(m_mockPharmacyService.getSuitableCourier()).thenReturn(new Courier());
        when(m_mockDeliveryRunService.newDeliveryRun(new Courier(),new ArrayList<>())).thenReturn(new DeliveryRun());
        when(m_mockDeliveryRunService.addNewDeliveryRun(new DeliveryRun())).thenReturn(true);
        boolean real = m_ctrl.registerDeliveryRun(new ArrayList<>());
        assertTrue(real);

        when(m_mockDeliveryRunService.addNewDeliveryRun(new DeliveryRun())).thenReturn(false);
        real = m_ctrl.registerDeliveryRun(new ArrayList<>());
        assertFalse(real);

        ApplicationPOT.getInstance().setCurrentSession(new UserSession("",1));
        real = m_ctrl.registerDeliveryRun(new ArrayList<>());
        assertFalse(real);


    }
}