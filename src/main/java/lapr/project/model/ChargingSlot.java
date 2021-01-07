package lapr.project.model;

import java.util.Objects;

public class ChargingSlot {
    private int m_intId;
    private Park m_oPark;
    private Scooter m_oScooter;
    private float m_outputPower;

    private static int DEFAULT_ID = -1;
    private static Park DEFAULT_PARK = new Park();
    private static Scooter DEFAULT_SCOOTER = null;
    private static float DEFAULT_OUTPUT_POWER = -1;

    public ChargingSlot(int intId, Park oPark, Scooter oScooter, float oOutputPower) {
        this.m_intId = intId;
        this.m_oPark = oPark;
        this.m_oScooter = oScooter;
        this.m_outputPower = oOutputPower;
    }

    public ChargingSlot(Park oPark, float oOutputPower) {
        this.m_intId = DEFAULT_ID;
        this.m_oPark = oPark;
        this.m_oScooter = DEFAULT_SCOOTER;
        this.m_outputPower = oOutputPower;
    }

    public ChargingSlot() {
        this.m_intId = DEFAULT_ID;
        this.m_oPark = DEFAULT_PARK;
        this.m_oScooter = DEFAULT_SCOOTER;
        this.m_outputPower = DEFAULT_OUTPUT_POWER;
    }

    public int getM_intId() {
        return m_intId;
    }

    public void setM_intId(int m_intId) {
        this.m_intId = m_intId;
    }

    public Park getPark() {
        return m_oPark;
    }

    public void setPark(Park m_oPark) {
        this.m_oPark = m_oPark;
    }

    public Scooter getM_oScooter() {
        return m_oScooter;
    }

    public void setM_oScooter(Scooter m_oScooter) {
        this.m_oScooter = m_oScooter;
    }

    public float getM_outputPower() {
        return m_outputPower;
    }

    public void setM_outputPower(float m_outputPower) {
        this.m_outputPower = m_outputPower;
    }

    @Override
    public String toString() {
        return "ChargingSlot{" +
                "m_intId=" + m_intId +
                ", Park=" + m_oPark +
                ", m_oScooter=" + m_oScooter +
                ", m_outputPower=" + m_outputPower +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChargingSlot that = (ChargingSlot) o;
        return m_intId == that.m_intId &&
                Float.compare(that.m_outputPower, m_outputPower) == 0 &&
                m_oPark.equals(that.m_oPark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_intId, m_oPark, m_outputPower);
    }
}
