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
    void getCourierByID() {
        when(mockCourierService.getCourierByID(1)).thenReturn(new Courier());
        Courier courier = mockCourierService.getCourierByID(1);
        assertEquals(new Courier(),courier);
    }

    @Test
    void updateCourier() throws NoSuchAlgorithmException {
        when(mockPharmacyService.getPharmacy(-1)).thenReturn(new Pharmacy());
        when(mockCourierService.updateCourier(new Courier(),"name","email@gmail.com",123456789,
                "PT98003506514853185258910",mockPharmacyService.getPharmacy(-1))).thenReturn(new Courier());

        Courier c2 = updateCourierController.updateCourier(new Courier(),"name","email@gmail.com",123456789,
                "PT98003506514853185258910",-1);

        assertEquals(new Courier(),c2);
    }

    @Test
    void updateCourierDB() {
        when(mockCourierService.updateCourierDB(new Courier())).thenReturn(assertTrue);
        boolean result = updateCourierController.updateCourierDB(new Courier());

        assertEquals(assertTrue,result);

        when(mockCourierService.updateCourierDB(new Courier())).thenReturn(false);
        result = updateCourierController.updateCourierDB(new Courier());

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
    }

    @Test
    void testValidateInput() {

        boolean real = updateCourierController.validateInput(1);
        assertEquals(assertTrue, real);

        real = updateCourierController.validateInput(-1);
        assertFalse(real);

        real = updateCourierController.validateInput(0);
        assertFalse(real);

        real = updateCourierController.validateInput(null);
        assertFalse(real);

    }
}