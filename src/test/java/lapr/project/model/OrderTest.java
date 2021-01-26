package lapr.project.model;

import org.junit.jupiter.api.Test;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Order o1;
    private Order o3;
    private Order o4;
    private Order o5;

    public OrderTest(){
        Map<Product, Integer> map = new TreeMap<>();
        map.put(new Product("", "",19d, 11d), 3);
        o1 = new Order(1, 2.0d, 3.0d, 2.0d, new Date(12 - 12 - 13),
                "testDesc", "testStatus", true, new Client(), new Pharmacy(), map);
        o3 = new Order(1, "", false, new Client(), new Pharmacy(), map);
        o4 = new Order("", true, new Client(), new Pharmacy(), map);
        o5 = new Order();
    }


    @Test
    void getId() {
        Integer expected = 1;
        Integer real = o1.getId();
        assertEquals(expected, real);
        Integer expected3 = 1;
        Integer real3 = o3.getId();
        assertEquals(expected3, real3);
        Integer expected5 = -1;
        Integer real5 = o5.getId();
        assertEquals(expected5, real5);

    }

    @Test
    void getAmount() {
        double expected = 57.0f;
        double real = o4.getAmount();
        assertEquals(expected, real);
    }

    @Test
    void getTotalWeight() {
        double expected = 33.0f;
        double real = o4.getTotalWeight();
        assertEquals(expected, real);
    }


    @Test
    void getAdditionalFee() {
        double expected = 5.0f;
        double real = o4.getAdditionalFee();
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
    void getDescription() {
        String expected = "testDesc";
        String real = o1.getDescription();
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
    void getClient() {
        Order oOrder = new Order();
        Client expected = new Client();
        Client real = oOrder.getClient();
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
    void getProducts() {
        Order oOrder = new Order();
        Map<Product, Integer> expected = new TreeMap<>();
        expected.put(new Product(), 1);
        oOrder.setProducts(expected);
        Map<Product, Integer> real = oOrder.getProducts();
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
    void setDescription() {
        Order oOrder = new Order();
        String expected = "This is a test.";
        oOrder.setDescription(expected);
        String real = oOrder.getDescription();
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
    void setClient() {
        Order oOrder = new Order();
        Client expected = new Client();
        oOrder.setClient(expected);
        Client real = oOrder.getClient();
        assertEquals(expected, real);
    }


    @Test
    void setPharmacy() {
        Client oClient = new Client();
        oClient.setCredits(999);
        Order oOrder = new Order("", false, oClient, new Pharmacy(), new HashMap<>());
        Pharmacy expected = new Pharmacy();
        oOrder.setPharmacy(expected);
        Pharmacy real = oOrder.getPharmacy();
        assertEquals(expected, real);
    }


    @Test
    void setTotalWeight() {
        Order oOrder = new Order();
        double expected = -2f;
        oOrder.setTotalWeight(expected);
        double real = oOrder.getTotalWeight();
        assertEquals(expected, real);
    }

    @Test
    void setAdditionalFee() {
        Order oOrder = new Order();
        double expected = -2f;
        oOrder.setAdditionalFee(expected);
        double real = oOrder.getAdditionalFee();
        assertEquals(expected, real);
    }

    @Test
    void setAmount() {
        Order oOrder = new Order();
        double expected = -2f;
        oOrder.setAmount(expected);
        double real = oOrder.getAmount();
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
    void setId() {
        Order oOrder = new Order();
        Integer expected = -2;
        oOrder.setId(expected);
        Integer real = oOrder.getId();
        assertEquals(expected, real);
    }

    @Test
    void isHomeDelivery() {
        Order oOrder = new Order();
        boolean expected = false;
        boolean real = oOrder.isHomeDelivery();
        assertEquals(expected, real);

        Client c = new Client();
        c.setCredits(1119);
        Order orderF = new Order("Teste", true, c, new Pharmacy(), new HashMap<>());
        real = orderF.isHomeDelivery();
        assertTrue(real);
    }

    @Test
    void setHomeDelivery() {
        Order oOrder = new Order();
        oOrder.setHomeDelivery(true);
        boolean real = oOrder.isHomeDelivery();
        assertTrue(real);
    }

    @Test
    void testEquals() {
        Order oOrder = new Order();
        boolean expected = true;
        boolean real = oOrder.equals(new Order());
        assertEquals(expected, real);
        real = oOrder.equals(oOrder);
        assertTrue(real);
        real = oOrder.equals(new Pharmacy());
        assertFalse(real);
        oOrder.setId(-2);
        real = oOrder.equals(new Order());
        assertFalse(real);
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
        String expected = "Order{m_intId=-1, m_dblAmount=-1.0, m_dblTotalWeight=-1.0, m_dblAdditionalFee=-1.0, m_dtOrderDate=";
        String real = oOrder.toString().substring(0, 98);
        assertEquals(expected, real);
    }
}