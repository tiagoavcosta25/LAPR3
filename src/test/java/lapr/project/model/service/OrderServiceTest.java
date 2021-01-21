package lapr.project.model.service;

import lapr.project.data.OrderDB;
import lapr.project.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderDB mockOrderDB;

    private Order expectedOrder;

    @BeforeEach
    void setUp() {
        this.expectedOrder = new Order("Description", true, new Client(),  new Pharmacy(), new TreeMap<>());
        this.orderService = new OrderService();
        this.mockOrderDB = Mockito.mock(OrderDB.class);
        initMocks(this);
    }

    @Test
    void getOrder() {
        System.out.println("getOrder");
        Order expected = new Order();
        when(mockOrderDB.getOrder(1)).thenReturn(new Order());
        Order result = orderService.getOrder(1);
        assertEquals(expected, result);
    }

    @Test
    void removeOrder() {
        System.out.println("removeOrder");
        when(mockOrderDB.removeOrder(1)).thenReturn(true);
        boolean result = orderService.removeOrder(1);
        assertTrue(result);

        when(mockOrderDB.removeOrder(-1)).thenReturn(false);
        result = orderService.removeOrder(2);
        assertFalse(result);
    }

    @Test
    void registerOrder() {
        System.out.println("registerOrder");
        when(mockOrderDB.registerOrder(expectedOrder)).thenReturn(true);
        boolean result = orderService.registerOrder(expectedOrder);
        assertTrue(result);

        when(mockOrderDB.registerOrder(null)).thenReturn(false);
        result = orderService.registerOrder(null);
        assertFalse(result);
    }

    @Test
    void newOrder() {
        System.out.println("newOrder");
        Order result = orderService.newOrder("Description", false, new Client(),  new Pharmacy(), new TreeMap<>());
        assertEquals(expectedOrder, result);
    }

    @Test
    void testNewOrder() {
        System.out.println("testNewOrder");
        Order expected = new Order("Description", true, new Client(), new Pharmacy(), new TreeMap<>());
        Order result = orderService.newOrder("Description", true, new Client(), new Pharmacy(), new TreeMap<>());
        assertEquals(expected, result);
    }

    @Test
    void getLatestOrder() {
        System.out.println("getLatestOrder");
        Order expected = new Order();
        when(mockOrderDB.getLatestOrder(new Client())).thenReturn(new Order());
        Order result = orderService.getLatestOrder(new Client());
        assertEquals(expected, result);
    }

    @Test
    void getOrderByCourier() {
        System.out.println("getOrderByCourier");
        Order expected = new Order();
        when(mockOrderDB.getOrderByCourier("test@isep.ipp.pt")).thenReturn(new Order());
        Order result = orderService.getOrderByCourier("test@isep.ipp.pt");
        assertEquals(expected, result);
    }

    @Test
    void notifyAndRemove() {
        System.out.println("notifyAndRemove");
        Map<Product,Integer> map = new TreeMap<>();
        when(mockOrderDB.notifyAndRemove(expectedOrder)).thenReturn(null);
        Map<Product, Integer> result = orderService.notifyAndRemove(expectedOrder);
        assertEquals(null,result);

        when(mockOrderDB.notifyAndRemove(expectedOrder)).thenReturn(map);
        result = orderService.notifyAndRemove(expectedOrder);
        assertEquals(map,result);
    }
}