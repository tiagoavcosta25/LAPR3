package lapr.project.data;

import lapr.project.model.*;
import oracle.jdbc.internal.OracleTypes;

import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DroneDB extends DataHandler {

    public boolean updateDrone(Float percentage, Integer pharmacyId, Float potency, Float weight, Double batteryCapacity,
                               Float maxPayload, Float batteryVoltage, String chargingStatus, Integer droneId) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{call updateDrone(?,?,?,?,?,?,?,?,?)}");

            callStmt.setFloat(1, percentage);
            callStmt.setInt(2, pharmacyId);
            callStmt.setFloat(3, potency);
            callStmt.setFloat(4, weight);
            callStmt.setDouble(5, batteryCapacity);
            callStmt.setFloat(6, maxPayload);
            callStmt.setFloat(7, batteryVoltage);
            callStmt.setString(8, chargingStatus);
            callStmt.setInt(9, droneId);

            callStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll();
        }
        return true;
    }

    public boolean registerDrone(Drone d) {
        return addDrone(d.getBatteryPerc(), d.getModel(), d.getPharmacy());
    }

    public boolean addDrone(double dblBatteryPerc, VehicleModel oVehicleModel, Pharmacy oPharmacy) {
        boolean flag = true;
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call addDrone(?,?,?) }");


            callStmt.setDouble(1, dblBatteryPerc);
            callStmt.setInt(2, oVehicleModel.getId());
            callStmt.setInt(3, oPharmacy.getId());

            callStmt.execute();

        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return flag;
    }

    public List<Drone> getDronesList(int intPharmacyId) {

        CallableStatement callStmt = null;
        List<Drone> lstDrone = new ArrayList<>();
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{ ? = call getDronesList(?) }");

            callStmt.registerOutParameter(1, oracle.jdbc.internal.OracleTypes.CURSOR);
            callStmt.setInt(2, intPharmacyId);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                int intId = rSet.getInt(1);
                float fltPotency = rSet.getFloat(2);
                float fltWeight = rSet.getFloat(3);
                float fltMaxPayload = rSet.getFloat(4);
                String strCharginStatus = rSet.getString(5);
                int intBatteryId = rSet.getInt(6);
                float fltBatteryPerc = rSet.getInt(7);
                int intBatteryCapacity = rSet.getInt(8);
                float fltBatteryVoltage = rSet.getFloat(9);
                Pharmacy oPharmacy = pharmacyManager(rSet, 10);

                //TODO: Criar droneManager no DataHandler
                lstDrone.add(new Drone(intId, fltBatteryPerc, new VehicleModel(), oPharmacy));
                rSet.next();
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Drones Avaliable.");
    }

    public boolean removeDroneFromDB(int intId) {
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call removeDrone(?) }");

            callStmt.setInt(1, intId);

            callStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll();
        }
        return true;

    }

    public float getDronePayload(int droneId) {

        CallableStatement callStmt = null;
        float payload = 0f;
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{ ? = call getDronePayload(?) }");

            callStmt.registerOutParameter(1, OracleTypes.FLOAT);
            callStmt.setInt(2, droneId);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            if (rSet.next()) {
                payload = rSet.getFloat(1);
            }
            return payload;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Drone with the ID.");
    }

}
