package lapr.project.model;

public class NonChargingSlot extends ParkingSlot implements Comparable{

    public NonChargingSlot() {
    }

    public NonChargingSlot(int m_intId) {
        super(m_intId);
    }

    public NonChargingSlot(Vehicle m_oVehicle) {
        super(m_oVehicle);
    }

    public NonChargingSlot(int m_intId, Vehicle m_oVehicle) {
        super(m_intId, m_oVehicle);
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
