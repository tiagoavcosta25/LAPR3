package lapr.project.controller;

import lapr.project.model.Drone;
import lapr.project.model.Pharmacy;
import lapr.project.model.VehicleModel;
import lapr.project.model.VehicleType;
import lapr.project.model.service.DroneService;
import lapr.project.model.service.PharmacyService;
import lapr.project.model.service.VehicleService;

import java.util.List;

public class RegisterDroneController {

    /**
     * Drone class instance
     */
    private Drone moDrone;

    /**
     * Pharmacy Management class
     */
    private PharmacyService moPharmacyService;

    /**
     * Drone Management class
     */
    private DroneService moDroneService;

    /**
     * VehicleModel class instance
     */
    private VehicleModel moVehicleModel;

    /**
     * Vehicle Management class
     */
    private VehicleService moVehicleService;

    private Pharmacy moPharmacy;

    /**
     * An empty constructor of RegisterDroneController.
     */
    public RegisterDroneController() {
        this.moPharmacyService = new PharmacyService();
        this.moDroneService = new DroneService();
        this.moVehicleService = new VehicleService();
    }

    /**
     * The method receives Drone's battery percentage, charging status, potency, weight, battery capacity and a pharmacy.
     * Initiates the Pharmacy instance and the Scooter instance with the provided data.
     * The method returns the validation of that instance of Drone. True if the data is correct and false if
     * it doesn't.
     */
    public boolean newDrone() {
        try {
            this.moDrone = moDroneService.newDrone(this.moVehicleModel, this.moPharmacy);
            return true;
        }
        catch(Exception ex) {
            this.moDrone = null;
        }
        return false;
    }

    /**
     * The method registers an order to the database.
     */
    public boolean registersDrone() {
        try {
            int intId = this.moDroneService.registerDrone(moDrone);
            this.moDrone.setId(intId);
            this.moVehicleService.generateQRCode(this.moDrone);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    /**
     * The method returns the list of pharmacies.
     */
    public List<Pharmacy> showPharmacies() {
        return this.moPharmacyService.getPharmacies();
    }

    /**
     * The method sets the scooter.
     */
    public void setDrone(Drone oDrone) {
        this.moDrone = oDrone;
    }

    /**
     * The method sets the Vehicle Model.
     */
    public boolean setVehicleModel(String strDesignation) {
        try {
            this.moVehicleModel = this.moVehicleService.getVehicleModel(strDesignation);
            return true;
        }
        catch(Exception ex) {
            this.moVehicleModel = null;
        }
        return false;
    }

    public boolean newVehicleModel(String strDesignation, double dblPotency, double dblWeight, double dblMaxPayload, int intBatteryCapacity,
                                   double dblBatteryVoltage, double dblEfficiency) {
        try {
            this.moVehicleModel = moVehicleService.newVehicleModel(strDesignation, dblPotency, dblWeight, dblMaxPayload, intBatteryCapacity,
                    dblBatteryVoltage, dblEfficiency, VehicleType.DRONE);
            int intModelId = this.moVehicleService.registerVehicleModel(this.moVehicleModel);
            this.moVehicleModel.setId(intModelId);
            return true;
        }
        catch(Exception ex) {
            this.moVehicleModel = null;
        }
        return false;
    }

    public boolean setPharmacy(String strPharmacyEmail){
        try {
            this.moPharmacy = this.moPharmacyService.getPharmacy(strPharmacyEmail);
            return true;
        } catch (Exception ex){
            return false;
        }
    }
}
