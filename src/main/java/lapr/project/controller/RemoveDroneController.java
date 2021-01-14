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
    private PharmacyService m_oPharmacyService;

    /**
     * Scooter Management class
     */
    private DroneService m_oDroneService;


    /**
     * An empty constructor of RegisterDroneController.
     */
    public RemoveDroneController() {
        this.m_oPharmacyService = new PharmacyService();
        this.m_oDroneService = new DroneService();
    }

    /**
     * The method returns the list of pharmacies.
     */
    public List<Pharmacy> showPharmacies() {
        return this.m_oPharmacyService.getPharmacies();
    }

    /**
     * The method returns the list of scooters.
     */
    public List<Drone> showDronesList(int intPharmacyId) {
        try {
            return m_oDroneService.getDronesList(intPharmacyId);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * The method removes a scooter from the database.
     */
    public boolean removeDrone(int intDroneId){
        return m_oDroneService.removeDroneFromDB(intDroneId);
    }

}
