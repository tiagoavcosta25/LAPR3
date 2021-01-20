package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderStatusTest {

    @Test
    void getDesignation() {
        OrderStatus o = OrderStatus.DELIVERED;
        String expected = "Delivered";
        String real = o.getDesignation();
        assertEquals(expected, real);
    }

    @Test
    void testToString() {
        OrderStatus o = OrderStatus.ORDERED;
        String expected = "OrderStatus{m_strDesignation='Ordered'}";
        String real = o.toString();
        assertEquals(expected, real);
    }
}