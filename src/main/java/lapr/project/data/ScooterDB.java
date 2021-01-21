package lapr.project.data;


import lapr.project.model.*;
import oracle.jdbc.OracleTypes;

import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScooterDB extends DataHandler {

    public Scooter getScooter(int id) {

        CallableStatement callStmt = null;
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{ ? = call getScooter(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, id);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {

                double dblBatteryPerc = rSet.getDouble(1);
                int intModelId = rSet.getInt(2);
                String strDesignation = rSet.getString(3);
                double dblPotency = rSet.getDouble(4);
                double dblWeight = rSet.getDouble(5);
                double dblMaxPayload = rSet.getDouble(6);
                int intBatteryId = rSet.getInt(7);
                int intBatteryCapacity = rSet.getInt(8);
                double dblBatteryVoltage = rSet.getDouble(9);
                double dblEfficiency = rSet.getDouble(10);
                Pharmacy oPharmacy = pharmacyManager(rSet,11);

                VehicleModel oVehicleModel = new VehicleModel(intModelId, strDesignation, dblPotency, dblWeight,
                        dblMaxPayload, new Battery(intBatteryId, intBatteryCapacity, dblBatteryVoltage, dblEfficiency),
                        VehicleType.SCOOTER);
                return new Scooter(id, dblBatteryPerc, oVehicleModel, oPharmacy);
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
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
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ ? = call addScooter(?,?,?) }");

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
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{call updateScooter(?,?,?,?,?,?,?,?,?)}");

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
        CallableStatement callStmt = null;
        List<Scooter> lstScooter = new ArrayList<>();
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{ ? = call getScootersList(?) }");

            callStmt.registerOutParameter(1, oracle.jdbc.internal.OracleTypes.CURSOR);
            callStmt.setString(2, strPharmacyEmail);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while(rSet.next()){
                int intId = rSet.getInt(1);
                double dblPotency = rSet.getDouble(2);
                double dblWeight = rSet.getDouble(3);
                double dblMaxPayload = rSet.getDouble(4);
                String strCharginStatus = rSet.getString(5);
                int intBatteryId = rSet.getInt(6);
                double dblBatteryPerc = rSet.getDouble(7);
                int intBatteryCapacity = rSet.getInt(8);
                double dblBatteryVoltage = rSet.getDouble(9);
                Pharmacy oPharmacy = pharmacyManager(rSet,10);

                //TODO: Criar scooterManager no DataHandler
                lstScooter.add(new Scooter(intId, dblBatteryPerc, new VehicleModel(), oPharmacy));

                rSet.next();
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Scooters Avaliable.");
    }

    public boolean removeScooterFromDB(int intId) {
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call removeScooter(?) }");

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

}
