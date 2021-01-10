package lapr.project.controller;

import lapr.project.model.Order;
import lapr.project.model.Platform;
import lapr.project.data.registration.OrderRegistration;

public class KnowDeliveryController {

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
     * Order Registration class
     */
    private OrderRegistration oOrderRegistration;

    /**
     * Courier class instance
     */
    private String oCourierEmail;

    /**
     * Order class instance
     */
    private Order oOrder;

    public Order getOrderByCour() {
        this.m_ApplicationPOT = ApplicationPOT.getInstance();
        this.m_oPlatform = m_ApplicationPOT.getPlatform();
        this.m_oSession = m_ApplicationPOT.getCurrentSession();
        this.oCourierEmail = m_oSession.getCurrentUserEmail();
        this.oOrderRegistration = this.m_oPlatform.getOrderReg();
        this.oOrder = this.oOrderRegistration.getOrderByCourier(oCourierEmail);
        return oOrder;
    }


}
