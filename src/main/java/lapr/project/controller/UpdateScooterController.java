package lapr.project.controller;

import lapr.project.model.Pharmacy;
import lapr.project.model.Platform;
import lapr.project.model.Scooter;
import lapr.project.data.registration.PharmacyRegistration;
import lapr.project.data.registration.ScooterRegistration;

import java.util.List;

public class UpdateScooterController {

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
    public UpdateScooterController() {
        this.m_oPlatform = ApplicationPOT.getInstance().getPlatform();
        this.m_oPharmacyRegistration = m_oPlatform.getPharmacyReg();
        this.m_oUserSession = ApplicationPOT.getInstance().getCurrentSession();
        this.m_strUserEmail = m_oUserSession.getCurrentUserEmail();
        this.m_oScooterRegistration = ApplicationPOT.getInstance().getPlatform().getScooterReg();
    }

    public List<Scooter> getScootersList () {
        try {
            this.m_oPharmacy = m_oPharmacyRegistration.getPharmacyByManagerEmail(m_strUserEmail);
            return m_oScooterRegistration.getScootersList(m_oPharmacy.getId());
        } catch (RuntimeException ex) {
            return this.m_lstScooters = null;
        }
    }

    public boolean updateScooter(int intId, float fltBatteryPerc, String strCharginStatus, float fltPotency,
                                 float fltWeight, int intBatteryCapacity, float fltMaxPayload){

        return m_oScooterRegistration.updateScooterFromDB(intId, fltBatteryPerc, strCharginStatus, fltPotency, fltWeight,
                intBatteryCapacity, fltMaxPayload);
    }

}
