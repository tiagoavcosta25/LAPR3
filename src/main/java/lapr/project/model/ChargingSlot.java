package lapr.project.model;

import java.util.Objects;

public class ChargingSlot extends ParkingSlot implements Comparable{

    public ChargingSlot() {
    }

    public ChargingSlot(int mintId) {
        super(mintId);
    }

    public ChargingSlot(Vehicle moVehicle) {
        super(moVehicle);
    }

    public ChargingSlot(int mintId, Vehicle moVehicle) {
        super(mintId, moVehicle);
    }


    @Override
    public int compareTo(Object o) {
        ChargingSlot p = (ChargingSlot) o;
        return this.getId() - p.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChargingSlot that = (ChargingSlot) o;
        return super.getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getId());
    }
}
