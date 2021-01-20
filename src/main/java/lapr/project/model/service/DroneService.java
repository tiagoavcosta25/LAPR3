package lapr.project.model.service;

import lapr.project.data.DroneDB;
import lapr.project.model.*;
import lapr.project.utils.Constants;
import lapr.project.utils.EnergyCalculator;

import java.util.List;

public class DroneService {

    private DroneDB moDroneDB;

    public DroneService() {
        moDroneDB = new DroneDB();
    }

    public boolean validate(Float percentage, Integer pharmacyId, Float potency, Float weight, Double batteryCapacity,
                            Float maxPayload, Float batteryVoltage, String chargingStatus, Integer droneId) {
        if (percentage < 0 || percentage > 100 || pharmacyId <= 0 || potency <= 0 || weight <= 0 || batteryCapacity <= 0
                || maxPayload <= 0 || batteryVoltage <= 0 || chargingStatus.isEmpty() || droneId <= 0) return false;

        return true;
    }

    public boolean updateDrone(Float percentage, Integer pharmacyId, Float potency, Float weight, Double batteryCapacity,
                               Float maxPayload, Float batteryVoltage, String chargingStatus, Integer droneId) {
        return moDroneDB.updateDrone(percentage, pharmacyId, potency, weight, batteryCapacity, maxPayload, batteryVoltage, chargingStatus, droneId);
    }

    public Drone newDrone(VehicleModel oVehicleModel, Pharmacy oPharmacy) {
        return new Drone(oVehicleModel, oPharmacy);
    }

    public boolean registerDrone(Drone oDrone) {
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
            //velocidade otimizada para o lift/drag ratio
            double velocity = Math.pow((2 * Math.pow(totalWeight, 2)) / (Constants.DRAG_COEFFICIENT * Constants.DEFAULT_DRONE_AREA * Math.pow(Constants.DRONE_WIDTH, 2) * Math.pow(Constants.AIR_DENSITY, 2)), 0.25);
            double force = (0.5*Constants.DRAG_COEFFICIENT*Constants.DEFAULT_DRONE_AREA*Constants.AIR_DENSITY*Math.pow(velocity,2))+((Math.pow(totalWeight,2))/(Constants.AIR_DENSITY*Math.pow(Constants.DRONE_WIDTH,2)*Math.pow(velocity,2)));
            double liftEnergy = (Math.pow((totalWeight*Constants.GRAVITIC_ACCELERATION),1.5)/(Math.sqrt(2*Constants.AIR_DENSITY*Constants.DEFAULT_DRONE_AREA)))*(Constants.DEFAULT_HEIGHT/velocity);
            totalEnergy = force*distance+liftEnergy;
        } else {
            totalEnergy = (EnergyCalculator.calculoEnergia(distance, 0, 1, 10, totalWeight + oVehicleModel.getWeight() + Constants.DEFAULT_COURIER_WEIGHT, Constants.KINETIC_FRICTION_COEFFICIENT)) * Constants.KILOWATTHOUR;
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
