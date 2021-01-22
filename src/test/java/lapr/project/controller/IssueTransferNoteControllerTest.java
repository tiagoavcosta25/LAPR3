package lapr.project.controller;

import lapr.project.model.PharmacyTransfer;
import lapr.project.model.service.PharmacyTransferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class IssueTransferNoteControllerTest {

    @InjectMocks
    private IssueTransferNoteController issueTransferNoteController;

    @Mock
    private PharmacyTransferService mockPharmacyTransferService;

    private PharmacyTransfer m_oPharmacyTransfer;

    @BeforeEach
    void setUp() {
        this.m_oPharmacyTransfer = new PharmacyTransfer();
        this.issueTransferNoteController = new IssueTransferNoteController();
        this.mockPharmacyTransferService = Mockito.mock(PharmacyTransferService.class);
        initMocks(this);
    }

    @Test
    void issueTransferNote() {
        System.out.println("issueTransferNote");
        when(mockPharmacyTransferService.sendEmailWithTransferNote(m_oPharmacyTransfer)).thenReturn(true);
        boolean result = issueTransferNoteController.issueTransferNote(m_oPharmacyTransfer);
        assertTrue(result);

        when(mockPharmacyTransferService.sendEmailWithTransferNote(m_oPharmacyTransfer)).thenReturn(false);
        result = issueTransferNoteController.issueTransferNote(m_oPharmacyTransfer);
        assertFalse(result);

        when(mockPharmacyTransferService.sendEmailWithTransferNote(m_oPharmacyTransfer)).thenThrow(new IllegalArgumentException());
        result = issueTransferNoteController.issueTransferNote(m_oPharmacyTransfer);
        assertFalse(result);
    }
}