package lapr.project.model.service;

import lapr.project.data.ScooterDB;
import lapr.project.model.Pharmacy;
import lapr.project.model.Scooter;
import lapr.project.model.VehicleModel;

import java.util.List;

/**
 * Scooter Service.
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

public class ScooterService {

    /**
     * Scooter Database.
     */
    private ScooterDB moScooterDB;

    /**
     * Returns Scooter Database.
     * @return Scooter Database.
     */
    public ScooterDB getScooterDB() {
        return moScooterDB;
    }

    /**
     * Modifies Scooter Database.
     * @param oScooterDB Scooter Database.
     */
    public void setScooterDB(ScooterDB oScooterDB) {
        this.moScooterDB = oScooterDB;
    }

    /**
     * An empty constructor of Scooter Service.
     */
    public ScooterService() {
        this.moScooterDB = new ScooterDB();
    }

    /**
     * Returns a Scooter by ID.
     * @param intId Scooter ID.
     * @return Scooter
     */
    public Scooter getScooter(int intId){
        return moScooterDB.getScooter(intId);
    }

    /**
     * Update a Scooter Information.
     *
     * @param intId Scooter ID.
     * @param dblBatteryPerc Scooter Battery Percentage.
     * @param strDesignation Scooter Designation.
     * @param dblPotency Scooter Potency.
     * @param dblWeight Scooter Weight.
     * @param dblMaxPayload Scooter Maximum Payload.
     * @param intBatteryCapacity Scooter Battery Capacity.
     * @param dblBatteryVoltage Scooter Battery Voltage.
     * @param dblEfficiency Scooter Efficiency.
     * @return true if the Scooter is updated. False if otherwise.
     */
    public boolean updateScooterFromDB(int intId, double dblBatteryPerc, String strDesignation, double dblPotency, double dblWeight, double dblMaxPayload,
                                       int intBatteryCapacity, double dblBatteryVoltage, double dblEfficiency){
        return moScooterDB.updateScooterFromDB(intId, dblBatteryPerc, strDesignation, dblPotency, dblWeight, dblMaxPayload,
                intBatteryCapacity, dblBatteryVoltage, dblEfficiency);
    }

    /**
     * Returns a list od Scooters by Pharmacy Email.
     * @param strPharmacyEmail Pharmacy Email.
     * @return a list od Scooters.
     */
    public List<Scooter> getScootersList(String strPharmacyEmail) { return moScooterDB.getScootersList(strPharmacyEmail);}

    /**
     * Remove a Scooter.
     * @param intId Scooter ID.
     * @return true if the Scooter is removed. False if otherwise.
     */
    public boolean removeScooterFromDB(int intId) { return moScooterDB.removeScooterFromDB(intId);}

    /**
     * Creates a new Scooter.
     * @param oVehicleModel Scooter Model.
     * @param oPharmacy Scooter Pharmacy.
     * @return new Scooter Instance.
     */
    public Scooter newScooter(VehicleModel oVehicleModel, Pharmacy oPharmacy) {
        return new Scooter(oVehicleModel, oPharmacy);
    }

    /**
     * Register a Scooter.
     * @param oScooter Scooter.
     * @return true if the Scooter is registered. False if otherwise.
     */
    public int registerScooter(Scooter oScooter) {
        return moScooterDB.registerScooter(oScooter);
    }

}
