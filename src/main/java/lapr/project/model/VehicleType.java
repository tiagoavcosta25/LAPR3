package lapr.project.model;

public enum VehicleType {

    SCOOTER("Scooter"),DRONE("Drone"),NOTDEFINED("Not defined") ;
    private String mstrDesignation;

     VehicleType(String strDesignation) {
        this.mstrDesignation = strDesignation;
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
