package lapr.project.controller;

import lapr.project.model.Park;
import lapr.project.model.UserSession;
import lapr.project.model.VehicleType;
import lapr.project.model.service.PharmacyService;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterParkController {

    private static final Logger LOGGER = Logger.getLogger(RegisterParkController.class.getName());

    private PharmacyService pServ;

    public RegisterParkController() {
        this.pServ = new PharmacyService();
    }

    public boolean addPark(int intPharmacyId, int intMaxSlots, float fltOutputCurrent, VehicleType enumVehicleType, int intNonChargingSlots, int intChargingSlots) {
        Park p = new Park(intMaxSlots, fltOutputCurrent, enumVehicleType);
        try {
            if(ApplicationPOT.getInstance().getCurrentSession().getRole().equals(UserSession.Role.ADMIN))
                return pServ.addPark(intPharmacyId, p, intNonChargingSlots, intChargingSlots);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "User not logged in!");
        }
        return false;
    }
}
