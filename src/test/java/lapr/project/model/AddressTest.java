package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    private Address a1;
    private Address a2;
    private Address a3;

    public AddressTest() {
        a1 = new Address();
        a2 = new Address(102030.23, 103121.01,10d, "Rua 1", "2esq", "4444-111",
                "Mafamude", "Portugal");
        a3 = new Address(1123112.0, 103121.01, 20d,"Rua 2", "5dir", "4222-131",
                "Ermesinde", "Portugal");
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
        Double expected = 1.3540206217510603E7;
        Double real = a2.distanceTo(a3);
        assertEquals(expected,real);

        Double expected2 = 0d;
        Double real2 = a2.distanceTo(a2);
        assertEquals(expected2,real2);

    }


    @Test
    void testEquals() {
        Address oAddress = new Address(1123112.0, 103121.01,50d, "Rua 1", "2esq", "4444-111",
                "Mafamude", "Portugal");
        boolean real = a3.equals(oAddress);
        assertTrue(real);

        assertEquals(a3, a3);

        Address oAddress1 = new Address(1232132.0,2131451.0,50d,"","","","","");

        boolean real1 = a3.equals(oAddress1);
        assertFalse(real1);

        String c = "";
        assertNotEquals(a3, c);

        Address oAddress2 = null;
        assertNotEquals(a3, oAddress2);

        Address oAddress3 = new Address(1123112.0,1.0,50d, "Rua 1", "2esq", "4444-111",
                "Mafamude", "Portugal");
        assertNotEquals(oAddress,oAddress3);

        Address oAddress4 = new Address(1.0,103121.01,50d, "Rua 1", "2esq", "4444-111",
                "Mafamude", "Portugal");
        assertNotEquals(oAddress,oAddress4);
    }

    @Test
    void testHashCode() {
        Address oAddress = new Address();
        int expected = 113247169;
        int real = oAddress.hashCode();
        assertEquals(expected, real);
    }

    @Test
    void testToString() {
        Address oAddress = new Address();
        String expected = "No Street Name";
        String real = oAddress.toString();
        assertEquals(expected, real);
    }

    @Test
    void getAltitude() {
        Double expected = 10d;
        Double real = a2.getAltitude();
        assertEquals(expected,real);
    }

    @Test
    void setAltitude() {
        Double expected = 100d;
        a2.setAltitude(100d);
        Double real = a2.getAltitude();
        assertEquals(expected,real);
    }
}