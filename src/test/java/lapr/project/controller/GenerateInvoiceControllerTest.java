package lapr.project.controller;

import lapr.project.data.ClientDB;
import lapr.project.data.InvoiceDB;
import lapr.project.data.OrderDB;
import lapr.project.model.Client;
import lapr.project.model.Invoice;
import lapr.project.model.Order;
import lapr.project.model.UserSession;
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
    private OrderDB mockOrderDB;
    @Mock
    private InvoiceDB mockInvoiceDB;
    @Mock
    private ClientDB mockClientDB;

    @BeforeEach
    void setUp() {
        this.generateInvoiceController = new GenerateInvoiceController("","","");
        this.mockOrderDB = Mockito.mock(OrderDB.class);
        this.mockClientDB = Mockito.mock(ClientDB.class);
        this.mockInvoiceDB = Mockito.mock(InvoiceDB.class);
        initMocks(this);
    }

    @Test
    void newInvoice() {
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email1@gmail.com"));
        when(mockClientDB.getClientByEmail("email1@gmail.com")).thenReturn(new Client());
        when(mockOrderDB.getLatestOrder(new Client())).thenReturn(new Order());
        when(mockInvoiceDB.newInvoice(new Date(2020,12,2), 10f, new Order())).thenReturn(new Invoice());
        when(mockInvoiceDB.registerInvoice(new Invoice())).thenReturn(true);
        boolean result = this.generateInvoiceController.newInvoice(new Date(2020,12,2), 10f);
        assertTrue(result);

        when(mockInvoiceDB.registerInvoice(new Invoice())).thenReturn(false);
        result = this.generateInvoiceController.newInvoice(new Date(2020,12,2), 10f);
        assertFalse(result);

        ApplicationPOT.getInstance().setCurrentSession(null);
        result = this.generateInvoiceController.newInvoice(new Date(2020,12,2), 10f);
        assertFalse(result);
    }
}