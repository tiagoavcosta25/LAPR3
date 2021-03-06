package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NonChargingSlotTest {

    @Test
    void getId() {
        Scooter sc1 = new Scooter();
        NonChargingSlot oNonChargingSlot = new NonChargingSlot(sc1);
        Integer expected = -1;
        Integer real = oNonChargingSlot.getId();
        assertEquals(expected, real);
    }

    @Test
    void setId() {
        NonChargingSlot oNonChargingSlot = new NonChargingSlot();
        Integer expected = -2;
        oNonChargingSlot.setId(expected);
        Integer real = oNonChargingSlot.getId();
        assertEquals(expected, real);
    }

    @Test
    void getVehicle() {
        NonChargingSlot oNonChargingSlot = new NonChargingSlot(-1, new Scooter());
        Scooter expected = new Scooter();
        Scooter real = (Scooter) oNonChargingSlot.getVehicle();
        assertEquals(expected, real);
    }

    @Test
    void setVehicle() {
        NonChargingSlot oNonChargingSlot = new NonChargingSlot(-1, new Scooter());
        Scooter expected = new Scooter();
        oNonChargingSlot.setVehicle(expected);
        Scooter real = (Scooter) oNonChargingSlot.getVehicle();
        assertEquals(expected, real);
    }

    @Test
    void testCompareTo() {
        NonChargingSlot oNonChargingSlot = new NonChargingSlot(1);
        oNonChargingSlot.setId(-2);
        int expected = -1;
        int real = oNonChargingSlot.compareTo(new NonChargingSlot());
        assertEquals(expected, real);
    }

    @Test
    void testEquals() {
        NonChargingSlot oNonChargingSlot = new NonChargingSlot();
        boolean expected = true;
        boolean real = oNonChargingSlot.equals(new NonChargingSlot());
        assertEquals(expected, real);
        real = oNonChargingSlot.equals(oNonChargingSlot);
        assertTrue(real);
        real = oNonChargingSlot.equals(new Pharmacy());
        assertFalse(real);
        oNonChargingSlot.setId(-2);
        real = oNonChargingSlot.equals(new NonChargingSlot());
        assertFalse(real);
        real = oNonChargingSlot.equals(null);
        assertFalse(real);
    }

    @Test
    void testHashCode() {
        NonChargingSlot oNonChargingSlot = new NonChargingSlot();
        int expected = 30;
        int real = oNonChargingSlot.hashCode();
        assertEquals(expected, real);
    }
}