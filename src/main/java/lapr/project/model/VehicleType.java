package lapr.project.model;

/**
 * Vehicle Type.
 *
 * Group: Team Lisa [G-021]
 * ______________________________________________________
 * @author António Barbosa <1190404@isep.ipp.pt>
 * @author Ernesto Rodrigues <1190560@isep.ipp.pt>
 * @author Jessica Alves <1190682@isep.ipp.pt>
 * @author Pedro Santos <1190967@isep.ipp.pt>
 * @author Rodrigo Costa <1191014@isep.ipp.pt>
 * @author Tiago Costa <1191460@isep.ipp.pt>
 */

public enum VehicleType {

    /**
     * Vehicle Types.
     */
    SCOOTER("Scooter"),DRONE("Drone"),NOTDEFINED("Not defined") ;

    /**
     * Vehicle Type Designation.
     */
    private String mstrDesignation;

    /**
     * An empty constructor of VehicleType.
     * @param strDesignation Vehicle Type Designation.
     */
     VehicleType(String strDesignation) {
        this.mstrDesignation = strDesignation;
    }

    /**
     * Returns the Vehicle Type by its Designation.
     * @param designation Vehicle Type Designation.
     * @return Vehicle Type.
     */
    public static VehicleType getTypeByDesignation(String designation) {
         if (designation.equalsIgnoreCase(VehicleType.SCOOTER.getDesignation())){
             return VehicleType.SCOOTER;
         } else if(designation.equalsIgnoreCase(VehicleType.DRONE.getDesignation())){
             return VehicleType.DRONE;
         }
        return VehicleType.NOTDEFINED;
    }

    /**
     * Returns Vehicle Type Designation.
     * @return Vehicle Type Designation.
     */
    public String getDesignation() {
        return mstrDesignation;
    }

    /**
     * Returns the textual description of the VehicleType in the format: Designation.
     * @return textual description of the VehicleType.
     */
    @Override
    public String toString() {
        return "VehicleType{" +
                "m_strDesignation='" + mstrDesignation + '\'' +
                '}';
    }
}
