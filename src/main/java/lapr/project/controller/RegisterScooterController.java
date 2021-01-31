package lapr.project.controller;

import lapr.project.model.Pharmacy;
import lapr.project.model.Scooter;
import lapr.project.model.VehicleModel;
import lapr.project.model.VehicleType;
import lapr.project.model.service.PharmacyService;
import lapr.project.model.service.ScooterService;
import lapr.project.model.service.VehicleService;

import java.util.List;

/**
 * Register Scooter Controller.
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
public class RegisterScooterController {

    /**
     * Scooter class instance.
     */
    private Scooter moScooter;

    /**
     * Pharmacy Management class.
     */
    private PharmacyService moPharmacyService;

    /**
     * Scooter Management class.
     */
    private ScooterService moScooterService;

    /**
     * VehicleModel class instance.
     */
    private VehicleModel moVehicleModel;

    /**
     * Vehicle Management class.
     */
    private VehicleService moVehicleService;

    private Pharmacy moPharmacy;

    /**
     * An empty constructor of RegisterScooterController.
     */
    public RegisterScooterController() {
        this.moPharmacyService = new PharmacyService();
        this.moScooterService = new ScooterService();
        this.moVehicleService = new VehicleService();
    }

    /**
     * The method creates a new Scooter.
     * @return true if the Scooter is created. False if otherwise.
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
     * @return true if the Scooter is registered. False if otherwise.
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
     * @return List of Pharmacies.
     */
    public List<Pharmacy> showPharmacies() {
        return this.moPharmacyService.getPharmacies();
    }

    /**
     * Modifies the Scooter.
     */
    public void setScooter(Scooter oScooter) {
         this.moScooter = oScooter;
    }

    /**
     * Modifies the Vehicle Model.
     * @return true if the Vehicle Model is modified. False if otherwise.
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
     * @return true if the Vehicle Model is created. False if otherwise.
     */
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

    /**
     * Modifies the Phramacy.
     * @param strPharmacyEmail Phramacy Email.
     * @return true if the Pharmacy is modified. False if otherwise.
     */
    public boolean setPharmacy(String strPharmacyEmail){
        try {
            this.moPharmacy = this.moPharmacyService.getPharmacy(strPharmacyEmail);
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    /**
     * Returns the Pharmacy Service Instance.
     * @return Pharmacy Service Instance.
     */
    public PharmacyService getPharmacyService() {
        return moPharmacyService;
    }

    /**
     * Modifies Pharmacy Service Instance.
     * @param oPharmacyService Pharmacy Service Instance.
     */
    public void setPharmacyService(PharmacyService oPharmacyService) {
        this.moPharmacyService = oPharmacyService;
    }

    /**
     * Returns the Scooter Service Instance.
     * @return Scooter Service Instance.
     */
    public ScooterService getScooterService() {
        return moScooterService;
    }

    /**
     * Modifies Scooter Service Instance.
     * @param oScooterService Scooter Service Instance.
     */
    public void setScooterService(ScooterService oScooterService) {
        this.moScooterService = oScooterService;
    }

    /**
     * Returns the Vehicle Service Instance.
     * @return Vehicle Service Instance.
     */
    public VehicleService getVehicleService() {
        return moVehicleService;
    }

    /**
     * Modifies Vehicle Service Instance.
     * @param oVehicleService Vehicle Service Instance.
     */
    public void setVehicleService(VehicleService oVehicleService) {
        this.moVehicleService = oVehicleService;
    }
}
