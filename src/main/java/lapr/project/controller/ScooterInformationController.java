package lapr.project.controller;

import lapr.project.model.Pharmacy;
import lapr.project.model.Scooter;
import lapr.project.data.PharmacyDB;
import lapr.project.data.ScooterDB;
import lapr.project.model.UserSession;

import java.util.List;

public class ScooterInformationController {

    /**
     * Scooter class instance
     */
    //private Scooter m_oScooter;

    /**
     * Pharmacy class instance
     */
    private Pharmacy m_oPharmacy;

    /**
     * Pharmacy Management class
     */
    private PharmacyDB m_oPharmacyDB;

    /**
     * Scooter Management class
     */
    private ScooterDB m_oScooterDB;

    /**
     * Scooter's List
     */
    private List<Scooter> m_lstScooters;

    /**
     * An empty constructor of RegisterScooterController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public ScooterInformationController(String jdbcUrl, String username, String password) {
        this.m_oPharmacyDB = new PharmacyDB(jdbcUrl, username, password);
        this.m_oScooterDB = new ScooterDB(jdbcUrl, username, password);
    }

    public List<Scooter> getScootersList() {
        try {
            this.m_oPharmacy = m_oPharmacyDB.getPharmacyByManagerEmail(ApplicationPOT.getInstance().
                    getCurrentSession().getCurrentUserEmail());
            return m_oScooterDB.getScootersList(m_oPharmacy.getId());
        } catch (Exception ex) {
            return this.m_lstScooters = null;
        }
    }

    public Scooter getScooterInformation(int intScooterId){
        return m_oScooterDB.getScooter(intScooterId);
    }
}
