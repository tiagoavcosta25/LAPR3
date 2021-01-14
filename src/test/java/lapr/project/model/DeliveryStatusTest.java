package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryStatusTest {

    private DeliveryStatus m_deliveryStatus;
    private DeliveryStatus m_emptyDeliveryStatus;

    DeliveryStatusTest() {
        m_deliveryStatus = DeliveryStatus.INPROGRESS;
        m_emptyDeliveryStatus = DeliveryStatus.IDLE;
    }

    @Test
    void getDesignation() {
        String expected = "In Progress";
        String real = m_deliveryStatus.getDesignation();
        assertEquals(expected,real);
    }

    @Test
    void testEquals() {
        DeliveryStatus d = DeliveryStatus.INPROGRESS;
        assertEquals(d,m_deliveryStatus);

        assertEquals(m_deliveryStatus,m_deliveryStatus);

        d = DeliveryStatus.IDLE;
        assertNotEquals(d,m_deliveryStatus);

        String s = "";
        assertNotEquals(s,m_deliveryStatus);

        d = null;
        assertNotEquals(d,m_deliveryStatus);
    }

    @Test
    void testToString() {
        String expected = "DeliveryStatus{" +
                "m_strDesignation='" + m_deliveryStatus.getDesignation() + '\'' +
                '}';
        String real = m_deliveryStatus.toString();
        assertEquals(expected,real);
    }
}