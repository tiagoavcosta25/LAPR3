package lapr.project.controller;

import lapr.project.model.Courier;
import lapr.project.model.Pharmacy;
import lapr.project.data.CourierDB;
import lapr.project.data.PharmacyDB;

import java.security.NoSuchAlgorithmException;

/**
 * Register Courier Controller.
 * <p>
 * Group: Team Lisa [G-037]
 * ______________________________________________________
 *
 * @author Ernesto Rodrigues <1190560@isep.ipp.pt>
 * @author Jessica Alves <1190682@isep.ipp.pt>
 * @author Pedro Santos <1190967@isep.ipp.pt>
 * @author Rodrigo Costa <1191014@isep.ipp.pt>
 */
public class RegisterCourierController {
    /**
     * Courier class instance
     */
    private Courier oCourier;
    /**
     * Courier Management class
     */
    private CourierDB oCourierDB;

    /**
     * Pharmacy Management class
     */
    private PharmacyDB oPharmacyDB;

    /**
     * Pharmacy
     */
    private Pharmacy oPharmacy;

    /**
     * An empty constructor of RegisterCourierController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public RegisterCourierController(String jdbcUrl, String username, String password) {
        this.oCourierDB = new CourierDB(jdbcUrl, username, password);
        this.oPharmacyDB = new PharmacyDB(jdbcUrl, username, password);
    }

    /**
     * The method receives Courier's name, email, nif and iban.
     * Initiates the CourierRegistration instance and the Courier instance with the provided data.
     * The method returns the validation of that instance of Courier. True if the data is correct and false if
     * it doesn't.
     *
     * @param strName  Courier's name.
     * @param strEmail Courier's email.
     * @param intNIF   Courier's nif.
     * @param strIBAN  Courier's iban.
     */
    public boolean newCourier(String strName, String strEmail, Integer intNIF, String strIBAN) {
        try {
            this.oPharmacy = oPharmacyDB.getPharmacyByManagerEmail(ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail());
            if (validateInput(strName, strEmail, intNIF, strIBAN)) {
                this.oCourier = oCourierDB.newCourier(strName, strEmail, intNIF, strIBAN, this.oPharmacy);
                return true;
            }
        } catch (Exception ex) {
            this.oCourier = null;
        }
        return false;
    }

    /**
     * The method adds a Freelancer to the Organization of the current user.
     */
    public boolean registersCourier() {
        return this.oCourierDB.registersCourier(this.oCourier);
    }

    /**
     * The method sets the courier.
     */
    public void setCourier(Courier oCourier) {
        this.oCourier = oCourier;
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

        if (strName.isEmpty() || intNIF <= 0 || strEmail.isEmpty() || strIBAN.isEmpty()) return false;

        if (!strEmail.contains("@")) return false;

        if ((strIBAN.trim().length() != 25)
                || (int) (Math.log10(intNIF) + 1) != 9) return false;

        return true;
    }

}

