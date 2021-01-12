package lapr.project.controller;

import lapr.project.model.Pharmacy;
import lapr.project.model.Scooter;
import lapr.project.data.PharmacyDB;
import lapr.project.data.ScooterDB;

import java.util.List;

public class RegisterScooterController {

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
    private PharmacyDB m_oPharmacyDB;

    /**
     * Scooter Management class
     */
    private ScooterDB m_oScooterDB;

    /**
     * An empty constructor of RegisterScooterController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public RegisterScooterController(String jdbcUrl, String username, String password) {
        this.m_oPharmacyDB = new PharmacyDB(jdbcUrl, username, password);
        this.m_oScooterDB = new ScooterDB(jdbcUrl, username, password);
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
            this.m_oPharmacy = m_oPharmacyDB.getPharmacy(oPharmacy.getId());
            this.m_oScooter = m_oScooterDB.newScooter(fltBatteryPerc, strCharginStatus, fltPotency,
                    fltWeight, intBatteryCapacity, fltMaxPayload, oPharmacy);
            return true;
        }
        catch(Exception ex) {
            this.m_oScooter = null;
        }
        return false;
    }

    /**
     * The method registers an order to the database.
     */
    public boolean registersScooter() {
       return this.m_oScooterDB.registerScooter(m_oScooter);
    }

    /**
     * The method returns the list of scooters for a pharmacy.
     */
    public List<Scooter> getScooters(int intPharmacyId) {
        return this.m_oScooterDB.getScootersList(intPharmacyId);
    }

    public void setScooter(Scooter oScooter) {
         this.m_oScooter = oScooter;
    }
}
