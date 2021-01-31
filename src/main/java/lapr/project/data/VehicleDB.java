package lapr.project.data;


import lapr.project.model.*;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Vehicle Database.
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

public class VehicleDB extends DataHandler {

    /**
     * Returns a list of Vehicle Models by Pharmacy Email.
     * @param strPharmacyEmail Pharmacy Email.
     * @return a list of Vehicle Models.
     */
    public List<VehicleModel> getPharmacyModel(String strPharmacyEmail) {

        List<VehicleModel> lstModels = new ArrayList<>();
        try(CallableStatement callStmt = getConnection().prepareCall("{ ? = call getPharmacyModel(?) }");) {

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
            }
            return lstModels;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Models Avaliable.");
    }

    /**
     * Returns a Vehicle Model by Designation.
     * @param strDesignation Vehicle Model Designation.
     * @return a Vehicle Model
     */
    public VehicleModel getVehicleModel(String strDesignation) {

        try(CallableStatement callStmt = getConnection().prepareCall("{ ? = call getVehicleModel(?) }");) {

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, strDesignation);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                return vehicleModelManager(rSet, 1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Model with Designation:" + strDesignation);
    }

    /**
     * Gets all the Vehicle Model's atributtes.
     * @param moVehicleModel Vehicle Model.
     * @return Vehicle Model ID.
     */
    public int registerVehicleModel(VehicleModel moVehicleModel) {
        return addVehicleModel(moVehicleModel.getDesignation(), moVehicleModel.getMaxPayload(), moVehicleModel.getPotency(),
                moVehicleModel.getVehicleType(), moVehicleModel.getWeight(), moVehicleModel.getBattery());
    }

    /**
     * Registers a new Vehicle Model.
     * @param designation Vehicle Model Designation
     * @param maxPayload Vehicle ModelMaximum Payload
     * @param potency Vehicle Model Potency
     * @param vehicleType Vehicle Model Type
     * @param weight Vehicle Model Weight
     * @param battery Vehicle Model Battery
     * @return Vehicle Model ID.
     */
    private int addVehicleModel(String designation, double maxPayload, double potency, VehicleType vehicleType, double weight, Battery battery) {
        try(CallableStatement callStmt = getConnection().prepareCall("{ ? = call addVehicleModel(?,?,?,?,?,?,?,?) }");) {
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

    /**
     * Returns Energy By Vehicle Model.
     * @param intVehicleModel Vehicle Model ID.
     * @return Energy.
     */
    public Double getEnergyByVehicleModel(int intVehicleModel) {
        try(CallableStatement callStmt = getConnection().prepareCall("{ ? = call getEnergyByVehicleModel(?) }");) {

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

    /**
     * Returns Vehicle Payload.
     * @param vehicleId Vehicle ID.
     * @return Vehicle Payload.
     */
    public double getVehiclePayload(int vehicleId, String email){
        try(CallableStatement callStmt = getConnection().prepareCall("{ ? = call getVehiclePayload(?) }");) {

            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setInt(2, vehicleId);
            callStmt.setString(3,email);
            callStmt.execute();
            return callStmt.getDouble(1);
        } catch (SQLException e) {
            throw new IllegalArgumentException("Couldn't return value.");
        } finally {
            closeAll();
        }
    }
}
