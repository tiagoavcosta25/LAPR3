package lapr.project.controller;

import lapr.project.model.Pharmacy;
import lapr.project.model.Product;
import lapr.project.model.Scooter;
import lapr.project.model.registration.ProductRegistration;
import lapr.project.model.registration.ScooterRegistration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class RegisterScooterControllerTest {

    @InjectMocks
    private RegisterScooterController registerScoterController;

    @Mock
    private ScooterRegistration mockScooterRegistration;

    private boolean expectedTrue;

    @BeforeEach
    void setUp() {
        this.expectedTrue = true;
        this.registerScoterController = new RegisterScooterController();
        this.mockScooterRegistration = Mockito.mock(ScooterRegistration.class);
        initMocks(this);
    }

    @Test
    void ensureScooterRegistrationWorks() {
        System.out.println("registerScooterToDB");
        Scooter s = new Scooter(35.5f, "Charging", 250f, 30f,
                100, 20f, new Pharmacy());

        when(mockScooterRegistration.addScooter(s)).thenReturn(expectedTrue);
        boolean result = registerScoterController.registersScooter();
        assertEquals(expectedTrue, result);
    }

    @Test
    void newScooter() {
    }

    @Test
    void registersScooter() {
    }

    @Test
    void getScooters() {
    }
}