package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.service.ClientService;
import lapr.project.model.service.OrderService;
import lapr.project.model.service.PharmacyService;
import lapr.project.model.service.ProductService;
import org.junit.jupiter.api.Test;
import lapr.project.data.ProductDB;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MakeAnOrderControllerTest {

    @InjectMocks
    private MakeAnOrderController makeAnOrderController;

    @Mock
    private OrderService mockOrderService;

    @Mock
    private ClientService mockClientService;

    @Mock
    private PharmacyService mockPharmacyService;

    @Mock
    private ProductService mockProductService;

    @Mock
    private GenerateInvoiceController mockGenerateInvoiceController;

    private Order expectedOrder;
    private boolean expectedValue;
    private Order expectedNull;

    @BeforeEach
    void setUp() {
        this.expectedOrder = new Order();
        this.expectedValue = true;
        this.expectedNull = null;
        this.makeAnOrderController = new MakeAnOrderController();
        this.mockOrderService = Mockito.mock(OrderService.class);
        this.mockClientService = Mockito.mock(ClientService.class);
        this.mockProductService = Mockito.mock(ProductService.class);
        this.mockGenerateInvoiceController = Mockito.mock(GenerateInvoiceController.class);
        initMocks(this);
    }

    @Test
    void newOrder() {
        System.out.println("newOrder");
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email3@gmail.com"));
        when(mockClientService.getClientByEmail("email3@gmail.com")).thenReturn(new Client());
        when(mockOrderService.newOrder("Description", true, new Client(), new Pharmacy(), new TreeMap<>())).thenReturn(expectedOrder);

        makeAnOrderController.getPharmacies();
        makeAnOrderController.getAvailableProducts(new Pharmacy());
        Order result = makeAnOrderController.newOrder("Description", true);
        assertEquals(expectedOrder, result);

        makeAnOrderController.getPharmacies();
        makeAnOrderController.getAvailableProducts(new Pharmacy());
        when(mockOrderService.newOrder(null, false, new Client(), new Pharmacy(), new TreeMap<>())).thenReturn(expectedOrder);
        result = makeAnOrderController.newOrder(null, false);
        assertEquals(expectedOrder, result);

        makeAnOrderController.getPharmacies();
        ApplicationPOT.getInstance().setCurrentSession(null);
        result = makeAnOrderController.newOrder(null, null);

        assertEquals(expectedNull, result);


    }

    @Test
    void registerOrder() {
        System.out.println("registerOrder");
        when(mockOrderService.registerOrder(this.expectedOrder)).thenReturn(expectedValue);

        makeAnOrderController.setOrder(this.expectedOrder);
        boolean result = makeAnOrderController.registerOrder();
        assertEquals(expectedValue, result);

        expectedValue = false;
        when(mockOrderService.registerOrder(this.expectedOrder)).thenReturn(expectedValue);

        result = makeAnOrderController.registerOrder();
        assertEquals(expectedValue, result);
    }

    @Test
    void getPharmacies() {
        System.out.println("getPharmacies");

        List<Pharmacy> expectedListPharmacies = new ArrayList<>(Arrays.asList(new Pharmacy()));

        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email3@gmail.com"));
        when(mockClientService.getClientByEmail("email3@gmail.com")).thenReturn(new Client());
        when(mockPharmacyService.getPharmacies()).thenReturn(expectedListPharmacies);

        List<Pharmacy>  result = makeAnOrderController.getPharmacies();
        assertEquals(expectedListPharmacies, result);
    }

    @Test
    void getAvailableProducts() {
        System.out.println("getAvailableProducts");

        List<Product> expectedListProducts = new ArrayList<>(Arrays.asList(new Product()));

        when(mockProductService.getAvailableProducts(-1)).thenReturn(expectedListProducts);

        List<Product>  result = makeAnOrderController.getAvailableProducts(new Pharmacy());
        assertEquals(expectedListProducts, result);

        result = makeAnOrderController.getAvailableProducts(null);
        expectedListProducts = null;
        assertEquals(expectedListProducts, result);
    }

    @Test
    void addProductToOrder() {
        System.out.println("addProductToOrder");

        boolean expected = true;
        boolean real = makeAnOrderController.addProductToOrder(new Product(), 1);
        assertEquals(expected, real);

        real = makeAnOrderController.addProductToOrder(null, 1);
        expected = false;
        assertEquals(expected, real);
    }

    @Test
    void addPayment() {
        System.out.println("addPayment");

        makeAnOrderController.setOrder(new Order());
        boolean  result = makeAnOrderController.addPayment(new CreditCard(), -1f);
        assertTrue(result);

        result = makeAnOrderController.addPayment(new CreditCard(), 2f);
        assertFalse(result);

        result = makeAnOrderController.addPayment(null, null);
        assertFalse(result);

    }

    @Test
    void generateInvoice() {
        System.out.println("generateInvoice");

        when(mockGenerateInvoiceController.generateInvoice(expectedOrder, new TreeMap<>())).thenReturn(true);

        makeAnOrderController.setOrder(expectedOrder);
        boolean result = makeAnOrderController.generateInvoice();
        assertTrue(result);

        when(mockGenerateInvoiceController.generateInvoice(expectedOrder, new TreeMap<>())).thenReturn(false);

        result = makeAnOrderController.generateInvoice();
        assertFalse(result);

        when(mockGenerateInvoiceController.generateInvoice(expectedOrder, new TreeMap<>())).thenThrow(new IllegalArgumentException());

        result = makeAnOrderController.generateInvoice();
        assertFalse(result);

    }

    @Test
    void getCreditCardsByClient() {
        System.out.println("getCreditCardsByClient");

        List<CreditCard> expectedListCreditCards = new ArrayList<>(Arrays.asList(new CreditCard()));

        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email3@gmail.com"));
        when(mockClientService.getCreditCardsByClient("email3@gmail.com")).thenReturn(expectedListCreditCards);

        List<CreditCard>  result = makeAnOrderController.getCreditCardsByClient("email3@gmail.com");
        assertEquals(expectedListCreditCards, result);

        when(mockClientService.getCreditCardsByClient(null)).thenThrow(new IllegalArgumentException());
        result = makeAnOrderController.getCreditCardsByClient(null);
        expectedListCreditCards = null;
        assertEquals(expectedListCreditCards, result);
    }
}