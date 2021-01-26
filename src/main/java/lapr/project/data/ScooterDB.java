package lapr.project.data;


import javafx.util.Pair;
import lapr.project.model.*;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Scooter Database.
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

public class ScooterDB extends DataHandler {

    private static final String NOSCOOTERAVAIABLE = "No Scooters Avaliable.";

    /**
     * Returns Scooter by ID.
     * @param id Scooter ID.
     * @return Scooter.
     */
    public Scooter getScooter(int id) {

        try(CallableStatement callStmt = getConnection().prepareCall("{ ? = call getScooter(?) }");) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, id);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                return scooterManager(rSet,1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Scooter with ID:" + id);
    }

    /**
     * Add a new Scooter to the DataBase.
     * @param dblBatteryPerc Scooter Battery Percentage.
     * @param oVehicleModel Scooter Vehicle Model.
     * @param oPharmacy Scooter Pharmacy.
     * @return Model ID.
     */
    private int addScooter(double dblBatteryPerc, VehicleModel oVehicleModel, Pharmacy oPharmacy) {
        try(CallableStatement callStmt = getConnection().prepareCall("{ ? = call addScooter(?,?,?) }");) {
            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
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
     * Update Scooter from Database.
     * The method returns the validation of that instance of Scooter. True if the data is correct and false if
     * it doesn't.
     *
     * @param
     * @param intId Drone ID.
     * @param dblBatteryPerc Drone Battery Percentage.
     * @param strDesignation Drone Designation.
     * @param dblPotency Drone Potency.
     * @param dblWeight Drone Weight.
     * @param dblMaxPayload Drone Maximum Payload.
     * @param intBatteryCapacity Drone Battery Capacity.
     * @param dblBatteryVoltage Drone Battery Voltage.
     * @param dblEfficiency Drone Efficiency.
     * @return the validation of that instance of Drone.
     */
    public boolean updateScooterFromDB(int intId, double dblBatteryPerc, String strDesignation, double dblPotency, double dblWeight, double dblMaxPayload,
                                       int intBatteryCapacity, double dblBatteryVoltage, double dblEfficiency) {
        try(CallableStatement callStmt = getConnection().prepareCall("{call updateScooter(?,?,?,?,?,?,?,?,?)}");) {

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
     * Returns a list of Scooters by Pharmacy Email.
     * @param strPharmacyEmail Pharmacy Email.
     * @return a list of Scooters
     */
    public List<Scooter> getScootersList(String strPharmacyEmail) {
        List<Scooter> lstScooter = new ArrayList<>();
        try(CallableStatement callStmt = getConnection().prepareCall("{ ? = call getScootersList(?) }");) {

            callStmt.registerOutParameter(1, oracle.jdbc.internal.OracleTypes.CURSOR);
            callStmt.setString(2, strPharmacyEmail);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while(rSet.next()){
                lstScooter.add(scooterManager(rSet,1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(NOSCOOTERAVAIABLE);
        } finally {
            closeAll();
        }
        return lstScooter;
    }

    /**
     * The method removes a Scooter from the Database.
     * The method returns the validation of that instance of Scooter. True if the data is correct and false if
     * it doesn't.
     * @param intId Scooter ID.
     * @return the validation of that instance of Scooter.
     */
    public boolean removeScooterFromDB(int intId) {
        try(CallableStatement callStmt = getConnection().prepareCall("{ call removeScooter(?) }");) {

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
     * Gets all the Scooter's atributtes.
     * @param s Scooter.
     * @return Model ID.
     */
    public int registerScooter(Scooter s) {
        return addScooter(s.getBatteryPerc(), s.getModel(), s.getPharmacy());
    }

    /**
     * Returns a list of a pair with the Scooter's Email and Sccoter.
     * @param intParkId Park ID.
     * @return list of a pair with the Scooter's Email and Sccoter.
     */
    public List<Pair<String, Scooter>> getEmailPerChargingScooter(int intParkId) {
        List<Pair<String, Scooter>> lstPairs = new ArrayList<>();
        try(CallableStatement callStmt = getConnection().prepareCall("{ ? = call getEmailPerChargingScooter(?) }");) {

            callStmt.registerOutParameter(1, oracle.jdbc.internal.OracleTypes.CURSOR);
            callStmt.setInt(2, intParkId);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while(rSet.next()){
                String strEmail = rSet.getString(1);
                Scooter oScooter = scooterManager(rSet,2);
                lstPairs.add(new Pair<>(strEmail, oScooter));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(NOSCOOTERAVAIABLE);
        } finally {
            closeAll();
        }
        return lstPairs;
    }

    /**
     * Returns the Charger.
     * @param intParkId Park ID.
     * @return the Charger.
     */
    public Double getCurrentPerCharger(int intParkId) {
        try(CallableStatement callStmt = getConnection().prepareCall("{ ? = call getCurrentPerCharger(?) }");) {

            callStmt.registerOutParameter(1, OracleTypes.NUMBER);
            callStmt.setInt(2, intParkId);
            callStmt.execute();



            return callStmt.getDouble(1);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(NOSCOOTERAVAIABLE);
        } finally {
            closeAll();
        }
    }

}
