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

    private Order expectedOrder;
    private boolean expectedValue;
    private Order expectedNull;

    @BeforeEach
    void setUp() {
        this.expectedOrder = new Order("Description", new Client(), new Address(-1d, -1d, "Street",
                "1o Direito", "4400-123", "Locality", "Country"), new Pharmacy(), new TreeMap<>());
        this.expectedValue = true;
        this.expectedNull = null;
        this.makeAnOrderController = new MakeAnOrderController();
        this.mockOrderService = Mockito.mock(OrderService.class);
        this.mockClientService = Mockito.mock(ClientService.class);
        this.mockProductService = Mockito.mock(ProductService.class);
        initMocks(this);
    }

    @Test
    void newOrder() {
        System.out.println("newOrder");
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email3@gmail.com"));
        when(mockClientService.getClientByEmail("email3@gmail.com")).thenReturn(new Client());
        when(mockOrderService.newOrder("Description", new Client(), -1d, -1d, "Street", "1o Direito", "4400-123", "Locality",
                "Country", null, new TreeMap<>())).thenReturn(expectedOrder);
        Order result = makeAnOrderController.newOrder("Description", -1d, -1d, "Street", "1o Direito", "4400-123", "Locality",
                "Country");
        assertEquals(expectedOrder, result);

        result = makeAnOrderController.newOrder(null, null, null, null, null, null, null, null);
        assertEquals(expectedNull, result);

        ApplicationPOT.getInstance().setCurrentSession(null);
        result = makeAnOrderController.newOrder(null, null, null, null, null, null, null, null);

        assertEquals(null, result);


    }

    @Test
    void testNewOrder() {
        System.out.println("testNewOrder");
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email3@gmail.com"));
        Address a = new Address();
        when(mockClientService.getClientByEmail("email3@gmail.com")).thenReturn(new Client());
        when(mockOrderService.newOrder("Description", new Client(), a.getLatitude(), a.getLongitude(), a.getStreetName(), a.getDoorNumber(), a.getPostalCode(), a.getLocality(),
                a.getCountry(), null, new TreeMap<>())).thenReturn(expectedOrder);
        Order result = makeAnOrderController.newOrder("Description", true);
        assertEquals(expectedOrder, result);

        when(mockOrderService.newOrder("Description", new Client(), null, new TreeMap<>())).thenReturn(expectedOrder);
        assertEquals(expectedOrder, result);

        result = makeAnOrderController.newOrder(null, null);
        assertEquals(expectedNull, result);

        result = makeAnOrderController.newOrder("Description", false);
        assertEquals(expectedOrder, result);
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
}