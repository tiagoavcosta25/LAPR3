package lapr.project.controller;

import lapr.project.model.Pharmacy;
import lapr.project.model.Scooter;
import lapr.project.data.PharmacyDB;
import lapr.project.data.ScooterDB;
import lapr.project.model.UserSession;
import lapr.project.model.service.PharmacyService;
import lapr.project.model.service.ScooterService;

import java.util.List;

public class ScooterInformationController {

    /**
     * Pharmacy Management class
     */
    private PharmacyService m_oPharmacyService;

    /**
     * Scooter Management class
     */
    private ScooterService m_oScooterService;

    /**
     * An empty constructor of RegisterScooterController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public ScooterInformationController() {
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
            return null;
        }
    }

    /**
     * The method shows the scooter information from the database.
     */
    public Scooter getScooterInformation(int intScooterId){
        return m_oScooterService.getScooter(intScooterId);
    }
}
