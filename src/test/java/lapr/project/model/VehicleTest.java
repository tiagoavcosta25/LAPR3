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
        scooterTest = new Scooter(new VehicleModel(), new Pharmacy());
        scooterTest1 = new Scooter(-1, new VehicleModel(), new Pharmacy());
        scooterTest2 = new Scooter();
        scooterTest3 = new Scooter(-1, 98, new VehicleModel(), new Pharmacy());

        droneTest = new Drone(new VehicleModel(), new Pharmacy());
        droneTest1 = new Drone(-2, new VehicleModel(), new Pharmacy());
        droneTest2 = new Drone();
        droneTest3 = new Drone(-1, 98, new VehicleModel(), new Pharmacy());
    }

    @Test
    void getId() {
        Integer expected = -1;
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
        String expected = "Vehicle{Id=-1, BatteryPerc=100.0, Model=VehicleModel{m_intId=-1, m_strDesignation='No designation'," +
                " m_dblPotency=-1.0, m_dblWeight=-1.0, m_dblMaxPayload=-1.0, m_oBattery=Battery{Id=-1, Efficiency=-1.0," +
                " Battery Capacity=-1, Battery Voltage=-1.0}, m_enumVehicleType=DeliveryStatus{m_strDesignation='Not defined'}}, " +
                "Pharmacy=Pharmacy{m_intId=-1, m_strName='No name.', m_strEmail='No email.', m_oAddress=Address{m_dblLatitude=-22.0," +
                " m_dblLongitude=-22.0, m_dblAltitude=-1.7976931348623157E308, m_strStreetName='No Street Name', m_strDoorNumber='No Door Number', " +
                "m_strPostalCode='No Postal Code', m_strLocality='No Locality', m_strCountry='No Country'}, m_lstParks=[], m_mapStock={}}}";
        String real = scooterTest1.toString();
        assertEquals(expected, real);
    }

    @Test
    void testEquals() {
        Scooter oScooter = new Scooter(-1, new VehicleModel(), new Pharmacy());

        boolean real = scooterTest1.equals(oScooter);
        assertTrue(real);

        assertEquals(scooterTest1, scooterTest1);

        boolean expected = false;
        real = scooterTest1.equals(null);
        assertEquals(expected, real);

        String s = "";
        assertNotEquals(scooterTest1,s);

        Scooter oScooter1 = new Scooter(-3, new VehicleModel(), new Pharmacy());

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

    @Test
    void setModel() {
        VehicleModel expected = new VehicleModel();
        scooterTest1.setModel(expected);
        VehicleModel real = droneTest3.getModel();
        assertEquals(expected, real);
    }

    @Test
    void setBatteryPerc() {
        double expected = -1;
        scooterTest1.setBatteryPerc(expected);
        double real = droneTest2.getBatteryPerc();
        assertEquals(expected, real);
    }
}