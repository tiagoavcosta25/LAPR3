package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceTest {

    @Test
    void getId() {
        Invoice oInvoice = new Invoice(-1, new Date(Calendar.getInstance().getTimeInMillis()), 100f,new Order());
        Integer expected = -1;
        Integer real = oInvoice.getId();
        assertEquals(expected, real);
    }

    @Test
    void setId() {
        Invoice oInvoice = new Invoice();
        Integer expected = -2;
        oInvoice.setId(expected);
        Integer real = oInvoice.getId();
        assertEquals(expected, real);
    }

    @Test
    void getInvoiceDate() {
        Invoice oInvoice = new Invoice();
        Date expected = new Date(Calendar.getInstance().getTimeInMillis());
        oInvoice.setInvoiceDate(expected);
        Date real = oInvoice.getInvoiceDate();
        assertEquals(expected, real);
    }

    @Test
    void setInvoiceDate() {
        Invoice oInvoice = new Invoice();
        Date date = new Date(Calendar.getInstance().getTimeInMillis());
        oInvoice.setInvoiceDate(date);
        Date expected = date;
        Date real = oInvoice.getInvoiceDate();
        assertEquals(expected, real);
    }

    @Test
    void getTotalPrice() {
        Invoice oInvoice = new Invoice();
        float expected = -1;
        float real = oInvoice.getTotalPrice();
        assertEquals(expected, real);
    }

    @Test
    void setTotalPrice() {
        Invoice oInvoice = new Invoice();
        float expected = -2f;
        oInvoice.setTotalPrice(expected);
        float real = oInvoice.getTotalPrice();
        assertEquals(expected, real);
    }

    @Test
    void getOrder() {
        Invoice oInvoice = new Invoice();
        Order expected = new Order();
        Order real = oInvoice.getOrder();
        assertEquals(expected, real);
    }

    @Test
    void setOrder() {
        Invoice oInvoice = new Invoice();
        Order expected = new Order();
        oInvoice.setOrder(expected);
        Order real = oInvoice.getOrder();
        assertEquals(expected, real);
    }

    @Test
    void testEquals() {
        Invoice oInvoice = new Invoice();
        boolean expected = true;
        boolean real = oInvoice.equals(new Invoice());
        assertEquals(expected, real);
        real = oInvoice.equals(oInvoice);
        assertTrue(real);
        real = oInvoice.equals(new Pharmacy());
        assertFalse(real);
        oInvoice.setId(-2);
        real = oInvoice.equals(new Invoice());
        assertFalse(real);
    }

    @Test
    void testHashCode() {
        Invoice oInvoice = new Invoice();
        int expected = 30;
        int real = oInvoice.hashCode();
        assertEquals(expected, real);
    }

    @Test
    void testToString() {
        Invoice oInvoice = new Invoice();
        String expected = "Invoice{m_intId=-1, m_dtInvoiceDate=";
        String real = oInvoice.toString().substring(0, 36);
        assertEquals(expected, real);
    }
}