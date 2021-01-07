package lapr.project.controller;

import lapr.project.model.Courier;
import lapr.project.model.Platform;
import lapr.project.model.registration.CourierRegistration;

import java.io.Serializable;

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
public class RegisterCourierController implements Serializable {
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
     * @param strName  Courier's Id.
     * @param strEmail Courier's email.
     * @param strNIF   Courier's nif.
     * @param strIBAN  Courier's iban.
     */
    public void newCourier(String strName, String strEmail, String strNIF, String strIBAN) {
        try {
            oCourierRegistration = m_oPlatform.getCourReg();
            this.oCourier = oCourierRegistration.newCourier(strName, strEmail, strNIF, strIBAN);
        } catch (RuntimeException ex) {
            this.oCourier = null;
        }
    }

    /**
     * The method adds a Freelancer to the Organization of the current user.
     */
    public void registersCourier() {
        m_oPlatform.getCourReg().registersCourier(this.oCourier);
    }

}

