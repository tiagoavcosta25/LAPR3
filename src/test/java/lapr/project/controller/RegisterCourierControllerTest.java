package lapr.project.controller;

import lapr.project.model.Courier;
import lapr.project.model.PassGenerator;
import lapr.project.model.Pharmacy;
import lapr.project.model.Product;
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

    @Mock
    private CourierRegistration courierRegistration;



    private PassGenerator passGenerator;

    private boolean expectedTrue;

    @BeforeEach
    void setUp() {
        this.expectedTrue = true;
        this.RegisterCourierController = new RegisterCourierControllerTest();
        this.passGenerator = new PassGenerator();
        this.mockCourierRegistrationController = Mockito.mock(RegisterCourierController.class);
        this.courierRegistration = Mockito.mock(CourierRegistration.class);
        initMocks(this);
    }


    @Test
    void newCourier() {
        when(mockCourierRegistrationController.newCourier("testName","email@gmail.com",
                123456789,"PT50123456789098765432123")).thenReturn(expectedTrue);
    }

    @Test
    void registersCourier() throws NoSuchAlgorithmException {
        System.out.println("registerProductToDB");
        Courier c = new Courier("testName","email@gmail.com","123",123456789,"testIban",new Pharmacy());
        when(courierRegistration.registersCourier(c)).thenReturn(expectedTrue);
        boolean result = courierRegistration.registersCourier(c);
        assertEquals(expectedTrue, result);
    }

    @Test
    void validateInput() {
        boolean real = mockCourierRegistrationController.validateInput("testName","email@gmail.com",
                123456789,"PT50123456789098765432123");
        assertEquals(expectedTrue,real);
    }
}