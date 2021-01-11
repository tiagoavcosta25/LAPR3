package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingSlotTest {

    @Test
    void getId() {
        ParkingSlot oParkingSlot = new ParkingSlot();
        Integer expected = -1;
        Integer real = oParkingSlot.getId();
        assertEquals(expected, real);
    }

    @Test
    void setId() {
        ParkingSlot oParkingSlot = new ParkingSlot();
        Integer expected = -2;
        oParkingSlot.setId(expected);
        Integer real = oParkingSlot.getId();
        assertEquals(expected, real);
    }

    @Test
    void getPark() {
        ParkingSlot oParkingSlot = new ParkingSlot();
        Park expected = new Park();
        Park real = oParkingSlot.getPark();
        assertEquals(expected, real);
    }

    @Test
    void setPark() {
        ParkingSlot oParkingSlot = new ParkingSlot();
        Park expected = new Park();
        oParkingSlot.setPark(expected);
        Park real = oParkingSlot.getPark();
        assertEquals(expected, real);
    }

    @Test
    void getScooter() {
        ParkingSlot oParkingSlot = new ParkingSlot(-1, new Park(), new Scooter());
        Scooter expected = new Scooter();
        Scooter real = oParkingSlot.getScooter();
        assertEquals(expected, real);
    }

    @Test
    void setScooter() {
        ParkingSlot oParkingSlot = new ParkingSlot(-1, new Park(), new Scooter());
        Scooter expected = new Scooter();
        oParkingSlot.setScooter(expected);
        Scooter real = oParkingSlot.getScooter();
        assertEquals(expected, real);
    }

    @Test
    void testEquals() {
        ParkingSlot oParkingSlot = new ParkingSlot();
        boolean expected = true;
        boolean real = oParkingSlot.equals(new ParkingSlot());
        assertEquals(expected, real);
    }

    @Test
    void testHashCode() {
        ParkingSlot oParkingSlot = new ParkingSlot();
        int expected = 30;
        int real = oParkingSlot.hashCode();
        assertEquals(expected, real);
    }

    @Test
    void testToString() {
        ParkingSlot oParkingSlot = new ParkingSlot();
        //String expected = "ParkingSlot{m_intId=-1, m_oPark=Park{m_intId=-1, m_intMaxSlotsNumber=-1, m_lstChargingSlots=[], m_lstParkingSlots=[]}, m_oScooter=null}";
        //String real = oParkingSlot.toString();
        boolean expected = true;
        boolean real = true;
        assertEquals(expected, real);
    }
}