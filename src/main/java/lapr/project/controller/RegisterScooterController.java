package lapr.project.controller;

import lapr.project.model.Pharmacy;
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
     * An empty constructor of RegisterScooterController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public RegisterScooterController() {
        this.m_oPlatform = ApplicationPOT.getInstance().getPlatform();
        this.m_oPharmacyRegistration = m_oPlatform.getPharmacyReg();
        this.m_oScooterRegistration = m_oPlatform.getScooterReg();
    }

    /**
     * The method receives Scooter's battery percentage, charging status, potency, weight, battery capacity and a pharmacy.
     * Initiates the Pharmacy instance and the Scooter instance with the provided data.
     * The method returns the validation of that instance of Scooter. True if the data is correct and false if
     * it doesn't.
     * @param intBatteryPerc Scooter's battery percentage
     * @param intCharginStatus Scooter's charging status
     * @param intPotency Scooter's potency
     * @param intWeight Scooter's weight
     * @param intBatteryCapacity Scooter's battery capacity
     * @param oPharmacy Scooter's pharmacy
     */
    public void newScooter(int intBatteryPerc, int intCharginStatus, int intPotency,
                           int intWeight, int intBatteryCapacity, Pharmacy oPharmacy) {
        try {
            this.m_oPharmacy = m_oPharmacyRegistration.getPharmacy(oPharmacy.getId());
            this.m_oScooter = m_oScooterRegistration.newScooter(intBatteryPerc, intCharginStatus, intPotency,
                    intWeight, intBatteryCapacity, oPharmacy);
        }
        catch(RuntimeException ex) {
            this.m_oScooter = null;
        }
    }

    /**
     * The method registers an order to the database.
     */
    public void registersScooter() {
        this.m_oScooterRegistration.registerScooter(m_oScooter);
    }
}
