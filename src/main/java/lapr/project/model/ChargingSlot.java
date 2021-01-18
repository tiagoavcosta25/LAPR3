package lapr.project.model;

public class ChargingSlot extends ParkingSlot implements Comparable{

    public ChargingSlot() {
    }

    public ChargingSlot(int m_intId) {
        super(m_intId);
    }

    public ChargingSlot(Vehicle m_oVehicle) {
        super(m_oVehicle);
    }

    public ChargingSlot(int m_intId, Vehicle m_oVehicle) {
        super(m_intId, m_oVehicle);
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
}
