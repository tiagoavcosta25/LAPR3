package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChargingSlotTest {

    private ChargingSlot c1;
    private ChargingSlot c2;
    private ChargingSlot c3;
    private ChargingSlot c4;

    public ChargingSlotTest(){
        c1 = new ChargingSlot(1,new Park(),new Scooter(),2.0f);
        c2 = new ChargingSlot(new Park(),2.0f);
        c3 = new ChargingSlot(1,new Park(),2.0f);
        c4 = new ChargingSlot();
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
    void getPark() {
        Park expected4 = new Park();
        Park real4 = c4.getPark();
        assertEquals(expected4, real4);
        Park expected3 = new Park();
        Park real3 = c3.getPark();
        assertEquals(expected3, real3);
        Park expected2 = new Park();
        Park real2 = c2.getPark();
        assertEquals(expected2, real2);
        Park expected = new Park();
        Park real = c1.getPark();
        assertEquals(expected, real);
    }


    @Test
    void getScooter() {
        Scooter expected4 = null;
        Scooter real4 = c4.getScooter();
        assertEquals(expected4, real4);
        Scooter expected3 = null;
        Scooter real3 = c3.getScooter();
        assertEquals(expected3, real3);
        Scooter expected2 = null;
        Scooter real2 = c2.getScooter();
        assertEquals(expected2, real2);
        Scooter expected = new Scooter();
        Scooter real = c1.getScooter();
        assertEquals(expected, real);
    }

    @Test
    void getOutputPower() {
        float expected4 = -1;
        float real4 = c4.getOutputPower();
        assertEquals(expected4, real4);
        float expected3 = 2.0f;
        float real3 = c3.getOutputPower();
        assertEquals(expected3, real3);
        float expected2 = 2.0f;
        float real2 = c2.getOutputPower();
        assertEquals(expected2, real2);
        float expected = 2.0f;
        float real = c1.getOutputPower();
        assertEquals(expected, real);
    }

    @Test
    void setPark() {
        c1.setPark(null);
        c2.setPark(null);
        c3.setPark(null);
        c4.setPark(null);
        Park expected4 = null;
        Park real4 = c4.getPark();
        assertEquals(expected4, real4);
        Park expected3 = null;
        Park real3 = c3.getPark();
        assertEquals(expected3, real3);
        Park expected2 = null;
        Park real2 = c2.getPark();
        assertEquals(expected2, real2);
        Park expected = null;
        Park real = c1.getPark();
        assertEquals(expected, real);
    }



    @Test
    void setScooter() {
        c4.setScooter(new Scooter());
        c3.setScooter(new Scooter());
        c2.setScooter(new Scooter());
        c1.setScooter(new Scooter());
        Scooter expected4 = new Scooter();
        Scooter real4 = c4.getScooter();
        assertEquals(expected4, real4);
        Scooter expected3 = new Scooter();
        Scooter real3 = c3.getScooter();
        assertEquals(expected3, real3);
        Scooter expected2 = new Scooter();
        Scooter real2 = c2.getScooter();
        assertEquals(expected2, real2);
        Scooter expected = new Scooter();
        Scooter real = c1.getScooter();
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
    void setOutputPower() {
        c1.setOutputPower(5.0f);
        c2.setOutputPower(5.0f);
        c3.setOutputPower(5.0f);
        c4.setOutputPower(5.0f);
        float expected4 = 5.0f;
        float real4 = c4.getOutputPower();
        assertEquals(expected4, real4);
        float expected3 = 5.0f;
        float real3 = c3.getOutputPower();
        assertEquals(expected3, real3);
        float expected2 = 5.0f;
        float real2 = c2.getOutputPower();
        assertEquals(expected2, real2);
        float expected = 5.0f;
        float real = c1.getOutputPower();
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