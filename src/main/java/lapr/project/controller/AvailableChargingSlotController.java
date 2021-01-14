package lapr.project.controller;

import lapr.project.model.ChargingSlot;
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
public class AvailableChargingSlotController {

    /**
     * Courier Management class
     */
    private CourierService oCourierService;

    /**
     * An empty constructor of RegisterCourierController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public AvailableChargingSlotController() {
        oCourierService = new CourierService();
    }

    public ChargingSlot getAvailableChargingSlot(String vehycleType){
        return oCourierService.getAvailableChargingSlot(ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail(),vehycleType);
    }

}

