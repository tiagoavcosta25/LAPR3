package lapr.project.model.service;

import lapr.project.data.VehicleDB;
import lapr.project.model.*;
import lapr.project.utils.QRCode;
import java.util.List;

/**
 * VehicleService.
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

public class VehicleService {

    /**
     * Vehicle Database.
     */
    private VehicleDB moVehicleDB;

    /**
     * Returns Vehicle Database.
     * @return Vehicle Database.
     */
    public VehicleDB getVehicleDB() {
        return moVehicleDB;
    }

    /**
     * Modifies Vehicle Database.
     * @param oVehicleDB Vehicle Database.
     */
    public void setVehicleDB(VehicleDB oVehicleDB) {
        this.moVehicleDB = oVehicleDB;
    }

    /**
     * An empty constructor of Vehicle Service.
     */
    public VehicleService() {
        this.moVehicleDB = new VehicleDB();
    }

    /**
     * Returns a list of Vehicle Model's by Pharmacy.
     * @param strPharmacyEmail Pharmacy Email.
     * @return a list of Vehicle Model's by Pharmacy.
     */
    public List<VehicleModel> getPharmacyModel(String strPharmacyEmail) {
        return moVehicleDB.getPharmacyModel(strPharmacyEmail);
    }

    /**
     * The method creates a new Vehicle Model.
     *
     * @param strDesignation Vehicle Model Designation
     * @param dblPotency Vehicle Model Potency
     * @param dblWeight Vehicle Model Weight
     * @param dblMaxPayload Vehicle Model Maximum Paylod
     * @param intBatteryCapacity Vehicle Model Battery Capacity
     * @param dblBatteryVoltage Vehicle Model Battery Voltage
     * @param dblEfficiency Vehicle Model Efficiency
     * @return Vehicle Model.
     */
    public VehicleModel newVehicleModel(String strDesignation, double dblPotency, double dblWeight, double dblMaxPayload, int intBatteryCapacity,
                                        double dblBatteryVoltage, double dblEfficiency, VehicleType oVehicleType) {
            return new VehicleModel(strDesignation, dblPotency, dblWeight, dblMaxPayload, new Battery(intBatteryCapacity,
                    dblBatteryVoltage, dblEfficiency), oVehicleType);
    }

    /**
     * The method generates a QrCode for the vehicle.
     *
     * @param oVehicle Vehicle.
     * @return true if the QrCode is generated. False if otherwise.
     */
    public boolean generateQRCode(Vehicle oVehicle) {
        return QRCode.generateQRCode(oVehicle.getModel().getVehicleType().getDesignation() + "#" + oVehicle.getId()
                        + "_" + oVehicle.getPharmacy().getEmail(), oVehicle.getModel().getVehicleType().getDesignation() + "_" + oVehicle.getId());
    }

    /**
     * Returns Vehicle Model by Designation.
     * @param strDesignation Vehicle Model Designation.
     * @return Vehicle Model.
     */
    public VehicleModel getVehicleModel(String strDesignation) {
        return this.moVehicleDB.getVehicleModel(strDesignation);
    }

    /**
     * Register a new Vehicle Model.
     * @param moVehicleModel Vehicle Model.
     * @return Vehicle Model ID.
     */
    public int registerVehicleModel(VehicleModel moVehicleModel) {
        return this.moVehicleDB.registerVehicleModel(moVehicleModel);
    }

    /**
     * Returns the Vehicle Payload by Vehicle ID.
     * @param vehicleId Vehicle ID.
     * @return the Vehicle Payload.
     */
    public double getVehiclePayload(int vehicleId){
        return this.moVehicleDB.getVehiclePayload(vehicleId);
    }

}
