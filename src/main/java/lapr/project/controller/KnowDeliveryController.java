package lapr.project.controller;

import lapr.project.model.Order;
import lapr.project.data.OrderDB;
import lapr.project.model.UserSession;
import lapr.project.model.service.OrderService;

public class KnowDeliveryController {

    /**
     * Order Registration class
     */
    private OrderService oOrderService;

    /**
     * Courier class instance
     */
    private String oCourierEmail;

    /**
     * Order class instance
     */
    private Order oOrder;

    public KnowDeliveryController() {
        this.oOrderService = new OrderService();
    }

    public Order getOrderByCour() {
        this.oCourierEmail = ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail();
        this.oOrder = this.oOrderService.getOrderByCourier(oCourierEmail);
        return oOrder;
    }


}
