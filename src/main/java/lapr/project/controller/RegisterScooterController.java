package lapr.project.controller;

import lapr.project.model.Pharmacy;
import lapr.project.model.Scooter;
import lapr.project.data.PharmacyDB;
import lapr.project.data.ScooterDB;
import lapr.project.model.service.OrderService;
import lapr.project.model.service.PharmacyService;
import lapr.project.model.service.ScooterService;

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
    private PharmacyService m_oPharmacyService;

    /**
     * Scooter Management class
     */
    private ScooterService m_oScooterService;

    /**
     * An empty constructor of RegisterScooterController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public RegisterScooterController() {
        this.m_oPharmacyService = new PharmacyService();
        this.m_oScooterService = new ScooterService();
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
            this.m_oPharmacy = m_oPharmacyService.getPharmacy(oPharmacy.getId());
            this.m_oScooter = m_oScooterService.newScooter(fltBatteryPerc, strCharginStatus, fltPotency,
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
       return this.m_oScooterService.registerScooter(m_oScooter);
    }

    /**
     * The method returns the list of pharmacies.
     */
    public List<Pharmacy> showPharmacies() {
        return this.m_oPharmacyService.getPharmacies();
    }

    /**
     * The method sets the scooter.
     */
    public void setScooter(Scooter oScooter) {
         this.m_oScooter = oScooter;
    }
}
