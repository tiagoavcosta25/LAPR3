package lapr.project.controller;

import lapr.project.model.Pharmacy;
import lapr.project.model.Platform;
import lapr.project.model.Scooter;
import lapr.project.data.registration.PharmacyRegistration;
import lapr.project.data.registration.ScooterRegistration;

import java.util.List;

public class ScooterInformationController {
    /**
     * Platform class instance
     */
    private final Platform m_oPlatform;

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
    private PharmacyRegistration m_oPharmacyRegistration;

    /**
     * Scooter Management class
     */
    private ScooterRegistration m_oScooterRegistration;

    /**
     * Scooter's List
     */
    private List<Scooter> m_lstScooters;

    /**
     * User Session Class Instance
     */
    private UserSession m_oUserSession;

    /**
     * User's Email
     */
    private String m_strUserEmail;

    /**
     * An empty constructor of RegisterScooterController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public ScooterInformationController() {
        this.m_oPlatform = ApplicationPOT.getInstance().getPlatform();
        this.m_oPharmacyRegistration = m_oPlatform.getPharmacyReg();
        this.m_strUserEmail = m_oUserSession.getCurrentUserEmail();
    }

    public List<Scooter> getScootersList() {
        try {
            this.m_oPharmacy = m_oPharmacyRegistration.getPharmacyByManagerEmail(m_strUserEmail);
            return m_oScooterRegistration.getScootersList(m_oPharmacy.getId());
        } catch (RuntimeException ex) {
            return this.m_lstScooters = null;
        }
    }

    public Scooter getScooterInformation(int intScooterId){
        return m_oScooterRegistration.getScooter(intScooterId);
    }
}
