package lapr.project.controller;

import lapr.project.model.Order;
import lapr.project.model.service.OrderService;

/**
 * Know Delivery Controller.
 *
 * Group: Team Lisa [G-021]
 * ______________________________________________________
 * @author António Barbosa <1190404@isep.ipp.pt>
 * @author Ernesto Rodrigues <1190560@isep.ipp.pt>
 * @author Jessica Alves <1190682@isep.ipp.pt>
 * @author Pedro Santos <1190967@isep.ipp.pt>
 * @author Rodrigo Costa <1191014@isep.ipp.pt>
 * @author Tiago Costa <1191460@isep.ipp.pt>
 */

public class KnowDeliveryController {

    /**
     * Order Registration class
     */
    private OrderService moOrderService;

    /**
     * Courier class instance
     */
    private String moCourierEmail;

    /**
     * Order class instance
     */
    private Order moOrder;

    /**
     * An empty constructor of KnowDeliveryController.
     */
    public KnowDeliveryController() {
        this.moOrderService = new OrderService();
    }

    /**
     * Returns a Order By its Courier.
     * @return Order.
     */
    public Order getOrderByCour() {
        this.moCourierEmail = ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail();
        this.moOrder = this.moOrderService.getOrderByCourier(moCourierEmail);
        return moOrder;
    }

    /**
     * Returns the Order Service Instance.
     * @return Order Service Instance.
     */
    public OrderService getMoOrderService() {
        return moOrderService;
    }

    /**
     * Modifies the Order Service Instance.
     * @param moOrderService Order Service Instance.
     */
    public void setMoOrderService(OrderService moOrderService) {
        this.moOrderService = moOrderService;
    }

    /**
     * Returns the Courier Email.
     * @return Courier Email.
     */
    public String getMoCourierEmail() {
        return moCourierEmail;
    }

    /**
     * Modifies Courier Email.
     * @param moCourierEmail Courier Email.
     */
    public void setMoCourierEmail(String moCourierEmail) {
        this.moCourierEmail = moCourierEmail;
    }

    /**
     * Modifies Order.
     * @param moOrder Order.
     */
    public void setMoOrder(Order moOrder) {
        this.moOrder = moOrder;
    }

    /**
     * Returns Order.
     * @return Order.
     */
    public Order getMoOrder() {
        return moOrder;
    }
}
