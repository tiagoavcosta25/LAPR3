package lapr.project.model.service;

import lapr.project.data.DroneDB;
import lapr.project.model.*;
import lapr.project.utils.Constants;
import lapr.project.utils.EnergyCalculator;
import lapr.project.utils.WriteFile;

import java.util.List;

public class DroneService {

    private DroneDB moDroneDB;

    public DroneService() {
        moDroneDB = new DroneDB();
    }

    public boolean validate(Float percentage, String pharmacyEmail, Float potency, Float weight, Double batteryCapacity,
                            Float maxPayload, Float batteryVoltage, String chargingStatus, Integer droneId) {
        return percentage >= 0 && percentage <= 100 && !pharmacyEmail.isEmpty() && potency > 0 && weight > 0 && batteryCapacity > 0
                && maxPayload > 0 && batteryVoltage > 0 && !chargingStatus.isEmpty() && droneId > 0;
    }

    public boolean updateDrone(Float percentage, String pharmacyEmail, Float potency, Float weight, Double batteryCapacity,
                               Float maxPayload, Float batteryVoltage, String chargingStatus, Integer droneId) {
        if (moDroneDB.updateDrone(percentage, pharmacyEmail, potency, weight, batteryCapacity, maxPayload, batteryVoltage, chargingStatus, droneId)){
            String body = String.format("Drone Update Log\n\n" +
                    "Drone New Info:\n-Updated Percentage: %.2f\n-Updated Potency: %.2f\n-Updated Weight: %.2fkg\n-Updated " +
                    "Battery Capacity: %.2f\n-Updated Max Payload: %.2f\n-Updated Battery Voltage: %.2f\nUpdated " +
                    "Charging Status: %s\n-Pharmacy Email: %s",percentage,potency,weight,batteryCapacity,maxPayload,batteryVoltage,
                    chargingStatus,pharmacyEmail);
            return WriteFile.write("UpdateDrone_" + droneId,body);
        }else return false;
    }

    public Drone newDrone(VehicleModel oVehicleModel, Pharmacy oPharmacy) {
        return new Drone(oVehicleModel, oPharmacy);
    }

    public int registerDrone(Drone oDrone) {
        return moDroneDB.registerDrone(oDrone);
    }

    public List<Drone> getDronesList(int intPharmacyId) {
        return moDroneDB.getDronesList(intPharmacyId);
    }

    public boolean removeDroneFromDB(int intId) {
        return moDroneDB.removeDroneFromDB(intId);
    }

    public boolean checkEnergy(double distance, DeliveryRun oDeliveryRun) {
        Vehicle oVehicle = oDeliveryRun.getVehicle();
        VehicleModel oVehicleModel = oVehicle.getModel();
        double totalWeight = 0;
        for (Order o : oDeliveryRun.getOrderList()) {
            totalWeight = totalWeight + o.getTotalWeight();
        }
        double totalEnergy;
        if (oVehicle instanceof Drone) {
            totalEnergy = EnergyCalculator.calculateDroneEnergy(totalWeight, 0, 1, 100);

        } else {
            totalEnergy = EnergyCalculator.calculateScooterEnergy(distance, 0, 1, 10, totalWeight + oVehicleModel.getWeight() + Constants.DEFAULT_COURIER_WEIGHT, Constants.KINETIC_FRICTION_COEFFICIENT);
        }
        return (((oVehicleModel.getBattery().getBatteryCapacity() * oVehicleModel.getBattery().getBatteryVoltage() * (oVehicle.getBatteryPerc() / 100)) / 1000) >= totalEnergy / Constants.KILOWATTHOUR);

    }

    public boolean startDelivery(DeliveryRun oDeliveryRun) {
        return oDeliveryRun.getStatus().getDesignation().equalsIgnoreCase("Idle");
    }

    public float getDronePayload(int droneId) {
        return moDroneDB.getDronePayload(droneId);
    }
}
