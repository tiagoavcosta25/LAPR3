package lapr.project.controller;

import lapr.project.model.Pharmacy;
import lapr.project.model.Scooter;
import lapr.project.model.service.PharmacyService;
import lapr.project.model.service.ScooterService;

import java.util.List;

/**
 * Remove Scooter Controller.
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
public class RemoveScooterController {

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
    public RemoveScooterController() {
        this.moPharmacyService = new PharmacyService();
        this.moScooterService = new ScooterService();
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
            return null;
        }
    }

    /**
     * The method removes a scooter from the database.
     * @return true if the Scooter is removed. False if otherwise.
     */
    public boolean removeScooter(int intScooterId){
        return moScooterService.removeScooterFromDB(intScooterId);
    }

}
