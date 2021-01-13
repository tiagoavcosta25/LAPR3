package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryTest {

    private Delivery m_delivery;
    private Delivery m_emptyDelivery;

    DeliveryTest() {
        Date d = new Date(2, 2, 2);
        java.sql.Date date = new java.sql.Date(d.getTime());
        m_delivery = new Delivery(new Order(1, 2.0f, 3.0f, 2.0f, date,
                "testDesc", "testStatus", new Client(), new Address(), new Pharmacy(), new TreeMap<>()));
        m_emptyDelivery = new Delivery();
    }

    @Test
    void getOrder() {
        Order expected = new Order(1, 2.0f, 3.0f, 2.0f, (new java.sql.Date(2, 2, 2)),
                "testDesc", "testStatus", new Client(), new Address(), new Pharmacy(), new TreeMap<>());
        Order real = m_delivery.getOrder();
        assertEquals(expected, real);
    }

    @Test
    void setOrder() {
        Order expected = null;
        m_delivery.setOrder(null);
        Order real = m_delivery.getOrder();
        assertEquals(expected, real);
    }

    @Test
    void getDeliveryDate() {
        m_delivery.setDeliveryDate(new Date(1, 1, 1));
        Date expected = new Date(1, 1, 1);
        Date real = m_delivery.getDeliveryDate();
        assertEquals(expected, real);
    }

    @Test
    void setDeliveryDate() {
        Date expected = new Date(2, 2, 2);
        m_delivery.setDeliveryDate(new Date(2, 2, 2));
        Date real = m_delivery.getDeliveryDate();
        assertEquals(expected, real);
    }

    @Test
    void getNotes() {
        String expected = "";
        String real = m_delivery.getNotes();
        assertEquals(expected, real);
    }

    @Test
    void setNotes() {
        String expected = "newstr";
        m_delivery.setNotes("newstr");
        String real = m_delivery.getNotes();
        assertEquals(expected, real);
    }

    @Test
    void testEquals() {
        Delivery d = new Delivery(new Order(1, 2.0f, 3.0f, 2.0f, new java.sql.Date(2,2,2),
                "testDesc", "testStatus", new Client(), new Address(), new Pharmacy(), new TreeMap<>()));

        boolean real = m_delivery.equals(d);
        assertTrue(real);

        real = m_delivery.equals(m_delivery);

        assertTrue(real);

        d = new Delivery(new Order(5, 1.0f, 1.0f, 2.0f, new java.sql.Date(5,5,5),
                "testDesc", "testStatus", new Client(), new Address(), new Pharmacy(), new TreeMap<>()));

        real = m_delivery.equals(d);

        assertFalse(real);

        String s = "";
        real = m_delivery.equals(s);
        assertFalse(real);

        Order o = new Order();
        real = m_delivery.equals(o);
        assertFalse(real);

        d = null;
        real = m_delivery.equals(d);
        assertFalse(real);

        d = new Delivery();
        real = m_delivery.equals(d);
        assertFalse(real);

    }

    @Test
    void testToString() {
        String expected = "Delivery{" +
                "m_oOrder=" + m_delivery.getOrder() +
                ", m_dtDeliveryDate=" + m_delivery.getDeliveryDate() +
                ", m_strNotes='" + m_delivery.getNotes() + '\'' +
                '}';
        String real = m_delivery.toString();
        assertEquals(expected,real);
    }
}