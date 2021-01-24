package lapr.project.controller;

import lapr.project.model.service.DroneService;

public class UpdateDroneController {

    private DroneService moDroneService;

    public UpdateDroneController() {
        moDroneService = new DroneService();
    }


    public boolean validate(Float percentage, String pharmacyEmail, Float potency, Float weight, Double batteryCapacity,
                            Float maxPayload, Float batteryVoltage, String chargingStatus, Integer droneId) {
        return moDroneService.validate(percentage,pharmacyEmail,potency,weight,batteryCapacity,maxPayload,batteryVoltage,chargingStatus,droneId);
    }

    public boolean updateDrone(Float percentage, String pharmacyEmail, Float potency, Float weight, Double batteryCapacity,
                               Float maxPayload, Float batteryVoltage, String chargingStatus, Integer droneId) {
        if (validate(percentage,pharmacyEmail,potency,weight,batteryCapacity,maxPayload,batteryVoltage,chargingStatus,droneId))
        return moDroneService.updateDrone(percentage,pharmacyEmail,potency,weight,batteryCapacity,maxPayload,batteryVoltage,chargingStatus,droneId);
        else return false;
    }
}
