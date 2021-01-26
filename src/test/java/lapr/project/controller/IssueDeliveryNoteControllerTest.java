package lapr.project.controller;

import lapr.project.model.PharmacyTransfer;
import lapr.project.model.service.InvoiceService;
import lapr.project.model.service.PharmacyTransferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class IssueDeliveryNoteControllerTest {

    @InjectMocks
    private IssueDeliveryNoteController issueDeliveryNoteController;

    @Mock
    private PharmacyTransferService mockPharmacyTransferService;

    private PharmacyTransfer m_oPharmacyTransfer;

    @BeforeEach
    void setUp() {
        this.m_oPharmacyTransfer = new PharmacyTransfer();
        this.issueDeliveryNoteController = new IssueDeliveryNoteController();
        this.mockPharmacyTransferService = Mockito.mock(PharmacyTransferService.class);
        initMocks(this);
    }

    @Test
    void issueDeliveryNote() {
        System.out.println("issueTransferNote");
        when(mockPharmacyTransferService.sendEmailWithDeliveryNote(m_oPharmacyTransfer)).thenReturn(true);
        when(mockPharmacyTransferService.getPharmacyTransfer(-1)).thenReturn(new PharmacyTransfer());
        when(mockPharmacyTransferService.updateStockFromTransfer(-1)).thenReturn(true);
        boolean result = issueDeliveryNoteController.issueDeliveryNote(-1);
        assertTrue(result);

        when(mockPharmacyTransferService.sendEmailWithDeliveryNote(m_oPharmacyTransfer)).thenReturn(false);
        when(mockPharmacyTransferService.getPharmacyTransfer(-1)).thenReturn(new PharmacyTransfer());
        when(mockPharmacyTransferService.updateStockFromTransfer(-1)).thenReturn(true);
        result = issueDeliveryNoteController.issueDeliveryNote(-1);
        assertFalse(result);

        when(mockPharmacyTransferService.sendEmailWithDeliveryNote(m_oPharmacyTransfer)).thenThrow(new IllegalArgumentException());
        when(mockPharmacyTransferService.getPharmacyTransfer(-2)).thenThrow(new IllegalArgumentException());
        result = issueDeliveryNoteController.issueDeliveryNote(-2);
        assertFalse(result);
    }

    @Test
    void getPharmacyTransferService() {
        IssueDeliveryNoteController ctrl = new IssueDeliveryNoteController();
        PharmacyTransferService expected = new PharmacyTransferService();
        ctrl.setPharmacyTransferService(expected);
        PharmacyTransferService real = ctrl.getPharmacyTransferService();
        assertEquals(expected, real);
    }

    @Test
    void setPharmacyTransferService() {
        IssueDeliveryNoteController ctrl = new IssueDeliveryNoteController();
        PharmacyTransferService expected = new PharmacyTransferService();
        ctrl.setPharmacyTransferService(expected);
        PharmacyTransferService real = ctrl.getPharmacyTransferService();
        assertEquals(expected, real);
    }
}