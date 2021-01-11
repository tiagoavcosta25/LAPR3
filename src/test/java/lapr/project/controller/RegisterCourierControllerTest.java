package lapr.project.controller;

import lapr.project.data.registration.PharmacyRegistration;
import lapr.project.model.*;
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
    private RegisterCourierController registerCourierController;

    @Mock
    private CourierRegistration mockCourierRegistration;

    @Mock
    private PharmacyRegistration mockPharmacyRegistration;

    private boolean assertTrue;

    @BeforeEach
    void setUp() {
        this.assertTrue = true;
        this.registerCourierController = new RegisterCourierController();
        this.mockCourierRegistration = Mockito.mock(CourierRegistration.class);
        this.mockPharmacyRegistration = Mockito.mock(PharmacyRegistration.class);
        initMocks(this);
    }


    @Test
    void newCourier() throws NoSuchAlgorithmException {
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email3@gmail.com"));
        when(mockPharmacyRegistration.getPharmacyByManagerEmail("email3@gmail.com")).thenReturn(new Pharmacy());
        when(mockCourierRegistration.newCourier("Name", "email4@gmail.com", 250161761, "PT98003506514853185258910", new Pharmacy())).thenReturn(new Courier());
        boolean result = registerCourierController.newCourier("Name", "email4@gmail.com", 250161761, "PT98003506514853185258910");
        assertEquals(assertTrue, result);
    }

    @Test
    void registersCourier() {
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email3@gmail.com"));
        when(mockCourierRegistration.registersCourier(new Courier())).thenReturn(assertTrue);
        registerCourierController.setCourier(new Courier());
        boolean result = registerCourierController.registersCourier();
        assertEquals(assertTrue, result);
    }

    @Test
    void validateInput() {
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email3@gmail.com"));
        boolean real = registerCourierController.validateInput("Ernesto","ernesto@gmail.com",250161761,"PT98003506514853185258910");
        assertEquals(assertTrue,real);
    }
}