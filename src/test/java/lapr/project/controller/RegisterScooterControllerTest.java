package lapr.project.controller;

import lapr.project.model.Pharmacy;
import lapr.project.model.Scooter;
import lapr.project.model.VehicleModel;
import lapr.project.model.service.PharmacyService;
import lapr.project.model.service.ScooterService;
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

class RegisterScooterControllerTest {

    @InjectMocks
    private RegisterScooterController registerScooterController;

    @Mock
    private ScooterService mockScooterService;

    @Mock
    private PharmacyService mockPharmacyService;

    private Scooter expectedTrue;

    @BeforeEach
    void setUp() {
        this.expectedTrue = new Scooter(new VehicleModel(), new Pharmacy());
        this.registerScooterController = new RegisterScooterController();
        this.mockScooterService = Mockito.mock(ScooterService.class);
        this.mockPharmacyService = Mockito.mock(PharmacyService.class);
        initMocks(this);
    }

    @Test
    void newScooter() {
        System.out.println("newScooter");

        when(mockScooterService.newScooter(new VehicleModel(), new Pharmacy())).thenReturn(expectedTrue);

        boolean result = registerScooterController.newScooter(new VehicleModel(), new Pharmacy());
        assertTrue(result);


        when(mockScooterService.newScooter(null, null)).thenThrow(new IllegalArgumentException());

        result = registerScooterController.newScooter(null, null);
        assertFalse(result);
    }

    @Test
    void registersScooter() {
        System.out.println("registerScooter");
        Scooter s = new Scooter(-1, 98, new VehicleModel(), new Pharmacy());

        registerScooterController.setScooter(s);
        when(mockScooterService.registerScooter(s)).thenReturn(true);
        boolean result = registerScooterController.registersScooter();
        assertTrue(result);
    }

    @Test
    void showPharmacies() {
        System.out.println("showPharmacies");

        List<Pharmacy> expectedListPharmacies = new ArrayList<>(Arrays.asList(new Pharmacy()));

        when(mockPharmacyService.getPharmacies()).thenReturn(expectedListPharmacies);

        List<Pharmacy> result = registerScooterController.showPharmacies();
        assertEquals(expectedListPharmacies, result);
    }
}