package lapr.project.model;

import java.util.Objects;

public abstract class ParkingSlot {

    private int mintId;
    private Vehicle moVehicle;

    public ParkingSlot() {
        this.mintId = -1;
        this.moVehicle = null;
    }

    public ParkingSlot(int intId) {
        this.mintId = intId;
        this.moVehicle = null;
    }

    public ParkingSlot(Vehicle oVehicle) {
        this.mintId = -1;
        this.moVehicle = oVehicle;
    }

    public ParkingSlot(int intId, Vehicle oVehicle) {
        this.mintId = intId;
        this.moVehicle = oVehicle;
    }

    public int getId() {
        return mintId;
    }

    public void setId(int intId) {
        this.mintId = intId;
    }

    public Vehicle getVehicle() {
        return moVehicle;
    }

    public void setVehicle(Vehicle oVehicle) {
        this.moVehicle = oVehicle;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mintId);
    }

    @Override
    public String toString() {
        return "ParkingSlot{" +
                "m_intId=" + mintId +
                ", m_oVehicle=" + moVehicle +
                '}';
    }
}
