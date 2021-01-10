package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ScooterTest {

    private Scooter scooterTest1;
    private Scooter scooterTest2;
    private Scooter scooterTest3;
    private Scooter scooterTest4;

    public ScooterTest(){
        scooterTest1 = new Scooter();
        scooterTest2 = new Scooter(1, 1, "Not Charging", 1, 1,
                1, 1, new Pharmacy());
        scooterTest3 = new Scooter(2, "Not Charging", 2, 2,
                2, 2, new Pharmacy());
        scooterTest4 = new Scooter(3, 3, "Not Charging", 3, 3,
                3, 3);

    }

    @Test
    void getId() {
        Integer expected = -1;
        Integer real = scooterTest1.getId();
        assertEquals(expected, real);
        expected = 1;
        real = scooterTest2.getId();
        assertEquals(expected, real);
        expected = 3;
        real = scooterTest4.getId();
        assertEquals(expected, real);
    }

    @Test
    void setId() {
        Integer expected = -2;
        scooterTest1.setId(expected);
        Integer real = scooterTest1.getId();
        assertEquals(expected, real);
        scooterTest2.setId(expected);
        real = scooterTest2.getId();
        assertEquals(expected, real);
        scooterTest4.setId(expected);
        real = scooterTest4.getId();
        assertEquals(expected, real);
    }

    @Test
    void hasId() {
        boolean expected = true;
        boolean real = scooterTest1.hasId(scooterTest1.getId());
        assertEquals(expected, real);
        real = scooterTest1.hasId(scooterTest1.getId());
        assertEquals(expected, real);
        real = scooterTest2.hasId(scooterTest2.getId());
        assertEquals(expected, real);
        real = scooterTest3.hasId(scooterTest3.getId());
        assertEquals(expected, real);
        real = scooterTest4.hasId(scooterTest4.getId());
        assertEquals(expected, real);
    }

    @Test
    void getBatteryPerc() {
        float expected = -1;
        float real = scooterTest1.getBatteryPerc();
        assertEquals(expected, real);
        expected = 1;
        real = scooterTest2.getBatteryPerc();
        assertEquals(expected, real);
        expected = 2;
        real = scooterTest3.getBatteryPerc();
        assertEquals(expected, real);
        expected = 3;
        real = scooterTest4.getBatteryPerc();
        assertEquals(expected, real);
    }

    @Test
    void setBatteryPerc() {
        float expected = -2;
        scooterTest1.setBatteryPerc(expected);
        float real = scooterTest1.getBatteryPerc();
        assertEquals(expected, real);
        scooterTest2.setBatteryPerc(expected);
        real = scooterTest2.getBatteryPerc();
        assertEquals(expected, real);
        scooterTest3.setBatteryPerc(expected);
        real = scooterTest3.getBatteryPerc();
        assertEquals(expected, real);
        scooterTest4.setBatteryPerc(expected);
        real = scooterTest4.getBatteryPerc();
        assertEquals(expected, real);
    }

    @Test
    void getCharginStatus() {
        String expected = "Not Charging";
        String real = scooterTest1.getCharginStatus();
        assertEquals(expected, real);
        real = scooterTest2.getCharginStatus();
        assertEquals(expected, real);
        real = scooterTest3.getCharginStatus();
        assertEquals(expected, real);
        real = scooterTest4.getCharginStatus();
        assertEquals(expected, real);
    }

    @Test
    void setCharginStatus() {
        String expected = "Charging";
        scooterTest1.setCharginStatus(expected);
        String real = scooterTest1.getCharginStatus();
        assertEquals(expected, real);
        scooterTest2.setCharginStatus(expected);
        real = scooterTest2.getCharginStatus();
        assertEquals(expected, real);
        scooterTest3.setCharginStatus(expected);
        real = scooterTest3.getCharginStatus();
        assertEquals(expected, real);
        scooterTest4.setCharginStatus(expected);
        real = scooterTest4.getCharginStatus();
        assertEquals(expected, real);
    }

    @Test
    void getPotency() {
        float expected = -1;
        float real = scooterTest1.getPotency();
        assertEquals(expected, real);
        expected = 1;
        real = scooterTest2.getPotency();
        assertEquals(expected, real);
        expected = 2;
        real = scooterTest3.getPotency();
        assertEquals(expected, real);
        expected = 3;
        real = scooterTest4.getPotency();
        assertEquals(expected, real);
    }

    @Test
    void setPotency() {
        float expected = -2;
        scooterTest1.setPotency(expected);
        float real = scooterTest1.getPotency();
        assertEquals(expected, real);
        scooterTest2.setPotency(expected);
        real = scooterTest2.getPotency();
        assertEquals(expected, real);
        scooterTest3.setPotency(expected);
        real = scooterTest3.getPotency();
        assertEquals(expected, real);
        scooterTest4.setPotency(expected);
        real = scooterTest4.getPotency();
        assertEquals(expected, real);
    }

    @Test
    void getWeight() {
        float expected = -1;
        float real = scooterTest1.getWeight();
        assertEquals(expected, real);
        expected = 1;
        real = scooterTest2.getWeight();
        assertEquals(expected, real);
        expected = 2;
        real = scooterTest3.getWeight();
        assertEquals(expected, real);
        expected = 3;
        real = scooterTest4.getWeight();
        assertEquals(expected, real);
    }

    @Test
    void setWeight() {
        float expected = -2;
        scooterTest1.setWeight(expected);
        float real = scooterTest1.getWeight();
        assertEquals(expected, real);
        scooterTest2.setWeight(expected);
        real = scooterTest2.getWeight();
        assertEquals(expected, real);
        scooterTest3.setWeight(expected);
        real = scooterTest3.getWeight();
        assertEquals(expected, real);
        scooterTest4.setWeight(expected);
        real = scooterTest4.getWeight();
        assertEquals(expected, real);
    }

    @Test
    void getBatteryCapacity() {
        int expected = -1;
        int real = scooterTest1.getBatteryCapacity();
        assertEquals(expected, real);
        expected = 1;
        real = scooterTest2.getBatteryCapacity();
        assertEquals(expected, real);
        expected = 2;
        real = scooterTest3.getBatteryCapacity();
        assertEquals(expected, real);
        expected = 3;
        real = scooterTest4.getBatteryCapacity();
        assertEquals(expected, real);
    }

    @Test
    void setBatteryCapacity() {
        int expected = -2;
        scooterTest1.setBatteryCapacity(expected);
        int real = scooterTest1.getBatteryCapacity();
        assertEquals(expected, real);
        scooterTest2.setBatteryCapacity(expected);
        real = scooterTest2.getBatteryCapacity();
        assertEquals(expected, real);
        scooterTest3.setBatteryCapacity(expected);
        real = scooterTest3.getBatteryCapacity();
        assertEquals(expected, real);
        scooterTest4.setBatteryCapacity(expected);
        real = scooterTest4.getBatteryCapacity();
        assertEquals(expected, real);
    }

    @Test
    void getMaxPayload() {
        float expected = -1;
        float real = scooterTest1.getMaxPayload();
        assertEquals(expected, real);
        expected = 1;
        real = scooterTest2.getMaxPayload();
        assertEquals(expected, real);
        expected = 2;
        real = scooterTest3.getMaxPayload();
        assertEquals(expected, real);
        expected = 3;
        real = scooterTest4.getMaxPayload();
        assertEquals(expected, real);
    }

    @Test
    void setMaxPayload() {
        float expected = -2;
        scooterTest1.setMaxPayload(expected);
        float real = scooterTest1.getMaxPayload();
        assertEquals(expected, real);
        scooterTest2.setMaxPayload(expected);
        real = scooterTest2.getMaxPayload();
        assertEquals(expected, real);
        scooterTest3.setMaxPayload(expected);
        real = scooterTest3.getMaxPayload();
        assertEquals(expected, real);
        scooterTest4.setMaxPayload(expected);
        real = scooterTest4.getMaxPayload();
        assertEquals(expected, real);
    }

    @Test
    void getPharmacy() {
        Pharmacy expected = new Pharmacy();
        Pharmacy real = scooterTest2.getPharmacy();
        assertEquals(expected, real);
        real = scooterTest3.getPharmacy();
        assertEquals(expected, real);
    }

    @Test
    void setPharmacy() {
        Pharmacy expected = new Pharmacy();
        scooterTest3.setPharmacy(expected);
        Pharmacy real = scooterTest3.getPharmacy();
        assertEquals(expected, real);
        scooterTest4.setPharmacy(expected);
        real = scooterTest4.getPharmacy();
        assertEquals(expected, real);
    }

    @Test
    void testToString() {
        String expected = "Scooter{Id = -1, Battery Perc = -1.0, Chargin Status = Not Charging, Potency = -1.0, " +
                "Weight = -1.0, Battery Capacity = -1, Max Payload = -1.0, Pharmacy = Pharmacy{m_intId=-1, " +
                "m_strName='No name.'";
        String real = scooterTest1.toString().substring(0,195);
        assertEquals(expected, real);
        expected = "Scooter{Id = 1, Battery Perc = 1.0, Chargin Status = Not Charging, Potency = 1.0, Weight = 1.0, " +
                "Battery Capacity = 1, Max Payload = 1.0, Pharmacy = Pharmacy{m_intId=-1, m_strName='No name.'";
        real = scooterTest2.toString().substring(0,189);
        assertEquals(expected, real);
        expected = "Scooter{Id = 0, Battery Perc = 2.0, Chargin Status = Not Charging, Potency = 2.0, Weight = 2.0, " +
                "Battery Capacity = 2, Max Payload = 2.0, Pharmacy = Pharmacy{m_intId=-1, m_strName='No name.'";
        real = scooterTest3.toString().substring(0,189);
        assertEquals(expected, real);
        expected = "Scooter{Id = 3, Battery Perc = 3.0, Chargin Status = Not Charging, Potency = 3.0, Weight = 3.0, " +
                "Battery Capacity = 3, Max Payload = 3.0, Pharmacy = null}";
        real = scooterTest4.toString();
        assertEquals(expected, real);
    }

    @Test
    void testEquals() {
        Scooter oScooter = new Scooter();
        boolean expected = true;
        boolean real = oScooter.equals(new Scooter());
        assertEquals(expected, real);
    }

    @Test
    void testHashCode() {
        Scooter oScooter = new Scooter();
        int expected = 30;
        int real = oScooter.hashCode();
        assertEquals(expected, real);
    }
}
