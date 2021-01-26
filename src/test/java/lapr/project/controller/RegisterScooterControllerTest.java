package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.service.PharmacyService;
import lapr.project.model.service.ProductService;
import lapr.project.model.service.ScooterService;
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
import static org.mockito.ArgumentMatchers.booleanThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class RegisterScooterControllerTest {

    @InjectMocks
    private RegisterScooterController registerScooterController;

    @Mock
    private ScooterService mockScooterService;

    @Mock
    private PharmacyService mockPharmacyService;

    @Mock
    private VehicleService mockVehicleService;

    private Scooter expectedScooter;

    @BeforeEach
    void setUp() {
        this.expectedScooter = new Scooter(new VehicleModel(), new Pharmacy());
        this.registerScooterController = new RegisterScooterController();
        this.mockScooterService = Mockito.mock(ScooterService.class);
        this.mockPharmacyService = Mockito.mock(PharmacyService.class);
        this.mockVehicleService = Mockito.mock(VehicleService.class);
        initMocks(this);
    }

    @Test
    void newScooter() {
        System.out.println("newScooter");

        when(mockScooterService.newScooter(new VehicleModel(), new Pharmacy())).thenReturn(expectedScooter);

        boolean result = registerScooterController.newScooter();
        assertTrue(result);


        when(mockScooterService.newScooter(null, null)).thenThrow(new IllegalArgumentException());

        result = registerScooterController.newScooter();
        assertFalse(result);
    }

    @Test
    void registersScooter() {
        System.out.println("registerScooter");
        Scooter s = new Scooter(-1, 98, new VehicleModel(), new Pharmacy());

        registerScooterController.setScooter(s);
        when(mockScooterService.registerScooter(s)).thenReturn(-1);
        boolean result = registerScooterController.registersScooter();
        assertTrue(result);

        when(mockScooterService.registerScooter(s)).thenThrow(new IllegalArgumentException());
        result = registerScooterController.registersScooter();
        assertFalse(result);
    }

    @Test
    void showPharmacies() {
        System.out.println("showPharmacies");

        List<Pharmacy> expectedListPharmacies = new ArrayList<>(Arrays.asList(new Pharmacy()));

        when(mockPharmacyService.getPharmacies()).thenReturn(expectedListPharmacies);

        List<Pharmacy> result = registerScooterController.showPharmacies();
        assertEquals(expectedListPharmacies, result);
    }

    @Test
    void setVehicleModel() {
        System.out.println("setVehicleModel");

        when(mockVehicleService.getVehicleModel("Test")).thenReturn(new VehicleModel());
        boolean real = registerScooterController.setVehicleModel("Test");
        assertTrue(real);

        when(mockVehicleService.getVehicleModel("Test")).thenThrow(new IllegalArgumentException());
        real = registerScooterController.setVehicleModel("Test");
        assertFalse(real);
    }

    @Test
    void newVehicleModel() {
        System.out.println("newVehicleModel");

        when(mockVehicleService.newVehicleModel("Test", 1d, 1d, 1d,
                1, 1d, 1d, VehicleType.SCOOTER)).thenReturn(new VehicleModel());

        boolean result = registerScooterController.newVehicleModel("Test", 1d, 1d, 1d,
                1, 1d, 1d);
        assertTrue(result);


        when(mockVehicleService.newVehicleModel("", 0d,0d,0d,0,
                0d,0d,VehicleType.SCOOTER)).thenThrow(new IllegalArgumentException());

        result = registerScooterController.newVehicleModel("", 0d,0d,0d,0,
                0d,0d);
        assertFalse(result);
    }

    @Test
    void setPharmacy() {
        System.out.println("setPharmacy");

        when(mockPharmacyService.getPharmacy("Test")).thenReturn(new Pharmacy());
        boolean real = registerScooterController.setPharmacy("Test");
        assertTrue(real);

        when(mockPharmacyService.getPharmacy("Test")).thenThrow(new IllegalArgumentException());
        real = registerScooterController.setPharmacy("Test");
        assertFalse(real);
    }

    @Test
    void getPharmacyService() {
        RegisterScooterController ctrl = new RegisterScooterController();
        PharmacyService expected = new PharmacyService();
        ctrl.setPharmacyService(expected);
        PharmacyService real = ctrl.getPharmacyService();
        assertEquals(expected, real);
    }

    @Test
    void setPharmacyService() {
        RegisterScooterController ctrl = new RegisterScooterController();
        PharmacyService expected = new PharmacyService();
        ctrl.setPharmacyService(expected);
        PharmacyService real = ctrl.getPharmacyService();
        assertEquals(expected, real);
    }

    @Test
    void getScooterService() {
        RegisterScooterController ctrl = new RegisterScooterController();
        ScooterService expected = new ScooterService();
        ctrl.setScooterService(expected);
        ScooterService real = ctrl.getScooterService();
        assertEquals(expected, real);
    }

    @Test
    void setScooterService() {
        RegisterScooterController ctrl = new RegisterScooterController();
        ScooterService expected = new ScooterService();
        ctrl.setScooterService(expected);
        ScooterService real = ctrl.getScooterService();
        assertEquals(expected, real);
    }

    @Test
    void getVehicleService() {
        RegisterScooterController ctrl = new RegisterScooterController();
        VehicleService expected = new VehicleService();
        ctrl.setVehicleService(expected);
        VehicleService real = ctrl.getVehicleService();
        assertEquals(expected, real);
    }

    @Test
    void setVehicleService() {
        RegisterScooterController ctrl = new RegisterScooterController();
        VehicleService expected = new VehicleService();
        ctrl.setVehicleService(expected);
        VehicleService real = ctrl.getVehicleService();
        assertEquals(expected, real);
    }
}