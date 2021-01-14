package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.service.CourierService;
import lapr.project.model.service.PharmacyService;
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
        when(mockPharmacyService.getPharmacy(1)).thenReturn(new Pharmacy());
        boolean result = registerCourierController.newCourier("Name", "email4@gmail.com", 250161761, "PT98003506514853185258910", 1);
        assertEquals(assertTrue, result);

        result = registerCourierController.newCourier("", "email4@gmail.com", 250161761, "PT98003506514853185258910", -1);
        assertFalse(result);

        result = registerCourierController.newCourier("", "email4@gmail.com", 250161761, "PT98003506514853185258910", -1);
        assertFalse(result);

        when(mockPharmacyService.getPharmacy(1)).thenThrow(new NullPointerException());
        result = registerCourierController.newCourier("", "email4@gmail.com", 250161761, "PT98003506514853185258910", 1);
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
        boolean real = registerCourierController.validateInput("Ernesto", "ernesto@gmail.com", 250161761, "PT98003506514853185258910",1);
        assertEquals(assertTrue, real);

        real = registerCourierController.validateInput("", "ernesto@gmail.com", 250161761, "PT98003506514853185258910",1);
        assertFalse(real);

        real = registerCourierController.validateInput("Ernesto", "", 250161761, "PT98003506514853185258910",1);
        assertFalse(real);

        real = registerCourierController.validateInput("Ernesto", "ernesto@gmail.com", 0, "PT98003506514853185258910",1);
        assertFalse(real);

        real = registerCourierController.validateInput("Ernesto", "ernesto@gmail.com", -1, "PT98003506514853185258910",1);
        assertFalse(real);

        real = registerCourierController.validateInput("Ernesto", "ernesto@gmail.com", 250161761, "",1);
        assertFalse(real);

        real = registerCourierController.validateInput("Ernesto", "ernesto.com", 250161761, "PT98003506514853185258910",1);
        assertFalse(real);

        real = registerCourierController.validateInput("Ernesto", "ernesto@gmail.com", 250161761, "PT98003506514853aasd185258910",1);
        assertFalse(real);

        real = registerCourierController.validateInput("Ernesto", "ernesto@gmail.com", 2501761, "PT98003506514853185258910",1);
        assertFalse(real);

        real = registerCourierController.validateInput("Ernesto", "ernesto@gmail.com", 2501761, "PT98003506514853185258910",0);
        assertFalse(real);

        real = registerCourierController.validateInput("Ernesto", "ernesto@gmail.com", 2501761, "PT98003506514853185258910",-2);
        assertFalse(real);



    }
}