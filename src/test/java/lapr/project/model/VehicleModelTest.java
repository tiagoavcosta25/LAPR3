package lapr.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleModelTest {

    private VehicleModel model;
    private VehicleModel modelDiff;
    private VehicleModel modelDiff3;

    @BeforeEach
    void setUp() {
        model = new VehicleModel(2,"Designacao", 2, 2, 2,
                new Battery(), VehicleType.SCOOTER);
        modelDiff = new VehicleModel(2,"DesignacaoDif", 2, 2, 2,
                new Battery(), VehicleType.SCOOTER);
        modelDiff3 = new VehicleModel(3,"DesignacaoDif", 2, 2, 2,
                new Battery(), VehicleType.SCOOTER);
    }

    @Test
    void getM_intId() {
        int expResult = 1;
        int result = model.getId();
        assertNotEquals(expResult, result);

        expResult = 2;
        assertEquals(expResult, result);
    }

    @Test
    void setM_intId() {
        int expResult = 2;
        int result = model.getId();
        assertEquals(expResult, result);

        model.setId(1);
        result = model.getId();
        assertNotEquals(expResult, result);

        expResult = 3;
        model.setId(3);
        result = model.getId();
        assertEquals(expResult, result);
    }

    @Test
    void getM_strDesignation() {
        String expResult = "";
        String result = model.getDesignation();
        assertNotEquals(expResult, result);

        expResult = "Designacao";
        assertEquals(expResult, result);
    }

    @Test
    void setM_strDesignation() {
        String expResult = "Designacao";
        String result = model.getDesignation();
        assertEquals(expResult, result);

        model.setDesignation("test");
        result = model.getDesignation();
        assertNotEquals(expResult, result);

        expResult = "test";
        result = model.getDesignation();
        assertEquals(expResult, result);
    }

    @Test
    void getM_dblPotency() {
        double expResult = 1;
        double result = model.getPotency();
        assertNotEquals(expResult, result);

        expResult = 2;
        assertEquals(expResult, result);
    }

    @Test
    void setM_dblPotency() {
        double expResult = 2;
        double result = model.getPotency();
        assertEquals(expResult, result);

        model.setPotency(1);
        result = model.getPotency();
        assertNotEquals(expResult, result);

        expResult = 3;
        model.setPotency(3);
        result = model.getPotency();
        assertEquals(expResult, result);
    }

    @Test
    void getM_dblWeight() {
        double expResult = 1;
        double result = model.getWeight();
        assertNotEquals(expResult, result);

        expResult = 2;
        assertEquals(expResult, result);
    }

    @Test
    void setM_dblWeight() {
        double expResult = 2;
        double result = model.getWeight();
        assertEquals(expResult, result);

        model.setWeight(1);
        result = model.getWeight();
        assertNotEquals(expResult, result);

        expResult = 3;
        model.setWeight(3);
        result = model.getWeight();
        assertEquals(expResult, result);
    }

    @Test
    void getM_dblMaxPayload() {
        double expResult = 1;
        double result = model.getMaxPayload();
        assertNotEquals(expResult, result);

        expResult = 2;
        assertEquals(expResult, result);
    }

    @Test
    void setM_dblMaxPayload() {
        double expResult = 2;
        double result = model.getMaxPayload();
        assertEquals(expResult, result);

        model.setMaxPayload(1);
        result = model.getMaxPayload();
        assertNotEquals(expResult, result);

        expResult = 3;
        model.setMaxPayload(3);
        result = model.getMaxPayload();
        assertEquals(expResult, result);
    }

    @Test
    void getM_oBattery() {
        Battery expResult = new Battery();
        expResult.setId(40);
        Battery result = model.getBattery();
        assertNotEquals(expResult, result);

        expResult = new Battery();
        assertEquals(expResult, result);
    }

    @Test
    void setM_oBattery() {
        Battery expResult = new Battery();
        Battery result = model.getBattery();
        assertEquals(expResult, result);

        Battery b = new Battery();
        b.setId(40);
        model.setBattery(b);
        result = model.getBattery();
        assertNotEquals(expResult, result);

        expResult = b;
        model.setBattery(b);
        result = model.getBattery();
        assertEquals(expResult, result);
    }

    @Test
    void getM_enumVehicleType() {
        VehicleType expResult = VehicleType.DRONE;
        VehicleType result = model.getVehicleType();
        assertNotEquals(expResult, result);

        expResult = VehicleType.SCOOTER;
        assertEquals(expResult, result);
    }

    @Test
    void setM_enumVehicleType() {
        VehicleType expResult = VehicleType.SCOOTER;
        VehicleType result = model.getVehicleType();
        assertEquals(expResult, result);

        model.setVehicleType(VehicleType.DRONE);
        result = model.getVehicleType();
        assertNotEquals(expResult, result);

        expResult = VehicleType.NOTDEFINED;
        model.setVehicleType(expResult);
        result = model.getVehicleType();
        assertEquals(expResult, result);
    }

    @Test
    void testEquals() {
        assertEquals(model, model);
        assertNotEquals(model, modelDiff3);
        assertNotEquals(null, model);
        assertNotEquals(model,null);
        assertNotEquals(model, new Product());
        assertNotEquals(model, modelDiff);
    }

    @Test
    void testHashCode() {
        int expResult = 0;
        int result = model.hashCode();
        assertNotEquals(expResult, result);

        expResult = 1964385997;
        assertEquals(expResult, result);
    }

    @Test
    void testToString() {
        String expResult = "";
        String result = model.toString();
        assertNotEquals(expResult, result);

        expResult = "VehicleModel{m_intId=2, m_strDesignation='Designacao', m_dblPotency=2.0, m_dblWeight=2.0, m_dblMaxPayload=2.0, m_oBattery=Battery{Id=-1, Efficiency=-1.0, Battery Capacity=-1, Battery Voltage=-1.0}, m_enumVehicleType=VehicleType{m_strDesignation='Scooter'}}";
        assertEquals(expResult, result);
    }

    @Test
    void toStringRoute() {
        String expResult = "";
        String result = model.toStringRoute();
        assertNotEquals(expResult, result);
        expResult = "name->Designacao, potency->2.0 watt's, weight->2.0 kilos, max payload weight->2.0 kilos, battery efficiency->-1.0, battery capacity->-1, battery voltage->-1.0.";
        assertEquals(expResult, result);
    }
}