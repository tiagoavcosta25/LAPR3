package lapr.project.model;

import java.util.Objects;

public abstract class ParkingSlot {

    private int m_intId;
    private Vehicle m_oVehicle;

    public ParkingSlot() {
        this.m_intId = -1;
        this.m_oVehicle = null;
    }

    public ParkingSlot(int m_intId) {
        this.m_intId = m_intId;
        this.m_oVehicle = null;
    }

    public ParkingSlot(Vehicle m_oVehicle) {
        this.m_intId = -1;
        this.m_oVehicle = m_oVehicle;
    }

    public ParkingSlot(int m_intId, Vehicle m_oVehicle) {
        this.m_intId = m_intId;
        this.m_oVehicle = m_oVehicle;
    }

    public int getId() {
        return m_intId;
    }

    public void setId(int m_intId) {
        this.m_intId = m_intId;
    }

    public Vehicle getVehicle() {
        return m_oVehicle;
    }

    public void setVehicle(Vehicle m_oVehicle) {
        this.m_oVehicle = m_oVehicle;
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_intId);
    }

    @Override
    public String toString() {
        return "ParkingSlot{" +
                "m_intId=" + m_intId +
                ", m_oVehicle=" + m_oVehicle +
                '}';
    }
}
