package lapr.project.model;

public class ChargingSlot extends ParkingSlot implements Comparable{

    private float m_fltOutputPower;

    public ChargingSlot() {
        m_fltOutputPower = -1;
    }

    public ChargingSlot(float m_fltOutputPower) {
        this.m_fltOutputPower = m_fltOutputPower;
    }

    public ChargingSlot(int m_intId, float m_fltOutputPower) {
        super(m_intId);
        this.m_fltOutputPower = m_fltOutputPower;
    }

    public ChargingSlot(Vehicle m_oVehicle, float m_fltOutputPower) {
        super(m_oVehicle);
        this.m_fltOutputPower = m_fltOutputPower;
    }

    public ChargingSlot(int m_intId, Vehicle m_oVehicle, float m_fltOutputPower) {
        super(m_intId, m_oVehicle);
        this.m_fltOutputPower = m_fltOutputPower;
    }

    public float getOutputPower() {
        return m_fltOutputPower;
    }

    public void setOutputPower(float m_outputPower) {
        this.m_fltOutputPower = m_outputPower;
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
    public String toString() {
        return "ChargingSlot{" +
                "m_intId=" + super.getId() +
                ", m_oScooter=" + super.getVehicle() +
                ", m_fltOutputPower=" + m_fltOutputPower +
                '}';
    }
}
