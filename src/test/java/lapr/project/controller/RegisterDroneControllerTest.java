package lapr.project.controller;

import lapr.project.model.Drone;
import lapr.project.model.Pharmacy;
import lapr.project.model.VehicleModel;
import lapr.project.model.VehicleType;
import lapr.project.model.service.DroneService;
import lapr.project.model.service.PharmacyService;
import lapr.project.model.service.VehicleService;
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

    @Mock
    private VehicleService mockVehicleService;

    private Drone expectedDrone;

    @BeforeEach
    void setUp() {
        this.expectedDrone = new Drone(new VehicleModel(), new Pharmacy());
        this.registerDroneController = new RegisterDroneController();
        this.mockDroneService = Mockito.mock(DroneService.class);
        this.mockPharmacyService = Mockito.mock(PharmacyService.class);
        this.mockVehicleService = Mockito.mock(VehicleService.class);
        initMocks(this);
    }

    @Test
    void newDrone() {
        System.out.println("newDrone");

        when(mockDroneService.newDrone(new VehicleModel(), new Pharmacy())).thenReturn(expectedDrone);

        boolean result = registerDroneController.newDrone();
        assertTrue(result);


        when(mockDroneService.newDrone(null, null)).thenThrow(new IllegalArgumentException());
        result = registerDroneController.newDrone();
        assertFalse(result);
    }

    @Test
    void registersDrone() {
        System.out.println("registerDrone");
        Drone d = new Drone(-2, new VehicleModel(), new Pharmacy());

        registerDroneController.setDrone(d);
        when(mockDroneService.registerDrone(d)).thenReturn(-2);
        boolean result = registerDroneController.registersDrone();
        assertTrue(result);

        when(mockDroneService.registerDrone(d)).thenThrow(new IllegalArgumentException());
        result = registerDroneController.registersDrone();
        assertFalse(result);
    }

    @Test
    void showPharmacies() {
        System.out.println("showPharmacies");

        List<Pharmacy> expectedListPharmacies = new ArrayList<>(Arrays.asList(new Pharmacy()));

        when(mockPharmacyService.getPharmacies()).thenReturn(expectedListPharmacies);

        List<Pharmacy> result = registerDroneController.showPharmacies();
        assertEquals(expectedListPharmacies, result);
    }

    @Test
    void setVehicleModel() {
        System.out.println("setVehicleModel");

        when(mockVehicleService.getVehicleModel("Test")).thenReturn(new VehicleModel());
        boolean real = registerDroneController.setVehicleModel("Test");
        assertTrue(real);

        when(mockVehicleService.getVehicleModel("Test")).thenThrow(new IllegalArgumentException());
        real = registerDroneController.setVehicleModel("Test");
        assertFalse(real);
    }

    @Test
    void newVehicleModel() {
        System.out.println("newVehicleModel");

        when(mockVehicleService.newVehicleModel("Test", 1d, 1d, 1d,
                1, 1d, 1d, VehicleType.DRONE)).thenReturn(new VehicleModel());

        boolean result = registerDroneController.newVehicleModel("Test", 1d, 1d, 1d,
                1, 1d, 1d);
        assertTrue(result);


        when(mockVehicleService.newVehicleModel("", 0d,0d,0d,0,
                0d,0d,VehicleType.SCOOTER)).thenThrow(new IllegalArgumentException());

        result = registerDroneController.newVehicleModel("", 0d,0d,0d,0,
                0d,0d);
        assertFalse(result);
    }

    @Test
    void setPharmacy() {
        System.out.println("setPharmacy");

        when(mockPharmacyService.getPharmacy("Test")).thenReturn(new Pharmacy());
        boolean real = registerDroneController.setPharmacy("Test");
        assertTrue(real);

        when(mockPharmacyService.getPharmacy("Test")).thenThrow(new IllegalArgumentException());
        real = registerDroneController.setPharmacy("Test");
        assertFalse(real);
    }

    @Test
    void getPharmacyService() {
        RegisterDroneController ctrl = new RegisterDroneController();
        PharmacyService expected = new PharmacyService();
        ctrl.setPharmacyService(expected);
        PharmacyService real = ctrl.getPharmacyService();
        assertEquals(expected, real);
    }

    @Test
    void setPharmacyService() {
        RegisterDroneController ctrl = new RegisterDroneController();
        PharmacyService expected = new PharmacyService();
        ctrl.setPharmacyService(expected);
        PharmacyService real = ctrl.getPharmacyService();
        assertEquals(expected, real);
    }

    @Test
    void getDroneService() {
        RegisterDroneController ctrl = new RegisterDroneController();
        DroneService expected = new DroneService();
        ctrl.setDroneService(expected);
        DroneService real = ctrl.getDroneService();
        assertEquals(expected, real);
    }

    @Test
    void setDroneService() {
        RegisterDroneController ctrl = new RegisterDroneController();
        DroneService expected = new DroneService();
        ctrl.setDroneService(expected);
        DroneService real = ctrl.getDroneService();
        assertEquals(expected, real);
    }

    @Test
    void getVehicleService() {
        RegisterDroneController ctrl = new RegisterDroneController();
        VehicleService expected = new VehicleService();
        ctrl.setVehicleService(expected);
        VehicleService real = ctrl.getVehicleService();
        assertEquals(expected, real);
    }

    @Test
    void setVehicleService() {
        RegisterDroneController ctrl = new RegisterDroneController();
        VehicleService expected = new VehicleService();
        ctrl.setVehicleService(expected);
        VehicleService real = ctrl.getVehicleService();
        assertEquals(expected, real);
    }
}