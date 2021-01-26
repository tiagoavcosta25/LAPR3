package lapr.project.data;


import javafx.util.Pair;
import lapr.project.model.*;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScooterDB extends DataHandler {

    private static final String NOSCOOTERAVAIABLE = "No Scooters Avaliable.";

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
     * DATABASE
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

    public int registerScooter(Scooter s) {
        return addScooter(s.getBatteryPerc(), s.getModel(), s.getPharmacy());
    }

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
