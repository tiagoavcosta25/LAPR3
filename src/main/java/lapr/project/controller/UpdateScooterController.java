package lapr.project.controller;

import lapr.project.model.Pharmacy;
import lapr.project.model.Scooter;
import lapr.project.model.service.PharmacyService;
import lapr.project.model.service.ScooterService;

import java.util.List;

public class UpdateScooterController {

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
    public UpdateScooterController() {
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
    public List<Scooter> showScootersList (int intPharmacyId) {
        try {
            return moScooterService.getScootersList(intPharmacyId);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * The method updates a scooter from the database.
     */
    public boolean updateScooter(int intId, double dblBatteryPerc, String strCharginStatus, double dblPotency,
                                 double dblWeight, int intBatteryCapacity, double dblBatteryVoltage,
                                 double dblMaxPayload, int intPharmacyId){

        return moScooterService.updateScooterFromDB(intId, dblBatteryPerc, strCharginStatus, dblPotency, dblWeight,
                intBatteryCapacity, dblBatteryVoltage, dblMaxPayload, intPharmacyId);
    }

}
