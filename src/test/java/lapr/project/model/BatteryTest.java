package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BatteryTest {

    private Battery batteryTest1;
    private Battery batteryTest2;
    private Battery batteryTest3;

    public BatteryTest(){
        batteryTest1 = new Battery();
        batteryTest2 = new Battery(12, 12, 12);
        batteryTest3 = new Battery(12,12, 12, 12);
    }

    @Test
    void getId() {
        float expected = 12;
        float real = batteryTest3.getId();
        assertEquals(expected, real);
    }

    @Test
    void setId() {
        int expected = -2;
        batteryTest1.setId(expected);
        int real = batteryTest1.getId();
        assertEquals(expected, real);
    }

    @Test
    void getBatteryCapacity() {
        int expected = 12;
        int real = batteryTest2.getBatteryCapacity();
        assertEquals(expected, real);
    }

    @Test
    void setBatteryCapacity() {
        int expected = -2;
        batteryTest2.setBatteryCapacity(expected);
        int real = batteryTest2.getBatteryCapacity();
        assertEquals(expected, real);
    }

    @Test
    void getBatteryVoltage() {
        double expected = 12;
        double real = batteryTest2.getBatteryVoltage();
        assertEquals(expected, real);
    }

    @Test
    void setBatteryVoltage() {
        double expected = -2;
        batteryTest2.setBatteryVoltage(expected);
        double real = batteryTest2.getBatteryVoltage();
        assertEquals(expected, real);
    }

    @Test
    void getEfficiency() {
        double expected = 12;
        double real = batteryTest2.getEfficiency();
        assertEquals(expected, real);
    }

    @Test
    void setEfficiency() {
        double expected = -2;
        batteryTest2.setEfficiency(expected);
        double real = batteryTest2.getEfficiency();
        assertEquals(expected, real);
    }

    @Test
    void testToString() {
        String expected = "Battery{Id=12, Efficiency=12.0, Battery Capacity=12, Battery Voltage=12.0}";
        String real = batteryTest3.toString();
        assertEquals(expected, real);
    }

    @Test
    void testEquals() {
        Battery oBattery = new Battery();
        boolean real = oBattery.equals(new Battery());
        assertTrue(real);
        real = oBattery.equals(oBattery);
        assertTrue(real);
        real = oBattery.equals(new Scooter());
        assertFalse(real);
        real = oBattery.equals(null);
        assertFalse(real);
        oBattery.setId(-2);
        real = oBattery.equals(new Battery());
        assertFalse(real);
    }

    @Test
    void testHashCode() {
        Battery oBattery = new Battery();
        int expected = 30;
        int real = oBattery.hashCode();
        assertEquals(expected, real);
    }

    @Test
    void toStringRoute() {
        Battery b = new Battery();
        System.out.println(b.toString());
        String expResult = ", battery efficiency->-1.0, battery capacity->-1, battery voltage->-1.0.";
        String result = b.toStringRoute();
        assertEquals(expResult, result);
    }
}