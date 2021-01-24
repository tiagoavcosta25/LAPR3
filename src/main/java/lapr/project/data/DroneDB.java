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

    public Drone getDrone(int id) {

        CallableStatement callStmt = null;
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{ ? = call getDrone(?) }");

            callStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            callStmt.setInt(2, id);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                return droneManager(rSet,1);
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Scooter with ID:" + id);
    }

    public boolean updateDrone(Float percentage, String pharmacyEmail, Float potency, Float weight, Double batteryCapacity,
                               Float maxPayload, Float batteryVoltage, String chargingStatus, Integer droneId) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{call updateDrone(?,?,?,?,?,?,?,?,?)}");

            callStmt.setFloat(1, percentage);
            callStmt.setString(2, pharmacyEmail);
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

    public int registerDrone(Drone d) {
        return addDrone(d.getBatteryPerc(), d.getModel(), d.getPharmacy());
    }

    public int addDrone(double dblBatteryPerc, VehicleModel oVehicleModel, Pharmacy oPharmacy) {
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ ? = call addDrone(?,?,?) }");

            callStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            callStmt.setDouble(2, dblBatteryPerc);
            callStmt.setInt(3, oVehicleModel.getId());
            callStmt.setInt(4, oPharmacy.getId());

            callStmt.execute();
            return (int) callStmt.getObject(1);

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            closeAll();
        }
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
                lstDrone.add(droneManager(rSet,1));
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("No Drones Avaliable.");
        } finally {
            closeAll();
        }
        return lstDrone;
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
