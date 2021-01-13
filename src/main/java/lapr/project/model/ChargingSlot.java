package lapr.project.model;

import java.util.Objects;

public class ChargingSlot implements Comparable{
    private int m_intId;
    private Park m_oPark;
    private Scooter m_oScooter;
    private float m_fltOutputPower;

    private static int DEFAULT_ID = -1;
    private static Park DEFAULT_PARK = new Park();
    private static Scooter DEFAULT_SCOOTER = null;
    private static float DEFAULT_OUTPUT_POWER = -1;

    public ChargingSlot(int intId, Park oPark, Scooter oScooter, float oOutputPower) {
        this.m_intId = intId;
        this.m_oPark = oPark;
        this.m_oScooter = oScooter;
        this.m_fltOutputPower = oOutputPower;
    }

    public ChargingSlot(Park oPark, float oOutputPower) {
        this.m_intId = DEFAULT_ID;
        this.m_oPark = oPark;
        this.m_oScooter = DEFAULT_SCOOTER;
        this.m_fltOutputPower = oOutputPower;
    }

    public ChargingSlot(int intID, Park oPark, float oOutputPower) {
        this.m_intId = intID;
        this.m_oPark = oPark;
        this.m_oScooter = DEFAULT_SCOOTER;
        this.m_fltOutputPower = oOutputPower;
    }

    public ChargingSlot() {
        this.m_intId = DEFAULT_ID;
        this.m_oPark = DEFAULT_PARK;
        this.m_oScooter = DEFAULT_SCOOTER;
        this.m_fltOutputPower = DEFAULT_OUTPUT_POWER;
    }

    public int getId() {
        return m_intId;
    }

    public void setId(int m_intId) {
        this.m_intId = m_intId;
    }

    public Park getPark() {
        return m_oPark;
    }

    public void setPark(Park m_oPark) {
        this.m_oPark = m_oPark;
    }

    public Scooter getScooter() {
        return m_oScooter;
    }

    public void setScooter(Scooter m_oScooter) {
        this.m_oScooter = m_oScooter;
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
        return m_intId == that.m_intId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_intId);
    }

    @Override
    public String toString() {
        return "ChargingSlot{" +
                "m_intId=" + m_intId +
                ", m_oScooter=" + m_oScooter +
                ", m_fltOutputPower=" + m_fltOutputPower +
                '}';
    }
}
