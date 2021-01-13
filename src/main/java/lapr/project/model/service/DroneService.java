package lapr.project.model.service;

import lapr.project.data.DroneDB;

public class DroneService {

    private DroneDB m_oDroneDB;

    public DroneService() {
        m_oDroneDB = new DroneDB();
    }

    public boolean validate(Float percentage, Integer pharmacyId, Float potency, Float weight, Double batteryCapacity,
                            Float maxPayload, Float batteryVoltage, String chargingStatus, Integer droneId) {
        if (percentage < 0 || percentage > 100 || pharmacyId <= 0 || potency <= 0 || weight <= 0|| batteryCapacity <= 0
                || maxPayload <= 0 || batteryVoltage <= 0 || chargingStatus.isEmpty() || droneId <= 0) return false;

        return true;
    }

    public boolean updateDrone(Float percentage, Integer pharmacyId, Float potency, Float weight, Double batteryCapacity,
                               Float maxPayload, Float batteryVoltage, String chargingStatus,Integer droneId) {
        return m_oDroneDB.updateDrone(percentage,pharmacyId,potency,weight,batteryCapacity,maxPayload,batteryVoltage,chargingStatus,droneId);
    }
}
