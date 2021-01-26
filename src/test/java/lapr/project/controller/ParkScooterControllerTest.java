package lapr.project.controller;

import lapr.project.model.UserSession;
import lapr.project.model.service.CourierService;
import lapr.project.model.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ParkScooterControllerTest {
    @InjectMocks
    private ParkScooterController oParkScooterController;
    @Mock
    private CourierService mockCourierService;

    public ParkScooterControllerTest() {
        this.oParkScooterController = new ParkScooterController();
    }

    @BeforeEach
    void setUp() {
        this.oParkScooterController = new ParkScooterController();
        this.mockCourierService = Mockito.mock(CourierService.class);
        initMocks(this);
    }


    @Test
    void parkScooter() {
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email123@gmail.com", 3));

        when (mockCourierService.parkScooter(10)).thenReturn(true);
        boolean result = oParkScooterController.parkScooter(10);
        assertTrue(result);

        when (mockCourierService.parkScooter(10)).thenReturn(false);
        result = oParkScooterController.parkScooter(10);

        assertFalse(result);

        when (mockCourierService.parkScooter(10)).thenThrow(new IllegalArgumentException());
        result = oParkScooterController.parkScooter(10);

        assertFalse(result);

        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email123@gmail.com", 1));
        result = oParkScooterController.parkScooter(10);

        assertFalse(result);

    }

    @Test
    void getServ() {
        ParkScooterController ctrl = new ParkScooterController();
        CourierService expected = new CourierService();
        ctrl.setServ(expected);
        CourierService real = ctrl.getServ();
        assertEquals(expected, real);
    }

    @Test
    void setServ() {
        ParkScooterController ctrl = new ParkScooterController();
        CourierService expected = new CourierService();
        ctrl.setServ(expected);
        CourierService real = ctrl.getServ();
        assertEquals(expected, real);
    }
}