package lapr.project.controller;

import lapr.project.model.service.CourierService;


/**
 * Remove Courier Controller.
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
public class RemoveCourierController {

    /**
     * Courier Service class
     */
    private CourierService moCourierService;

    /**
     * A constructor of UpdateCourierController that initiates the Courier Service.
     */
    public RemoveCourierController() {
        this.moCourierService = new CourierService();
    }

    /**
     * The method receives Courier's nameemail.
     * The method returns the validation of that Courier's email. True if the data is correct and false if
     * it doesn't.
     */
    public boolean validateCourier(String email) {
        return validateInput(email);
    }

    /**
     * Recieves the Courier's email and removes it from the Database.
     * @param email Courier's email
     * @return true if the Courier is removed. False if otherwise.
     */
    public boolean removeCourier(String email) {
        return this.moCourierService.removeCourier(email);
    }


    /**
     * Validates the input information regarding
     * a Courier's email
     *
     * @param email Courier's email.
     * @return True if input is valid, false if otherwise
     */
    public boolean validateInput(String email) {

        return email != null && !email.isEmpty() && email.contains("@");
    }
}

