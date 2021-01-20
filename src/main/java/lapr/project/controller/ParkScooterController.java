package lapr.project.controller;

import lapr.project.model.UserSession;
import lapr.project.model.service.CourierService;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ParkScooterController {

    private static final Logger LOGGER = Logger.getLogger(ParkScooterController.class.getName());

    private CourierService cServ;

    public ParkScooterController() {
        this.cServ = new CourierService();
    }

    public boolean parkScooter(int intIdScooter) {
        try {
            if (ApplicationPOT.getInstance().getCurrentSession().getRole().equals(UserSession.Role.COURIER)) {
                return cServ.parkScooter(intIdScooter);
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "There was an error parking the scooter!");
        }
        return false;
    }
}