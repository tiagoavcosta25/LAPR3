package lapr.project.model.service;

import lapr.project.data.DroneDB;
import lapr.project.model.*;

import java.util.List;

/**
 * Drone Service.
 * <p>
 * Group: Team Lisa [G-021]
 * ______________________________________________________
 *
 * @author António Barbosa <1190404@isep.ipp.pt>
 * @author Ernesto Rodrigues <1190560@isep.ipp.pt>
 * @author Jessica Alves <1190682@isep.ipp.pt>
 * @author Pedro Santos <1190967@isep.ipp.pt>
 * @author Rodrigo Costa <1191014@isep.ipp.pt>
 * @author Tiago Costa <1191460@isep.ipp.pt>
 */
public class DroneService {

    /**
     * Drone Database
     */
    private DroneDB moDroneDB;

    /**
     * Empty constructor, which instantiates a new Drone Database
     */
    public DroneService() {
        moDroneDB = new DroneDB();
    }

    /**
     * Returns the Drone Database
     *
     * @return Drone Database
     */
    public DroneDB getDroneDB() {
        return moDroneDB;
    }

    /**
     * Set the Drone Database to the one given by parameter
     *
     * @param oDroneDB new Drone Database
     */
    public void setDroneDB(DroneDB oDroneDB) {
        this.moDroneDB = oDroneDB;
    }

    /**
     * Updates a Drone from the Database, basing on the atributes
     * given by parameter
     *
     * @param intId              Drone ID
     * @param dblBatteryPerc     Drone Battery Capacity
     * @param strDesignation     Drone Designation
     * @param dblPotency         Drone Potency
     * @param dblWeight          Drone Weight
     * @param dblMaxPayload      Drone Max Payload
     * @param intBatteryCapacity Drone Battery Capacity
     * @param dblBatteryVoltage  Drone Batteru Voltage
     * @param dblEfficiency      Drone Battery Efficiency
     * @return True if the Drone was updated, false if otherwise
     */
    public boolean updateDroneFromDB(int intId, double dblBatteryPerc, String strDesignation, double dblPotency, double dblWeight, double dblMaxPayload,
                                     int intBatteryCapacity, double dblBatteryVoltage, double dblEfficiency) {
        return moDroneDB.updateDroneFromDB(intId, dblBatteryPerc, strDesignation, dblPotency, dblWeight, dblMaxPayload,
                intBatteryCapacity, dblBatteryVoltage, dblEfficiency);
    }

    /**
     * Creates a new Drone basing on the atributes given by parameter
     *
     * @param oVehicleModel Drone's Vehicle Model
     * @param oPharmacy     Drone's Pharmacy
     * @return Drone which was created
     */
    public Drone newDrone(VehicleModel oVehicleModel, Pharmacy oPharmacy) {
        return new Drone(oVehicleModel, oPharmacy);
    }

    /**
     * Registers a new Drone into the Database
     *
     * @param oDrone Drone to be registered
     * @return Drone's ID
     */
    public int registerDrone(Drone oDrone) {
        return moDroneDB.registerDrone(oDrone);
    }

    /**
     * Returns the List of Drones regarding a certain Pharmacy, by the email
     *
     * @param strPharmacyEmail Pharmacy Email
     * @return List of Drones regarding a certain Pharmacy
     */
    public List<Drone> getDronesList(String strPharmacyEmail) {
        return moDroneDB.getDronesList(strPharmacyEmail);
    }

    /**
     * Removes a Drone from the Database, basing on the ID given by parameter
     *
     * @param intId Drone ID
     * @return True if the Drone was removed, false if otherwise
     */
    public boolean removeDroneFromDB(int intId) {
        return moDroneDB.removeDroneFromDB(intId);
    }

}
