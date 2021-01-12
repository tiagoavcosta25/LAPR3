package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.data.PharmacyManagerDB;
import lapr.project.data.PharmacyDB;
import java.security.NoSuchAlgorithmException;

public class RegisterPharmacyController {
    /**
     * Pharmacy class instance
     */
    private Pharmacy m_oPharmacy;

    /**
     * Pharmacy's Manager Management class
     */
    private PharmacyManagerDB m_oPharmacyManagerDB;

    /**
     * Pharmacy Management class
     */
    private PharmacyDB m_oPharmacyDB;

    /**
     * Pharmacy's Manager
     */
    private PharmacyManager m_oPharmacyManager;


    /**
     * An empty constructor of MakeAnOrderController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public RegisterPharmacyController(String jdbcUrl, String username, String password) {
        this.m_oPharmacyManagerDB = new PharmacyManagerDB(jdbcUrl, username, password);
        this.m_oPharmacyDB = new PharmacyDB(jdbcUrl, username, password);
    }

    public RegisterPharmacyController() {
    }

    public Pharmacy newPharmacy(String strManagerName, String strEmail, String strPassword, Integer intNIF, String strName, Double dblLatitude,
                                Double dblLongitude, String strStreetName, String strDoorNumber, String strPostalCode, String strLocality, String strCountry) {
        try {
            this.m_oPharmacyManager = this.m_oPharmacyManagerDB.newPharmacyManager(strEmail, strPassword, intNIF, strManagerName);
            this.m_oPharmacy = m_oPharmacyDB.newPharmacy(strName, this.m_oPharmacyManager, dblLatitude, dblLongitude, strStreetName, strDoorNumber,
                    strPostalCode, strLocality, strCountry);
            return this.m_oPharmacy;
        } catch (Exception ex) {
            this.m_oPharmacy = null;
            return null;
        }
    }

    /**
     * The method registers an order to the database.
     */
    public boolean registerPharmacy() {
        return this.m_oPharmacyDB.registerPharmacy(m_oPharmacy);
    }
    public void setPharmacy(Pharmacy p){
        this.m_oPharmacy = p;
    }
}
