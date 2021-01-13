package lapr.project.model.service;

import lapr.project.data.OrderDB;
import lapr.project.data.PharmacyDB;
import lapr.project.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class PharmacyServiceTest {

    @InjectMocks
    private PharmacyService pharmacyService;

    @Mock
    private PharmacyDB mockPharmacyDB;

    private Pharmacy expectedPharmacy;

    @BeforeEach
    void setUp() {
        this.expectedPharmacy = new Pharmacy();
        this.pharmacyService = new PharmacyService();
        this.mockPharmacyDB = Mockito.mock(PharmacyDB.class);
        initMocks(this);
    }

    @Test
    void getPharmacy() {
        System.out.println("getPharmacy");
        when(mockPharmacyDB.getPharmacy(1)).thenReturn(new Pharmacy());
        Pharmacy result = pharmacyService.getPharmacy(1);
        assertEquals(expectedPharmacy, result);
    }

    @Test
    void removePharmacy() {
        System.out.println("removePharmacy");
        when(mockPharmacyDB.removePharmacy(1)).thenReturn(true);
        boolean result = pharmacyService.removePharmacy(1);
        assertTrue(result);

        when(mockPharmacyDB.removePharmacy(-1)).thenReturn(false);
        result = pharmacyService.removePharmacy(2);
        assertFalse(result);
    }

    @Test
    void registerPharmacy() {
        System.out.println("registerPharmacy");
        when(mockPharmacyDB.registerPharmacy(expectedPharmacy)).thenReturn(true);
        boolean result = pharmacyService.registerPharmacy(expectedPharmacy);
        assertTrue(result);

        when(mockPharmacyDB.registerPharmacy(null)).thenReturn(false);
        result = pharmacyService.registerPharmacy(null);
        assertFalse(result);
    }

    @Test
    void newPharmacy() {
        System.out.println("newOrder");
        Order result = pharmacyService.newPharmacy();
        assertEquals(pharmacyService, result);
    }

    @Test
    void registerPharmacyProduct() {
    }

    @Test
    void getPharmacies() {
    }

    @Test
    void getPharmacyByManagerEmail() {
    }
}