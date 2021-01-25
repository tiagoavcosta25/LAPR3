package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.service.PharmacyService;
import lapr.project.model.service.ScooterService;
import lapr.project.model.service.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class RegisterParkControllerTest {

    @InjectMocks
    private RegisterParkController moRegisterParkController;

    @Mock
    private PharmacyService mockPharmcyService;

    @BeforeEach
    void setUp() {
        this.moRegisterParkController = new RegisterParkController();
        this.mockPharmcyService = Mockito.mock(PharmacyService.class);
        initMocks(this);
    }

    @Test
    void addPark() {
        System.out.println("addPark");

        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email", UserSession.Role.ADMIN));

        when(mockPharmcyService.addPark("email", new Park(1,1d,
                        VehicleType.SCOOTER), 1,1)).thenReturn(true);
        boolean result = moRegisterParkController.addPark("email", 1,
                1d,VehicleType.SCOOTER,1,1);
        assertTrue(result);

        when(mockPharmcyService.addPark("test", new Park(3,3d,
                VehicleType.SCOOTER), 3,3)).thenThrow(new IllegalArgumentException());
        result = moRegisterParkController.addPark("test", 3,
                3d,VehicleType.SCOOTER,3,3);
        assertFalse(result);

        ApplicationPOT.getInstance().setCurrentSession(new UserSession("test", UserSession.Role.CLIENT));

        when(mockPharmcyService.addPark("test", new Park(2,2d,
                VehicleType.SCOOTER), 2,2)).thenReturn(true);
        result = moRegisterParkController.addPark("test", 2,
                2d,VehicleType.SCOOTER,2,2);
        assertFalse(result);
    }
}