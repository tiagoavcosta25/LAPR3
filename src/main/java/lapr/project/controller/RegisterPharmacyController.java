package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.service.PharmacyService;

/**
 * Register Pharmacy Controller.
 *
 * Group: Team Lisa [G-021]
 * ______________________________________________________
 * @author António Barbosa <1190404@isep.ipp.pt>
 * @author Ernesto Rodrigues <1190560@isep.ipp.pt>
 * @author Jessica Alves <1190682@isep.ipp.pt>
 * @author Pedro Santos <1190967@isep.ipp.pt>
 * @author Rodrigo Costa <1191014@isep.ipp.pt>
 * @author Tiago Costa <1191460@isep.ipp.pt>
 */
public class RegisterPharmacyController {
    /**
     * Pharmacy class instance.
     */
    private Pharmacy moPharmacy;

    /**
     * Pharmacy Management class.
     */
    private PharmacyService moPharmacyService;


    /**
     * An empty constructor of RegisterPharmacyController.
     */
    public RegisterPharmacyController() {
        this.moPharmacyService = new PharmacyService();
    }

    /**
     * Method that Creates a new Pharmacy.
     * @param strName Name.
     * @param strEmail Email.
     * @param dblLatitude Latitude.
     * @param dblLongitude Longitude.
     * @param dblAltitude Altitude.
     * @param strStreetName Street Name.
     * @param strDoorNumber Door Number.
     * @param strPostalCode Postal Code.
     * @param strLocality Locality.
     * @param strCountry Country.
     * @return The Pharmacy.
     */
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
     * The method registers a pharmacy to the database.
     * @return true if everything works out, false if it doesn't.
     */
    public boolean registerPharmacy() {
        return this.moPharmacyService.registerPharmacy(moPharmacy);
    }

    /**
     * The method sets the pharmacy.
     * @param p Pharmacy.
     */
    public void setPharmacy(Pharmacy p){
        this.moPharmacy = p;
    }

    /**
     * The method sets the pharmacy management.
     * @param p The pharmacy management.
     */
    public void setPharmacyService(PharmacyService p){
        this.moPharmacyService = p;
    }
}
