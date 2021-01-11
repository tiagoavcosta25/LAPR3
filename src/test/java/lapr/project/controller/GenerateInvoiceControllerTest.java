package lapr.project.controller;

import lapr.project.data.registration.*;
import lapr.project.model.Client;
import lapr.project.model.Order;
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
   /* @InjectMocks
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

        this.mockOrderRegistration = Mockito.mock(OrderRegistration.class);
        this.mockClientRegistration = Mockito.mock(ClientRegistration.class);
        initMocks(this);
    }

    @Test
    void newInvoice() {
        Client c = new Client();
        Order o = new Order();
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email1@gmail.com"));
        when(mockClientRegistration.getClientByEmail("email1@gmail.com")).thenReturn(c);
        when(mockOrderRegistration.getLatestOrder(c)).thenReturn(o);
        this.generateInvoiceController = new GenerateInvoiceController();
        boolean result = this.generateInvoiceController.newInvoice(new Date(2,12,2020), 10f);
        assertTrue(result);
    }

    @Test
    void registerInvoice() {
    }*/
}