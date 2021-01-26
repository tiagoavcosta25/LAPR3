package lapr.project.controller;

import lapr.project.model.Courier;
import lapr.project.model.Pharmacy;
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

class UpdateCourierControllerTest {

    @InjectMocks
    private UpdateCourierController updateCourierController;

    @Mock
    private CourierService mockCourierService;

    @Mock
    private PharmacyService mockPharmacyService;

    private boolean assertTrue;

    @BeforeEach
    void setUp() {
        this.assertTrue = true;
        this.updateCourierController = new UpdateCourierController();
        this.mockCourierService = Mockito.mock(CourierService.class);
        this.mockPharmacyService = Mockito.mock(PharmacyService.class);
        initMocks(this);
    }

    @Test
    void getCourierByEmail() {
        when(mockCourierService.getCourierByEmail("ernesto@gmail.com")).thenReturn(new Courier());
        Courier courier = updateCourierController.getCourierByEmail("ernesto@gmail.com");
        assertEquals(new Courier(),courier);

        when(mockCourierService.getCourierByEmail("ernesto@gmail.com")).thenThrow(new IllegalArgumentException());
        courier = updateCourierController.getCourierByEmail("ernesto@gmail.com");
        assertEquals(null,courier);

        courier = updateCourierController.getCourierByEmail("");
        assertEquals(null,courier);
    }

    @Test
    void updateCourier() {
        when(mockPharmacyService.getPharmacy("test@email.com")).thenReturn(new Pharmacy());
        when(mockCourierService.updateCourier(new Courier(),"name","email@gmail.com",123456789,
                "PT98003506514853185258910",mockPharmacyService.getPharmacy("test@email.com"))).thenReturn(new Courier());

        Courier c2 = updateCourierController.updateCourier(new Courier(),"name","email@gmail.com",123456789,
                "PT98003506514853185258910","test@email.com");

        assertEquals(new Courier(),c2);

        when(mockPharmacyService.getPharmacy("test@email.com")).thenThrow(new NullPointerException());
        c2 = updateCourierController.updateCourier(new Courier(),"name","email@gmail.com",123456789,
                "PT98003506514853185258910","test@email.com");
        assertNull(c2);
    }

    @Test
    void updateCourierDB() {
        when(mockCourierService.updateCourierDB(new Courier())).thenReturn(assertTrue);
        updateCourierController.setCourier(new Courier());
        boolean result = updateCourierController.updateCourierDB();

        assertEquals(assertTrue,result);

        when(mockCourierService.updateCourierDB(new Courier())).thenReturn(false);
        updateCourierController.setCourier(new Courier());
        result = updateCourierController.updateCourierDB();

        assertEquals(false,result);
    }

    @Test
    void validateInput() {
        boolean real = updateCourierController.validateInput("Ernesto", "ernesto@gmail.com", 250161761, "PT98003506514853185258910");
        assertEquals(assertTrue, real);

        real = updateCourierController.validateInput("", "ernesto@gmail.com", 250161761, "PT98003506514853185258910");
        assertFalse(real);

        real = updateCourierController.validateInput("Ernesto", "", 250161761, "PT98003506514853185258910");
        assertFalse(real);

        real = updateCourierController.validateInput("Ernesto", "ernesto@gmail.com", 0, "PT98003506514853185258910");
        assertFalse(real);

        real = updateCourierController.validateInput("Ernesto", "ernesto@gmail.com", -1, "PT98003506514853185258910");
        assertFalse(real);

        real = updateCourierController.validateInput("Ernesto", "ernesto@gmail.com", 250161761, "");
        assertFalse(real);

        real = updateCourierController.validateInput("Ernesto", "ernesto.com", 250161761, "PT98003506514853185258910");
        assertFalse(real);

        real = updateCourierController.validateInput("Ernesto", "ernesto@gmail.com", 250161761, "PT98003506514853aasd185258910");
        assertFalse(real);

        real = updateCourierController.validateInput("Ernesto", "ernesto@gmail.com", 2501761, "PT98003506514853185258910");
        assertFalse(real);

        real = updateCourierController.validateInput("Ernesto", "ernesto@gmail.com", 250161761, "PT98003506514853185258910");
        assertEquals(assertTrue, real);

        real = updateCourierController.validateInput("Ernesto", "ernesto@gmail.com", 250161761, "PT9800350651485318525891");
        assertEquals(false, real);

    }

    @Test
    void testValidateInput() {

        boolean real = updateCourierController.validateInput("ernesto@gmail.com");
        assertEquals(assertTrue, real);

        real = updateCourierController.validateInput("");
        assertFalse(real);

        real = updateCourierController.validateInput(null);
        assertFalse(real);

        real = updateCourierController.validateInput("ernestogmail.com");
        assertFalse(real);

    }
}