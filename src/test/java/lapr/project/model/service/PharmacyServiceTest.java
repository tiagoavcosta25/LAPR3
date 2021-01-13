package lapr.project.model.service;

import lapr.project.controller.ApplicationPOT;
import lapr.project.controller.MakeAnOrderController;
import lapr.project.data.ClientDB;
import lapr.project.data.OrderDB;
import lapr.project.data.ProductDB;
import lapr.project.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class PharmacyServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderDB mockOrderDB;

    private Order expectedOrder;
    private boolean expectedValue;
    private Order expectedNull;

    @BeforeEach
    void setUp() {
        this.expectedOrder = new Order("Description", new Client(), new Address(-1d, -1d, "Street",
                "1o Direito", "4400-123", "Locality", "Country"), new Pharmacy(), new TreeMap<>());
        this.expectedValue = true;
        this.expectedNull = null;
        this.orderService = new OrderService();
        this.mockOrderDB = Mockito.mock(OrderDB.class);
        initMocks(this);
    }

    @Test
    void getPharmacy() {
    }

    @Test
    void removePharmacy() {
    }

    @Test
    void registerOrder() {
    }

    @Test
    void newPharmacy() {
    }

    @Test
    void registerPharmacyProduct() {
    }

    @Test
    void getPharmacies() {
    }

    @Test
    void getPharmacyByManagerEmail() {
    }
}