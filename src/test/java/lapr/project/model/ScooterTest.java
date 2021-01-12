package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

public class ScooterTest {

    private Scooter scooterTest1;
    private Scooter scooterTest2;
    private Scooter scooterTest3;
    private Scooter scooterTest4;

    public ScooterTest(){
        scooterTest1 = new Scooter();
        scooterTest2 = new Scooter(1, 1, "Not Charging", 1, 1,
                1, 1, new Pharmacy());
        scooterTest3 = new Scooter(2, "Not Charging", 2, 2,
                2, 2, new Pharmacy());
        scooterTest4 = new Scooter(3, 3, "Not Charging", 3, 3,
                3, 3);

    }

    @Test
    void getId() {
        Integer expected = -1;
        Integer real = scooterTest1.getId();
        assertEquals(expected, real);
    }

    @Test
    void setId() {
        Integer expected = -2;
        scooterTest2.setId(expected);
        Integer real = scooterTest2.getId();
        assertEquals(expected, real);
    }

    @Test
    void hasId() {
        boolean expected = true;
        boolean real = scooterTest1.hasId(scooterTest1.getId());
        assertEquals(expected, real);
        expected = false;
        real = scooterTest1.hasId(10);
        assertEquals(expected, real);
    }

    @Test
    void getBatteryPerc() {
        float expected = 2;
        float real = scooterTest3.getBatteryPerc();
        assertEquals(expected, real);
    }

    @Test
    void setBatteryPerc() {
        float expected = -2;
        scooterTest4.setBatteryPerc(expected);
        float real = scooterTest4.getBatteryPerc();
        assertEquals(expected, real);
    }

    @Test
    void getCharginStatus() {
        String expected = "Not Charging";
        String real = scooterTest1.getCharginStatus();
        assertEquals(expected, real);
    }

    @Test
    void setCharginStatus() {
        String expected = "Charging";
        scooterTest2.setCharginStatus(expected);
        String real = scooterTest2.getCharginStatus();
        assertEquals(expected, real);
    }

    @Test
    void getPotency() {
        float expected = 2;
        float real = scooterTest3.getPotency();
        assertEquals(expected, real);
    }

    @Test
    void setPotency() {
        float expected = -2;
        scooterTest4.setPotency(expected);
        float real = scooterTest4.getPotency();
        assertEquals(expected, real);
    }

    @Test
    void getWeight() {
        float expected = -1;
        float real = scooterTest1.getWeight();
        assertEquals(expected, real);
    }

    @Test
    void setWeight() {
        float expected = -2;
        scooterTest2.setWeight(expected);
        float real = scooterTest2.getWeight();
        assertEquals(expected, real);
    }

    @Test
    void getBatteryCapacity() {
        int expected = 2;
        int real = scooterTest3.getBatteryCapacity();
        assertEquals(expected, real);
    }

    @Test
    void setBatteryCapacity() {
        int expected = -2;
        scooterTest4.setBatteryCapacity(expected);
        int real = scooterTest4.getBatteryCapacity();
        assertEquals(expected, real);
    }

    @Test
    void getMaxPayload() {
        float expected = -1;
        float real = scooterTest1.getMaxPayload();
        assertEquals(expected, real);
    }

    @Test
    void setMaxPayload() {
        float expected = -2;
        scooterTest2.setMaxPayload(expected);
        float real = scooterTest2.getMaxPayload();
        assertEquals(expected, real);
    }

    @Test
    void getPharmacy() {
        Pharmacy expected = new Pharmacy();
        Pharmacy real = scooterTest3.getPharmacy();
        assertEquals(expected, real);
    }

    @Test
    void setPharmacy() {
        Pharmacy expected = new Pharmacy();
        scooterTest4.setPharmacy(expected);
        Pharmacy real = scooterTest4.getPharmacy();
        assertEquals(expected, real);
    }

    @Test
    void testToString() {
        String expected = "Scooter{Id = -1, Battery Perc = -1.0, Chargin Status = Not Charging, Potency = -1.0, " +
                "Weight = -1.0, Battery Capacity = -1, Max Payload = -1.0, Pharmacy = Pharmacy{m_intId=-1, " +
                "m_strName='No name.', m_oPharmacyManager=User{m_intId=null, m_strEmail='No Email Registered', " +
                "m_strPassword='No Password Registered', m_intNif=0, m_strName='No Name'}, m_oAddress=Address{m_id=-1," +
                " m_latitude=-22.0, m_longitude=-22.0, m_streetName='No Street Name', m_doorNumber='No Door Number'," +
                " m_postalCode='No Postal Code', m_locality='No Locality', m_country='No Country'}, m_mapStock={}}}";
        String real = scooterTest1.toString();
        assertEquals(expected, real);
    }

    @Test
    void testEquals() {
        Scooter oScooter2 = new Scooter(1, 1, "Not Charging", 1, 1,
                1, 1, new Pharmacy());

        boolean real = scooterTest2.equals(oScooter2);
        assertTrue(real);

        assertEquals(scooterTest2, scooterTest2);

        boolean expected = false;
        real = scooterTest2.equals(null);
        assertEquals(expected, real);

        String s = "";
        assertNotEquals(scooterTest2,s);
        
        Scooter oScooter3 = new Scooter(3, "Not Charging", 2, 2,
                2, 2, new Pharmacy());
        Scooter oScooter4 = new Scooter(3, "Not Charging", 2, 2,
                2, 2, new Pharmacy());
        Scooter oScooter5 = new Scooter(4, "Not Charging", 2, 2,
                2, 2, new Pharmacy());
        Scooter oScooter6 = new Scooter(3, "Charging", 2, 2,
                2, 2, new Pharmacy());
        Scooter oScooter7 = new Scooter(3, "Not Charging", 4, 2,
                2, 2, new Pharmacy());
        Scooter oScooter8 = new Scooter(3, "Not Charging", 2, 4,
                2, 2, new Pharmacy());
        Scooter oScooter9 = new Scooter(3, "Not Charging", 2, 2,
                4, 2, new Pharmacy());
        Scooter oScooter10 = new Scooter(3, "Not Charging", 2, 2,
                2, 4, new Pharmacy());

        assertNotEquals(oScooter3, scooterTest2);

        real = scooterTest3.equals(oScooter3);
        assertFalse(real);

        real = oScooter4.equals(oScooter3);
        assertTrue(real);

        real = oScooter5.equals(oScooter3);
        assertFalse(real);

        real = oScooter6.equals(oScooter3);
        assertFalse(real);

        real = oScooter7.equals(oScooter3);
        assertFalse(real);

        real = oScooter8.equals(oScooter3);
        assertFalse(real);

        real = oScooter9.equals(oScooter3);
        assertFalse(real);

        real = oScooter10.equals(oScooter3);
        assertFalse(real);

    }

    @Test
    void testHashCode() {
        Scooter oScooter = new Scooter();
        int expected = 30;
        int real = oScooter.hashCode();
        assertEquals(expected, real);
    }
}
