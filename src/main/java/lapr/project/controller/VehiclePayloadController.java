package lapr.project.controller;

import lapr.project.model.service.VehicleService;

public class VehiclePayloadController {

    private VehicleService moVehicleServ;

    public VehiclePayloadController() {
        this.moVehicleServ = new VehicleService();
    }

    public double getVehiclePayload(int vehicleId) {
        return this.moVehicleServ.getVehiclePayload(vehicleId);
    }
}
