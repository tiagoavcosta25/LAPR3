package lapr.project.controller;

import lapr.project.model.Courier;
import lapr.project.model.DeliveryRun;
import lapr.project.model.Drone;
import lapr.project.model.UserSession;
import lapr.project.model.service.CourierService;
import lapr.project.model.service.DeliveryRunService;
import lapr.project.model.service.PharmacyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class StartDeliveryRunControllerTest {

    @InjectMocks
    private StartDeliveryRunController startDeliveryRunController;


    @Mock
    private DeliveryRunService mockDeliveryService;

    private boolean assertTrue;

    @BeforeEach
    void setUp() {
        this.assertTrue = true;
        this.startDeliveryRunController = new StartDeliveryRunController();
        this.mockDeliveryService = Mockito.mock(DeliveryRunService.class);
        initMocks(this);
    }

    @Test
    void startDeliveryRun() {
        Map<String,String> lst = new TreeMap<>();
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("123"));
        when(mockDeliveryService.startDeliveryRun(ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail())).thenReturn(lst);
        when(mockDeliveryService.sendsEmail(lst)).thenReturn(assertTrue);
        boolean result =  startDeliveryRunController.startDeliveryRun();
        assertEquals(assertTrue,result);

        when(mockDeliveryService.startDeliveryRun(ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail())).thenReturn(lst);
        when(mockDeliveryService.sendsEmail(lst)).thenReturn(false);
        result =  startDeliveryRunController.startDeliveryRun();
        assertEquals(false,result);

        when(mockDeliveryService.startDeliveryRun(ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail())).thenThrow(new NullPointerException());
        when(mockDeliveryService.sendsEmail(lst)).thenReturn(assertTrue);
        result =  startDeliveryRunController.startDeliveryRun();
        assertEquals(false,result);

    }

}