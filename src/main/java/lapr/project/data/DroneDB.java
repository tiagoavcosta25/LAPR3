package lapr.project.data;


import lapr.project.model.Battery;
import lapr.project.model.Drone;
import lapr.project.model.Pharmacy;
import lapr.project.model.Scooter;

import java.sql.CallableStatement;
import java.sql.SQLException;

public class DroneDB extends DataHandler  {

    public DroneDB() {

    }

    public boolean updateDrone(Float percentage, Integer pharmacyId, Float potency, Float weight, Double batteryCapacity,
                               Float maxPayload, Float batteryVoltage, String chargingStatus,Integer droneId) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{call updateDrone(?,?,?,?,?,?,?,?,?}");

            callStmt.setFloat(1, percentage);
            callStmt.setFloat(2, pharmacyId);
            callStmt.setFloat(3, potency);
            callStmt.setFloat(4, weight);
            callStmt.setDouble(5, batteryCapacity);
            callStmt.setFloat(6, maxPayload);
            callStmt.setFloat(7, batteryVoltage);
            callStmt.setString(8,chargingStatus);
            callStmt.setInt(9,droneId);

            callStmt.execute();
            closeAll();

        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public boolean registerDrone(Drone d) {
        return addDrone(d.getPotency(), d.getWeight(), d.getMaxPayload(), d.getCharginStatus(),
                d.getBattery(), d.getPharmacy());
    }

    public boolean addDrone(float fltPotency, float fltWeight, float fltMaxPayload, String strCharginStatus,
                               Battery oBattery, Pharmacy oPharmacy) {
        boolean flag = true;
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call addScooter(?,?,?,?,?,?,?,?) }");


            callStmt.setFloat(1, fltPotency);
            callStmt.setFloat(2, fltWeight);
            callStmt.setFloat(3, fltMaxPayload);
            callStmt.setString(4, strCharginStatus);
            callStmt.setFloat(5, oBattery.getBatteryPerc());
            callStmt.setFloat(6, oBattery.getBatteryCapacity());
            callStmt.setFloat(7, oBattery.getBatteryVoltage());
            callStmt.setInt(8, oPharmacy.getId());

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }
}
