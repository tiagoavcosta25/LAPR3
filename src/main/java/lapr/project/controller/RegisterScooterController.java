package lapr.project.controller;

import lapr.project.model.Pharmacy;
import lapr.project.model.Scooter;
import lapr.project.model.VehicleModel;
import lapr.project.model.service.PharmacyService;
import lapr.project.model.service.ScooterService;

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
     * An empty constructor of RegisterScooterController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public RegisterScooterController() {
        this.moPharmacyService = new PharmacyService();
        this.moScooterService = new ScooterService();
    }

    /**
     * The method receives Scooter's battery percentage, charging status, potency, weight, battery capacity and a pharmacy.
     * Initiates the Pharmacy instance and the Scooter instance with the provided data.
     * The method returns the validation of that instance of Scooter. True if the data is correct and false if
     * it doesn't.
     * @param oVehicleModel Scooter's Vehicle Model
     * @param oPharmacy Pharmacy's instance
     */
    public boolean newScooter(VehicleModel oVehicleModel, Pharmacy oPharmacy) {
        try {
            this.moScooter = moScooterService.newScooter(oVehicleModel, oPharmacy);
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
       return this.moScooterService.registerScooter(moScooter);
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
}
