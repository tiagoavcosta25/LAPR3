package lapr.project.controller;

import lapr.project.model.service.DeliveryRunService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RegisterPathControllerTest {
    @InjectMocks
    private RegisterPathController oRegisterPathController;
    @Mock
    private DeliveryRunService mockDeliveryRunService;
    @BeforeEach
    void setUp() {
        this.oRegisterPathController = new RegisterPathController();
        this.mockDeliveryRunService = Mockito.mock(DeliveryRunService.class);
        initMocks(this);
    }

    @Test
    void registerPath() {
        when(mockDeliveryRunService.registerPath(41.08555,-8.53666,41.9985,-8.058,"rua1",12,45,0.8)).thenReturn(true);
        boolean result = this.oRegisterPathController.registerPath(41.08555,-8.53666,41.9985,-8.058,"rua1",12,45,0.8);
        assertTrue(result);
    }
    @Test
    void registerPat2h() {
        when(mockDeliveryRunService.registerPath(41.08555,-8.53666,41.9985,-8.058,"rua1",12,45,0.8)).thenReturn(false);
        boolean result = this.oRegisterPathController.registerPath(41.08555,-8.53666,41.9985,-8.058,"rua1",12,45,0.8);
        assertFalse(result);
    }
}
