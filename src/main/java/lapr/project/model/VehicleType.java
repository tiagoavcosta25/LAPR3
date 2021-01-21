package lapr.project.model;

public enum VehicleType {

    SCOOTER("Scooter"),DRONE("Drone"),NOTDEFINED("Not defined") ;
    private String mstrDesignation;

     VehicleType(String strDesignation) {
        this.mstrDesignation = strDesignation;
    }

    public static VehicleType getTypeByDesignation(String designation) {
         if (designation.equalsIgnoreCase(VehicleType.SCOOTER.getDesignation())){
             return VehicleType.SCOOTER;
         } else if(designation.equalsIgnoreCase(VehicleType.DRONE.getDesignation())){
             return VehicleType.DRONE;
         }
        return VehicleType.NOTDEFINED;
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
