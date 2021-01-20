package lapr.project.controller;

import lapr.project.model.Pharmacy;
import lapr.project.model.Scooter;
import lapr.project.model.service.PharmacyService;
import lapr.project.model.service.ScooterService;

import java.util.List;

public class ScooterInformationController {

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
    public ScooterInformationController() {
        this.moPharmacyService = new PharmacyService();
        this.moScooterService = new ScooterService();
    }

    /**
     * The method returns the list of pharmacies.
     */
    public List<Pharmacy> showPharmacies() {
        return this.moPharmacyService.getPharmacies();
    }


    /**
     * The method returns the list of scooters.
     */
    public List<Scooter> showScootersList(int intPharmacyId) {
        try {
            return moScooterService.getScootersList(intPharmacyId);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * The method shows the scooter information from the database.
     */
    public Scooter getScooterInformation(int intScooterId){
        return moScooterService.getScooter(intScooterId);
    }
}
