package lapr.project.controller;

import lapr.project.model.UserSession;
import lapr.project.model.service.CourierService;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ParkScooterController {

    private static final Logger LOGGER = Logger.getLogger(ParkScooterController.class.getName());

    private CourierService moServ;

    public ParkScooterController() {
        this.moServ = new CourierService();
    }

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

    public CourierService getServ() {
        return moServ;
    }

    public void setServ(CourierService moServ) {
        this.moServ = moServ;
    }
}
