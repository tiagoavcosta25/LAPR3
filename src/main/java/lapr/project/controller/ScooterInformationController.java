package lapr.project.controller;

import lapr.project.model.Pharmacy;
import lapr.project.model.Scooter;
import lapr.project.model.service.PharmacyService;
import lapr.project.model.service.ScooterService;

import java.util.Collections;
import java.util.List;

/**
 * Scooter Information Controller.
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
     * The method returns the list of pharmacies.
     * @return List of Pharmacies.
     */
    public List<Pharmacy> showPharmacies() {
        return this.moPharmacyService.getPharmacies();
    }


    /**
     * The method returns the list of scooters.
     * @return List of Scooters.
     */
    public List<Scooter> showScootersList(String strPharmacyEmail) {
        try {
            return moScooterService.getScootersList(strPharmacyEmail);
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }

    /**
     * The method gets the scooter information from the database.
     * @return Scooter Information.
     */
    public Scooter getScooterInformation(int intScooterId){
        return moScooterService.getScooter(intScooterId);
    }
}
