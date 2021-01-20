package lapr.project.controller;

import lapr.project.model.service.DroneService;

public class DronePayloadController {

    private DroneService moDroneServ;

    public DronePayloadController() {
        this.moDroneServ = new DroneService();
    }

    public float getDronePayload(int droneId) {
        return this.moDroneServ.getDronePayload(droneId);
    }
}
