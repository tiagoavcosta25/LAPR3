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
        when(mockOrderService.notifyAndRemove(new Order())).thenReturn(null);
        boolean result = notifyAndRemoveController.notifyAndRemove(new Order());
        assertEquals(false,result);

        when(mockOrderService.notifyAndRemove(new Order())).thenReturn(map);
        result = notifyAndRemoveController.notifyAndRemove(new Order());
        assertEquals(true,result);

        map.put(new Product(),2);
        when(mockOrderService.notifyAndRemove(new Order())).thenReturn(map);
        when(mockController.getStockFromAnotherPharamacy(new Order(),new Product(),2)).thenReturn(true);
        result = notifyAndRemoveController.notifyAndRemove(new Order());
        assertEquals(false,result);
    }
}