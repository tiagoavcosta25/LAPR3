package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChargingSlotTest {

    private ChargingSlot c1;
    private ChargingSlot c2;
    private ChargingSlot c3;
    private ChargingSlot c4;
    private ChargingSlot cCopy;
    private ChargingSlot cDif;
    private ChargingSlot cDif2;

    public ChargingSlotTest(){
        c1 = new ChargingSlot(1,new Scooter());
        c2 = new ChargingSlot();
        c3 = new ChargingSlot(1);
        c4 = new ChargingSlot();
        cCopy = c1;
        cDif = new ChargingSlot(2,new Scooter());
        cDif2 = new ChargingSlot(2,new Scooter());
    }


    @Test
    void getId() {
        Integer expected4 = -1;
        Integer real4 = c4.getId();
        assertEquals(expected4, real4);
        Integer expected3 = 1;
        Integer real3 = c3.getId();
        assertEquals(expected3, real3);
        Integer expected2 = -1;
        Integer real2 = c2.getId();
        assertEquals(expected2, real2);
        Integer expected = 1;
        Integer real = c1.getId();
        assertEquals(expected, real);
    }

    @Test
    void getVehicle() {
        Vehicle expected4 = null;
        Vehicle real4 = c4.getVehicle();
        assertEquals(expected4, real4);
        Vehicle expected3 = null;
        Vehicle real3 = c3.getVehicle();
        assertEquals(expected3, real3);
        Vehicle expected2 = null;
        Vehicle real2 = c2.getVehicle();
        assertEquals(expected2, real2);
        Vehicle expected = new Scooter();
        Vehicle real = c1.getVehicle();
        assertEquals(expected, real);
    }

    @Test
    void setVehicle() {
        c4.setVehicle(new Scooter());
        c3.setVehicle(new Scooter());
        c2.setVehicle(new Scooter());
        c1.setVehicle(new Scooter());
        Vehicle expected4 = new Scooter();
        Vehicle real4 = c4.getVehicle();
        assertEquals(expected4, real4);
        Vehicle expected3 = new Scooter();
        Vehicle real3 = c3.getVehicle();
        assertEquals(expected3, real3);
        Vehicle expected2 = new Scooter();
        Vehicle real2 = c2.getVehicle();
        assertEquals(expected2, real2);
        Vehicle expected = new Scooter();
        Vehicle real = c1.getVehicle();
        assertEquals(expected, real);
    }

    @Test
    void setId() {
        c1.setId(1);
        c2.setId(1);
        c3.setId(1);
        c4.setId(1);
        Integer expected4 = 1;
        Integer real4 = c4.getId();
        assertEquals(expected4, real4);
        Integer expected3 = 1;
        Integer real3 = c3.getId();
        assertEquals(expected3, real3);
        Integer expected2 = 1;
        Integer real2 = c2.getId();
        assertEquals(expected2, real2);
        Integer expected = 1;
        Integer real = c1.getId();
        assertEquals(expected, real);
    }

    @Test
    void testEquals() {
        Client u = new Client();
        assertEquals(c1, cCopy);
        assertNotEquals(null, c1);
        assertNotEquals(c1, null);
        assertNotEquals(c1, cDif);
        assertNotEquals(cDif, c1);
        assertNotEquals(c1, u);
        assertNotEquals(c1, cDif2);
        assertNotEquals(cDif2, c1);
        ChargingSlot oChargingSlot = new ChargingSlot();
        boolean expected = true;
        boolean real = oChargingSlot.equals(new ChargingSlot());
        assertEquals(expected, real);
    }

    @Test
    void testHashCode() {
        ChargingSlot oChargingSlot = new ChargingSlot();
        int expected = 30;
        int real = oChargingSlot.hashCode();
        assertEquals(expected, real);
    }

    @Test
    void testCompareTo() {
        int expResult = -1;
        int result = c1.compareTo(cDif);
        assertEquals(expResult, result);

        expResult = 1;
        result = cDif.compareTo(c1);
        assertEquals(expResult, result);

        expResult = 0;
        result = c1.compareTo(cCopy);
        assertEquals(expResult, result);
    }

    @Test
    void testToString() {
        String expResult = "ParkingSlot{m_intId=1, m_oVehicle=Vehicle{Id=-1, BatteryPerc=-1.0, Model=VehicleModel{m_intId=-1," +
                " m_strDesignation='No designation', m_dblPotency=-1.0, m_dblWeight=-1.0, m_dblMaxPayload=-1.0, m_oBattery=Battery{Id=-1, " +
                "Efficiency=-1.0, Battery Capacity=-1, Battery Voltage=-1.0}, m_enumVehicleType=DeliveryStatus{m_strDesignation='Not defined'}}, " +
                "Pharmacy=Pharmacy{m_intId=-1, m_strName='No name.', m_strEmail='No email.', m_oAddress=Address{m_dblLatitude=-22.0, m_dblLongitude=-22.0," +
                " m_dblAltitude=-1.7976931348623157E308, m_strStreetName='No Street Name', m_strDoorNumber='No Door Number', m_strPostalCode='No Postal Code', " +
                "m_strLocality='No Locality', m_strCountry='No Country'}, m_lstParks=[], m_mapStock={}}}}";
        String result = c1.toString();
        assertEquals(expResult, result);
    }
}