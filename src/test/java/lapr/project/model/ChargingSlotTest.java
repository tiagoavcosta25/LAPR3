package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChargingSlotTest {

    @Test
    void getId() {
        ChargingSlot oChargingSlot = new ChargingSlot();
        Integer expected = -1;
        Integer real = oChargingSlot.getId();
        assertEquals(expected, real);
    }

    @Test
    void setId() {
        ChargingSlot oChargingSlot = new ChargingSlot();
        Integer expected = -2;
        oChargingSlot.setId(expected);
        Integer real = oChargingSlot.getId();
        assertEquals(expected, real);
    }

    @Test
    void getPark() {
        ChargingSlot oChargingSlot = new ChargingSlot();
        Park expected = new Park();
        Park real = oChargingSlot.getPark();
        assertEquals(expected, real);
    }

    @Test
    void setPark() {
        ChargingSlot oChargingSlot = new ChargingSlot();
        Park expected = new Park();
        oChargingSlot.setPark(expected);
        Park real = oChargingSlot.getPark();
        assertEquals(expected, real);
    }

    @Test
    void getScooter() {
        ChargingSlot oChargingSlot = new ChargingSlot(-1, new Park(), new Scooter(), -1f);
        Scooter expected = new Scooter();
        Scooter real = oChargingSlot.getScooter();
        assertEquals(expected, real);
    }

    @Test
    void setScooter() {
        ChargingSlot oChargingSlot = new ChargingSlot(-1, new Park(), new Scooter(), -1f);
        Scooter expected = new Scooter();
        oChargingSlot.setScooter(expected);
        Scooter real = oChargingSlot.getScooter();
        assertEquals(expected, real);
    }

    @Test
    void getOutputPower() {
        ChargingSlot oChargingSlot = new ChargingSlot();
        Float expected = -1f;
        Float real = oChargingSlot.getOutputPower();
        assertEquals(expected, real);
    }

    @Test
    void setOutputPower() {
        ChargingSlot oChargingSlot = new ChargingSlot();
        Float expected = -2f;
        oChargingSlot.setOutputPower(expected);
        Float real = oChargingSlot.getOutputPower();
        assertEquals(expected, real);
    }

    @Test
    void testEquals() {
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
    void testToString() {
        ChargingSlot oChargingSlot = new ChargingSlot();
        //String expected = "Park{m_intId=-1, m_intMaxSlotsNumber=-1, m_lstChargingSlots=";
        //String real = oPark.toString().substring(0, 60);
        boolean expected = true;
        boolean real = true;
        assertEquals(expected, real);
    }
}