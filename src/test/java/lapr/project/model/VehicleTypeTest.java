package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTypeTest {

    private VehicleType m_vehicleType;
    private VehicleType m_emptyVehicleType;

    VehicleTypeTest() {
        m_vehicleType = VehicleType.SCOOTER;
        m_emptyVehicleType = VehicleType.NOTDEFINED;
    }

    @Test
    void getDesignation() {
        String expected = "Scooter";
        String real = m_vehicleType.getDesignation();
        assertEquals(expected,real);
    }

    @Test
    void testEquals() {
        VehicleType d = VehicleType.SCOOTER;
        assertEquals(d,m_vehicleType);

        assertEquals(m_vehicleType,m_vehicleType);

        d = VehicleType.NOTDEFINED;
        assertNotEquals(d,m_vehicleType);

        d = VehicleType.DRONE;
        assertNotEquals(d,m_vehicleType);

        String s = "";
        assertNotEquals(s,m_vehicleType);

        d = null;
        assertNotEquals(d,m_vehicleType);
    }

    @Test
    void testToString() {
        String expected =  "VehicleType{m_strDesignation='Scooter'}";

        String real = m_vehicleType.toString();
        assertEquals(expected,real);
    }

    @Test
    void getTypeByDesignation() {
        VehicleType real = VehicleType.getTypeByDesignation("Scooter");
        VehicleType expected = VehicleType.SCOOTER;
        assertEquals(expected, real);

        real = VehicleType.getTypeByDesignation("Drone");
        expected = VehicleType.DRONE;
        assertEquals(expected, real);

        real = VehicleType.getTypeByDesignation("Not Defined");
        expected = VehicleType.NOTDEFINED;
        assertEquals(expected, real);
    }
}