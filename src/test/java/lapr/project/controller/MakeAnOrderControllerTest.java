package lapr.project.controller;

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

    /*@InjectMocks
    private MakeAnOrderController makeAnOrderController;

    @Mock
    private OrderRegistration mockOrderRegistration;

    @Mock
    private OrderRegistration mockOrderRegistration;

    private Order expectedTrue;

    @BeforeEach
    void setUp() {
        this.expectedTrue = new Order("Description", new Client(), new Address(-1d, -1d, "Street",
                "1o Direito", "4400-123", "Locality", "Country"), new Pharmacy(), new TreeMap<>());
        this.makeAnOrderController = new MakeAnOrderController();
        this.mockOrderRegistration = Mockito.mock(OrderRegistration.class);
        initMocks(this);
    }

    @Test
    void newOrder() {
        System.out.println("newOrder");
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email3@gmail.com"));

        when(mockOrderRegistration.newOrder("Description", new Client(), -1d, -1d, "Street", "1o Direito", "4400-123", "Locality",
                "Country", new Pharmacy(), new TreeMap<>())).thenReturn(expectedTrue);
        Order result = makeAnOrderController.newOrder("Description", -1d, -1d, "Street", "1o Direito", "4400-123", "Locality",
                "Country");
        assertEquals(expectedTrue, result);
    }

    @Test
    void testNewOrder() {
    }

    @Test
    void registerOrder() {
    }

    @Test
    void getPharmacies() {
    }

    @Test
    void getAvailableProducts() {
    }

    @Test
    void addProductToOrder() {
    }*/
}