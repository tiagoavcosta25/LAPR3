package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {
    Address a1;
    Address a2;
    Address a3;

    public AddressTest() {
        a1 = new Address();
        a2 = new Address(102030.23, 103121.01, "Rua 1", "2esq", "4444-111",
                "Mafamude", "Portugal");
        a3 = new Address(10, 423232.77, 523236.53, "Rua 2", "5dir", "4222-131",
                "Ermesinde", "Portugal");
    }

    @Test
    void getM_id() {
        Integer expected = 10;
        Integer real = a3.getM_id();
        assertEquals(expected, real);
    }

    @Test
    void getM_latitude() {
        Double expected = 102030.23;
        Double real = a2.getM_latitude();
        assertEquals(expected, real);
    }

    @Test
    void getM_longitude() {
        Double expected = 103121.01;
        Double real = a2.getM_longitude();
        assertEquals(expected, real);
    }

    @Test
    void getM_streetName() {
        String expected = "No Street Name";
        String real = a1.getM_streetName();
        assertEquals(expected, real);
    }

    @Test
    void getM_doorNumber() {
        String expected = "No Door Number";
        String real = a1.getM_doorNumber();
        assertEquals(expected,real);
    }

    @Test
    void getM_postalCode() {
        String expected = "4444-111";
        String real = a2.getM_postalCode();
        assertEquals(expected,real);
    }

    @Test
    void getM_locality() {
        String expected = "Ermesinde";
        String real = a3.getM_locality();
        assertEquals(expected,real);
    }

    @Test
    void getM_country() {
        String expected = "Portugal";
        String real = a3.getM_country();
        assertEquals(expected,real);
    }

    @Test
    void setM_id() {
        a1.setM_id(2);
        Integer expected = 2;
        Integer real = a1.getM_id();
        assertEquals(expected,real);
    }

    @Test
    void setM_latitude() {
        a1.setM_latitude(23232d);
        Double expected = 23232d;
        Double real = a1.getM_latitude();
        assertEquals(expected,real);

    }

    @Test
    void setM_longitude() {
        a1.setM_longitude(23232d);
        Double expected = 23232d;
        Double real = a1.getM_longitude();
        assertEquals(expected,real);
    }

    @Test
    void setM_streetName() {
        a1.setM_streetName("Test");
        String expected = "Test";
        String real = a1.getM_streetName();
        assertEquals(expected,real);
    }

    @Test
    void setM_doorNumber() {
        a1.setM_doorNumber("1esq");
        String expected = "1esq";
        String real = a1.getM_doorNumber();
        assertEquals(expected,real);
    }

    @Test
    void setM_postalCode() {
        a1.setM_postalCode("4321-321");
        String expected = "4321-321";
        String real = a1.getM_postalCode();
        assertEquals(expected,real);
    }

    @Test
    void setM_locality() {
        a1.setM_locality("Localidade");
        String expected = "Localidade";
        String real = a1.getM_locality();
        assertEquals(expected,real);
    }

    @Test
    void setM_country() {
        a1.setM_country("Country");
        String expected = "Country";
        String real = a1.getM_country();
        assertEquals(expected,real);
    }

    @Test
    void distanceTo() {
        Double expected = 5765561.672247104;
        Double real = a1.distanceTo(a2);
        assertEquals(expected,real);

        Double expected2 = 0d;
        Double real2 = a1.distanceTo(a1);
        assertEquals(expected2,real2);

    }


}