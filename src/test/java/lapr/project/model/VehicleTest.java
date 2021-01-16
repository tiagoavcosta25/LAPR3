package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    private Scooter scooterTest;
    private Scooter scooterTest1;
    private Scooter scooterTest2;
    private Scooter scooterTest3;
    private Drone droneTest;
    private Drone droneTest1;
    private Drone droneTest2;
    private Drone droneTest3;

    public VehicleTest(){
        scooterTest = new Scooter(200f, 200f, 20f, "Not Charging",
                32f,100, 32f, new Pharmacy());
        scooterTest1 = new Scooter(-2, 200f, 200f, 20f, "Not Charging",
                32f,100, 32f, new Pharmacy());
        scooterTest2 = new Scooter();
        scooterTest3 = new Scooter(-2, 200f, 200f, 20f, "Not Charging",
                0,-1.0f,-1, -1.0f, new Pharmacy());

        droneTest = new Drone(200f, 200f, 20f, "Not Charging",
                32f,100, 32f, new Pharmacy());
        droneTest1 = new Drone(-2, 200f, 200f, 20f, "Not Charging",
                32f,100, 32f, new Pharmacy());
        droneTest2 = new Drone();
        droneTest3 = new Drone(-2, 200f, 200f, 20f, "Not Charging",
                -2,32f,100, 32f, new Pharmacy());
    }

    @Test
    void getId() {
        Integer expected = 0;
        Integer real = scooterTest.getId();
        assertEquals(expected, real);
    }

    @Test
    void setId() {
        Integer expected = 200;
        scooterTest1.setId(expected);
        Integer real = scooterTest1.getId();
        assertEquals(expected, real);
    }

    @Test
    void hasId() {
        boolean expected = true;
        boolean real = scooterTest1.hasId(scooterTest1.getId());
        assertEquals(expected, real);
        expected = false;
        real = scooterTest.hasId(10);
        assertEquals(expected, real);
    }

    @Test
    void getPharmacyId() {
        Integer expected = 0;
        Integer real = droneTest.getPharmacyId();
        assertEquals(expected, real);
    }

    @Test
    void setPharmacyId() {
        Integer expected = -2;
        droneTest1.setPharmacyId(expected);
        Integer real = droneTest1.getPharmacyId();
        assertEquals(expected, real);
    }

    @Test
    void getPotency() {
        float expected = -1f;
        float real = droneTest2.getPotency();
        assertEquals(expected, real);
    }

    @Test
    void setPotency() {
        float expected = -2;
        scooterTest1.setPotency(expected);
        float real = scooterTest1.getPotency();
        assertEquals(expected, real);
    }

    @Test
    void getWeight() {
        float expected = 200;
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
    void getMaxPayload() {
        float expected = 20;
        float real = droneTest3.getMaxPayload();
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
    void getCharginStatus() {
        String expected = "Not Charging";
        String real = scooterTest2.getCharginStatus();
        assertEquals(expected, real);
    }

    @Test
    void setCharginStatus() {
        String expected = "Charging";
        scooterTest1.setCharginStatus(expected);
        String real = scooterTest1.getCharginStatus();
        assertEquals(expected, real);
    }

    @Test
    void getBattery() {
        Battery expected = new Battery();
        Battery real = scooterTest3.getBattery();
        assertEquals(expected, real);
    }

    @Test
    void setBattery() {
        Battery expected = new Battery();
        scooterTest1.setBattery(expected);
        Battery real = scooterTest1.getBattery();
        assertEquals(expected, real);
    }

    @Test
    void getPharmacy() {
        Pharmacy expected = new Pharmacy();
        Pharmacy real = scooterTest2.getPharmacy();
        assertEquals(expected, real);
    }

    @Test
    void setPharmacy() {
        Pharmacy expected = new Pharmacy();
        scooterTest1.setPharmacy(expected);
        Pharmacy real = scooterTest1.getPharmacy();
        assertEquals(expected, real);
    }

    @Test
    void testToString() {
        String expected = "Vehicle{Id=-2, PharmacyId=0, Potency=200.0, Weight=200.0, MaxPayload=20.0, CharginStatus='Not Charging'," +
                " Battery=Battery{Id=0, BatteryPerc=32.0, BatteryCapacity=100, BatteryVoltage=32.0}, Pharmacy=Pharmacy{m_intId=-1," +
                " m_strName='No name.', m_strEmail='No email.', m_oAddress=Address{m_id=-1, m_latitude=-22.0, m_longitude=-22.0," +
                " m_streetName='No Street Name', m_doorNumber='No Door Number', m_postalCode='No Postal Code', m_locality='No Locality'," +
                " m_country='No Country'}, m_lstParks=[], m_mapStock={}}}";
        String real = scooterTest1.toString();
        assertEquals(expected, real);
    }

    @Test
    void testEquals() {
        Scooter oScooter = new Scooter(-2, 200f, 200f, 20f, "Not Charging",
                32f,100, 32f, new Pharmacy());

        boolean real = scooterTest1.equals(oScooter);
        assertTrue(real);

        assertEquals(scooterTest1, scooterTest1);

        boolean expected = false;
        real = scooterTest1.equals(null);
        assertEquals(expected, real);

        String s = "";
        assertNotEquals(scooterTest1,s);

        Scooter oScooter1 = new Scooter(-3, 200f, 200f, 20f, "Not Charging",
                32f,100, 32f, new Pharmacy());

        assertNotEquals(oScooter, oScooter1);

        real = scooterTest1.equals(oScooter1);
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