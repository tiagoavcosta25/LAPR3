package lapr.project.controller;

import lapr.project.model.Platform;
import lapr.project.model.Scooter;
import lapr.project.model.registration.PharmacyRegistration;
import lapr.project.model.registration.ScooterRegistration;

public class RegisterScooterController {

    /**
     * Platform class instance
     */
    private final Platform m_oPlatform;

    /**
     * Scooter class instance
     */
    private Scooter m_oScooter;

    /**
     * Pharmacy Management class
     */
    private PharmacyRegistration m_oPharmacyRegistration;

    /**
     * Scooter Management class
     */
    private ScooterRegistration m_oScooterRegistration;

    /**
     * An empty constructor of RegisterScooterController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public RegisterScooterController() {
        this.m_oPlatform = ApplicationPOT.getInstance().getPlatform();
        this.m_oPharmacyRegistration = m_oPlatform.getPharmacyReg();
        this.m_oScooterRegistration = m_oPlatform.getScooterReg();
    }

    public boolean newScooter(int intBatteryPerc, int intCharginStatus, int intPotency,
                              int intWeight, int intBatteryCapacity)
    {
        try
        {
            this.m_oScooter = m_oScooterRegistration.newScooter( intBatteryPerc, intCharginStatus, intPotency,
                    intWeight, intBatteryCapacity);
            return true;
        }
        catch(RuntimeException ex)
        {
            this.m_oScooter = null;
            return false;        }
    }
}
