package lapr.project.controller;

import lapr.project.model.ChargingSlot;
import lapr.project.model.Platform;
import lapr.project.data.registration.CourierRegistration;

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
     * Platform class instance
     */
    private Platform m_oPlatform;
    /**
     * Courier Management class
     */
    private CourierRegistration oCourierRegistration;

    /**
     * An empty constructor of RegisterCourierController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public AvailableChargingSlotController() {
        this.m_oPlatform = ApplicationPOT.getInstance().getPlatform();
        oCourierRegistration = m_oPlatform.getCourReg();
    }

    public ChargingSlot getAvailableChargingSlot(){
        return oCourierRegistration.getAvailableChargingSlot(ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail());
    }

}

