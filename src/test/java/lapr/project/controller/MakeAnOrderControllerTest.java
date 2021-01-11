package lapr.project.controller;

import lapr.project.data.registration.ClientRegistration;
import lapr.project.data.registration.OrderRegistration;
import lapr.project.model.*;
import org.junit.jupiter.api.Test;
import lapr.project.data.registration.ProductRegistration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MakeAnOrderControllerTest {

    @InjectMocks
    private MakeAnOrderController makeAnOrderController;

    @Mock
    private OrderRegistration mockOrderRegistration;

    @Mock
    private ClientRegistration mockClientRegistration;

    private Order expectedOrder;

    private Client c;

    @BeforeEach
    void setUp() {
        this.expectedOrder = new Order("Description", new Client(), new Address(-1d, -1d, "Street",
                "1o Direito", "4400-123", "Locality", "Country"), new Pharmacy(), new TreeMap<>());
        this.makeAnOrderController = new MakeAnOrderController();
        this.mockOrderRegistration = Mockito.mock(OrderRegistration.class);
        this.mockClientRegistration = Mockito.mock(ClientRegistration.class);
        c = new Client();
        initMocks(this);
    }

    @Test
    void newOrder() {
        System.out.println("newOrder");
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email3@gmail.com"));
        c.setEmail("email3@gmail.com");
        when(mockClientRegistration.getClientByEmail("email3@gmail.com")).thenReturn(c);
        when(mockOrderRegistration.newOrder("Description", c, -1d, -1d, "Street", "1o Direito", "4400-123", "Locality",
                "Country", null, new TreeMap<>())).thenReturn(expectedOrder);
        Order result = makeAnOrderController.newOrder("Description", -1d, -1d, "Street", "1o Direito", "4400-123", "Locality",
                "Country");
        assertEquals(expectedOrder, result);
    }

    @Test
    void testNewOrder() {
        System.out.println("testNewOrder");
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email3@gmail.com"));
        c.setEmail("email3@gmail.com");
        Address a = new Address();
        when(mockClientRegistration.getClientByEmail("email3@gmail.com")).thenReturn(c);
        when(mockOrderRegistration.newOrder("Description", c, a.getLatitude(), a.getLongitude(), a.getStreetName(), a.getDoorNumber(), a.getPostalCode(), a.getLocality(),
                a.getCountry(), null, new TreeMap<>())).thenReturn(expectedOrder);
        Order result = makeAnOrderController.newOrder("Description", true);
        assertEquals(expectedOrder, result);
    }

    @Test
    void registerOrder() {
        System.out.println("testNewOrder");
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email3@gmail.com"));
        c.setEmail("email3@gmail.com");
        Address a = new Address();
        when(mockClientRegistration.getClientByEmail("email3@gmail.com")).thenReturn(c);
        when(mockOrderRegistration.newOrder("Description", c, a.getLatitude(), a.getLongitude(), a.getStreetName(), a.getDoorNumber(), a.getPostalCode(), a.getLocality(),
                a.getCountry(), null, new TreeMap<>())).thenReturn(expectedOrder);
        Order result = makeAnOrderController.newOrder("Description", true);
        assertEquals(expectedOrder, result);
    }

    @Test
    void getPharmacies() {
        System.out.println("testNewOrder");
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email3@gmail.com"));
        c.setEmail("email3@gmail.com");
        Address a = new Address();
        when(mockClientRegistration.getClientByEmail("email3@gmail.com")).thenReturn(c);
        when(mockOrderRegistration.newOrder("Description", c, a.getLatitude(), a.getLongitude(), a.getStreetName(), a.getDoorNumber(), a.getPostalCode(), a.getLocality(),
                a.getCountry(), null, new TreeMap<>())).thenReturn(expectedOrder);
        Order result = makeAnOrderController.newOrder("Description", true);
        assertEquals(expectedOrder, result);
    }

    @Test
    void getAvailableProducts() {
    }

    @Test
    void addProductToOrder() {
    }
}