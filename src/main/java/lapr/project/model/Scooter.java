package lapr.project.model;

/**
 * Scooter.
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

public class Scooter extends Vehicle {

    /**
     * An empty constructor of Scooter.
     */
    public Scooter() {
        super();
    }

    /**
     * Builds an instance of Scooter receiving Vehicle Model and Pharmacy.
     *
     * @param oVehicleModel Scooter's Vehicle Model.
     * @param oPharmacy Scooter's Pharmacy.
     */
    public Scooter(VehicleModel oVehicleModel, Pharmacy oPharmacy) {
        super(oVehicleModel, oPharmacy);
    }

    /**
     * Builds an instance of Scooter receiving Scooter's ID, Vehicle Model and Pharmacy.
     *
     * @param intId Scooter's ID.
     * @param oVehicleModel Scooter's Vehicle Model.
     * @param oPharmacy Scooter's Pharmacy.
     */
    public Scooter(int intId, VehicleModel oVehicleModel, Pharmacy oPharmacy) {
        super(intId, oVehicleModel, oPharmacy);
    }

    /**
     * Builds an instance of Scooter receiving Scooter's ID, Battery Percentage, Vehicle Model and Pharmacy.
     *
     * @param intId Scooter's ID.
     * @param dblBatteryPerc Scooter's Battery Percentage
     * @param oVehicleModel Scooter's Vehicle Model.
     * @param oPharmacy Scooter's Pharmacy.
     */
    public Scooter(int intId, double dblBatteryPerc, VehicleModel oVehicleModel, Pharmacy oPharmacy) {
        super(intId, dblBatteryPerc, oVehicleModel, oPharmacy);
    }
}
