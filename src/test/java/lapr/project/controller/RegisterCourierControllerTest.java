package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.service.CourierService;
import lapr.project.model.service.PharmacyService;
import lapr.project.ui.console.RegisterCourierUI;
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
    private CourierService mockCourierService;

    @Mock
    private PharmacyService mockPharmacyService;

    private boolean assertTrue;

    @BeforeEach
    void setUp() {
        this.assertTrue = true;
        this.registerCourierController = new RegisterCourierController();
        this.mockCourierService = Mockito.mock(CourierService.class);
        this.mockPharmacyService = Mockito.mock(PharmacyService.class);
        initMocks(this);
    }


    @Test
    void newCourier() throws NoSuchAlgorithmException {
        when(mockPharmacyService.getPharmacy("test@email.com")).thenReturn(new Pharmacy());
        boolean result = registerCourierController.newCourier("Name", "email4@gmail.com", 250161761, "PT98003506514853185258910", "test@email.com");
        assertEquals(assertTrue, result);

        result = registerCourierController.newCourier("", "email4@gmail.com", 250161761, "PT98003506514853185258910", "test2");
        assertFalse(result);

        result = registerCourierController.newCourier("", "email4@gmail.com", 250161761, "PT98003506514853185258910", "test2");
        assertFalse(result);

        when(mockPharmacyService.getPharmacy("test@email.com")).thenThrow(new NullPointerException());
        result = registerCourierController.newCourier("", "email4@gmail.com", 250161761, "PT98003506514853185258910", "test@email.com");
        assertFalse(result);
    }

    @Test
    void registersCourier() {
        when(mockCourierService.registersCourier(new Courier())).thenReturn(assertTrue);
        registerCourierController.setCourier(new Courier());
        boolean result = registerCourierController.registersCourier();
        assertEquals(assertTrue, result);

        when(mockCourierService.registersCourier(new Courier())).thenReturn(false);
        result = registerCourierController.registersCourier();
        assertEquals(false, result);
    }

    @Test
    void validateInput() {
        boolean real = registerCourierController.validateInput("Ernesto", "ernesto@gmail.com", 250161761, "PT98003506514853185258910","test@email.com");
        assertEquals(assertTrue, real);

        real = registerCourierController.validateInput("", "ernesto@gmail.com", 250161761, "PT98003506514853185258910","test@email.com");
        assertFalse(real);

        real = registerCourierController.validateInput("Ernesto", "", 250161761, "PT98003506514853185258910","test@email.com");
        assertFalse(real);

        real = registerCourierController.validateInput("Ernesto", "ernesto@gmail.com", 0, "PT98003506514853185258910","test@email.com");
        assertFalse(real);

        real = registerCourierController.validateInput("Ernesto", "ernesto@gmail.com", -1, "PT98003506514853185258910","test@email.com");
        assertFalse(real);

        real = registerCourierController.validateInput("Ernesto", "ernesto@gmail.com", 250161761, "","test@email.com");
        assertFalse(real);

        real = registerCourierController.validateInput("Ernesto", "ernesto.com", 250161761, "PT98003506514853185258910","test@email.com");
        assertFalse(real);

        real = registerCourierController.validateInput("Ernesto", "ernesto@gmail.com", 250161761, "PT98003506514853aasd185258910","test@email.com");
        assertFalse(real);

        real = registerCourierController.validateInput("Ernesto", "ernesto@gmail.com", 2501761, "PT98003506514853185258910","test@email.com");
        assertFalse(real);

        real = registerCourierController.validateInput("Ernesto", "ernesto@gmail.com", 2501761, "PT98003506514853185258910","test");
        assertFalse(real);

        real = registerCourierController.validateInput("Ernesto", "ernesto@gmail.com", 2501761, "PT98003506514853185258910","test2");
        assertFalse(real);

        real = registerCourierController.validateInput("Ernesto", "ernesto@gmail.com", 2501761, "PT98003506514853185258910","");
        assertFalse(real);



    }

    @Test
    void getCourierService() {
        RegisterCourierController ctrl = new RegisterCourierController();
        CourierService expected = new CourierService();
        ctrl.setCourierService(expected);
        CourierService real = ctrl.getCourierService();
        assertEquals(expected, real);
    }

    @Test
    void setCourierService() {
        RegisterCourierController ctrl = new RegisterCourierController();
        CourierService expected = new CourierService();
        ctrl.setCourierService(expected);
        CourierService real = ctrl.getCourierService();
        assertEquals(expected, real);
    }

    @Test
    void getPharmacyService() {
        RegisterCourierController ctrl = new RegisterCourierController();
        PharmacyService expected = new PharmacyService();
        ctrl.setPharmacyService(expected);
        PharmacyService real = ctrl.getPharmacyService();
        assertEquals(expected, real);
    }

    @Test
    void setMoPharmacyService() {
        RegisterCourierController ctrl = new RegisterCourierController();
        PharmacyService expected = new PharmacyService();
        ctrl.setPharmacyService(expected);
        PharmacyService real = ctrl.getPharmacyService();
        assertEquals(expected, real);
    }
}