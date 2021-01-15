package lapr.project.controller;

import lapr.project.model.service.DroneService;

public class DronePayloadController {

    private DroneService oDroneServ;

    public DronePayloadController() {
        this.oDroneServ = new DroneService();
    }

    public float getDronePayload(int droneId) {
        return this.oDroneServ.getDronePayload(droneId);
    }
}
