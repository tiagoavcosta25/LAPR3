package lapr.project.data;

import lapr.project.model.*;
import oracle.jdbc.internal.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DroneDB extends DataHandler {

    public Drone getDrone(int id) {

        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call getDrone(?) }");){
            callStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            callStmt.setInt(2, id);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                return droneManager(rSet,1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Scooter with ID:" + id);
    }

    public boolean updateDroneFromDB(int intId, double dblBatteryPerc, String strDesignation, double dblPotency, double dblWeight, double dblMaxPayload,
                                       int intBatteryCapacity, double dblBatteryVoltage, double dblEfficiency) {
        try(CallableStatement callStmt = getConnection().prepareCall("{call updateDrone(?,?,?,?,?,?,?,?,?)}");) {

            callStmt.setInt(1, intId);
            callStmt.setDouble(2, dblBatteryPerc);
            callStmt.setString(3, strDesignation);
            callStmt.setDouble(4, dblPotency);
            callStmt.setDouble(5, dblWeight);
            callStmt.setDouble(6, dblMaxPayload);
            callStmt.setInt(7, intBatteryCapacity);
            callStmt.setDouble(8, dblBatteryVoltage);
            callStmt.setDouble(9, dblEfficiency);

            callStmt.execute();

        } catch (SQLException e) {
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
        try(CallableStatement callStmt = getConnection().prepareCall("{ ? = call addDrone(?,?,?) }");) {
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

    public List<Drone> getDronesList(String strPharmacyEmail) {

        List<Drone> lstDrone = new ArrayList<>();
        try(CallableStatement callStmt = getConnection().prepareCall("{ ? = call getDronesList(?) }");) {

            callStmt.registerOutParameter(1, oracle.jdbc.internal.OracleTypes.CURSOR);
            callStmt.setString(2, strPharmacyEmail);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                lstDrone.add(droneManager(rSet,1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("No Drones Avaliable.");
        } finally {
            closeAll();
        }
        return lstDrone;
    }

    public boolean removeDroneFromDB(int intId) {
        try(CallableStatement callStmt = getConnection().prepareCall("{ call removeDrone(?) }");) {
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

        float payload = 0f;
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call getDronePayload(?) }");){
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
