package lapr.project.controller;

import lapr.project.model.Drone;
import lapr.project.model.Pharmacy;
import lapr.project.model.service.DroneService;
import lapr.project.model.service.PharmacyService;

import java.util.Collections;
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

    public PharmacyService getPharmacyService() {
        return moPharmacyService;
    }

    public void setPharmacyService(PharmacyService moPharmacyService) {
        this.moPharmacyService = moPharmacyService;
    }

    public DroneService getDroneService() {
        return moDroneService;
    }

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
