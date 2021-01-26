package lapr.project.controller;

import lapr.project.model.Courier;
import lapr.project.model.Pharmacy;
import lapr.project.model.service.CourierService;
import lapr.project.model.service.PharmacyService;

/**
 * Update Courier Controller.
 * <p>
 * Group: Team Lisa [G-021]
 * ______________________________________________________
 *
 * @author António Barbosa <1190404@isep.ipp.pt>
 * @author Ernesto Rodrigues <1190560@isep.ipp.pt>
 * @author Jessica Alves <1190682@isep.ipp.pt>
 * @author Pedro Santos <1190967@isep.ipp.pt>
 * @author Rodrigo Costa <1191014@isep.ipp.pt>
 * @author Tiago Costa <1191460@isep.ipp.pt>
 */
public class UpdateCourierController {
    /**
     * Courier class instance
     */
    private Courier moCourier;

    /**
     * Courier Service class
     */
    private CourierService moCourierService;

    /**
     * Pharmacy Service class
     */
    private PharmacyService moPharmacyService;

    /**
     * Pharmacy class instance
     */
    private Pharmacy moPharmacy;

    /**
     * A constructor of UpdateCourierController that initiates the Courier Service and Pharmacy Service.
     */
    public UpdateCourierController() {
        this.moCourierService = new CourierService();
        this.moPharmacyService = new PharmacyService();
    }

    /**
     * Recieves the Courier's email, validates and returns the Courier's instance.
     *
     * @param email Courier's email
     * @return the Courier's instance
     */
    public Courier getCourierByEmail(String email) {
        try {
            if (validateInput(email)) {
                this.moCourier = moCourierService.getCourierByEmail(email);
                return moCourier;
            }
        } catch (Exception ex) {
            this.moCourier = null;
        }
        return this.moCourier;
    }

    /**
     * The method recieves the Courier's instance, name, email, nif, iban, Courier's pharmacy email,
     * validates and updates the values.
     *
     * @param courier       Courier's instance
     * @param strName       Courier's name
     * @param strEmail      Courier's email
     * @param intNif        Courier's nif
     * @param strIban       Courier's iban
     * @param pharmacyEmail Courier's pharmacy  email
     * @return the Courier's instance
     */
    public Courier updateCourier(Courier courier, String strName, String strEmail, Integer intNif, String strIban, String pharmacyEmail) {
        try {
            validateInput(strName, strEmail, intNif, strIban);
            moPharmacy = this.moPharmacyService.getPharmacy(pharmacyEmail);
            moCourier = this.moCourierService.updateCourier(courier, strName, strEmail, intNif, strIban, moPharmacy);
            return this.moCourier;
        } catch (Exception ex) {
            this.moCourier = null;
        }
        return null;
    }

    /**
     * Uses the Courier's instance and update's it on the Database.
     *
     * @return true if the Courier is updated on the Database. False otherwise.
     */
    public boolean updateCourierDB() {
        return moCourierService.updateCourierDB(moCourier);
    }

    /**
     * The method sets the courier.
     */
    public void setCourier(Courier oCourier) {
        this.moCourier = oCourier;
    }


    /**
     * Validates the input information regarding
     * a Courier
     *
     * @param strName  Courier's name.
     * @param strEmail Courier's email.
     * @param intNIF   Courier's nif.
     * @param strIBAN  Courier's iban.
     * @return True if input is valid, false if otherwise
     */
    public boolean validateInput(String strName, String strEmail, Integer intNIF, String strIBAN) {

        if (strName.isEmpty() || intNIF < 0 || strEmail.isEmpty() || strIBAN.isEmpty()) return false;

        if (!strEmail.contains("@")) return false;

        return (strIBAN.trim().length() == 25)
                && (int) (Math.log10(intNIF) + 1) == 9;
    }

    /**
     * Validates the input information regarding
     * a Courier
     *
     * @param email Courier's email.
     * @return True if input is valid, false if otherwise
     */
    public boolean validateInput(String email) {

        return (!(email == null || email.isEmpty() || !email.contains("@")));

    }

    /**
     * The method returns the Courier Service
     *
     * @return the Courier's Service
     */
    public CourierService getMoCourierService() {
        return moCourierService;
    }

    /**
     * The methods sets the Courier Service
     *
     * @param moCourierService Courier Service class
     */
    public void setMoCourierService(CourierService moCourierService) {
        this.moCourierService = moCourierService;
    }

    /**
     * The method returns the Pharmacy Service
     *
     * @return the Pharmacies's Service
     */
    public PharmacyService getMoPharmacyService() {
        return moPharmacyService;
    }

    /**
     * The methods sets the Pharmacy Service
     *
     * @param moPharmacyService Pharmacy Service class
     */
    public void setMoPharmacyService(PharmacyService moPharmacyService) {
        this.moPharmacyService = moPharmacyService;
    }

    /**
     * Returns the Pharmacy instance
     *
     * @return Pharmacy instance
     */
    public Pharmacy getMoPharmacy() {
        return moPharmacy;
    }

    /**
     * Recieves a Pharmacy instance and sets it.
     *
     * @param moPharmacy Pharamcy instance
     */
    public void setMoPharmacy(Pharmacy moPharmacy) {
        this.moPharmacy = moPharmacy;
    }
}

