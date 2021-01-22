package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.service.ClientService;
import lapr.project.model.service.InvoiceService;
import lapr.project.model.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.Date;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class GenerateInvoiceControllerTest {

   @InjectMocks
    private GenerateInvoiceController generateInvoiceController;
    @Mock
    private OrderService mockOrderService;
    @Mock
    private InvoiceService mockInvoiceService;
    @Mock
    private ClientService mockClientService;

    @BeforeEach
    void setUp() {
        this.generateInvoiceController = new GenerateInvoiceController();
        this.mockOrderService = Mockito.mock(OrderService.class);
        this.mockClientService = Mockito.mock(ClientService.class);
        this.mockInvoiceService = Mockito.mock(InvoiceService.class);
        initMocks(this);
    }

    @Test
    void newInvoice() {
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email1@gmail.com"));
        when(mockInvoiceService.newInvoice(new Order(), new TreeMap<>())).thenReturn(new Invoice());
        when(mockInvoiceService.registerInvoice(new Invoice())).thenReturn(true);
        when(mockInvoiceService.sendInvoiceByEmail(new Invoice())).thenReturn(true);
        boolean result = this.generateInvoiceController.generateInvoice(new Order(), new TreeMap<>());
        assertTrue(result);

        when(mockInvoiceService.newInvoice(new Order(), new TreeMap<>())).thenReturn(new Invoice());
        when(mockInvoiceService.registerInvoice(new Invoice())).thenReturn(true);
        when(mockInvoiceService.sendInvoiceByEmail(new Invoice())).thenReturn(false);
        result = this.generateInvoiceController.generateInvoice(new Order(), new TreeMap<>());
        assertFalse(result);

        when(mockInvoiceService.registerInvoice(new Invoice())).thenThrow(new IllegalArgumentException());
        result = this.generateInvoiceController.generateInvoice(new Order(), new TreeMap<>());
        assertFalse(result);
    }
}