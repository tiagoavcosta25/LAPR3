package lapr.project.controller;

import lapr.project.model.service.CourierService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class RemoveCourierControllerTest {

    @InjectMocks
    private RemoveCourierController removeCourierController;

    @Mock
    private CourierService mockCServ;

    private boolean assertTrue;

    @BeforeEach
    void setUp() {
        this.assertTrue = true;
        this.removeCourierController = new RemoveCourierController();
        this.mockCServ = Mockito.mock(CourierService.class);
        initMocks(this);
    }

    @Test
    void validateCourier() {
        boolean result = this.removeCourierController.validateCourier("ernesto@gmail.com");
        assertTrue(result);

        result = this.removeCourierController.validateCourier("ernestogmail.com");
        assertFalse(result);

        result = this.removeCourierController.validateCourier("");
        assertFalse(result);

    }

    @Test
    void removeCourier() {
        when(mockCServ.removeCourier("ernesto@gmail.com")).thenReturn(assertTrue);
        boolean result = this.removeCourierController.removeCourier("ernesto@gmail.com");
        assertTrue(result);

        when(mockCServ.removeCourier("ernesto@gmail.com")).thenReturn(false);
        result = removeCourierController.removeCourier("ernesto@gmail.com");
        assertFalse(result);
    }

    @Test
    void validateInput() {
        boolean result = this.removeCourierController.validateInput("ernesto@gmail.com");
        assertTrue(result);

        result = this.removeCourierController.validateInput("ernestogmail.com");
        assertFalse(result);

        result = this.removeCourierController.validateInput("");
        assertFalse(result);

        result = this.removeCourierController.validateInput(null);
        assertFalse(result);

    }
}