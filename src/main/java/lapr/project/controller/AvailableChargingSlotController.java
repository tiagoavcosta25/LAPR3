package lapr.project.controller;

import lapr.project.model.ChargingSlot;
import lapr.project.data.CourierDB;

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
public class AvailableChargingSlotController {

    /**
     * Courier Management class
     */
    private CourierDB oCourierDB;

    /**
     * An empty constructor of RegisterCourierController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public AvailableChargingSlotController(String jdbcUrl, String username, String password) {
        oCourierDB = new CourierDB(jdbcUrl, username, password);
    }

    public AvailableChargingSlotController() {
    }

    public ChargingSlot getAvailableChargingSlot(){
        return oCourierDB.getAvailableChargingSlot(ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail());
    }

}

