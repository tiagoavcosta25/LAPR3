package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.service.CourierService;
import lapr.project.model.service.PharmacyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

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
    void newCourier() {
        when(mockPharmacyService.getPharmacy(-1)).thenReturn(new Pharmacy());
        boolean result = registerCourierController.newCourier("Name", "email4@gmail.com", 250161761, "PT98003506514853185258910", -1);
        assertEquals(assertTrue, result);

        result = registerCourierController.newCourier("", "email4@gmail.com", 250161761, "PT98003506514853185258910", -1);
        assertFalse(result);
    }

    @Test
    void registersCourier() {
        Courier c = new Courier();
        registerCourierController.setCourier(c);
        when(mockCourierService.registersCourier(c)).thenReturn(assertTrue);
        boolean result = registerCourierController.registersCourier();
        assertEquals(assertTrue, result);

        when(mockCourierService.registersCourier(c)).thenReturn(false);
        result = registerCourierController.registersCourier();
        assertEquals(false, result);
    }

    @Test
    void validateInput() {
        boolean real = registerCourierController.validateInput("Ernesto", "ernesto@gmail.com", 250161761, "PT98003506514853185258910");
        assertEquals(assertTrue, real);

        real = registerCourierController.validateInput("", "ernesto@gmail.com", 250161761, "PT98003506514853185258910");
        assertFalse(real);

        real = registerCourierController.validateInput("Ernesto", "", 250161761, "PT98003506514853185258910");
        assertFalse(real);

        real = registerCourierController.validateInput("Ernesto", "ernesto@gmail.com", 0, "PT98003506514853185258910");
        assertFalse(real);

        real = registerCourierController.validateInput("Ernesto", "ernesto@gmail.com", -1, "PT98003506514853185258910");
        assertFalse(real);

        real = registerCourierController.validateInput("Ernesto", "ernesto@gmail.com", 250161761, "");
        assertFalse(real);

        real = registerCourierController.validateInput("Ernesto", "ernesto.com", 250161761, "PT98003506514853185258910");
        assertFalse(real);

        real = registerCourierController.validateInput("Ernesto", "ernesto@gmail.com", 250161761, "PT98003506514853aasd185258910");
        assertFalse(real);

        real = registerCourierController.validateInput("Ernesto", "ernesto@gmail.com", 2501761, "PT98003506514853185258910");
        assertFalse(real);

    }
}