package lapr.project.controller;

import lapr.project.model.Pharmacy;
import lapr.project.model.Platform;
import lapr.project.model.Scooter;
import lapr.project.model.registration.PharmacyRegistration;
import lapr.project.model.registration.ScooterRegistration;

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
     * An empty constructor of RegisterScooterController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public UpdateScooterController() {
        this.m_oPlatform = ApplicationPOT.getInstance().getPlatform();
        this.m_oPharmacyRegistration = m_oPlatform.getPharmacyReg();
    }

    public List<Scooter> showScootersList (Pharmacy oPharmacy) {
        try {
            this.m_oPharmacy = m_oPharmacyRegistration.getPharmacy(oPharmacy.getId());
            return this.m_lstScooters;
        } catch (RuntimeException ex) {
            return this.m_lstScooters = null;
        }
    }

    public boolean updateScooter(int intId, int intBatteryPerc, int intCharginStatus, int intPotency,
                              int intWeight, int intBatteryCapacity){

        this.m_oScooterRegistration = m_oPlatform.getScooterReg();
        return m_oScooterRegistration.updateScooterFromDB(intId, intBatteryPerc, intCharginStatus, intPotency, intWeight,
                intBatteryCapacity);
    }

}
