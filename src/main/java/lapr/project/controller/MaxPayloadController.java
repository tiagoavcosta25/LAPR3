package lapr.project.controller;

import lapr.project.data.DeliveryDB;
import lapr.project.model.UserSession;
import lapr.project.model.service.DeliveryRunService;

public class MaxPayloadController {

    /**
     * Delivery Registration class
     */
    private DeliveryRunService oDeliveryService;


    public MaxPayloadController() {
        this.oDeliveryService = new DeliveryRunService();
    }

    public float getMaxPayload() {
        return this.oDeliveryService.getMaxPayload(ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail());
    }


}
