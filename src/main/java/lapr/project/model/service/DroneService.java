package lapr.project.model.service;

import lapr.project.data.DroneDB;
import lapr.project.model.*;
import lapr.project.utils.Constants;

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

    //TODO: Alterado para double, verificar se está correto
    public boolean checkEnergy(double distance, DeliveryRun oDeliveryRun) {
        Vehicle oVehicle = oDeliveryRun.getVehicle();
        VehicleModel oVehicleModel = oVehicle.getModel();
        double totalWeight = 0;
        for (Order o : oDeliveryRun.getOrderList()) {
            totalWeight = totalWeight + o.getTotalWeight();
        }
        double totalEnergy =0f;
        //calcular energia
        if (oVehicle instanceof Drone) {
            double velocity = Math.sqrt((2 * (oVehicleModel.getWeight() + totalWeight) * (Constants.GRAVITICACCELERATION)) / (Constants.AIRDENSITY * Constants.DEFAULTROTORAREA * Constants.DRAGCOEFFICIENT));
            //rever
            double liftEnergy = (oVehicleModel.getWeight() + totalWeight) * velocity * velocity / (Constants.DEFAULTHEIGHT - 10);

            double potentialEnergy = (oVehicleModel.getWeight() + totalWeight) * Constants.GRAVITICACCELERATION * Constants.DEFAULTHEIGHT;
            double workKineticEnergy = (oVehicleModel.getPotency() / velocity) *distance;
            totalEnergy = potentialEnergy + workKineticEnergy + (2 * liftEnergy);

            //conversão de unidades
             } else {
            double force = (((totalWeight + oVehicleModel.getWeight() + Constants.DEFAULTCOURIERWEIGHT)*Constants.GRAVITICACCELERATION *
                    Constants.KINETICFRICTIONCOEFFICIENT) + (0.5 * Constants.AIRDENSITY * Constants.DRAGCOEFFICIENT * Constants.DEFAULTSCOOTERAREA *
                    Constants.DEFAULTVELOCITY *Constants.DEFAULTVELOCITY));
            //adicionar angulo se possível
            totalEnergy = force*distance;
        }
        return (((oVehicleModel.getBattery().getBatteryCapacity() * oVehicleModel.getBattery().getBatteryVoltage() * (oVehicle.getBatteryPerc() / 100)) / 1000) >= totalEnergy * Constants.KILOWATTHOUR);

    }

    public boolean startDelivery(DeliveryRun oDeliveryRun) {
        return oDeliveryRun.getStatus().getDesignation().equalsIgnoreCase("Idle");
    }

    public float getDronePayload(int droneId) {
        return moDroneDB.getDronePayload(droneId);
    }
}
