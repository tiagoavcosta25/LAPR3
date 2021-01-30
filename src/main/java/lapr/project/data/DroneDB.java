package lapr.project.data;

import lapr.project.model.*;
import oracle.jdbc.internal.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Drone Database.
 *
 * Group: Team Lisa [G-021]
 * ______________________________________________________
 * @author António Barbosa <1190404@isep.ipp.pt>
 * @author Ernesto Rodrigues <1190560@isep.ipp.pt>
 * @author Jessica Alves <1190682@isep.ipp.pt>
 * @author Pedro Santos <1190967@isep.ipp.pt>
 * @author Rodrigo Costa <1191014@isep.ipp.pt>
 * @author Tiago Costa <1191460@isep.ipp.pt>
 */

public class DroneDB extends DataHandler {

    /**
     * Returns Drone by ID.
     * @param id Drone ID.
     * @return Drone.
     */
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

    /**
     * Update Drone from Database.
     *
     * @param intId Drone ID.
     * @param dblBatteryPerc Drone Battery Percentage.
     * @param strDesignation Drone Designation.
     * @param dblPotency Drone Potency.
     * @param dblWeight Drone Weight.
     * @param dblMaxPayload Drone Maximum Payload.
     * @param intBatteryCapacity Drone Battery Capacity.
     * @param dblBatteryVoltage Drone Battery Voltage.
     * @param dblEfficiency Drone Efficiency.
     * @return true if the Drone is updated. False if otherwise.
     */
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

    /**
     * Gets all the Drone's atributtes.
     * @param d Drone.
     * @return Model ID.
     */
    public int registerDrone(Drone d) {
        return addDrone(d.getBatteryPerc(), d.getModel(), d.getPharmacy());
    }

    /**
     * Adds a new Drone to the Database.
     * @param dblBatteryPerc Drone Battery Percentage.
     * @param oVehicleModel Drone Vehicle Model.
     * @param oPharmacy Drone Pharmacy.
     * @return Model ID.
     */
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

    /**
     * Returns a list of Drones by Pharmacy Email.
     * @param strPharmacyEmail Pharmacy Email.
     * @return a list of Drones
     */
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

    /**
     * The method removes a Drone from the Database.
     * @param intId Drone ID.
     * @return true if the Drone is removed. False if otherwise.
     */
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

    /**
     * Returns the Drone Payload.
     * @param droneId Drone Id.
     * @return Drone Payload.
     */
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
