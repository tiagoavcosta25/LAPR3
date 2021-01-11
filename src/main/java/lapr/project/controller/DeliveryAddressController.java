package lapr.project.controller;

import lapr.project.model.Address;
import lapr.project.model.Platform;
import lapr.project.data.registration.CourierRegistration;
import lapr.project.model.UserSession;

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
public class DeliveryAddressController {
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
    public DeliveryAddressController() {
        this.m_oPlatform = ApplicationPOT.getInstance().getPlatform();
    }

    public Address getDeliveryAddress(){
        m_oPlatform = ApplicationPOT.getInstance().getPlatform();
        UserSession session = ApplicationPOT.getInstance().getCurrentSession();
        String email = session.getCurrentUserEmail();
        oCourierRegistration = m_oPlatform.getCourReg();
        return oCourierRegistration.getDeliveryAddress(email);
    }

}

