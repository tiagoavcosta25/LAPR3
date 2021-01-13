package lapr.project.controller;

import lapr.project.model.Order;
import lapr.project.model.UserSession;
import lapr.project.model.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class KnowDeliveryControllerTest {
    @InjectMocks
    private KnowDeliveryController knowDeliveryController;

    @Mock
    private OrderService mockOrderService;

    private Order expectedTrue;

    @BeforeEach
    void setUp() {
        this.expectedTrue = new Order();
        this.knowDeliveryController = new KnowDeliveryController();
        this.mockOrderService = Mockito.mock(OrderService.class);
        initMocks(this);
    }

    @Test
    void ensureKnowDeliveryWorks() {
        System.out.println("knowDelivery");
        String email = "email6@gmail.com";
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email6@gmail.com"));
        when(mockOrderService.getOrderByCourier(email)).thenReturn(expectedTrue);
        Order result = knowDeliveryController.getOrderByCour();
        assertEquals(expectedTrue, result);
    }
}
