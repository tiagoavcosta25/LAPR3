package lapr.project.controller;

import lapr.project.data.registration.*;
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
    private OrderRegistration mockOrderRegistration;
    @Mock
    private InvoiceRegistration mockInvoiceRegistration;
    @Mock
    private ClientRegistration mockClientRegistration;
    private boolean expectedTrue;

    @BeforeEach
    void setUp() {
        this.expectedTrue = true;
        this.generateInvoiceController = new GenerateInvoiceController();
        this.mockOrderRegistration = Mockito.mock(OrderRegistration.class);
        this.mockClientRegistration = Mockito.mock(ClientRegistration.class);
        this.mockInvoiceRegistration = Mockito.mock(InvoiceRegistration.class);
        initMocks(this);
    }

    @Test
    void newInvoice() {
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email1@gmail.com"));
        when(mockClientRegistration.getClientByEmail("email1@gmail.com")).thenReturn(new Client());
        when(mockOrderRegistration.getLatestOrder(new Client())).thenReturn(new Order());
        when(mockInvoiceRegistration.newInvoice(new Date(2020,12,2), 10f, new Order())).thenReturn(new Invoice());
        when(mockInvoiceRegistration.registerInvoice(new Invoice())).thenReturn(expectedTrue);

        boolean result = this.generateInvoiceController.newInvoice(new Date(2020,12,2), 10f);
        assertEquals(expectedTrue, result);
    }
}