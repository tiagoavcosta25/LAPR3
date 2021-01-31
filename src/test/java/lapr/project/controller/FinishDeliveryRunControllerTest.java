package lapr.project.controller;

import lapr.project.model.PharmacyTransfer;
import lapr.project.model.UserSession;
import lapr.project.model.service.DeliveryRunService;
import lapr.project.model.service.PharmacyTransferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class FinishDeliveryRunControllerTest {

    @InjectMocks
    private FinishDeliveryRunController finishDeliveryRunController;

    @Mock
    private DeliveryRunService mockDeliveryRunService;

    @BeforeEach
    void setUp() {
        this.finishDeliveryRunController = new FinishDeliveryRunController();
        this.mockDeliveryRunService = Mockito.mock(DeliveryRunService.class);
        initMocks(this);
    }

    @Test
    void finishDeliveryRun() {
        System.out.println("issueTransferNote");

        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email3@email.com"));
        when(mockDeliveryRunService.finishDeliveryRun(1,1, "email3@email.com")).thenReturn(true);
        boolean result = finishDeliveryRunController.finishDeliveryRun(1, 1);
        assertTrue(result);


        when(mockDeliveryRunService.finishDeliveryRun(1,1, "email3@email.com")).thenReturn(false);
        result = finishDeliveryRunController.finishDeliveryRun(1, 1);
        assertFalse(result);

        when(mockDeliveryRunService.finishDeliveryRun(1,1, "email3@email.com")).thenThrow(new IllegalArgumentException());
        result = finishDeliveryRunController.finishDeliveryRun(1, 1);
        assertFalse(result);
    }

    @Test
    void getDeliveryRunService() {
        FinishDeliveryRunController ctrl = new FinishDeliveryRunController();
        DeliveryRunService expected = new DeliveryRunService();
        ctrl.setDeliveryRunService(expected);
        DeliveryRunService real = ctrl.getDeliveryRunService();
        assertEquals(expected, real);
    }

    @Test
    void setDeliveryRunService() {
        FinishDeliveryRunController ctrl = new FinishDeliveryRunController();
        DeliveryRunService expected = new DeliveryRunService();
        ctrl.setDeliveryRunService(expected);
        DeliveryRunService real = ctrl.getDeliveryRunService();
        assertEquals(expected, real);
    }
}