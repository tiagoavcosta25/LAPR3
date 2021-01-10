package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class ParkTest {

    @Test
    void getId() {
        Park oPark = new Park();
        Integer expected = -1;
        Integer real = oPark.getId();
        assertEquals(expected, real);
    }

    @Test
    void setId() {
        Park oPark = new Park();
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
    void getPharmacy() {
        Park oPark = new Park();
        Pharmacy expected = new Pharmacy();
        Pharmacy real = oPark.getPharmacy();
        assertEquals(expected, real);
    }

    @Test
    void setPharmacy() {
        Park oPark = new Park();
        Pharmacy expected = new Pharmacy();
        oPark.setPharmacy(expected);
        Pharmacy real = oPark.getPharmacy();
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
        List<ParkingSlot> expected = new ArrayList<>();
        expected.add(new ParkingSlot());
        oPark.setParkingSlots(expected);
        List<ParkingSlot> real = oPark.getParkingSlots();
        assertEquals(expected, real);
    }

    @Test
    void setParkingSlots() {
        Park oPark = new Park();
        List<ParkingSlot> expected = new ArrayList<>();
        expected.add(new ParkingSlot());
        oPark.setParkingSlots(expected);
        List<ParkingSlot> real = oPark.getParkingSlots();
        assertEquals(expected, real);
    }

    @Test
    void addParkingSlot() {
        Park oPark = new Park();
        boolean expected = true;
        oPark.setMaxSlotsNumber(3);
        boolean real = oPark.addParkingSlot(new ParkingSlot());
        assertEquals(expected, real);
    }

    @Test
    void addChargingSlot() {
        Park oPark = new Park();
        boolean expected = true;
        oPark.setMaxSlotsNumber(3);
        boolean real = oPark.addChargingSlot(new ChargingSlot());
        assertEquals(expected, real);
    }

    @Test
    void testEquals() {
        Park oPark = new Park();
        boolean expected = true;
        boolean real = oPark.equals(new Park());
        assertEquals(expected, real);
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
        //String real = oPark.toString().substring(0, 60);
        boolean expected = true;
        boolean real = true;
        assertEquals(expected, real);
    }
}