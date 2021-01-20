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
            callStmt.setInt(id, 2);

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

    private boolean addScooter(double dblBatteryPerc, VehicleModel oVehicleModel, Pharmacy oPharmacy) {
        boolean flag = true;
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call addScooter(?,?,?) }");


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

    public boolean updateScooterFromDB(int intId, float fltBatteryPerc, String strCharginStatus, float fltPotency,
                                       float fltWeight, int intBatteryCapacity, float fltBatteryVoltage,
                                       float fltMaxPayload, int intPharmacyId) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{call updateScooter(?,?,?,?,?,?,?,?)}");

            callStmt.setInt(1, intId);
            callStmt.setFloat(2, fltPotency);
            callStmt.setFloat(3, fltWeight);
            callStmt.setFloat(4, fltMaxPayload);
            callStmt.setString(5, strCharginStatus);
            callStmt.setFloat(6, fltBatteryPerc);
            callStmt.setFloat(7, intBatteryCapacity);
            callStmt.setFloat(8, fltBatteryVoltage);
            callStmt.setInt(9, intPharmacyId);

            callStmt.execute();

        } catch (SQLException e) {
            return false;
        } finally {
            closeAll();
        }
        return true;
    }

    public List<Scooter> getScootersList(int intPharmacyId) {
        CallableStatement callStmt = null;
        List<Scooter> lstScooter = new ArrayList<>();
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{ ? = call getScootersList(?) }");

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
                int intBatteryId = rSet.getInt(6);
                float fltBatteryPerc = rSet.getInt(7);
                int intBatteryCapacity = rSet.getInt(8);
                float fltBatteryVoltage = rSet.getFloat(9);
                Pharmacy oPharmacy = pharmacyManager(rSet,10);

                //TODO: Criar scooterManager no DataHandler
                lstScooter.add(new Scooter(intId, fltBatteryPerc, new VehicleModel(), oPharmacy));

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

    public boolean registerScooter(Scooter s) {
        return addScooter(s.getBatteryPerc(), s.getModel(), s.getPharmacy());
    }

}
