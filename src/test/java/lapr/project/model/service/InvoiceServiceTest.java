package lapr.project.model.service;

import lapr.project.data.InvoiceDB;
import lapr.project.data.OrderDB;
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

class InvoiceServiceTest {

    @InjectMocks
    private InvoiceService invoiceService;

    @Mock
    private InvoiceDB mockInvoiceDB;

    private Invoice expectedInvoice;
    private Order expectedOrder;


    @BeforeEach
    void setUp() {
        this.expectedInvoice = new Invoice();
        this.expectedOrder = this.expectedInvoice.getOrder();
        this.invoiceService = new InvoiceService();
        this.mockInvoiceDB = Mockito.mock(InvoiceDB.class);
        initMocks(this);
    }

    @Test
    void getInvoice() {
        System.out.println("getInvoice");
        when(mockInvoiceDB.getInvoice(1)).thenReturn(new Invoice());
        Invoice result = invoiceService.getInvoice(1);
        assertEquals(expectedInvoice, result);
    }

    @Test
    void removeInvoice() {
        System.out.println("removeInvoice");
        when(mockInvoiceDB.removeInvoice(1)).thenReturn(true);
        boolean result = invoiceService.removeInvoice(1);
        assertTrue(result);

        when(mockInvoiceDB.removeInvoice(-1)).thenReturn(false);
        result = invoiceService.removeInvoice(2);
        assertFalse(result);
    }

    @Test
    void registerInvoice() {
        System.out.println("registerInvoice");
        when(mockInvoiceDB.registerInvoice(expectedInvoice)).thenReturn(true);
        boolean result = invoiceService.registerInvoice(expectedInvoice);
        assertTrue(result);

        when(mockInvoiceDB.registerInvoice(null)).thenReturn(false);
        result = invoiceService.registerInvoice(null);
        assertFalse(result);
    }

    @Test
    void newInvoice() {
        System.out.println("newInvoice");
        Invoice result = invoiceService.newInvoice(expectedOrder, new TreeMap<>());
        assertEquals(expectedInvoice, result);
    }
}