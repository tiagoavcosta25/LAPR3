package lapr.project.controller;

import lapr.project.model.Pharmacy;
import lapr.project.model.Scooter;
import lapr.project.model.VehicleModel;
import lapr.project.model.VehicleType;
import lapr.project.model.service.PharmacyService;
import lapr.project.model.service.ScooterService;
import lapr.project.model.service.VehicleService;

import java.util.List;

public class RegisterScooterController {

    /**
     * Scooter class instance
     */
    private Scooter moScooter;

    /**
     * Pharmacy Management class
     */
    private PharmacyService moPharmacyService;

    /**
     * Scooter Management class
     */
    private ScooterService moScooterService;

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
     * An empty constructor of RegisterScooterController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public RegisterScooterController() {
        this.moPharmacyService = new PharmacyService();
        this.moScooterService = new ScooterService();
        this.moVehicleService = new VehicleService();
    }

    /**
     * The method receives Scooter's battery percentage, charging status, potency, weight, battery capacity and a pharmacy.
     * Initiates the Pharmacy instance and the Scooter instance with the provided data.
     * The method returns the validation of that instance of Scooter. True if the data is correct and false if
     * it doesn't.
     */
    public boolean newScooter() {
        try {
            this.moScooter = moScooterService.newScooter(this.moVehicleModel, this.moPharmacy);
            return true;
        }
        catch(Exception ex) {
            this.moScooter = null;
        }
        return false;
    }

    /**
     * The method registers an order to the database.
     */
    public boolean registersScooter() {
        try {
            int intId = this.moScooterService.registerScooter(moScooter);
            this.moScooter.setId(intId);
            this.moVehicleService.generateQRCode(this.moScooter);
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
    public void setScooter(Scooter oScooter) {
         this.moScooter = oScooter;
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
                    dblBatteryVoltage, dblEfficiency, VehicleType.SCOOTER);
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
