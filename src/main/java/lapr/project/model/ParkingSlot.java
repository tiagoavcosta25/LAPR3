package lapr.project.model;

import java.util.Objects;

public class ParkingSlot implements Comparable{

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

    @Override
    public int compareTo(Object o) {
        ParkingSlot p = (ParkingSlot) o;
        return this.getId() - p.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingSlot that = (ParkingSlot) o;
        return m_intId == that.m_intId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_intId);
    }

    @Override
    public String toString() {
        return "ParkingSlot{" +
                "m_intId=" + m_intId +
                ", m_oScooter=" + m_oScooter +
                '}';
    }
}
