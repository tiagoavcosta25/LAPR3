package lapr.project.model;

import java.util.Objects;

public class ParkingSlot {

    private int m_intId;
    private Park m_oPark;
    private Scooter m_oScooter;

    private static int DEFAULT_ID = -1;
    private static Park DEFAULT_PARK = new Park();
    private static Scooter DEFAULT_SCOOTER = null;

    public ParkingSlot(int intId, Park oPark, Scooter oScooter) {
        this.m_intId = intId;
        this.m_oPark = oPark;
        this.m_oScooter = oScooter;
    }

    public ParkingSlot(Park oPark) {
        this.m_intId = DEFAULT_ID;
        this.m_oPark = oPark;
        this.m_oScooter = DEFAULT_SCOOTER;
    }

    public ParkingSlot() {
        this.m_intId = DEFAULT_ID;
        this.m_oPark = DEFAULT_PARK;
        this.m_oScooter = DEFAULT_SCOOTER;
    }

    public int getM_intId() {
        return m_intId;
    }

    public void setM_intId(int m_intId) {
        this.m_intId = m_intId;
    }

    public Park getM_oPark() {
        return m_oPark;
    }

    public void setM_oPark(Park m_oPark) {
        this.m_oPark = m_oPark;
    }

    public Scooter getM_oScooter() {
        return m_oScooter;
    }

    public void setM_oScooter(Scooter m_oScooter) {
        this.m_oScooter = m_oScooter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingSlot that = (ParkingSlot) o;
        return m_intId == that.m_intId &&
                Objects.equals(m_oPark, that.m_oPark) &&
                Objects.equals(m_oScooter, that.m_oScooter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_intId, m_oPark);
    }

    @Override
    public String toString() {
        return "ParkingSlot{" +
                "m_intId=" + m_intId +
                ", m_oPark=" + m_oPark +
                ", m_oScooter=" + m_oScooter +
                '}';
    }
}
