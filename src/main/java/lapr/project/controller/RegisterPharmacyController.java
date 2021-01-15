package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.service.PharmacyService;

public class RegisterPharmacyController {
    /**
     * Pharmacy class instance
     */
    private Pharmacy m_oPharmacy;

    /**
     * Pharmacy Management class
     */
    private PharmacyService m_oPharmacyService;


    /**
     * An empty constructor of MakeAnOrderController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public RegisterPharmacyController() {
        this.m_oPharmacyService = new PharmacyService();
    }

    public Pharmacy newPharmacy(String strName, String strEmail, Double dblLatitude, Double dblLongitude, String strStreetName,
                                String strDoorNumber, String strPostalCode, String strLocality, String strCountry) {
        try {
            this.m_oPharmacy = m_oPharmacyService.newPharmacy(strName, strEmail, dblLatitude, dblLongitude, strStreetName, strDoorNumber,
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
        return this.m_oPharmacyService.registerPharmacy(m_oPharmacy);
    }
    public void setPharmacy(Pharmacy p){
        this.m_oPharmacy = p;
    }
    public void setPharmacyService(PharmacyService p){
        this.m_oPharmacyService = p;
    }
}
