package lapr.project.model;

public class NonChargingSlot extends ParkingSlot implements Comparable{

    public NonChargingSlot() {
    }

    public NonChargingSlot(int intId) {
        super(intId);
    }

    public NonChargingSlot(Vehicle oVehicle) {
        super(oVehicle);
    }

    public NonChargingSlot(int intId, Vehicle oVehicle) {
        super(intId, oVehicle);
    }

    @Override
    public int compareTo(Object o) {
        NonChargingSlot p = (NonChargingSlot) o;
        return this.getId() - p.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NonChargingSlot that = (NonChargingSlot) o;
        return super.getId() == that.getId();
    }
}
