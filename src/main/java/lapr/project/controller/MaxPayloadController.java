package lapr.project.controller;

import lapr.project.model.service.DeliveryRunService;

public class MaxPayloadController {

    /**
     * Delivery Registration class
     */
    private DeliveryRunService moDeliveryService;


    public MaxPayloadController() {
        this.moDeliveryService = new DeliveryRunService();
    }

    public float getMaxPayload() {
        return this.moDeliveryService.getMaxPayload(ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail());
    }


}
