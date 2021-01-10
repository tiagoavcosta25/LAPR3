package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class PharmacyTest {

    @Test
    void getId() {
        Pharmacy oPharmacy = new Pharmacy();
        Integer expected = -1;
        Integer real = oPharmacy.getId();
        assertEquals(expected, real);
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
        boolean expected = true;
        boolean real = oPharmacy.hasId(-1);
        assertEquals(expected, real);
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
    void getPharmacyManager() {
        Pharmacy oPharmacy = new Pharmacy();
        PharmacyManager expected = new PharmacyManager();
        PharmacyManager real = oPharmacy.getPharmacyManager();
        assertEquals(expected, real);
    }

    @Test
    void setPharmacyManager() throws NoSuchAlgorithmException {
        Pharmacy oPharmacy = new Pharmacy();
        PharmacyManager expected = new PharmacyManager(-19, "test@email.pt", "test", 123456789, "test");
        oPharmacy.setPharmacyManager(expected);
        PharmacyManager real = oPharmacy.getPharmacyManager();
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
        boolean expected = true;
        boolean real = oPharmacy.equals(new Pharmacy());
        assertEquals(expected, real);
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
        String expected = "Pharmacy{m_intId=-1, m_strName='No name.', m_oPharmacyManager=";
        String real = oPharmacy.toString().substring(0, 62);
        assertEquals(expected, real);
    }
}