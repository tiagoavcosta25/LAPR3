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
    @Test
    void testGetOrderService(){
        KnowDeliveryController ctrl = new KnowDeliveryController();
        OrderService expected = new OrderService();
        ctrl.setMoOrderService(expected);
        OrderService real = ctrl.getMoOrderService();
        assertEquals(expected, real);
    }
    @Test
    void testSetOrderService() {
        KnowDeliveryController ctrl = new KnowDeliveryController();
        OrderService expected = new OrderService();
        ctrl.setMoOrderService(expected);
        OrderService real = ctrl.getMoOrderService();
        assertEquals(expected, real);
    }
    @Test
    void testGetCourierEmail(){
        KnowDeliveryController ctrl = new KnowDeliveryController();
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email1@gmail.com"));
        String expected = "email1@gmail.com";
        ctrl.setMoCourierEmail(expected);
        String real = ctrl.getMoCourierEmail();
        assertEquals(expected, real);
    }
    @Test
    void testSetCourierEmail() {
        KnowDeliveryController ctrl = new KnowDeliveryController();
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email2@gmail.com"));
        String expected = "email2@gmail.com";
        ctrl.setMoCourierEmail(expected);
        String real = ctrl.getMoCourierEmail();
        assertEquals(expected, real);
    }
    @Test
    void testGetOrder(){
        KnowDeliveryController ctrl = new KnowDeliveryController();
        Order expected = new Order();
        ctrl.setMoOrder(expected);
        Order real = ctrl.getMoOrder();
        assertEquals(expected, real);
    }
    @Test
    void testSetOrder() {
        KnowDeliveryController ctrl = new KnowDeliveryController();
        Order expected = new Order();
        ctrl.setMoOrder(expected);
        Order real = ctrl.getMoOrder();
        assertEquals(expected, real);
    }
}
