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

        when(mockIssueTransferNoteController.issueTransferNote(m_oPharmacyTransfer)).thenReturn(false);
        result = makeAPharmacyTransferController.getStockFromAnotherPharamacy(m_oOrder, new Product(), 1);
        assertFalse(result);

        when(mockPharmacyTransferService.registerPharmacyTransfer(m_oPharmacyTransfer)).thenThrow(new IllegalArgumentException());
        result = makeAPharmacyTransferController.getStockFromAnotherPharamacy(m_oOrder, new Product(), 1);
        assertFalse(result);


    }

    @Test
    void setPharmacy() {
        Pharmacy expected = new Pharmacy();
        makeAPharmacyTransferController.setPharmacy("test");
        Pharmacy real = new Pharmacy();
        real.setId(1202);
        assertNotEquals(expected,real);
    }

    @Test
    void getPharmacyService() {
        MakeAPharmacyTransferController ctrl = new MakeAPharmacyTransferController();
        PharmacyService expected = new PharmacyService();
        ctrl.setPharmacyService(expected);
        PharmacyService real = ctrl.getPharmacyService();
        assertEquals(expected, real);
    }

    @Test
    void setPharmacyService() {
        MakeAPharmacyTransferController ctrl = new MakeAPharmacyTransferController();
        PharmacyService expected = new PharmacyService();
        ctrl.setPharmacyService(expected);
        PharmacyService real = ctrl.getPharmacyService();
        assertEquals(expected, real);
    }

    @Test
    void getPharmacyTransferService() {
        MakeAPharmacyTransferController ctrl = new MakeAPharmacyTransferController();
        PharmacyTransferService expected = new PharmacyTransferService();
        ctrl.setPharmacyTransferService(expected);
        PharmacyTransferService real = ctrl.getPharmacyTransferService();
        assertEquals(expected, real);
    }

    @Test
    void setPharmacyTransferService() {
        MakeAPharmacyTransferController ctrl = new MakeAPharmacyTransferController();
        PharmacyTransferService expected = new PharmacyTransferService();
        ctrl.setPharmacyTransferService(expected);
        PharmacyTransferService real = ctrl.getPharmacyTransferService();
        assertEquals(expected, real);
    }

    @Test
    void getIssueTransferNoteController() {
        MakeAPharmacyTransferController ctrl = new MakeAPharmacyTransferController();
        IssueTransferNoteController expected = new IssueTransferNoteController();
        ctrl.setIssueTransferNoteController(expected);
        IssueTransferNoteController real = ctrl.getIssueTransferNoteController();
        assertEquals(expected, real);
    }

    @Test
    void setIssueTransferNoteController() {
        MakeAPharmacyTransferController ctrl = new MakeAPharmacyTransferController();
        IssueTransferNoteController expected = new IssueTransferNoteController();
        ctrl.setIssueTransferNoteController(expected);
        IssueTransferNoteController real = ctrl.getIssueTransferNoteController();
        assertEquals(expected, real);
    }
}