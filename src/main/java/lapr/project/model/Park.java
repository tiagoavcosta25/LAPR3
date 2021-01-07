package lapr.project.model;

public class Park {

    private int m_intId;
    private int m_intMaxSlotsNumber;

    public Park(int intId, int intMaxSlotsNumber) {
        this.setM_intId(intId);
        this.setM_intMaxSlotsNumber(intMaxSlotsNumber);
    }

    public Park(int intMaxSlotsNumber) {
        this.setM_intMaxSlotsNumber(intMaxSlotsNumber);
    }

    public int getM_intId() {
        return m_intId;
    }

    public void setM_intId(int m_intId) {
        this.m_intId = m_intId;
    }

    public int getM_intMaxSlotsNumber() {
        return m_intMaxSlotsNumber;
    }

    public void setM_intMaxSlotsNumber(int m_intMaxSlotsNumber) {
        this.m_intMaxSlotsNumber = m_intMaxSlotsNumber;
    }
}
