package lapr.project.controller;

import lapr.project.model.Courier;
import lapr.project.model.Pharmacy;
import lapr.project.model.Platform;
import lapr.project.model.registration.CourierRegistration;
import lapr.project.model.registration.PharmacyRegistration;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

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
     * Platform class instance
     */
    private Platform m_oPlatform;
    /**
     * Courier class instance
     */
    private Courier oCourier;
    /**
     * Courier Management class
     */
    private CourierRegistration oCourierRegistration;

    /**
     * Pharmacy Management class
     */
    private PharmacyRegistration oPharmacyRegistration;

    /**
     * An empty constructor of RegisterCourierController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public RegisterCourierController() {
        this.m_oPlatform = ApplicationPOT.getInstance().getPlatform();
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
            if (validateInput(strName, strEmail, intNIF, strIBAN)) {
                /*UserSession session = new UserSession();
                String email = session.getCurrentUserEmail();*/
                oCourierRegistration = m_oPlatform.getCourReg();
                oPharmacyRegistration = m_oPlatform.getPharmacyReg();
                String email = "user5@gmail.com";
                Pharmacy oPharmacy = oPharmacyRegistration.getPharmacyByManagerEmail(email);
                this.oCourier = oCourierRegistration.newCourier(strName, strEmail, intNIF, strIBAN, oPharmacy);
                return true;
            }
        } catch (RuntimeException | NoSuchAlgorithmException ex) {
            this.oCourier = null;
        }
        return false;
    }

    /**
     * The method adds a Freelancer to the Organization of the current user.
     */
    public boolean registersCourier() {
        return m_oPlatform.getCourReg().registersCourier(this.oCourier);
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

