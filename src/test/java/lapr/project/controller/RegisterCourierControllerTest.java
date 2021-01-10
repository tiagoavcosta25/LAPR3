package lapr.project.controller;

import lapr.project.model.Courier;
import lapr.project.model.PassGenerator;
import lapr.project.model.Pharmacy;
import lapr.project.data.registration.CourierRegistration;
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

    private RegisterCourierController courierRegistrationController;

    @Mock
    private CourierRegistration courierRegistration;

    private boolean expectedTrue;

    @BeforeEach
    void setUp() {
        this.expectedTrue = true;
        this.RegisterCourierController = new RegisterCourierControllerTest();
        this.mockCourierRegistrationController = Mockito.mock(RegisterCourierController.class);
        this.courierRegistration = Mockito.mock(CourierRegistration.class);
        this.courierRegistrationController = new RegisterCourierController();
        initMocks(this);
    }


    @Test
    void newCourier() {
        when(mockCourierRegistrationController.newCourier("testName","email@gmail.com",
                123456789,"PT50123456789098765432123")).thenReturn(expectedTrue);
        boolean result = mockCourierRegistrationController.newCourier("testName","email@gmail.com",
                123456789,"PT50123456789098765432123");
        assertEquals(expectedTrue, result);
    }

    @Test
    void registersCourier() throws NoSuchAlgorithmException {
        Courier c = new Courier("testName","email@gmail.com","123",123456789,"testIban",new Pharmacy());
        when(courierRegistration.registersCourier(c)).thenReturn(expectedTrue);
        boolean result = courierRegistration.registersCourier(c);
        assertEquals(expectedTrue, result);
    }

    @Test
    void validateInput() {
        boolean real = courierRegistrationController.validateInput("Ernesto","ernesto@gmail.com",250161761,"PT98003506514853185258910");
        assertEquals(expectedTrue,real);
    }
}