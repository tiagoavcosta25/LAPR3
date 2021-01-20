package lapr.project.controller;

import lapr.project.model.Drone;
import lapr.project.model.Pharmacy;
import lapr.project.model.service.DroneService;
import lapr.project.model.service.PharmacyService;

import java.util.List;

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
    public List<Drone> showDronesList(int intPharmacyId) {
        try {
            return moDroneService.getDronesList(intPharmacyId);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * The method removes a scooter from the database.
     */
    public boolean removeDrone(int intDroneId){
        return moDroneService.removeDroneFromDB(intDroneId);
    }

}
