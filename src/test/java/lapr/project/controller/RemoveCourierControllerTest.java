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
        boolean result = this.removeCourierController.validateCourier(1);
        assertTrue(result);

        result = this.removeCourierController.validateCourier(-1);
        assertFalse(result);

        result = this.removeCourierController.validateCourier(0);
        assertFalse(result);

    }

    @Test
    void removeCourier() {
        when(mockCServ.removeCourier(1)).thenReturn(assertTrue);
        boolean result = this.removeCourierController.removeCourier(1);
        assertTrue(result);

        when(mockCServ.removeCourier(-1)).thenReturn(false);
        result = removeCourierController.removeCourier(-1);
        assertFalse(result);
    }

    @Test
    void validateInput() {
        boolean result = this.removeCourierController.validateInput(1);
        assertTrue(result);

        result = this.removeCourierController.validateInput(-1);
        assertFalse(result);

        result = this.removeCourierController.validateInput(0);
        assertFalse(result);

    }
}