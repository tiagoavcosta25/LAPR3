package lapr.project.controller;

import lapr.project.model.Pharmacy;
import lapr.project.model.Scooter;
import lapr.project.data.PharmacyDB;
import lapr.project.data.ScooterDB;
import lapr.project.model.UserSession;
import lapr.project.model.service.PharmacyService;
import lapr.project.model.service.ScooterService;

import java.util.List;

public class RemoveScooterController {

    /**
     * Scooter class instance
     */
    private Scooter m_oScooter;

    /**
     * Pharmacy class instance
     */
    private Pharmacy m_oPharmacy;

    /**
     * Pharmacy Management class
     */
    private PharmacyService m_oPharmacyService;

    /**
     * Scooter Management class
     */
    private ScooterService m_oScooterService;

    /**
     * Scooter's List
     */
    private List<Scooter> m_lstScooters;


    /**
     * An empty constructor of RegisterScooterController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public RemoveScooterController() {
        this.m_oPharmacyService = new PharmacyService();
        this.m_oScooterService = new ScooterService();
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
    public List<Scooter> showScootersList(int intPharmacyId) {
        try {
            return m_oScooterService.getScootersList(intPharmacyId);
        } catch (Exception ex) {
            return this.m_lstScooters = null;
        }
    }

    /**
     * The method removes a scooter from the database.
     */
    public boolean removeScooter(int intScooterId){
        return m_oScooterService.removeScooterFromDB(intScooterId);
    }

}
