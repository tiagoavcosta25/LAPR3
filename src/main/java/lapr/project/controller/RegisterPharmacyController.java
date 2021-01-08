package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.registration.PharmacyManagerRegistration;
import lapr.project.model.registration.PharmacyRegistration;
import java.security.NoSuchAlgorithmException;

public class RegisterPharmacyController {
    /**
     * Platform class instance
     */
    private Platform m_oPlatform;
    /**
     * Pharmacy class instance
     */
    private Pharmacy m_oPharmacy;

    /**
     * Pharmacy's Manager Management class
     */
    private PharmacyManagerRegistration m_oPharmacyManagerRegistration;

    /**
     * Pharmacy Management class
     */
    private PharmacyRegistration m_oPharmacyRegistration;

    /**
     * Pharmacy's Manager
     */
    private PharmacyManager m_oPharmacyManager;


    /**
     * An empty constructor of MakeAnOrderController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public RegisterPharmacyController() {
        this.m_oPlatform = ApplicationPOT.getInstance().getPlatform();
        this.m_oPharmacyManagerRegistration = m_oPlatform.getPharmacyManagerReg();
        this.m_oPharmacyRegistration = m_oPlatform.getPharmacyReg();
    }

    public void newPharmacy(String strManagerName, String strEmail, String strPassword, Integer intNIF, String strName,Double dblLatitude,
                            Double dblLongitude,String strStreetName, String strDoorNumber, String strPostalCode, String strLocality, String strCountry) {
        try {
            this.m_oPharmacyManager = this.m_oPharmacyManagerRegistration.newPharmacyManager(strEmail, strPassword, intNIF, strManagerName);
            this.m_oPharmacy = m_oPharmacyRegistration.newPharmacy(strName, this.m_oPharmacyManager, dblLatitude, dblLongitude, strStreetName, strDoorNumber,
                    strPostalCode, strLocality, strCountry);
        } catch (RuntimeException | NoSuchAlgorithmException ex) {
            this.m_oPharmacy = null;
        }
    }

    /**
     * The method registers an order to the database.
     */
    public void registerOrder() {
        this.m_oPharmacyRegistration.registerPharmacy(m_oPharmacy);
    }
}