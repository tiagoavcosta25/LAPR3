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
        Integer real = a3.getId();
        assertEquals(expected, real);
    }

    @Test
    void getM_latitude() {
        Double expected = 102030.23;
        Double real = a2.getLatitude();
        assertEquals(expected, real);
    }

    @Test
    void getM_longitude() {
        Double expected = 103121.01;
        Double real = a2.getLongitude();
        assertEquals(expected, real);
    }

    @Test
    void getM_streetName() {
        String expected = "No Street Name";
        String real = a1.getStreetName();
        assertEquals(expected, real);
    }

    @Test
    void getM_doorNumber() {
        String expected = "No Door Number";
        String real = a1.getDoorNumber();
        assertEquals(expected,real);
    }

    @Test
    void getM_postalCode() {
        String expected = "4444-111";
        String real = a2.getPostalCode();
        assertEquals(expected,real);
    }

    @Test
    void getM_locality() {
        String expected = "Ermesinde";
        String real = a3.getLocality();
        assertEquals(expected,real);
    }

    @Test
    void getM_country() {
        String expected = "Portugal";
        String real = a3.getCountry();
        assertEquals(expected,real);
    }

    @Test
    void setM_id() {
        a1.setId(2);
        Integer expected = 2;
        Integer real = a1.getId();
        assertEquals(expected,real);
    }

    @Test
    void setM_latitude() {
        a1.setLatitude(23232d);
        Double expected = 23232d;
        Double real = a1.getLatitude();
        assertEquals(expected,real);

    }

    @Test
    void setM_longitude() {
        a1.setLongitude(23232d);
        Double expected = 23232d;
        Double real = a1.getLongitude();
        assertEquals(expected,real);
    }

    @Test
    void setM_streetName() {
        a1.setStreetName("Test");
        String expected = "Test";
        String real = a1.getStreetName();
        assertEquals(expected,real);
    }

    @Test
    void setM_doorNumber() {
        a1.setDoorNumber("1esq");
        String expected = "1esq";
        String real = a1.getDoorNumber();
        assertEquals(expected,real);
    }

    @Test
    void setM_postalCode() {
        a1.setPostalCode("4321-321");
        String expected = "4321-321";
        String real = a1.getPostalCode();
        assertEquals(expected,real);
    }

    @Test
    void setM_locality() {
        a1.setLocality("Localidade");
        String expected = "Localidade";
        String real = a1.getLocality();
        assertEquals(expected,real);
    }

    @Test
    void setM_country() {
        a1.setCountry("Country");
        String expected = "Country";
        String real = a1.getCountry();
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