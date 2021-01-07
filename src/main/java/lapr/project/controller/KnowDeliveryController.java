package lapr.project.controller;

import lapr.project.model.Courier;
import lapr.project.model.Order;
import lapr.project.model.Platform;
import lapr.project.model.registration.OrderRegistration;

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
    private Courier oCourier;

    /**
     * Order class instance
     */
    private Order oOrder;


    public Courier getCourierInfo() {
        this.m_ApplicationPOT = ApplicationPOT.getInstance();
        this.m_oPlatform = m_ApplicationPOT.getPlatform();
        this.m_oSession = m_ApplicationPOT.getCurrentSession();
        this.oCourier = (Courier) m_oSession.getUserInstance();
        this.oOrderRegistration = this.m_oPlatform.getOrderReg();
        return oCourier;
    }

    public Order getOrderByCour() {
        this.oOrderRegistration = this.m_oPlatform.getOrderReg();
        this.oOrder = this.oOrderRegistration.getOrderByCourier(oCourier);
        return oOrder;
    }


}
