package lapr.project.controller;

import lapr.project.model.Pharmacy;
import lapr.project.model.Scooter;
import lapr.project.model.service.PharmacyService;
import lapr.project.model.service.ScooterService;

import java.util.Collections;
import java.util.List;

/**
 * Update Scooter Controller.
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
public class UpdateScooterController {

    /**
     * Pharmacy Management class.
     */
    private PharmacyService moPharmacyService;

    /**
     * Scooter Management class.
     */
    private ScooterService moScooterService;

    /**
     * An empty constructor of RegisterScooterController.
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
    public List<Scooter> showScootersList (String strPharmacyEmail) {
        try {
            List<Scooter> oList = moScooterService.getScootersList(strPharmacyEmail);
            if(oList.isEmpty()){
                throw new Exception();
            }
            return oList;
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }

    /**
     * The method updates a scooter from the database.
     */
    public boolean updateScooter(int intId, double dblBatteryPerc, String strDesignation, double dblPotency, double dblWeight,
                                 double dblMaxPayload, int intBatteryCapacity, double dblBatteryVoltage,
                                 double dblEfficiency){

        return moScooterService.updateScooterFromDB(intId, dblBatteryPerc, strDesignation, dblPotency, dblWeight, dblMaxPayload,
                intBatteryCapacity, dblBatteryVoltage, dblEfficiency);
    }

    /**
     * Returns the Pharmacy Service Instance.
     * @return Pharmacy Service Instance.
     */
    public PharmacyService getMoPharmacyService() {
        return moPharmacyService;
    }

    /**
     * Modifies Pharmacy Service Instance.
     * @param moPharmacyService Pharmacy Service Instance.
     */
    public void setMoPharmacyService(PharmacyService moPharmacyService) {
        this.moPharmacyService = moPharmacyService;
    }

    /**
     * Returns the Scooter Service Instance.
     * @return Scooter Service Instance.
     */
    public ScooterService getMoScooterService() {
        return moScooterService;
    }

    /**
     * Modifies Scooter Service Instance.
     * @param moScooterService Scooter Service Instance.
     */
    public void setMoScooterService(ScooterService moScooterService) {
        this.moScooterService = moScooterService;
    }
}
