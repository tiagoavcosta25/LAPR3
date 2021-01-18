package lapr.project.model;

public enum VehicleType {

    SCOOTER("Scooter"),DRONE("Drone"),NOTDEFINED("Not defined") ;
    private String m_strDesignation;

    VehicleType(String designation) {
        this.m_strDesignation = designation;
    }

    public String getDesignation() {
        return m_strDesignation;
    }

    @Override
    public String toString() {
        return "DeliveryStatus{" +
                "m_strDesignation='" + m_strDesignation + '\'' +
                '}';
    }
}
