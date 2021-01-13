package lapr.project.controller;

import lapr.project.model.Client;
import lapr.project.model.Invoice;
import lapr.project.model.Order;
import lapr.project.model.UserSession;
import lapr.project.model.service.ClientService;
import lapr.project.model.service.InvoiceService;
import lapr.project.model.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.Date;

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
        this.generateInvoiceController = new GenerateInvoiceController("","","");
        this.mockOrderService = Mockito.mock(OrderService.class);
        this.mockClientService = Mockito.mock(ClientService.class);
        this.mockInvoiceService = Mockito.mock(InvoiceService.class);
        initMocks(this);
    }

    @Test
    void newInvoice() {
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email1@gmail.com"));
        when(mockClientService.getClientByEmail("email1@gmail.com")).thenReturn(new Client());
        when(mockOrderService.getLatestOrder(new Client())).thenReturn(new Order());
        when(mockInvoiceService.newInvoice(new Date(1220227200), 10f, new Order())).thenReturn(new Invoice());
        when(mockInvoiceService.registerInvoice(new Invoice())).thenReturn(true);
        boolean result = this.generateInvoiceController.newInvoice(new Date(1220227200), 10f);
        assertTrue(result);

        when(mockInvoiceService.registerInvoice(new Invoice())).thenReturn(false);
        result = this.generateInvoiceController.newInvoice(new Date(1220227200), 10f);
        assertFalse(result);

        ApplicationPOT.getInstance().setCurrentSession(null);
        result = this.generateInvoiceController.newInvoice(new Date(1220227200), 10f);
        assertFalse(result);
    }
}