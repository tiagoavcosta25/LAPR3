package lapr.project.model;

import java.util.Objects;

public enum DeliveryStatus {

    IDLE("Idle"),INPROGRESS("In Progress");
    private String m_strDesignation;

    DeliveryStatus(String designation) {
        this.m_strDesignation = designation;
    }

    public String getDesignation() {
        return m_strDesignation;
    }

    public void setDesignation(String designation) {
        this.m_strDesignation = designation;
    }


    @Override
    public String toString() {
        return "DeliveryStatus{" +
                "m_strDesignation='" + m_strDesignation + '\'' +
                '}';
    }
}
