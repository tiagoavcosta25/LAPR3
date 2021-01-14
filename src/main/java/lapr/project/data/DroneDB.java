package lapr.project.data;


import lapr.project.model.Battery;
import lapr.project.model.Drone;
import lapr.project.model.Pharmacy;

import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DroneDB extends DataHandler  {

    public DroneDB() {

    }

    public boolean updateDrone(Float percentage, Integer pharmacyId, Float potency, Float weight, Double batteryCapacity,
                               Float maxPayload, Float batteryVoltage, String chargingStatus,Integer droneId) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{call updateDrone(?,?,?,?,?,?,?,?,?)}");

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

    public List<Drone> getDronesList(int intPharmacyId) {

        CallableStatement callStmt = null;
        List<Drone> lstDrone = new ArrayList<>();
        try {
            callStmt = getConnection().prepareCall("{ ? = call getDronesList(?) }");

            callStmt.registerOutParameter(1, oracle.jdbc.internal.OracleTypes.CURSOR);
            callStmt.setInt(2, intPharmacyId);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while(rSet.next()){
                int intId = rSet.getInt(1);
                float fltPotency = rSet.getFloat(2);
                float fltWeight = rSet.getFloat(3);
                float fltMaxPayload = rSet.getFloat(4);
                String strCharginStatus = rSet.getString(5);
                float fltBatteryPerc = rSet.getInt(6);
                int intBatteryCapacity = rSet.getInt(7);
                float fltBatteryVoltage = rSet.getFloat(8);
                Pharmacy oPharmacy = pharmacyManager(rSet,9);

                lstDrone.add(new Drone(intId, fltPotency, fltWeight, fltMaxPayload, strCharginStatus, fltBatteryPerc,
                        intBatteryCapacity, fltBatteryVoltage, oPharmacy));

                rSet.next();
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Drones Avaliable.");
    }

    public boolean removeDroneFromDB(int intId) {
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call removeDrone(?) }");

            callStmt.setInt(1, intId);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }
}
