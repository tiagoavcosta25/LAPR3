package lapr.project.controller;

import lapr.project.model.Pharmacy;
import lapr.project.model.Platform;
import lapr.project.model.Scooter;
import lapr.project.model.registration.PharmacyRegistration;
import lapr.project.model.registration.ScooterRegistration;

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
     * An empty constructor of RegisterScooterController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public ScooterInformationController() {
        this.m_oPlatform = ApplicationPOT.getInstance().getPlatform();
        this.m_oPharmacyRegistration = m_oPlatform.getPharmacyReg();
    }

    public List<Scooter> showScootersList (Pharmacy oPharmacy) {
        try {
            this.m_oPharmacy = m_oPharmacyRegistration.getPharmacy(oPharmacy.getId());
            return m_oScooterRegistration.getScootersList(oPharmacy.getId());
        } catch (RuntimeException ex) {
            return this.m_lstScooters = null;
        }
    }

    public Scooter getScooterInformation(int intScooterId){
        return m_oScooterRegistration.getScooter(intScooterId);
    }
}
