package lapr.project.model;

public enum DeliveryStatus {

    IDLE("Idle"),INPROGRESS("In Progress");
    private String mstrDesignation;

    DeliveryStatus(String designation) {
        this.mstrDesignation = designation;
    }

    public String getDesignation() {
        return mstrDesignation;
    }

    @Override
    public String toString() {
        return "DeliveryStatus{" +
                "m_strDesignation='" + mstrDesignation + '\'' +
                '}';
    }
}
