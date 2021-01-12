package lapr.project.controller;

import lapr.project.data.PharmacyDB;
import lapr.project.model.*;
import lapr.project.data.CourierDB;
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
    private CourierDB mockCourierDB;

    @Mock
    private PharmacyDB mockPharmacyDB;

    private boolean assertTrue;

    @BeforeEach
    void setUp() {
        this.assertTrue = true;
        this.registerCourierController = new RegisterCourierController();
        this.mockCourierDB = Mockito.mock(CourierDB.class);
        this.mockPharmacyDB = Mockito.mock(PharmacyDB.class);
        initMocks(this);
    }


    @Test
    void newCourier() throws NoSuchAlgorithmException {
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email3@gmail.com"));
        when(mockPharmacyDB.getPharmacyByManagerEmail("email3@gmail.com")).thenReturn(new Pharmacy());
        when(mockCourierDB.newCourier("Name", "email4@gmail.com", 250161761, "PT98003506514853185258910", new Pharmacy())).thenReturn(new Courier());
        boolean result = registerCourierController.newCourier("Name", "email4@gmail.com", 250161761, "PT98003506514853185258910");
        assertEquals(assertTrue, result);

        result = registerCourierController.newCourier("", "email4@gmail.com", 250161761, "PT98003506514853185258910");
        assertFalse(result);
    }

    @Test
    void registersCourier() {
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email3@gmail.com"));
        when(mockCourierDB.registersCourier(new Courier())).thenReturn(assertTrue);
        registerCourierController.setCourier(new Courier());
        boolean result = registerCourierController.registersCourier();
        assertEquals(assertTrue, result);

        when(mockCourierDB.registersCourier(new Courier())).thenReturn(false);
        result = registerCourierController.registersCourier();
        assertEquals(false, result);
    }

    @Test
    void validateInput() {
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email3@gmail.com"));
        boolean real = registerCourierController.validateInput("Ernesto","ernesto@gmail.com",250161761,"PT98003506514853185258910");
        assertEquals(assertTrue,real);

        real = registerCourierController.validateInput("","ernesto@gmail.com",250161761,"PT98003506514853185258910");
        assertFalse(real);

        real = registerCourierController.validateInput("Ernesto","",250161761,"PT98003506514853185258910");
        assertFalse(real);

        real = registerCourierController.validateInput("Ernesto","ernesto@gmail.com",0,"PT98003506514853185258910");
        assertFalse(real);

        real = registerCourierController.validateInput("Ernesto","ernesto@gmail.com",-1,"PT98003506514853185258910");
        assertFalse(real);

        real = registerCourierController.validateInput("Ernesto","ernesto@gmail.com",250161761,"");
        assertFalse(real);

        real = registerCourierController.validateInput("Ernesto","ernesto.com",250161761,"PT98003506514853185258910");
        assertFalse(real);

        real = registerCourierController.validateInput("Ernesto","ernesto@gmail.com",250161761,"PT98003506514853aasd185258910");
        assertFalse(real);

        real = registerCourierController.validateInput("Ernesto","ernesto@gmail.com",2501761,"PT98003506514853185258910");
        assertFalse(real);

    }
}