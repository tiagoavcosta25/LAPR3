package lapr.project.model;

public class DeliveryStatus {

    private String m_strDesignation;

    public DeliveryStatus() {
        this.m_strDesignation = "Idle";
    }

    public DeliveryStatus(String designation) {
        this.m_strDesignation = designation;
    }

    public String getDesignation() {
        return m_strDesignation;
    }

    public void setDesignation(String designation) {
        this.m_strDesignation = designation;
    }
}
