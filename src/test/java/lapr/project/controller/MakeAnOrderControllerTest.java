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

import java.util.*;

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

    @Mock
    private NotifyAndRemoveController mockNotifyAndRemoveController;

    private Order expectedOrder;
    private Order expectedNull;

    @BeforeEach
    void setUp() {
        this.expectedOrder = new Order();
        this.expectedNull = null;
        this.makeAnOrderController = new MakeAnOrderController();
        this.mockOrderService = Mockito.mock(OrderService.class);
        this.mockClientService = Mockito.mock(ClientService.class);
        this.mockProductService = Mockito.mock(ProductService.class);
        this.mockGenerateInvoiceController = Mockito.mock(GenerateInvoiceController.class);
        this.mockNotifyAndRemoveController = Mockito.mock(NotifyAndRemoveController.class);
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

        when(mockOrderService.registerOrder(this.expectedOrder)).thenReturn(-1);
        when(mockNotifyAndRemoveController.notifyAndRemove(this.expectedOrder)).thenReturn(true);
        when(mockClientService.updateClientCredits("No Email Registered", 0)).thenReturn(true);
        when(mockGenerateInvoiceController.generateInvoice(expectedOrder, new TreeMap<>())).thenReturn(true);

        makeAnOrderController.setClient(new Client());
        makeAnOrderController.setOrder(this.expectedOrder);
        boolean result = makeAnOrderController.registerOrder();
        assertTrue(result);

        when(mockOrderService.registerOrder(this.expectedOrder)).thenReturn(-1);
        when(mockGenerateInvoiceController.generateInvoice(expectedOrder, new TreeMap<>())).thenReturn(false);
        result = makeAnOrderController.registerOrder();
        assertFalse(result);

        when(mockOrderService.registerOrder(this.expectedOrder)).thenThrow(new IllegalArgumentException());
        result = makeAnOrderController.registerOrder();
        assertFalse(result);

        when(mockNotifyAndRemoveController.notifyAndRemove(this.expectedOrder)).thenReturn(false);
        result = makeAnOrderController.registerOrder();
        assertFalse(result);
    }

    @Test
    void registerOrder2() {
        System.out.println("registerOrder");

        when(mockOrderService.registerOrder(this.expectedOrder)).thenReturn(-1);
        when(mockNotifyAndRemoveController.notifyAndRemove(this.expectedOrder)).thenReturn(true);
        when(mockClientService.updateClientCredits("No Email Registered", 0)).thenReturn(true);
        when(mockGenerateInvoiceController.generateInvoice(expectedOrder, new TreeMap<>())).thenReturn(true);

        makeAnOrderController.setClient(new Client());
        makeAnOrderController.setOrder(this.expectedOrder);
        boolean result = makeAnOrderController.registerOrder();
        assertTrue(result);
        assertEquals(-1, makeAnOrderController.getOrder().getId());
        assertNotEquals(0, makeAnOrderController.getOrder().getId());

        when(mockOrderService.registerOrder(this.expectedOrder)).thenReturn(1);
        result = makeAnOrderController.registerOrder();
        assertTrue(result);
        assertNotEquals(-1, makeAnOrderController.getOrder().getId());
        assertEquals(1, makeAnOrderController.getOrder().getId());
    }

    @Test
    void getOrder() {
        Order o = new Order();
        assertNull(makeAnOrderController.getOrder());
        makeAnOrderController.setOrder(o);
        assertEquals(o, makeAnOrderController.getOrder());
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
        expectedListProducts = new ArrayList<>();
        assertEquals(expectedListProducts, result);
    }

    @Test
    void testGetAvailableProducts() {
        System.out.println("testGetAvailableProducts");

        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email3@gmail.com"));
        when(mockClientService.getClientByEmail("email3@gmail.com")).thenReturn(new Client());
        List<Product> expectedListProducts = new ArrayList<>(Arrays.asList(new Product()));

        when(mockPharmacyService.getClosestPharmacyToClient(new Client())).thenReturn(new Pharmacy());
        when(mockProductService.getAvailableProducts(-1)).thenReturn(expectedListProducts);

        makeAnOrderController.setClient(new Client());
        List<Product> result = makeAnOrderController.getAvailableProducts();
        assertEquals(expectedListProducts, result);

        when(mockPharmacyService.getClosestPharmacyToClient(new Client())).thenThrow(new IllegalArgumentException());
        makeAnOrderController.setClient(new Client());
        result = makeAnOrderController.getAvailableProducts();
        expectedListProducts = new ArrayList<>();
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
        boolean  result = makeAnOrderController.addPayment(new CreditCard(), -1d);
        assertTrue(result);

        result = makeAnOrderController.addPayment(new CreditCard(), 2d);
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

        Client oClient = new Client();
        oClient.setEmail("email3@gmail.com");
        makeAnOrderController.setClient(oClient);
        List<CreditCard>  result = makeAnOrderController.getCreditCardsByClient();
        assertEquals(expectedListCreditCards, result);


        oClient.setEmail(null);
        makeAnOrderController.setClient(oClient);
        when(mockClientService.getCreditCardsByClient(null)).thenThrow(new IllegalArgumentException());
        result = makeAnOrderController.getCreditCardsByClient();
        expectedListCreditCards = new ArrayList<>();
        assertEquals(expectedListCreditCards, result);
    }

    @Test
    void setPharmacy() {
        System.out.println("setPharmacy");

        when(mockPharmacyService.getPharmacy("Test")).thenReturn(new Pharmacy());
        boolean real = makeAnOrderController.setPharmacy("Test");
        assertTrue(real);

        when(mockPharmacyService.getPharmacy("Test")).thenThrow(new IllegalArgumentException());
        real = makeAnOrderController.setPharmacy("Test");
        assertFalse(real);
    }

    @Test
    void getOrderService() {
        MakeAnOrderController ctrl = new MakeAnOrderController();
        OrderService expected = new OrderService();
        ctrl.setOrderService(expected);
        OrderService real = ctrl.getOrderService();
        assertEquals(expected, real);
    }

    @Test
    void setOrderService() {
        MakeAnOrderController ctrl = new MakeAnOrderController();
        OrderService expected = new OrderService();
        ctrl.setOrderService(expected);
        OrderService real = ctrl.getOrderService();
        assertEquals(expected, real);
    }

    @Test
    void getClientService() {
        MakeAnOrderController ctrl = new MakeAnOrderController();
        ClientService expected = new ClientService();
        ctrl.setClientService(expected);
        ClientService real = ctrl.getClientService();
        assertEquals(expected, real);
    }

    @Test
    void setClientService() {
        MakeAnOrderController ctrl = new MakeAnOrderController();
        ClientService expected = new ClientService();
        ctrl.setClientService(expected);
        ClientService real = ctrl.getClientService();
        assertEquals(expected, real);
    }

    @Test
    void getProductService() {
        MakeAnOrderController ctrl = new MakeAnOrderController();
        ProductService expected = new ProductService();
        ctrl.setProductService(expected);
        ProductService real = ctrl.getProductService();
        assertEquals(expected, real);
    }

    @Test
    void setProductService() {
        MakeAnOrderController ctrl = new MakeAnOrderController();
        ProductService expected = new ProductService();
        ctrl.setProductService(expected);
        ProductService real = ctrl.getProductService();
        assertEquals(expected, real);
    }

    @Test
    void getPharmacyService() {
        MakeAnOrderController ctrl = new MakeAnOrderController();
        PharmacyService expected = new PharmacyService();
        ctrl.setPharmacyService(expected);
        PharmacyService real = ctrl.getPharmacyService();
        assertEquals(expected, real);
    }

    @Test
    void setPharmacyService() {
        MakeAnOrderController ctrl = new MakeAnOrderController();
        PharmacyService expected = new PharmacyService();
        ctrl.setPharmacyService(expected);
        PharmacyService real = ctrl.getPharmacyService();
        assertEquals(expected, real);
    }

    @Test
    void getMapProducts() {
        MakeAnOrderController ctrl = new MakeAnOrderController();
        Map<Product, Integer> expected = new TreeMap<>();
        ctrl.setMapProducts(expected);
        Map<Product, Integer> real = ctrl.getMapProducts();
        assertEquals(expected, real);
    }

    @Test
    void setMapProducts() {
        MakeAnOrderController ctrl = new MakeAnOrderController();
        Map<Product, Integer> expected = new TreeMap<>();
        ctrl.setMapProducts(expected);
        Map<Product, Integer> real = ctrl.getMapProducts();
        assertEquals(expected, real);
    }

    @Test
    void getMapPayments() {
        MakeAnOrderController ctrl = new MakeAnOrderController();
        Map<CreditCard, Double> expected = new TreeMap<>();
        ctrl.setMapPayments(expected);
        Map<CreditCard, Double> real = ctrl.getMapPayments();
        assertEquals(expected, real);
    }

    @Test
    void setMapPayments() {
        MakeAnOrderController ctrl = new MakeAnOrderController();
        Map<CreditCard, Double> expected = new TreeMap<>();
        ctrl.setMapPayments(expected);
        Map<CreditCard, Double> real = ctrl.getMapPayments();
        assertEquals(expected, real);
    }

    @Test
    void getGenerateInvoiceController() {
        MakeAnOrderController ctrl = new MakeAnOrderController();
        GenerateInvoiceController expected = new GenerateInvoiceController();
        ctrl.setGenerateInvoiceController(expected);
        GenerateInvoiceController real = ctrl.getGenerateInvoiceController();
        assertEquals(expected, real);
    }

    @Test
    void setGenerateInvoiceController() {
        MakeAnOrderController ctrl = new MakeAnOrderController();
        GenerateInvoiceController expected = new GenerateInvoiceController();
        ctrl.setGenerateInvoiceController(expected);
        GenerateInvoiceController real = ctrl.getGenerateInvoiceController();
        assertEquals(expected, real);
    }

    @Test
    void getNotifyAndRemoveController() {
        MakeAnOrderController ctrl = new MakeAnOrderController();
        NotifyAndRemoveController expected = new NotifyAndRemoveController();
        ctrl.setNotifyAndRemoveController(expected);
        NotifyAndRemoveController real = ctrl.getNotifyAndRemoveController();
        assertEquals(expected, real);
    }

    @Test
    void setNotifyAndRemoveController() {
        MakeAnOrderController ctrl = new MakeAnOrderController();
        NotifyAndRemoveController expected = new NotifyAndRemoveController();
        ctrl.setNotifyAndRemoveController(expected);
        NotifyAndRemoveController real = ctrl.getNotifyAndRemoveController();
        assertEquals(expected, real);
    }

    @Test
    void getCurrentPayment() {
        MakeAnOrderController ctrl = new MakeAnOrderController();
        float expected = 1f;
        ctrl.setCurrentPayment(expected);
        Float real = ctrl.getCurrentPayment();
        assertEquals(expected, real);
    }

    @Test
    void setCurrentPayment() {
        MakeAnOrderController ctrl = new MakeAnOrderController();
        float expected = 1f;
        ctrl.setCurrentPayment(expected);
        Float real = ctrl.getCurrentPayment();
        assertEquals(expected, real);
    }

    @Test
    void getExpectedPayment() {
        MakeAnOrderController ctrl = new MakeAnOrderController();
        float expected = 1f;
        ctrl.setExpectedPayment(expected);
        Float real = ctrl.getExpectedPayment();
        assertEquals(expected, real);
    }

    @Test
    void setExpectedPayment() {
        MakeAnOrderController ctrl = new MakeAnOrderController();
        float expected = 1f;
        ctrl.setExpectedPayment(expected);
        Float real = ctrl.getExpectedPayment();
        assertEquals(expected, real);
    }
}