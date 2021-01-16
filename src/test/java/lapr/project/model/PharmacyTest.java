package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class PharmacyTest {

    private Pharmacy p1;
    private Pharmacy p2;
    private Pharmacy p3;
    private Pharmacy p4;
    private Pharmacy p5;

    public PharmacyTest(){
        Map<Product,Integer> map = new TreeMap<>();
        p1 = new Pharmacy(1,"testName", "email",new Address(),map);
        p2 = new Pharmacy("testName", "email",new Address(),map);
        p3 = new Pharmacy(1,"testName", "email",new Address());
        p4 = new Pharmacy("testName", "email",new Address());
        p5 = new Pharmacy();
    }

    @Test
    void getId() {
        Integer expected = 1;
        Integer real = p1.getId();
        assertEquals(expected, real);
        Integer expected2 = -1;
        Integer real2 = p2.getId();
        assertEquals(expected2, real2);
        Integer expected3 = 1;
        Integer real3 = p3.getId();
        assertEquals(expected3, real3);
        Integer expected4 = -1;
        Integer real4 = p4.getId();
        assertEquals(expected4, real4);
        Integer expected5 = -1;
        Integer real5 = p5.getId();
        assertEquals(expected5, real5);
    }

    @Test
    void setId() {
        Pharmacy oPharmacy = new Pharmacy();
        Integer expected = -2;
        oPharmacy.setId(expected);
        Integer real = oPharmacy.getId();
        assertEquals(expected, real);
    }

    @Test
    void hasId() {
        Pharmacy oPharmacy = new Pharmacy();
        boolean real = oPharmacy.hasId(-1);
        assertTrue(real);
        real = oPharmacy.hasId(-2);
        assertFalse(real);
    }

    @Test
    void getName() {
        Pharmacy oPharmacy = new Pharmacy();
        String expected = "No name.";
        String real = oPharmacy.getName();
        assertEquals(expected, real);
    }

    @Test
    void setName() {
        Pharmacy oPharmacy = new Pharmacy();
        String expected = "This is a test.";
        oPharmacy.setName(expected);
        String real = oPharmacy.getName();
        assertEquals(expected, real);
    }

    @Test
    void getEmail() {
        Pharmacy oPharmacy = new Pharmacy();
        String expected = "No email.";
        String real = oPharmacy.getEmail();
        assertEquals(expected, real);
    }

    @Test
    void setEmail() {
        Pharmacy oPharmacy = new Pharmacy();
        String expected = "This is a test.";
        oPharmacy.setEmail(expected);
        String real = oPharmacy.getEmail();
        assertEquals(expected, real);
    }

    @Test
    void getAddress() {
        Pharmacy oPharmacy = new Pharmacy();
        Address expected = new Address();
        Address real = oPharmacy.getAddress();
        assertEquals(expected, real);
    }

    @Test
    void setAddress() {
        Pharmacy oPharmacy = new Pharmacy();
        Address expected = new Address(1d, 1d, "test", "test", "test", "test", "test");
        oPharmacy.setAddress(expected);
        Address real = oPharmacy.getAddress();
        assertEquals(expected, real);
    }

    @Test
    void getStock() {
        Pharmacy oPharmacy = new Pharmacy();
        Map<Product, Integer> expected = new TreeMap<>();
        expected.put(new Product(), 1);
        oPharmacy.setStock(expected);
        Map<Product, Integer> real = oPharmacy.getStock();
        assertEquals(expected, real);
    }

    @Test
    void setStock() {
        Pharmacy oPharmacy = new Pharmacy();
        Map<Product, Integer> expected = new TreeMap<>();
        expected.put(new Product(), 1);
        oPharmacy.setStock(expected);
        Map<Product, Integer> real = oPharmacy.getStock();
        assertEquals(expected, real);
    }

    @Test
    void testEquals() {
        Pharmacy oPharmacy = new Pharmacy();
        boolean real = oPharmacy.equals(new Pharmacy());
        assertTrue(real);
        real = oPharmacy.equals(oPharmacy);
        assertTrue(real);
        real = oPharmacy.equals(new Order());
        assertFalse(real);
        oPharmacy.setId(-2);
        real = oPharmacy.equals(new Pharmacy());
        assertFalse(real);
    }

    @Test
    void testHashCode() {
        Pharmacy oPharmacy = new Pharmacy();
        int expected = 30;
        int real = oPharmacy.hashCode();
        assertEquals(expected, real);
    }

    @Test
    void testToString() {
        Pharmacy oPharmacy = new Pharmacy();
        String expected = "Pharmacy{m_intId=-1, m_strName='No name.', m_strEmail='No email.', m_oAddress=Address{m_id=-1, m_latitude=-22.0, m_longitude=-22.0, m_streetName='No Street Name', m_doorNumber='No Door Number', m_postalCode='No Postal Code', m_locality='No Locality', m_country='No Country'}, m_lstParks=[], m_mapStock={}}";
        String real = oPharmacy.toString();
        assertEquals(expected, real);
    }
}