package lapr.project.model.service;

import lapr.project.data.InvoiceDB;
import lapr.project.data.OrderDB;
import lapr.project.model.*;
import lapr.project.utils.EmailSender;
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

    @Test
    void sendInvoiceByEmail() {
        System.out.println("sendInvoiceByEmail");
        Map<CreditCard, Float> mapPayments = new TreeMap<>();
        mapPayments.put(new CreditCard(123456789, new Date(), 123), 19.19f);
        mapPayments.put(new CreditCard(987654321, new Date(), 119), 11.17f);
        Map<Product, Integer> mapProducts = new TreeMap<>();
        mapProducts.put(new Product(1,"SARS-CoV-2 Vaccine", "Description.", 7.5f, 1f), 2);
        mapProducts.put(new Product(2,"Benuron", "Description.", 5.12f, 2f), 3);
        Invoice oInvoice = new Invoice(62176, new Order("Description", true, new Client(1, "", 12, "g21@trash-mail.com",
                "", 1, new Address(1d, 1d, "Rua Pádua Correia",
                "nº19 1º Direito", "4400-123", "Vila Nova de Gaia", "Portugal"), new CreditCard()),
                new Pharmacy("LAPR3 Pharmacy", "info@lapr3Pharmacy.com", new Address(1d, 1d, "Avenida dos Aliados",
                "nº11 R/C", "4532-987", "Porto", "Portugal")), mapProducts), mapPayments);

        boolean result = invoiceService.sendInvoiceByEmail(oInvoice);
        assertTrue(result);

        oInvoice.getOrder().setHomeDelivery(false);
        result = invoiceService.sendInvoiceByEmail(oInvoice);
        assertTrue(result);

        oInvoice.getOrder().getClient().setEmail(null);
        result = invoiceService.sendInvoiceByEmail(oInvoice);
        assertFalse(result);
    }
}