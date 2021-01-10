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
     * @param fltBatteryPerc Scooter's battery percentage
     * @param strCharginStatus Scooter's charging status
     * @param fltPotency Scooter's potency
     * @param fltWeight Scooter's weight
     * @param intBatteryCapacity Scooter's battery capacity
     * @param oPharmacy Scooter's pharmacy
     */
    public boolean newScooter(float fltBatteryPerc, String strCharginStatus, float fltPotency,
                           float fltWeight, int intBatteryCapacity, float fltMaxPayload, Pharmacy oPharmacy) {
        try {
            this.m_oPharmacy = m_oPharmacyRegistration.getPharmacy(oPharmacy.getId());
            this.m_oScooter = m_oScooterRegistration.newScooter(fltBatteryPerc, strCharginStatus, fltPotency,
                    fltWeight, intBatteryCapacity, fltMaxPayload, oPharmacy);
            return true;
        }
        catch(RuntimeException ex) {
            this.m_oScooter = null;
        }
        return false;
    }

    /**
     * The method registers an order to the database.
     */
    public boolean registersScooter() {
       return this.m_oScooterRegistration.registerScooter(m_oScooter);
    }
}
