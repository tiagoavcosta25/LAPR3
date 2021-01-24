package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParkTest {

    @Test
    void getId() {
        Park oPark = new Park(-1, 1, 2d, VehicleType.SCOOTER);
        Integer expected = -1;
        Integer real = oPark.getId();
        assertEquals(expected, real);
    }

    @Test
    void setId() {
        Park oPark = new Park(1, 2d, VehicleType.SCOOTER);
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
    void getVehicleType() {
        Park oPark = new Park(1, 2d, VehicleType.SCOOTER);
        VehicleType expected = VehicleType.SCOOTER;
        VehicleType real = oPark.getVehicleType();
        assertEquals(expected, real);
    }

    @Test
    void setVehicleType() {
        Park oPark = new Park(1, 2d, VehicleType.DRONE);
        VehicleType expected = VehicleType.DRONE;
        oPark.setVehicleType(expected);
        VehicleType real = oPark.getVehicleType();
        assertEquals(expected, real);
    }

    @Test
    void getTotalOutputCurrent() {
        Park oPark = new Park(1, 2d, VehicleType.SCOOTER);
        Double expected = 2d;
        Double real = oPark.getTotalOutputCurrent();
        assertEquals(expected, real);
    }

    @Test
    void setTotalOutputCurrent() {
        Park oPark = new Park(1, 2d, VehicleType.DRONE);
        Double expected = 2d;
        oPark.setTotalOutputCurrent(expected);
        Double real = oPark.getTotalOutputCurrent();
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
        real = oPark.equals(null);
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
        String expected = "Park{m_intId=-1, m_intMaxSlotsNumber=-1}";
        String real = oPark.toString();
        assertEquals(expected, real);
    }
}