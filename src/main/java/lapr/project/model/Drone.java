package lapr.project.model;

/**
 * Drone.
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

public class Drone extends Vehicle {

    /**
     * An empty constructor of Drone.
     */
    public Drone() {
        super();
    }

    /**
     * Builds an instance of Drone receiving Vehicle Model and Pharmacy.
     *
     * @param oVehicleModel Drone's Vehicle Model.
     * @param oPharmacy Drone's Pharmacy.
     */
    public Drone(VehicleModel oVehicleModel, Pharmacy oPharmacy) {
        super(oVehicleModel, oPharmacy);
    }

    /**
     * Builds an instance of Drone receiving Drone's ID, Vehicle Model and Pharmacy.
     *
     * @param intId Drone's ID.
     * @param oVehicleModel Drone's Vehicle Model.
     * @param oPharmacy Drone's Pharmacy.
     */
    public Drone(int intId, VehicleModel oVehicleModel, Pharmacy oPharmacy) {
        super(intId, oVehicleModel, oPharmacy);
    }

    /**
     * Builds an instance of Drone receiving Drone's ID, Battery Percentage, Vehicle Model and Pharmacy.
     *
     * @param intId Drone's ID.
     * @param dblBatteryPerc Drone's Battery Percentage
     * @param oVehicleModel Drone's Vehicle Model.
     * @param oPharmacy Drone's Pharmacy.
     */
    public Drone(int intId, double dblBatteryPerc, VehicleModel oVehicleModel, Pharmacy oPharmacy) {
        super(intId, dblBatteryPerc, oVehicleModel, oPharmacy);
    }
}
