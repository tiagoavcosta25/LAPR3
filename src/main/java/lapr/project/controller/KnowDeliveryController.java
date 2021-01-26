package lapr.project.controller;

import lapr.project.model.Order;
import lapr.project.model.service.OrderService;

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

    public KnowDeliveryController() {
        this.moOrderService = new OrderService();
    }

    public Order getOrderByCour() {
        this.moCourierEmail = ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail();
        this.moOrder = this.moOrderService.getOrderByCourier(moCourierEmail);
        return moOrder;
    }

    public OrderService getMoOrderService() {
        return moOrderService;
    }

    public void setMoOrderService(OrderService moOrderService) {
        this.moOrderService = moOrderService;
    }

    public String getMoCourierEmail() {
        return moCourierEmail;
    }

    public void setMoCourierEmail(String moCourierEmail) {
        this.moCourierEmail = moCourierEmail;
    }

    public void setMoOrder(Order moOrder) {
        this.moOrder = moOrder;
    }

    public Order getMoOrder() {
        return moOrder;
    }
}
