package lapr.project.controller;

import lapr.project.model.Drone;
import lapr.project.model.Pharmacy;
import lapr.project.model.service.DroneService;
import lapr.project.model.service.PharmacyService;

import java.util.Collections;
import java.util.List;

/**
 * Remove Drone Controller.
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
public class RemoveDroneController {
    /**
     * Pharmacy Management class
     */
    private PharmacyService moPharmacyService;

    /**
     * Scooter Management class
     */
    private DroneService moDroneService;
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
     *
     * @return Drone Service Instance.
     */
    public DroneService getDroneService() {
        return moDroneService;
    }

    /**
     * Modifies Drone Service Instance.
     * @param oDroneService Drone Service Instance.
     */
    public void setDroneService(DroneService oDroneService) {
        this.moDroneService = oDroneService;
    }

    /**
     * An empty constructor of RegisterDroneController.
     */
    public RemoveDroneController() {
        this.moPharmacyService = new PharmacyService();
        this.moDroneService = new DroneService();
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
    public List<Drone> showDronesList(String strPharmacyEmail) {
        try {
            return moDroneService.getDronesList(strPharmacyEmail);
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }

    /**
     * The method removes a scooter from the database.
     */
    public boolean removeDrone(int intDroneId){
        return moDroneService.removeDroneFromDB(intDroneId);
    }

}
