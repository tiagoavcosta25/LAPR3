package lapr.project.controller;

import lapr.project.model.Park;
import lapr.project.model.UserSession;
import lapr.project.model.VehicleType;
import lapr.project.model.service.PharmacyService;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Register Park Controller.
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

public class RegisterParkController {

    /**
     * Logger which is used to generate warnings or information, with
     * a custom message.
     */
    private static final Logger LOGGER = Logger.getLogger(RegisterParkController.class.getName());

    /**
     * Pharmacy Management class.
     */
    private PharmacyService moServ;

    /**
     * An empty constructor of ParkScooterController.
     */
    public RegisterParkController() {
        this.moServ = new PharmacyService();
    }

    /**
     * Adds a Park.
     * @param strPharmacyEmail Park's Pharmacy Email.
     * @param intMaxSlots Park's Max Slots.
     * @param dblOutputCurrent Park's Current Output.
     * @param enumVehicleType Park's Vehicle Type.
     * @param intNonChargingSlots Park's Non Charging Slots.
     * @param intChargingSlots Park's Charging Slots.
     * @return true if the Park is added. False if otherwise.
     */
    public boolean addPark(String strPharmacyEmail, int intMaxSlots, Double dblOutputCurrent, VehicleType enumVehicleType, int intNonChargingSlots, int intChargingSlots) {
        Park p = new Park(intMaxSlots, dblOutputCurrent, enumVehicleType);
        try {
            if(ApplicationPOT.getInstance().getCurrentSession().getRole().equals(UserSession.Role.ADMIN))
                return moServ.addPark(strPharmacyEmail, p, intNonChargingSlots, intChargingSlots);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "User not logged in!");
        }
        return false;
    }

    /**
     * Returns Pharmacy Service Instance.
     * @return Pharmacy Service Instance.
     */
    public PharmacyService getServ() {
        return moServ;
    }

    /**
     * Modifies Pharmacy Service Instance.
     * @param oServ Pharmacy Service Instance.
     */
    public void setServ(PharmacyService oServ) {
        this.moServ = oServ;
    }
}
