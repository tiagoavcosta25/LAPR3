package lapr.project.controller;

import lapr.project.model.Drone;
import lapr.project.model.Pharmacy;
import lapr.project.model.service.DroneService;
import lapr.project.model.service.PharmacyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class RegisterDroneControllerTest {

    @InjectMocks
    private RegisterDroneController registerDroneController;

    @Mock
    private DroneService mockDroneService;

    @Mock
    private PharmacyService mockPharmacyService;

    private Drone expectedTrue;

    @BeforeEach
    void setUp() {
        this.expectedTrue = new Drone(35.5f, 30f, 30f, "No Charging",
                250f, 100, 20, new Pharmacy());
        this.registerDroneController = new RegisterDroneController();
        this.mockDroneService = Mockito.mock(DroneService.class);
        this.mockPharmacyService = Mockito.mock(PharmacyService.class);
        initMocks(this);
    }

    @Test
    void newDrone() {
        System.out.println("newDrone");

        when(mockPharmacyService.getPharmacy(1)).thenReturn(new Pharmacy());

        when(mockDroneService.newDrone(35.5f, "No Charging",
                250f, 30f,100, 20f, 30f, new Pharmacy())).thenReturn(expectedTrue);

        boolean result = registerDroneController.newDrone(35.5f, "No Charging",
                250f, 30f,100, 20f, 30f,new Pharmacy());
        assertTrue(result);


        when(mockDroneService.newDrone(35.5f, "No Charging",
                250f, 30f,100, 20f, 30f,
                new Pharmacy())).thenThrow(new IllegalArgumentException());
        result = registerDroneController.newDrone(35.5f, "No Charging",
                250f, 30f,100, 20f, 30f,
                new Pharmacy());
        assertFalse(result);
    }

    @Test
    void registersDrone() {
        System.out.println("registerDrone");
        Drone d = new Drone(35.5f, 30f, 30f, "No Charging",
                250f, 100, 20, new Pharmacy());

        registerDroneController.setDrone(d);
        when(mockDroneService.registerDrone(d)).thenReturn(true);
        boolean result = registerDroneController.registersDrone();
        assertTrue(result);
    }

    @Test
    void showPharmacies() {
        System.out.println("showPharmacies");

        List<Pharmacy> expectedListPharmacies = new ArrayList<>(Arrays.asList(new Pharmacy()));

        when(mockPharmacyService.getPharmacies()).thenReturn(expectedListPharmacies);

        List<Pharmacy> result = registerDroneController.showPharmacies();
        assertEquals(expectedListPharmacies, result);
    }
}