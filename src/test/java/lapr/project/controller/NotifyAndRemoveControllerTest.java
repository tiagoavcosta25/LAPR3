package lapr.project.controller;


import lapr.project.model.Order;
import lapr.project.model.Pharmacy;
import lapr.project.model.Product;
import lapr.project.model.service.OrderService;
import lapr.project.model.service.PharmacyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class NotifyAndRemoveControllerTest {

    @InjectMocks
    private NotifyAndRemoveController notifyAndRemoveController;

    @Mock
    private OrderService mockOrderService;

    @Mock
    private MakeAPharmacyTransferController mockController;


    @BeforeEach
    void setUp() {
        this.notifyAndRemoveController = new NotifyAndRemoveController();
        this.mockOrderService = Mockito.mock(OrderService.class);
        this.mockController = Mockito.mock(MakeAPharmacyTransferController.class);
        initMocks(this);
    }

    @Test
    void notifyAndRemove() {
        Map<Product,Integer> map = new TreeMap<>();
        Order oOrder = new Order();
        oOrder.setProducts(map);
        when(mockOrderService.notifyAndRemove(new Order())).thenReturn(map);
        boolean result = notifyAndRemoveController.notifyAndRemove(new Order());
        assertTrue(result);

        map.put(new Product("dadwa", "adda", 1d, 1d), 420);
        oOrder.setProducts(map);
        when(mockOrderService.notifyAndRemove(oOrder)).thenReturn(null);
        result = notifyAndRemoveController.notifyAndRemove(oOrder);
        assertFalse(result);

        when(mockOrderService.notifyAndRemove(new Order())).thenReturn(map);
        result = notifyAndRemoveController.notifyAndRemove(new Order());
        assertTrue(result);

        map.put(new Product(),2);
        when(mockOrderService.notifyAndRemove(new Order())).thenReturn(map);
        when(mockController.getStockFromAnotherPharamacy(new Order(),new Product(),2)).thenReturn(true);
        result = notifyAndRemoveController.notifyAndRemove(new Order());
        assertTrue(result);
    }

    @Test
    void getOrderService() {
        NotifyAndRemoveController ctrl = new NotifyAndRemoveController();
        OrderService expected = new OrderService();
        ctrl.setOrderService(expected);
        OrderService real = ctrl.getOrderService();
        assertEquals(expected, real);
    }

    @Test
    void setOrderService() {
        NotifyAndRemoveController ctrl = new NotifyAndRemoveController();
        OrderService expected = new OrderService();
        ctrl.setOrderService(expected);
        OrderService real = ctrl.getOrderService();
        assertEquals(expected, real);
    }
}