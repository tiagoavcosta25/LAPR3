package lapr.project.controller;

import lapr.project.model.Courier;
import lapr.project.model.Pharmacy;
import lapr.project.model.service.CourierService;
import lapr.project.model.service.PharmacyService;


/**
 * Register Courier Controller.
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
public class RegisterCourierController {
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
    public RegisterCourierController() {
        this.moCourierService = new CourierService();
        this.moPharmacyService = new PharmacyService();
    }

    /**
     * The method receives Courier's name, email, nif, iban and pharmacy's email.
     * Validates the input recieved and gets the Pharmacy through the email provided and creates a new Courier's instance.
     * The method returns the validation of the operation. True if the data is correct
     * and created and false if it's not.
     *
     * @param strName  Courier's name.
     * @param strEmail Courier's email.
     * @param intNIF   Courier's nif.
     * @param strIBAN  Courier's iban.
     * @param strPharmacyEmail  Pharmacy's email that the Courier belongs to.
     */
    public boolean newCourier(String strName, String strEmail, Integer intNIF, String strIBAN, String strPharmacyEmail) {
        try {
            this.moPharmacy = moPharmacyService.getPharmacy(strPharmacyEmail);
            if (validateInput(strName, strEmail, intNIF, strIBAN,strPharmacyEmail)) {
                this.moCourier = moCourierService.newCourier(strName, strEmail, intNIF, strIBAN, this.moPharmacy);
                return true;
            }
        } catch (Exception ex) {
            this.moCourier = null;
        }
        return false;
    }

    /**
     * The method adds the instance of Courier to the DataBase.
     */
    public boolean registersCourier() {
        return this.moCourierService.registersCourier(this.moCourier);
    }

    /**
     * Recieves the Courier's instance and sets it.
     * @param oCourier Courier's instance
     */
    public void setCourier(Courier oCourier) {
        this.moCourier = oCourier;
    }


    /**
     * Validates the input information regarding a Courier
     *
     * @param strName  Courier's name.
     * @param strEmail Courier's email.
     * @param intNIF   Courier's nif.
     * @param strIBAN  Courier's iban.
     * @param strPharmacyEmail  Pharmacy's email that the Courier belongs to.
     * @return True if input is valid, false if otherwise.
     */
    public boolean validateInput(String strName, String strEmail, Integer intNIF, String strIBAN, String strPharmacyEmail) {

        if (strName.isEmpty() || intNIF <= 0 || strEmail.isEmpty() || strIBAN.isEmpty() || strPharmacyEmail.isEmpty()) return false;

        if (!strEmail.contains("@") || !strPharmacyEmail.contains("@")) return false;

        return (strIBAN.trim().length() == 25)
                && (int) (Math.log10(intNIF) + 1) == 9;
    }

    /**
     * The method returns the Courier Service
     * @return the Courier's Service
     */
    public CourierService getCourierService() {
        return moCourierService;
    }

    /**
     * The methods sets the Courier Service
     * @param oCourierService Courier Service class
     */
    public void setCourierService(CourierService oCourierService) {
        this.moCourierService = oCourierService;
    }

    /**
     * The method returns the Pharmacy Service
     * @return the Pharmacies's Service
     */
    public PharmacyService getPharmacyService() {
        return moPharmacyService;
    }

    /**
     * The methods sets the Pharmacy Service
     * @param oPharmacyService Pharmacy Service class
     */
    public void setPharmacyService(PharmacyService oPharmacyService) {
        this.moPharmacyService = oPharmacyService;
    }

}

