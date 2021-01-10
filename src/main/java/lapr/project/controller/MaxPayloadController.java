package lapr.project.controller;

import lapr.project.model.Courier;
import lapr.project.model.Order;
import lapr.project.model.Platform;
import lapr.project.model.registration.DeliveryRegistration;
import lapr.project.model.registration.OrderRegistration;

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

    public float getMaxPayload() {
        this.m_ApplicationPOT = ApplicationPOT.getInstance();
        this.m_oPlatform = m_ApplicationPOT.getPlatform();
        this.m_oSession = m_ApplicationPOT.getCurrentSession();
        this.oCourierEmail = m_oSession.getCurrentUserEmail();
        this.oDeliveryRegistration = this.m_oPlatform.getDelReg();
        return this.oDeliveryRegistration.getMaxPayload(oCourierEmail);
    }


}
