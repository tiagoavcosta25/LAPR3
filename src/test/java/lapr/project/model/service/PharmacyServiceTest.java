package lapr.project.model.service;

import lapr.project.data.PharmacyDB;
import lapr.project.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        System.out.println("newPharmacy");
        Pharmacy result = pharmacyService.newPharmacy("Test", "Test", -22d, -22d, "No Street Name",
                "No Door Number", "No Postal Code", "No Locality", "No Country");
        assertEquals(expectedPharmacy, result);
    }

    @Test
    void registerPharmacyProduct() {
        System.out.println("registerPharmacyProduct");
        when(mockPharmacyDB.registerPharmacyProduct(expectedPharmacy, new Product(), 1)).thenReturn(true);
        boolean result = pharmacyService.registerPharmacyProduct(expectedPharmacy, new Product(), 1);
        assertTrue(result);

        when(mockPharmacyDB.registerPharmacyProduct(null, null, -1)).thenReturn(false);
        result = pharmacyService.registerPharmacyProduct(null, null, -1);
        assertFalse(result);
    }

    @Test
    void getPharmacies() {
        System.out.println("getPharmacies");

        List<Pharmacy> expectedListPharmacies = new ArrayList<>(Arrays.asList(new Pharmacy()));

        when(mockPharmacyDB.getPharmacies()).thenReturn(expectedListPharmacies);

        List<Pharmacy>  result = pharmacyService.getPharmacies();
        assertEquals(expectedListPharmacies, result);
    }

    @Test
    void getPharmacyByManagerEmail() {
        System.out.println("getPharmacyByManagerEmail");
        when(mockPharmacyDB.getPharmacyByManagerEmail("test@isep.ipp.pt")).thenReturn(new Pharmacy());
        Pharmacy result = pharmacyService.getPharmacyByManagerEmail("test@isep.ipp.pt");
        assertEquals(expectedPharmacy, result);
    }

    @Test
    void getClosestPharmacyWithStock() {
        System.out.println("getClosestPharmacyWithStock");
        when(mockPharmacyDB.getClosestPharmacyWithStock(new Order(), new Product(), 1)).thenReturn(new Pharmacy());
        Pharmacy result = pharmacyService.getClosestPharmacyWithStock(new Order(), new Product(), 1);
        assertEquals(expectedPharmacy, result);
    }

    @Test
    void getSuitableCourier() {
        System.out.println("getSuitableCourier");
        when(mockPharmacyDB.getSuitableCourier()).thenReturn(new Courier());
        Courier result = pharmacyService.getSuitableCourier();
        assertEquals(new Courier(), result);
    }

    @Test
    void testGetSuitableCourier() {
        when (mockPharmacyDB.getSuitableCourier()).thenReturn(new Courier());
        Courier real = pharmacyService.getSuitableCourier();
        assertEquals(new Courier(),real);
    }
}