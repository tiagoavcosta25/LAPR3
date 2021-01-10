package lapr.project.controller;

import lapr.project.model.Courier;
import lapr.project.model.PassGenerator;
import lapr.project.model.Pharmacy;
import lapr.project.model.registration.CourierRegistration;
import lapr.project.model.registration.PharmacyRegistration;
import lapr.project.model.registration.ProductRegistration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class RegisterCourierControllerTest {

    @InjectMocks
    private RegisterCourierControllerTest RegisterCourierController;

    @Mock
    private RegisterCourierController mockCourierRegistrationController;



    private PassGenerator passGenerator;

    private boolean expectedTrue;

    @BeforeEach
    void setUp() {
        this.expectedTrue = true;
        this.RegisterCourierController = new RegisterCourierControllerTest();
        this.passGenerator = new PassGenerator();
        this.mockCourierRegistrationController = Mockito.mock(RegisterCourierController.class);
        initMocks(this);
    }


    @Test
    void newCourier() throws NoSuchAlgorithmException {

        when(mockCourierRegistrationController.newCourier("testName","email@gmail.com",
                123456789,"PT50123456789098765432123")).thenReturn(expectedTrue);
    }

    @Test
    void registersCourier() throws NoSuchAlgorithmException {
        Pharmacy pharmacy = new Pharmacy();
        mockCourierRegistrationController.oCourier = new Courier("testName","email@gmail.com","123",
                123456789,"testIban",pharmacy);
        boolean result = mockCourierRegistrationController.registersCourier();
        assertEquals(expectedTrue,result);
    }

    @Test
    void validateInput() {
    }
}