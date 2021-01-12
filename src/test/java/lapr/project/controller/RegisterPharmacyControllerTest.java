package lapr.project.controller;

import lapr.project.data.PharmacyDB;
import lapr.project.data.PharmacyManagerDB;
import lapr.project.model.Address;
import lapr.project.model.Pharmacy;
import lapr.project.model.PharmacyManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class RegisterPharmacyControllerTest {
    @InjectMocks
    private RegisterPharmacyController registerPharmacyController;

    @Mock
    private PharmacyDB mockPharmacyDB;

    @Mock
    private PharmacyManagerDB mockPharmacyManagerDB;

    private PharmacyManager oManager;

    @BeforeEach
    void setUp() throws NoSuchAlgorithmException {
        this.registerPharmacyController = new RegisterPharmacyController();
        this.mockPharmacyManagerDB = Mockito.mock(PharmacyManagerDB.class);
        this.mockPharmacyDB = Mockito.mock(PharmacyDB.class);
        this.oManager = new PharmacyManager("joel123@gmail.com", "12345", 123456789, "Joel");
        initMocks(this);
    }

    @Test
    void newPharmacy() throws NoSuchAlgorithmException {
        Pharmacy expResult = new Pharmacy("Farmacia 1", oManager, new Address(41.014152, -8.218524, "Rua2", "2ºesq", "4460-222", "Porto", "Portugal"));
        when(mockPharmacyManagerDB.newPharmacyManager("joel123@gmail.com", "12345", 123456789, "Joel")).thenReturn(oManager);
        when(mockPharmacyDB.newPharmacy("Farmacia 1", oManager, 41.014152, -8.218524, "Rua2", "2ºesq", "4460-222", "Porto", "Portugal")).thenReturn(expResult);
        Pharmacy result = this.registerPharmacyController.newPharmacy("Joel", "joel123@gmail.com", "12345", 123456789, "Farmacia 1", 41.014152, -8.218524, "Rua2", "2ºesq", "4460-222", "Porto", "Portugal");
        assertEquals(expResult, result);

        result = this.registerPharmacyController.newPharmacy(null, null, null, null, null, null, null, null, null, null, null, null);
        expResult = null;
        assertEquals(expResult, result);
    }

    @Test
    void registerPharmacy() throws NoSuchAlgorithmException {
        Pharmacy pharmacy = new Pharmacy("Farmacia 1", oManager, new Address(41.014152, -8.218524, "Rua2", "2ºesq", "4460-222", "Porto", "Portugal"));
        this.registerPharmacyController.setPharmacy(pharmacy);
        when(mockPharmacyDB.registerPharmacy(pharmacy)).thenReturn(true);
        boolean result = this.registerPharmacyController.registerPharmacy();
        assertTrue(result);

        when(mockPharmacyDB.registerPharmacy(pharmacy)).thenReturn(false);
        result = this.registerPharmacyController.registerPharmacy();
        assertFalse(result);
    }
}