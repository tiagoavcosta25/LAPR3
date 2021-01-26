package lapr.project.controller;

import lapr.project.model.service.CourierService;


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
public class RemoveCourierController {

    /**
     * Courier Management class
     */
    private CourierService moCourierService;

    /**
     * A constructor of RegisterCourierController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public RemoveCourierController() {
        this.moCourierService = new CourierService();
    }

    /**
     * The method receives Courier's name, email, nif and iban.
     * Initiates the CourierRegistration instance and the Courier instance with the provided data.
     * The method returns the validation of that instance of Courier. True if the data is correct and false if
     * it doesn't.
     */
    public boolean validateCourier(String email) {
        return validateInput(email);
    }

    /**
     * The method adds a Freelancer to the Organization of the current user.
     */
    public boolean removeCourier(String email) {
        return this.moCourierService.removeCourier(email);
    }


    /**
     * Validates the input information regarding
     * a Courier
     *
     * @param email Courier's email.
     * @return True if input is valid, false if otherwise
     */
    public boolean validateInput(String email) {

        return email != null && !email.isEmpty() && email.contains("@");
    }
}

