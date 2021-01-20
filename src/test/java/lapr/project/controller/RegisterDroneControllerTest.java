package lapr.project.controller;

import lapr.project.model.Drone;
import lapr.project.model.Pharmacy;
import lapr.project.model.VehicleModel;
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

    private Drone expectedDrone;

    @BeforeEach
    void setUp() {
        this.expectedDrone = new Drone(new VehicleModel(), new Pharmacy());
        this.registerDroneController = new RegisterDroneController();
        this.mockDroneService = Mockito.mock(DroneService.class);
        this.mockPharmacyService = Mockito.mock(PharmacyService.class);
        initMocks(this);
    }

    @Test
    void newDrone() {
        System.out.println("newDrone");

        when(mockDroneService.newDrone(new VehicleModel(), new Pharmacy())).thenReturn(expectedDrone);

        boolean result = registerDroneController.newDrone(new VehicleModel(), new Pharmacy());
        assertTrue(result);


        when(mockDroneService.newDrone(new VehicleModel(), new Pharmacy())).thenThrow(new IllegalArgumentException());
        result = registerDroneController.newDrone(new VehicleModel(), new Pharmacy());
        assertFalse(result);
    }

    @Test
    void registersDrone() {
        System.out.println("registerDrone");
        Drone d = new Drone(-2, new VehicleModel(), new Pharmacy());

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