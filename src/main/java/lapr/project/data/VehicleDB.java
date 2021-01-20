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

    public Vehicle getSuitableVehicle(Double distanceScooter, Double distanceDrone, String email) {
        /* Objeto "callStmt" para invocar a função "getSailor" armazenada na BD.
         *
         * FUNCTION getSailor(id NUMBER) RETURN pkgSailors.ref_cursor
         * PACKAGE pkgSailors AS TYPE ref_cursor IS REF CURSOR; END pkgSailors;
         */
        CallableStatement callStmt = null;
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{ ? = call getSuitableScooter(?,?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            // Especifica o parâmetro de entrada da função "getSailor".
            callStmt.setDouble(2, distanceScooter);
            callStmt.setString(3, email);

            // Executa a invocação da função "getSailor".
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int scooterID = rSet.getInt(1);
                float batteryPercScooter = rSet.getFloat(2);
                float finalBatteryEnergyScooter = rSet.getFloat(3);
                float potencyScooter = rSet.getFloat(4);
                float weightScooter = rSet.getFloat(5);
                Integer batteryCapacityScooter = rSet.getInt(6);
                String charginStatusScooter = rSet.getString(7);
                float fltMaxPayloadScooter = rSet.getFloat(8);
                float fltBatteryVoltageScooter = rSet.getFloat(9);
                Pharmacy oPharmacyScooter = pharmacyManager(rSet, 10);

                callStmt = getConnection().prepareCall("{ ? = call getSuitableDrone(?,?) }");

                // Regista o tipo de dados SQL para interpretar o resultado obtido.
                callStmt.registerOutParameter(1, OracleTypes.CURSOR);
                // Especifica o parâmetro de entrada da função "getSailor".
                callStmt.setDouble(2, distanceDrone);
                callStmt.setString(3, email);

                // Executa a invocação da função "getSailor".
                callStmt.execute();

                // Guarda o cursor retornado num objeto "ResultSet".
                rSet = (ResultSet) callStmt.getObject(1);

                    int droneID = rSet.getInt(1);
                    float batteryPercDrone = rSet.getFloat(2);
                    float finalBatteryEnergyDrone = rSet.getFloat(3);
                    float potencyDrone = rSet.getFloat(4);
                    float weightDrone = rSet.getFloat(5);
                    Integer batteryCapacityDrone = rSet.getInt(6);
                    String charginStatusDrone = rSet.getString(7);
                    float fltMaxPayloadDrone = rSet.getFloat(8);
                    float fltBatteryVoltageDrone = rSet.getFloat(9);
                    if(finalBatteryEnergyScooter > finalBatteryEnergyDrone){
                        return new Scooter(scooterID, potencyScooter, weightScooter, fltMaxPayloadScooter, charginStatusScooter, batteryPercScooter,
                                batteryCapacityScooter, fltBatteryVoltageScooter, oPharmacyScooter);
                    }else{
                        return new Drone(droneID, potencyDrone, weightDrone, fltMaxPayloadDrone, charginStatusDrone, batteryPercDrone,
                                batteryCapacityDrone, fltBatteryVoltageDrone, oPharmacyScooter);
                    }
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Courier with email: " + email);

    }

    public ArrayList<VehicleModel> getPharmacyModel(String strPharmacyEmail) {
        /*
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

                lstModels.add(new VehicleModel(intId,strDesignation,dblPotency,dblWeight,dblMaxPayload,
                        new Battery(intBatteryId,intBatteryCapacity,dblBatteryVoltage,dblEfficiency),vehicleType));

                rSet.next();
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Models Avaliable.");
        */
        return new ArrayList<>();
    }
}
