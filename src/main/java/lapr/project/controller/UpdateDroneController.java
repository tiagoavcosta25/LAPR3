package lapr.project.controller;

import lapr.project.model.service.DroneService;

public class UpdateDroneController {

    private DroneService moDroneService;

    public UpdateDroneController() {
        moDroneService = new DroneService();
    }


    public boolean validate(Float percentage, Integer pharmacyId, Float potency, Float weight, Double batteryCapacity,
                            Float maxPayload, Float batteryVoltage, String chargingStatus, Integer droneId) {
        return moDroneService.validate(percentage,pharmacyId,potency,weight,batteryCapacity,maxPayload,batteryVoltage,chargingStatus,droneId);
    }

    public boolean updateDrone(Float percentage, Integer pharmacyId, Float potency, Float weight, Double batteryCapacity,
                               Float maxPayload, Float batteryVoltage, String chargingStatus, Integer droneId) {
        return moDroneService.updateDrone(percentage,pharmacyId,potency,weight,batteryCapacity,maxPayload,batteryVoltage,chargingStatus,droneId);
    }
}
