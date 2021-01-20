package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MakeAPharmacyTransferControllerTest {

    @InjectMocks
    private MakeAPharmacyTransferController makeAPharmacyTransferController;

    @Mock
    private PharmacyTransferService mockPharmacyTransferService;

    @Mock
    private IssueTransferNoteController mockIssueTransferNoteController;

    @Mock
    private PharmacyService mockPharmacyService;

    private Order m_oOrder;
    private Pharmacy m_oPharmacy;
    private PharmacyTransfer m_oPharmacyTransfer;

    @BeforeEach
    void setUp() {
        this.m_oOrder = new Order();
        this.m_oPharmacy = new Pharmacy();
        this.m_oPharmacyTransfer = new PharmacyTransfer();
        this.makeAPharmacyTransferController = new MakeAPharmacyTransferController();
        this.mockPharmacyTransferService = Mockito.mock(PharmacyTransferService.class);
        this.mockPharmacyService = Mockito.mock(PharmacyService.class);
        this.mockIssueTransferNoteController = Mockito.mock(IssueTransferNoteController.class);
        initMocks(this);
    }

    @Test
    void getStockFromAnotherPharamacy() {
        System.out.println("getStockFromAnotherPharamacy");
        when(mockPharmacyService.getClosestPharmacyWithStock(m_oOrder, new Product(), 1)).thenReturn(m_oPharmacy);
        when(mockPharmacyTransferService.newPharmacyTransfer(m_oOrder, new Product(), 1, m_oPharmacy)).thenReturn(m_oPharmacyTransfer);
        when(mockPharmacyTransferService.registerPharmacyTransfer(m_oPharmacyTransfer)).thenReturn(true);
        when(mockIssueTransferNoteController.issueTransferNote(m_oPharmacyTransfer)).thenReturn(true);
        boolean result = makeAPharmacyTransferController.getStockFromAnotherPharamacy(m_oOrder, new Product(), 1);
        assertTrue(result);

        when(mockPharmacyTransferService.registerPharmacyTransfer(m_oPharmacyTransfer)).thenThrow(new IllegalArgumentException());
        result = makeAPharmacyTransferController.getStockFromAnotherPharamacy(m_oOrder, new Product(), 1);
        assertFalse(result);
    }
}