package lapr.project.controller;

import lapr.project.data.DeliveryDB;
import lapr.project.model.UserSession;
import lapr.project.model.service.DeliveryRunService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class MaxPayloadControllerTest {
    @InjectMocks
    private MaxPayloadController maxPayloadController;

    @Mock
    private DeliveryRunService mockDeliveryRunService;

    private float expectedTrue;

    @BeforeEach
    void setUp() {
        this.expectedTrue = 10f;
        this.maxPayloadController = new MaxPayloadController();
        this.mockDeliveryRunService = Mockito.mock(DeliveryRunService.class);
        initMocks(this);
    }

    @Test
    void ensureGetMaxPayloadWorks() {
        System.out.println("getMaxPayload");
        String email = "email6@gmail.com";
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email6@gmail.com"));
        when(mockDeliveryRunService.getMaxPayload(email)).thenReturn(expectedTrue);
        float result = maxPayloadController.getMaxPayload();
        assertEquals(expectedTrue, result);
    }
}
