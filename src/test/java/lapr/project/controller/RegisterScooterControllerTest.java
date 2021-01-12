package lapr.project.controller;

import lapr.project.data.PharmacyDB;
import lapr.project.model.Pharmacy;
import lapr.project.model.Scooter;
import lapr.project.data.ScooterDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class RegisterScooterControllerTest {

    @InjectMocks
    private RegisterScooterController registerScooterController;

    @Mock
    private ScooterDB mockScooterDB;

    @Mock
    private PharmacyDB mockPharmacyDB;

    private Scooter expectedTrue;

    @BeforeEach
    void setUp() {
        this.expectedTrue = new Scooter(35.5f, "Charging Test", 250f, 30f,
                100, 20f, new Pharmacy());
        this.registerScooterController = new RegisterScooterController();
        this.mockScooterDB = Mockito.mock(ScooterDB.class);
        this.mockPharmacyDB = Mockito.mock(PharmacyDB.class);
        initMocks(this);
    }

    @Test
    void newScooter() {
        System.out.println("newScooter");
        Scooter s = new Scooter(35.5f, "Charging Test", 250f, 30f,
                100, 20f, new Pharmacy());
        Pharmacy p = new Pharmacy();
        p.setId(1);

        when(mockPharmacyDB.getPharmacy(1)).thenReturn(new Pharmacy());

        when(mockScooterDB.newScooter(35.5f, "Charging Test", 250f, 30f,
                100, 20f, p)).thenReturn(expectedTrue);

        boolean result = registerScooterController.newScooter(35.5f, "Charging Test", 250f, 30f,
                100, 20f, p);
        assertTrue(result);

        when(mockPharmacyDB.getPharmacy(1)).thenReturn(null);
        when(mockScooterDB.newScooter(35.5f, "Charging Test", 250f, 30f,
                100, 20f, null)).thenReturn(null);

        boolean result1 = registerScooterController.newScooter(35.5f, "Charging Test", 250f, 30f,
                100, 20f, null);
        assertFalse(result1);
    }

    @Test
    void registersScooter() {
        System.out.println("registerScooterToDB");
        Scooter s = new Scooter(35.5f, "Charging Test", 250f, 30f,
                100, 20f, new Pharmacy());

        registerScooterController.setScooter(s);
        when(mockScooterDB.registerScooter(s)).thenReturn(true);
        boolean result = registerScooterController.registersScooter();
        assertTrue(result);
    }

    @Test
    void getScooters() {
        Scooter s = new Scooter(35.5f, "Charging Test", 250f, 30f,
                100, 20f, new Pharmacy());
        int pharmacyId = 1;

        when(mockScooterDB.getScootersList(pharmacyId)).thenReturn(new ArrayList<>());
        List<Scooter> result = registerScooterController.getScooters(pharmacyId);
        assertEquals(new ArrayList<>(), result);
    }
}