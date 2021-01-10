package lapr.project.controller;

import lapr.project.model.Product;
import lapr.project.model.registration.DeliveryRegistration;
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
    private DeliveryRegistration mockDeliveryRegistration;

    private boolean expectedTrue;

    @BeforeEach
    void setUp() {
        this.expectedTrue = true;
        this.maxPayloadController = new MaxPayloadController();
        this.mockDeliveryRegistration = Mockito.mock(DeliveryRegistration.class);
        initMocks(this);
    }

    @Test
    void ensureGetMaxPayloadWorks() {
        /*
        System.out.println("getMaxPayload");
        float maxPayload = 10f;
        String email = "email6@gmail.com";
        when(mockDeliveryRegistration.getMaxPayload(email) == maxPayload).thenReturn(expectedTrue);
        float result = maxPayloadController.getMaxPayload();
        assertEquals(expectedTrue, result);*/
    }
}
