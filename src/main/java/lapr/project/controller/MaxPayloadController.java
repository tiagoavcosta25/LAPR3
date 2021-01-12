package lapr.project.controller;

import lapr.project.data.DeliveryDB;
import lapr.project.model.UserSession;

public class MaxPayloadController {

    /**
     * Delivery Registration class
     */
    private DeliveryDB oDeliveryDB;


    public MaxPayloadController(String jdbcUrl, String username, String password) {
        this.oDeliveryDB = new DeliveryDB(jdbcUrl, username, password);
    }

    public float getMaxPayload() {
        return this.oDeliveryDB.getMaxPayload(ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail());
    }


}
