package lapr.project.data;


import lapr.project.model.*;
import oracle.jdbc.OracleTypes;

import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDB extends DataHandler {

    public List<VehicleModel> getPharmacyModel(String strPharmacyEmail) {

        CallableStatement callStmt = null;
        List<VehicleModel> lstModels = new ArrayList<>();
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{ ? = call getPharmacyModel(?) }");

            callStmt.registerOutParameter(1, oracle.jdbc.internal.OracleTypes.CURSOR);
            callStmt.setString(2, strPharmacyEmail);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while(rSet.next()){
                int intId = rSet.getInt(1);
                String strDesignation = rSet.getString(2);
                double dblPotency = rSet.getDouble(3);
                double dblWeight = rSet.getDouble(4);
                double dblMaxPayload = rSet.getDouble(5);
                int intBatteryId = rSet.getInt(6);
                int intBatteryCapacity = rSet.getInt(7);
                double dblBatteryVoltage = rSet.getDouble(8);
                double dblEfficiency = rSet.getDouble(9);
                String vehicleType = rSet.getString(10);
                VehicleType vt;
                if(vehicleType.equals(VehicleType.SCOOTER.getDesignation()))
                    vt = VehicleType.SCOOTER;
                else
                    vt = VehicleType.DRONE;


                lstModels.add(new VehicleModel(intId,strDesignation,dblPotency,dblWeight,dblMaxPayload,
                        new Battery(intBatteryId,intBatteryCapacity,dblBatteryVoltage,dblEfficiency),vt));

                rSet.next();
            }
            return lstModels;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                callStmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            closeAll();
        }
        throw new IllegalArgumentException("No Models Avaliable.");
    }

    public VehicleModel getVehicleModel(String strDesignation) {

        CallableStatement callStmt = null;
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{ ? = call getVehicleModel(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, strDesignation);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {

                int intModelId = rSet.getInt(1);
                double dblPotency = rSet.getDouble(2);
                double dblWeight = rSet.getDouble(3);
                double dblMaxPayload = rSet.getDouble(4);
                VehicleType oVehicleType = VehicleType.getTypeByDesignation(rSet.getString(5));
                int intBatteryId = rSet.getInt(6);
                int intBatteryCapacity = rSet.getInt(7);
                double dblBatteryVoltage = rSet.getDouble(8);
                double dblEfficiency = rSet.getDouble(9);

                return new VehicleModel(intModelId, strDesignation, dblPotency, dblWeight,
                        dblMaxPayload, new Battery(intBatteryId, intBatteryCapacity, dblBatteryVoltage, dblEfficiency),
                        oVehicleType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Model with Designation:" + strDesignation);
    }

    public int registerVehicleModel(VehicleModel moVehicleModel) {
        return addVehicleModel(moVehicleModel.getDesignation(), moVehicleModel.getMaxPayload(), moVehicleModel.getPotency(),
                moVehicleModel.getVehicleType(), moVehicleModel.getWeight(), moVehicleModel.getBattery());
    }

    private int addVehicleModel(String designation, double maxPayload, double potency, VehicleType vehicleType, double weight, Battery battery) {
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ ? = call addVehicleModel(?,?,?,?,?,?,?,?) }");


            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setString(2, designation);
            callStmt.setDouble(3, maxPayload);
            callStmt.setDouble(4, potency);
            callStmt.setDouble(5, weight);
            callStmt.setString(6, vehicleType.getDesignation());
            callStmt.setInt(7, battery.getBatteryCapacity());
            callStmt.setDouble(8, battery.getBatteryVoltage());
            callStmt.setDouble(9, battery.getEfficiency());

            callStmt.execute();
            return (int) callStmt.getObject(1);

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            closeAll();
        }
    }

    public Double getEnergyByVehicleModel(int intVehicleModel) {
        CallableStatement callStmt = null;
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{ ? = call getEnergyByVehicleModel(?) }");

            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setInt(2, intVehicleModel);
            callStmt.execute();
            return callStmt.getDouble(1);
        } catch (SQLException e) {
            throw new IllegalArgumentException("Couldn't return value.");
        } finally {
            closeAll();
        }
    }
    public double getVehiclePayload(int vehicleId){
        CallableStatement callStmt = null;
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{ ? = call getVehiclePayload(?) }");

            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setInt(2, vehicleId);
            callStmt.execute();
            return callStmt.getDouble(1);
        } catch (SQLException e) {
            throw new IllegalArgumentException("Couldn't return value.");
        } finally {
            closeAll();
        }
    }
}
