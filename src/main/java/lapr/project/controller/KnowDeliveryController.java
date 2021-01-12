package lapr.project.controller;

import lapr.project.model.Order;
import lapr.project.data.OrderDB;
import lapr.project.model.UserSession;

public class KnowDeliveryController {

    /**
     * Order Registration class
     */
    private OrderDB oOrderDB;

    /**
     * Courier class instance
     */
    private String oCourierEmail;

    /**
     * Order class instance
     */
    private Order oOrder;

    public KnowDeliveryController(String jdbcUrl, String username, String password) {
        this.oOrderDB = new OrderDB(jdbcUrl, username, password);
    }

    public Order getOrderByCour() {
        this.oCourierEmail = ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail();
        this.oOrder = this.oOrderDB.getOrderByCourier(oCourierEmail);
        return oOrder;
    }


}
