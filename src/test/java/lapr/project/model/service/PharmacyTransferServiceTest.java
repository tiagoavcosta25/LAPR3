package lapr.project.model.service;

import lapr.project.data.PharmacyTransferDB;
import lapr.project.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class PharmacyTransferServiceTest {

    @InjectMocks
    private PharmacyTransferService pharmacyTransferService;

    @Mock
    private PharmacyTransferDB mockPharmacyTransferDB;

    private PharmacyTransfer expectedPharmacyTransfer;

    @BeforeEach
    void setUp() {
        this.expectedPharmacyTransfer = new PharmacyTransfer();
        this.expectedPharmacyTransfer = new PharmacyTransfer();
        this.pharmacyTransferService = new PharmacyTransferService();
        this.mockPharmacyTransferDB = Mockito.mock(PharmacyTransferDB.class);
        initMocks(this);
    }

    @Test
    void getPharmacyTransfer() {
        System.out.println("getPharmacyTransfer");
        when(mockPharmacyTransferDB.getPharmacyTransfer(1)).thenReturn(new PharmacyTransfer());
        PharmacyTransfer result = pharmacyTransferService.getPharmacyTransfer(1);
        assertEquals(expectedPharmacyTransfer, result);
    }

    @Test
    void removePharmacyTransfer() {
        System.out.println("removePharmacyTransfer");
        when(mockPharmacyTransferDB.removePharmacyTransfer(1)).thenReturn(true);
        boolean result = pharmacyTransferService.removePharmacyTransfer(1);
        assertTrue(result);

        when(mockPharmacyTransferDB.removePharmacyTransfer(-1)).thenReturn(false);
        result = pharmacyTransferService.removePharmacyTransfer(-1);
        assertFalse(result);
    }

    @Test
    void registerPharmacyTransfer() {
        System.out.println("registerPharmacyTransfer");
        when(mockPharmacyTransferDB.registerPharmacyTransfer(expectedPharmacyTransfer)).thenReturn(true);
        boolean result = pharmacyTransferService.registerPharmacyTransfer(expectedPharmacyTransfer);
        assertTrue(result);

        when(mockPharmacyTransferDB.registerPharmacyTransfer(null)).thenReturn(false);
        result = pharmacyTransferService.registerPharmacyTransfer(null);
        assertFalse(result);
    }

    @Test
    void newPharmacyTransfer() {
        System.out.println("newPharmacy");
        PharmacyTransfer result = pharmacyTransferService.newPharmacyTransfer(new Order(), new Product(), -1, new Pharmacy());
        assertEquals(expectedPharmacyTransfer, result);
    }


    @Test
    void sendEmailWithTransferNote() {
        System.out.println("sendEmailWithTransferNote");
        PharmacyTransfer oPharmacyTransfer = new PharmacyTransfer();
        oPharmacyTransfer.setProduct(new Product(1,"SARS-CoV-2 Vaccine", "Description.", 7.5f, 1f));
        oPharmacyTransfer.setQuantity(2);
        oPharmacyTransfer.getNearbyPharmacy().setEmail("farmacyservice.g21@gmail.com");
        oPharmacyTransfer.getOrder().getPharmacy().setEmail("farmacyservice.g21@gmail.com");

        boolean result = pharmacyTransferService.sendEmailWithTransferNote(oPharmacyTransfer);
        assertTrue(result);

        oPharmacyTransfer.getOrder().getPharmacy().setEmail(null);
        oPharmacyTransfer.getNearbyPharmacy().setEmail(null);
        result = pharmacyTransferService.sendEmailWithTransferNote(oPharmacyTransfer);
        assertFalse(result);
    }
}