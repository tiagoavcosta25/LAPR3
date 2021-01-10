package lapr.project.model;

import org.junit.jupiter.api.Test;
import java.sql.Date;
import java.util.Calendar;
import java.util.Map;
import java.util.TreeMap;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void getId() {
        Order oOrder = new Order();
        Integer expected = -1;
        Integer real = oOrder.getId();
        assertEquals(expected, real);
    }

    @Test
    void setId() {
        Order oOrder = new Order();
        Integer expected = -2;
        oOrder.setId(expected);
        Integer real = oOrder.getId();
        assertEquals(expected, real);
    }

    @Test
    void getAmount() {
        Order oOrder = new Order();
        float expected = -1;
        float real = oOrder.getAmount();
        assertEquals(expected, real);
    }

    @Test
    void setAmount() {
        Order oOrder = new Order();
        Float expected = -2f;
        oOrder.setAmount(expected);
        Float real = oOrder.getAmount();
        assertEquals(expected, real);
    }

    @Test
    void getTotalWeight() {
        Order oOrder = new Order();
        float expected = -1;
        float real = oOrder.getTotalWeight();
        assertEquals(expected, real);
    }

    @Test
    void setTotalWeight() {
        Order oOrder = new Order();
        Float expected = -2f;
        oOrder.setTotalWeight(expected);
        Float real = oOrder.getTotalWeight();
        assertEquals(expected, real);
    }

    @Test
    void getAdditionalFee() {
        Order oOrder = new Order();
        float expected = -1f;
        float real = oOrder.getAdditionalFee();
        assertEquals(expected, real);
    }

    @Test
    void setAdditionalFee() {
        Order oOrder = new Order();
        Float expected = -2f;
        oOrder.setAdditionalFee(expected);
        Float real = oOrder.getAdditionalFee();
        assertEquals(expected, real);
    }

    @Test
    void getOrderDate() {
        Order oOrder = new Order();
        Date expected = new Date(Calendar.getInstance().getTimeInMillis());
        oOrder.setOrderDate(expected);
        Date real = oOrder.getOrderDate();
        assertEquals(expected, real);
    }

    @Test
    void setOrderDate() {
        Order oOrder = new Order();
        Date date = new Date(Calendar.getInstance().getTimeInMillis());
        oOrder.setOrderDate(date);
        Date expected = date;
        Date real = oOrder.getOrderDate();
        assertEquals(expected, real);
    }

    @Test
    void getDescription() {
        Order oOrder = new Order();
        String expected = "No Description.";
        String real = oOrder.getDescription();
        assertEquals(expected, real);
    }

    @Test
    void setDescription() {
        Order oOrder = new Order();
        String expected = "This is a test.";
        oOrder.setDescription(expected);
        String real = oOrder.getDescription();
        assertEquals(expected, real);
    }

    @Test
    void getStatus() {
        Order oOrder = new Order();
        String expected = "ordered";
        String real = oOrder.getStatus();
        assertEquals(expected, real);
    }

    @Test
    void setStatus() {
        Order oOrder = new Order();
        String expected = "This is a test.";
        oOrder.setStatus(expected);
        String real = oOrder.getStatus();
        assertEquals(expected, real);
    }

    @Test
    void getClient() {
        Order oOrder = new Order();
        Client expected = new Client();
        Client real = oOrder.getClient();
        assertEquals(expected, real);
    }

    @Test
    void setClient() {
        Order oOrder = new Order();
        Client expected = new Client();
        oOrder.setClient(expected);
        Client real = oOrder.getClient();
        assertEquals(expected, real);
    }

    @Test
    void getAddress() {
        Order oOrder = new Order();
        Address expected = new Address();
        Address real = oOrder.getAddress();
        assertEquals(expected, real);
    }

    @Test
    void setAddress() {
        Order oOrder = new Order();
        Address expected = new Address();
        oOrder.setAddress(expected);
        Address real = oOrder.getAddress();
        assertEquals(expected, real);
    }

    @Test
    void getPharmacy() {
        Order oOrder = new Order();
        Pharmacy expected = new Pharmacy();
        Pharmacy real = oOrder.getPharmacy();
        assertEquals(expected, real);
    }

    @Test
    void setPharmacy() {
        Order oOrder = new Order();
        Pharmacy expected = new Pharmacy();
        oOrder.setPharmacy(expected);
        Pharmacy real = oOrder.getPharmacy();
        assertEquals(expected, real);
    }

    @Test
    void getProducts() {
        Order oOrder = new Order();
        Map<Product, Integer> expected = new TreeMap<>();
        expected.put(new Product(), 1);
        oOrder.setProducts(expected);
        Map<Product, Integer> real = oOrder.getProducts();
        assertEquals(expected, real);
    }

    @Test
    void setProducts() {
        Order oOrder = new Order();
        Map<Product, Integer> expected = new TreeMap<>();
        expected.put(new Product(), 1);
        oOrder.setProducts(expected);
        Map<Product, Integer> real = oOrder.getProducts();
        assertEquals(expected, real);
    }

    @Test
    void isDelivery() {
        Order oOrder = new Order();
        boolean expected = false;
        boolean real = oOrder.isDelivery();
        assertEquals(expected, real);
    }

    @Test
    void calculateAmount() {
        Order oOrder = new Order();
        Map<Product, Integer> map = new TreeMap<>();
        map.put(new Product("Name", "Description", 10, 10), 2);
        oOrder.setProducts(map);
        Float expected = 20f;
        oOrder.calculateAmount();
        Float real = oOrder.getAmount();
        assertEquals(expected, real);
    }

    @Test
    void calculateTotalWeight() {
        Order oOrder = new Order();
        Map<Product, Integer> map = new TreeMap<>();
        map.put(new Product("Name", "Description", 10, 1), 2);
        oOrder.setProducts(map);
        Float expected = 20f;
        oOrder.calculateAmount();
        Float real = oOrder.getAmount();
        assertEquals(expected, real);
    }

    @Test
    void testEquals() {
        Order oOrder = new Order();
        boolean expected = true;
        boolean real = oOrder.equals(new Order());
        assertEquals(expected, real);
    }

    @Test
    void testHashCode() {
        Order oOrder = new Order();
        int expected = 30;
        int real = oOrder.hashCode();
        assertEquals(expected, real);
    }

    @Test
    void testToString() {
        Order oOrder = new Order();
        String expected = "Order{m_intId=-1, m_fltAmount=-1.0, m_fltTotalWeight=-1.0, m_fltAdditionalFee=-1.0, m_dtOrderDate=";
        String real = oOrder.toString().substring(0, 98);
        assertEquals(expected, real);
    }
}