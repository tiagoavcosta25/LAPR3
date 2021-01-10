package lapr.project.controller;

import lapr.project.model.Platform;
import lapr.project.data.registration.DeliveryRegistration;

public class MaxPayloadController {

    /**
     * Platform class instance
     */
    private Platform m_oPlatform;

    /**
     * ApplicationPot class instance
     */

    private ApplicationPOT m_ApplicationPOT;

    /**
     * UserSession class instance
     */
    private UserSession m_oSession;
    /**
     * Delivery Registration class
     */
    private DeliveryRegistration oDeliveryRegistration;

    /**
     * Courier class instance
     */
    private String oCourierEmail;

    public MaxPayloadController() {
        this.m_ApplicationPOT = ApplicationPOT.getInstance();
        this.m_oPlatform = m_ApplicationPOT.getPlatform();
        this.oDeliveryRegistration = this.m_oPlatform.getDelReg();
    }

    public float getMaxPayload() {
        this.m_oSession = m_ApplicationPOT.getCurrentSession();
        this.oCourierEmail = m_oSession.getCurrentUserEmail();
        return this.oDeliveryRegistration.getMaxPayload(oCourierEmail);
    }


}
