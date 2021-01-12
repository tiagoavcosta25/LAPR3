package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryRunTest {

    private DeliveryRun m_deliveryRun;
    private DeliveryRun m_secondDeliveryRun;
    private DeliveryRun m_emptyDeliveryRun;

    DeliveryRunTest() throws NoSuchAlgorithmException {
        m_deliveryRun = new DeliveryRun(new Courier(1, "name", "e@gmail.com", "123",
                123456789, "PT501234567890987654321234", new Pharmacy()), new ArrayList<>());
        m_secondDeliveryRun = new DeliveryRun(new Courier());
        m_emptyDeliveryRun = new DeliveryRun();
    }

    @Test
    void getId() {
        Integer expected = -1;
        Integer real = m_deliveryRun.getId();
        assertEquals(expected, real);
    }

    @Test
    void setId() {
        Integer expected = 10;
        m_deliveryRun.setId(10);
        Integer real = m_deliveryRun.getId();
        assertEquals(expected,real);
    }

    @Test
    void getCourier() {
        Courier expected = new Courier();
        Courier real = m_secondDeliveryRun.getCourier();
        assertEquals(expected,real);
    }

    @Test
    void setCourier() {
        Courier expected = null;
        m_deliveryRun.setCourier(null);
        Courier real = m_deliveryRun.getCourier();
        assertEquals(expected,real);
    }

    @Test
    void getScooter() {
        Scooter expected = null;
        Scooter real = m_emptyDeliveryRun.getScooter();
        assertEquals(expected,real);
    }

    @Test
    void setScooter() {
        Scooter expected = new Scooter();
        m_emptyDeliveryRun.setScooter(new Scooter());
        Scooter real = m_emptyDeliveryRun.getScooter();
        assertEquals(expected,real);
    }

    @Test
    void getDeliveryList() {
        List<Delivery> expected = new ArrayList<>();
        List<Delivery> real = m_deliveryRun.getDeliveryList();
        assertEquals(expected,real);
    }

    @Test
    void setDeliveryList() {
        List<Delivery> expected = null;
        m_deliveryRun.setDeliveryList(null);
        List<Delivery> real = m_deliveryRun.getDeliveryList();
        assertEquals(expected,real);
    }

    @Test
    void getStatus() {
        DeliveryStatus expected = DeliveryStatus.IDLE;
        DeliveryStatus real = m_deliveryRun.getStatus();
        assertEquals(expected,real);
    }

    @Test
    void setStatus() {
        DeliveryStatus expected = DeliveryStatus.INPROGRESS;
        m_deliveryRun.setStatus(DeliveryStatus.INPROGRESS);
        DeliveryStatus real = m_deliveryRun.getStatus();
        assertEquals(expected,real);
    }

    @Test
    void testEquals() throws NoSuchAlgorithmException {
        DeliveryRun dR = new DeliveryRun(new Courier(1, "name", "e@gmail.com", "123",
                123456789, "PT501234567890987654321234", new Pharmacy()), new ArrayList<>());

        assertEquals(dR,m_deliveryRun);

        assertEquals(m_deliveryRun,m_deliveryRun);

        DeliveryRun dR2 = new DeliveryRun(new Courier(1, "name", "e@gmail.com", "123",
                123456789, "PT501234567890987654321234", new Pharmacy()), new ArrayList<>());
        dR2.setId(2);

        assertNotEquals(dR2,m_deliveryRun);

        String s = "";
        assertNotEquals(s,m_deliveryRun);

        DeliveryRun dR3 = null;
        assertNotEquals(dR3,m_deliveryRun);


    }

    @Test
    void testHashCode() {
        DeliveryRun oDeliveryRun = new DeliveryRun();
        int expected = 30;
        int real = oDeliveryRun.hashCode();
        assertEquals(expected, real);
    }

    @Test
    void testToString() {
        String expected = "DeliveryRun{" +
                "m_intId=" + m_deliveryRun.getId() +
                ", m_oCourier=" + m_deliveryRun.getCourier() +
                ", m_oScooter=" + m_deliveryRun.getScooter() +
                ", m_lstDelivery=" + m_deliveryRun.getDeliveryList() +
                ", m_oStatus=" + m_deliveryRun.getStatus() +
                '}';
        String real = m_deliveryRun.toString();
        assertEquals(expected,real);
    }
}