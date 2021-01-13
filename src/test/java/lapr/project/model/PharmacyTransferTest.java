package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class PharmacyTransferTest {

    @Test
    void getId() {
        Integer expected = -1;
        PharmacyTransfer pt = new PharmacyTransfer();
        Integer real = pt.getId();
        assertEquals(expected, real);
    }

    @Test
    void setId() {
        PharmacyTransfer oPharmacyTransfer = new PharmacyTransfer();
        oPharmacyTransfer.setId(1);
        Integer expected = 1;
        Integer real = oPharmacyTransfer.getId();
        assertEquals(expected, real);
    }

    @Test
    void getOrder() {
        Order expected = new Order();
        PharmacyTransfer pt = new PharmacyTransfer(expected, new Product(), 1, new Pharmacy());
        Order real = pt.getOrder();
        assertEquals(expected, real);
    }

    @Test
    void setOrder() {
        PharmacyTransfer oPharmacyTransfer = new PharmacyTransfer();
        Order expected = new Order();
        oPharmacyTransfer.setOrder(expected);
        Order real = oPharmacyTransfer.getOrder();
        assertEquals(expected, real);
    }

    @Test
    void getProduct() {
        Product expected = new Product();
        PharmacyTransfer pt = new PharmacyTransfer(-1, new Order(), new Product(), 1, new Pharmacy());
        Product real = pt.getProduct();
        assertEquals(expected, real);
    }

    @Test
    void setProduct() {
        PharmacyTransfer oPharmacyTransfer = new PharmacyTransfer();
        Product expected = new Product();
        oPharmacyTransfer.setProduct(new Product());
        Product real = oPharmacyTransfer.getProduct();
        assertEquals(expected, real);
    }

    @Test
    void getQuantity() {
        Integer expected = 1;
        PharmacyTransfer pt = new PharmacyTransfer(new Order(), new Product(), 1, new Pharmacy());
        Integer real = pt.getQuantity();
        assertEquals(expected, real);
    }

    @Test
    void setQuantity() {
        PharmacyTransfer oPharmacyTransfer = new PharmacyTransfer();
        Integer expected = 1;
        oPharmacyTransfer.setQuantity(1);
        Integer real = oPharmacyTransfer.getQuantity();
        assertEquals(expected, real);
    }

    @Test
    void getNearbyPharmacy() {
        Pharmacy expected = new Pharmacy();
        PharmacyTransfer pt = new PharmacyTransfer(new Order(), new Product(), 1, expected);
        Pharmacy real = pt.getNearbyPharmacy();
        assertEquals(expected, real);
    }

    @Test
    void setNearbyPharmacy() {
        PharmacyTransfer oPharmacyTransfer = new PharmacyTransfer();
        Pharmacy expected = new Pharmacy();
        oPharmacyTransfer.setNearbyPharmacy(expected);
        Pharmacy real = oPharmacyTransfer.getNearbyPharmacy();
        assertEquals(expected, real);
    }

    @Test
    void testEquals() {
        PharmacyTransfer oPharmacyTransfer = new PharmacyTransfer();
        boolean expected = true;
        boolean real = oPharmacyTransfer.equals(new PharmacyTransfer());
        assertEquals(expected, real);
        real = oPharmacyTransfer.equals(oPharmacyTransfer);
        assertTrue(real);
        real = oPharmacyTransfer.equals(new Pharmacy());
        assertFalse(real);
        oPharmacyTransfer.setId(-2);
        real = oPharmacyTransfer.equals(new Order());
        assertFalse(real);
    }

    @Test
    void testHashCode() {
        PharmacyTransfer oPharmacyTransfer = new PharmacyTransfer();
        int expected = 30;
        int real = oPharmacyTransfer.hashCode();
        assertEquals(expected, real);
    }

    @Test
    void testToString() {
        PharmacyTransfer oPharmacyTransfer = new PharmacyTransfer();
        String expected = "PharmacyTransfer{m_intId=-1, m_oOrder=Order{m_intId=-1, m_fltAmount=-1.0, m_fltTotalWeight=-1.0," +
                " m_fltAdditionalFee=-1.0, m_dtOrderDate=2021-01-13, m_strDescription='No Description.', m_strStatus='ordered'," +
                " m_oClient=Client{m_credits=0, m_address=Address{m_id=-1, m_latitude=-22.0, m_longitude=-22.0, m_streetName='No Street Name'," +
                " m_doorNumber='No Door Number', m_postalCode='No Postal Code', m_locality='No Locality', m_country='No Country'}, " +
                "m_creditCard=CreditCard{m_creditCardNr=-1, m_validityDate=";
        String real = oPharmacyTransfer.toString().substring(0, 503);
        assertEquals(expected, real);
    }
}