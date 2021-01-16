package lapr.project.model.service;

import lapr.project.data.DroneDB;
import lapr.project.model.*;
import lapr.project.utils.Constants;

import java.util.List;

public class DroneService {

    private DroneDB m_oDroneDB;

    public DroneService() {
        m_oDroneDB = new DroneDB();
    }

    public boolean validate(Float percentage, Integer pharmacyId, Float potency, Float weight, Double batteryCapacity,
                            Float maxPayload, Float batteryVoltage, String chargingStatus, Integer droneId) {
        if (percentage < 0 || percentage > 100 || pharmacyId <= 0 || potency <= 0 || weight <= 0 || batteryCapacity <= 0
                || maxPayload <= 0 || batteryVoltage <= 0 || chargingStatus.isEmpty() || droneId <= 0) return false;

        return true;
    }

    public boolean updateDrone(Float percentage, Integer pharmacyId, Float potency, Float weight, Double batteryCapacity,
                               Float maxPayload, Float batteryVoltage, String chargingStatus, Integer droneId) {
        return m_oDroneDB.updateDrone(percentage, pharmacyId, potency, weight, batteryCapacity, maxPayload, batteryVoltage, chargingStatus, droneId);
    }

    public Drone newDrone(Float fltBatteryPerc, String strCharginStatus, Float fltPotency, Float fltWeight,
                          int intBatteryCapacity, Float fltBatteryVoltage, Float fltMaxPayload, Pharmacy oPharmacy) {
        return new Drone(fltPotency, fltWeight, fltMaxPayload, strCharginStatus, fltBatteryPerc,
                intBatteryCapacity, fltBatteryVoltage, oPharmacy);
    }

    public boolean registerDrone(Drone oDrone) {
        return m_oDroneDB.registerDrone(oDrone);
    }

    public List<Drone> getDronesList(int intPharmacyId) {
        return m_oDroneDB.getDronesList(intPharmacyId);
    }

    public boolean removeDroneFromDB(int intId) {
        return m_oDroneDB.removeDroneFromDB(intId);
    }

    private void teste() {

    }


    public boolean checkEnergy(double distance, DeliveryRun oDeliveryRun) {
        Vehicle oVehicle = oDeliveryRun.getVehicle();
        float totalWeight = 0f;
        for (Order o : oDeliveryRun.getOrderList()) {
            totalWeight = totalWeight + o.getTotalWeight();
        }
        float totalEnergy =0f;
        //calcular energia
        if (oVehicle instanceof Drone) {
            float velocity = (float) Math.sqrt((2 * (oVehicle.getWeight() + totalWeight) * (Constants.GRAVITIC_ACCELERATION)) / (Constants.AIR_DENSITY * Constants.DEFAULT_ROTOR_AREA * Constants.DRAG_COEFFICIENT));
            //rever
            float liftEnergy = (oVehicle.getWeight() + totalWeight) * velocity * velocity / (Constants.DEFAULT_HEIGHT - 10);

            float potentialEnergy = (oVehicle.getWeight() + totalWeight) * Constants.GRAVITIC_ACCELERATION * Constants.DEFAULT_HEIGHT;
            float workKineticEnergy = (oVehicle.getPotency() / velocity) * (float) distance;
            totalEnergy = potentialEnergy + workKineticEnergy + (2 * liftEnergy);

            //conversão de unidades
             } else {
            float force = (float) (((totalWeight + oVehicle.getWeight() + Constants.DEFAULT_COURIER_WEIGHT)*Constants.GRAVITIC_ACCELERATION *
                    Constants.KINETIC_FRICTION_COEFFICIENT) + (0.5 * Constants.AIR_DENSITY * Constants.DRAG_COEFFICIENT * Constants.DEFAULT_SCOOTER_AREA *
                    Constants.DEFAULT_VELOCITY*Constants.DEFAULT_VELOCITY));
            //adicionar angulo se possível
            totalEnergy = force*(float)distance;
        }
        return (((oVehicle.getBattery().getBatteryCapacity() * oVehicle.getBattery().getBatteryVoltage() * (oVehicle.getBattery().getBatteryPerc() / 100)) / 1000) >= totalEnergy * Constants.KILOWATTHOUR);

    }

    public boolean startDelivery() {
        return true;
    }

    public float getDronePayload(int droneId) {
        return m_oDroneDB.getDronePayload(droneId);
    }
}
