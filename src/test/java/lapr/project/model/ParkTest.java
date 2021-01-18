package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParkTest {

    @Test
    void getId() {
        Park oPark = new Park(-1, 1, 2, VehicleType.SCOOTER);
        Integer expected = -1;
        Integer real = oPark.getId();
        assertEquals(expected, real);
    }

    @Test
    void setId() {
        Park oPark = new Park(-1, 1, 2, VehicleType.SCOOTER);
        Integer expected = -2;
        oPark.setId(expected);
        Integer real = oPark.getId();
        assertEquals(expected, real);
    }

    @Test
    void getMaxSlotsNumber() {
        Park oPark = new Park();
        Integer expected = -1;
        Integer real = oPark.getMaxSlotsNumber();
        assertEquals(expected, real);
    }

    @Test
    void setMaxSlotsNumber() {
        Park oPark = new Park();
        Integer expected = -2;
        oPark.setMaxSlotsNumber(expected);
        Integer real = oPark.getMaxSlotsNumber();
        assertEquals(expected, real);
    }

    @Test
    void getChargingSlots() {
        Park oPark = new Park();
        List<ChargingSlot> expected = new ArrayList<>();
        expected.add(new ChargingSlot());
        oPark.setChargingSlots(expected);
        List<ChargingSlot> real = oPark.getChargingSlots();
        assertEquals(expected, real);
    }

    @Test
    void setChargingSlots() {
        Park oPark = new Park();
        List<ChargingSlot> expected = new ArrayList<>();
        expected.add(new ChargingSlot());
        oPark.setChargingSlots(expected);
        List<ChargingSlot> real = oPark.getChargingSlots();
        assertEquals(expected, real);
    }

    @Test
    void getParkingSlots() {
        Park oPark = new Park();
        List<NonChargingSlot> expected = new ArrayList<>();
        expected.add(new NonChargingSlot());
        oPark.setParkingSlots(expected);
        List<NonChargingSlot> real = oPark.getParkingSlots();
        assertEquals(expected, real);
    }

    @Test
    void setParkingSlots() {
        Park oPark = new Park();
        List<NonChargingSlot> expected = new ArrayList<>();
        expected.add(new NonChargingSlot());
        oPark.setParkingSlots(expected);
        List<NonChargingSlot> real = oPark.getParkingSlots();
        assertEquals(expected, real);
    }

    @Test
    void testEquals() {
        Park oPark = new Park();
        boolean expected = true;
        boolean real = oPark.equals(new Park());
        assertEquals(expected, real);
        real = oPark.equals(oPark);
        assertTrue(real);
        real = oPark.equals(new Pharmacy());
        assertFalse(real);
        oPark.setId(-2);
        real = oPark.equals(new Park());
        assertFalse(real);
    }

    @Test
    void testHashCode() {
        Park oPark = new Park();
        int expected = 30;
        int real = oPark.hashCode();
        assertEquals(expected, real);
    }

    @Test
    void testToString() {
        Park oPark = new Park();
        //String expected = "Park{m_intId=-1, m_intMaxSlotsNumber=-1, m_lstChargingSlots=";
        String strReal = oPark.toString();
        boolean expected = true;
        boolean real = true;
        assertEquals(expected, real);
    }
}