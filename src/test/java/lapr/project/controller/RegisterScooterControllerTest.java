package lapr.project.controller;

import lapr.project.data.registration.PharmacyRegistration;
import lapr.project.model.Client;
import lapr.project.model.Pharmacy;
import lapr.project.model.Scooter;
import lapr.project.data.registration.ScooterRegistration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class RegisterScooterControllerTest {

   /* @InjectMocks
    private RegisterScooterController registerScooterController;

    @Mock
    private ScooterRegistration mockScooterRegistration;

    @Mock
    private PharmacyRegistration mockPharmacyRegistration;

    private Scooter expectedTrue;

    @BeforeEach
    void setUp() {
        this.expectedTrue = new Scooter(35.5f, "Charging Test", 250f, 30f,
                100, 20f, new Pharmacy());
        this.registerScooterController = new RegisterScooterController();
        this.mockScooterRegistration = Mockito.mock(ScooterRegistration.class);
        this.mockPharmacyRegistration = Mockito.mock(PharmacyRegistration.class);
        initMocks(this);
    }

    @Test
    void newScooter() {
        System.out.println("newScooter");
        Scooter s = new Scooter(35.5f, "Charging Test", 250f, 30f,
                100, 20f, new Pharmacy());

        when(mockPharmacyRegistration.getPharmacy(1)).thenReturn(new Pharmacy());

        when(mockScooterRegistration.newScooter(35.5f, "Charging Test", 250f, 30f,
                100, 20f, new Pharmacy())).thenReturn(expectedTrue);

        boolean result = registerScooterController.newScooter(35.5f, "Charging Test", 250f, 30f,
                100, 20f, new Pharmacy());
        assertTrue(result);
    }

    @Test
    void registersScooter() {
        System.out.println("registerScooterToDB");
        Scooter s = new Scooter(35.5f, "Charging Test", 250f, 30f,
                100, 20f, new Pharmacy());

        when(mockScooterRegistration.registerScooter(s)).thenReturn(true);
        boolean result = registerScooterController.registersScooter();
        assertTrue(result);
    }

    @Test
    void getScooters() {
        Scooter s = new Scooter(35.5f, "Charging Test", 250f, 30f,
                100, 20f, new Pharmacy());
        int pharmacyId = 1;

        when(mockScooterRegistration.getScootersList(pharmacyId)).thenReturn(true);
        List<Scooter> result = registerScooterController.getScooters(pharmacyId);
        assertTrue(result);
    }*/
}