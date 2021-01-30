package lapr.project.controller;

import lapr.project.model.UserSession;
import lapr.project.model.service.CourierService;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Park Scooter Controller.
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

public class ParkScooterController {

    /**
     * Logger which is used to generate warnings or information, with
     * a custom message.
     */
    private static final Logger LOGGER = Logger.getLogger(ParkScooterController.class.getName());

    /**
     * Courier Management class.
     */
    private CourierService moServ;

    /**
     * An empty constructor of ParkScooterController.
     */
    public ParkScooterController() {
        this.moServ = new CourierService();
    }

    /**
     * The methods recieves the Scooter's Id and sets as parked.
     * @param intIdScooter Scooter ID.
     * @return true if the Scooter is parked correctly. False if otherwise.
     */
    public boolean parkScooter(int intIdScooter) {
        try {
            if (ApplicationPOT.getInstance().getCurrentSession().getRole().equals(UserSession.Role.COURIER)) {
                return moServ.parkScooter(intIdScooter);
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "There was an error parking the scooter!");
        }
        return false;
    }

    /**
     * Returns the Courier Service Instance.
     * @return Courier Service Instance.
     */
    public CourierService getServ() {
        return moServ;
    }

    /**
     * Modifies the Courier Service Instance.
     * @param moServ Courier Service Instance.
     */
    public void setServ(CourierService moServ) {
        this.moServ = moServ;
    }
}
