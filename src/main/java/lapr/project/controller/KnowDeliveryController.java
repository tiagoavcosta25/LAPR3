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


}
