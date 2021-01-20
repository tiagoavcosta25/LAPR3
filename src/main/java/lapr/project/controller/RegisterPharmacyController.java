package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.service.PharmacyService;

public class RegisterPharmacyController {
    /**
     * Pharmacy class instance
     */
    private Pharmacy moPharmacy;

    /**
     * Pharmacy Management class
     */
    private PharmacyService moPharmacyService;


    /**
     * An empty constructor of MakeAnOrderController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public RegisterPharmacyController() {
        this.moPharmacyService = new PharmacyService();
    }

    public Pharmacy newPharmacy(String strName, String strEmail, Double dblLatitude, Double dblLongitude, Double dblAltitude, String strStreetName,
                                String strDoorNumber, String strPostalCode, String strLocality, String strCountry) {
        try {
            this.moPharmacy = moPharmacyService.newPharmacy(strName, strEmail, dblLatitude, dblLongitude, dblAltitude, strStreetName, strDoorNumber,
                    strPostalCode, strLocality, strCountry);
            return this.moPharmacy;
        } catch (Exception ex) {
            this.moPharmacy = null;
            return null;
        }
    }

    /**
     * The method registers an order to the database.
     */
    public boolean registerPharmacy() {
        return this.moPharmacyService.registerPharmacy(moPharmacy);
    }
    public void setPharmacy(Pharmacy p){
        this.moPharmacy = p;
    }
    public void setPharmacyService(PharmacyService p){
        this.moPharmacyService = p;
    }
}
