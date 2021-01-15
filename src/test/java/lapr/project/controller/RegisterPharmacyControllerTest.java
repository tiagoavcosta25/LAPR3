package lapr.project.controller;

import lapr.project.model.Address;
import lapr.project.model.Pharmacy;
import lapr.project.model.service.PharmacyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class RegisterPharmacyControllerTest {
    @InjectMocks
    private RegisterPharmacyController registerPharmacyController;

    @Mock
    private PharmacyService mockPharmacyService;

    @BeforeEach
    void setUp() {
        this.registerPharmacyController = new RegisterPharmacyController();
        this.mockPharmacyService = Mockito.mock(PharmacyService.class);
        initMocks(this);
    }

    @Test
    void newPharmacy() {
        Pharmacy expResult = new Pharmacy("Farmacia 1", "email", new Address(41.014152, -8.218524, "Rua2", "2ºesq", "4460-222", "Porto", "Portugal"));
        when(mockPharmacyService.newPharmacy("Farmacia 1", "email", 41.014152, -8.218524, "Rua2", "2ºesq", "4460-222", "Porto", "Portugal")).thenReturn(expResult);
        Pharmacy result = this.registerPharmacyController.newPharmacy("Farmacia 1", "email", 41.014152, -8.218524, "Rua2", "2ºesq", "4460-222", "Porto", "Portugal");
        assertEquals(expResult, result);

        this.registerPharmacyController.setPharmacyService(null);
        result = this.registerPharmacyController.newPharmacy(null, null, null, null, null, null, null, null, null);
        expResult = null;
        assertEquals(expResult, result);
    }

    @Test
    void registerPharmacy() {
        Pharmacy pharmacy = new Pharmacy("Farmacia 1", "email", new Address(41.014152, -8.218524, "Rua2", "2ºesq", "4460-222", "Porto", "Portugal"));
        this.registerPharmacyController.setPharmacy(pharmacy);
        when(mockPharmacyService.registerPharmacy(pharmacy)).thenReturn(true);
        boolean result = this.registerPharmacyController.registerPharmacy();
        assertTrue(result);

        when(mockPharmacyService.registerPharmacy(pharmacy)).thenReturn(false);
        result = this.registerPharmacyController.registerPharmacy();
        assertFalse(result);
    }
}