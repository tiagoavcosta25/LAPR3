package lapr.project.controller;

import lapr.project.model.Park;
import lapr.project.model.UserSession;
import lapr.project.model.VehicleType;
import lapr.project.model.service.PharmacyService;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterParkController {

    private static final Logger LOGGER = Logger.getLogger(RegisterParkController.class.getName());

    private PharmacyService moServ;

    public RegisterParkController() {
        this.moServ = new PharmacyService();
    }

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

    public PharmacyService getServ() {
        return moServ;
    }

    public void setServ(PharmacyService oServ) {
        this.moServ = oServ;
    }
}
