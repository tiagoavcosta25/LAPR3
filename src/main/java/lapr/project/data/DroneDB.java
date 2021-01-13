package lapr.project.data;


import java.sql.CallableStatement;
import java.sql.SQLException;

public class DroneDB extends DataHandler  {

    public DroneDB() {

    }

    public boolean updateDrone(Float percentage, Integer pharmacyId, Float potency, Float weight, Double batteryCapacity,
                               Float maxPayload, Float batteryVoltage, String chargingStatus,Integer droneId) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{call updateDrone(?,?,?,?,?,?,?,?,?}");

            callStmt.setFloat(1, percentage);
            callStmt.setFloat(2, pharmacyId);
            callStmt.setFloat(3, potency);
            callStmt.setFloat(4, weight);
            callStmt.setDouble(5, batteryCapacity);
            callStmt.setFloat(6, maxPayload);
            callStmt.setFloat(7, batteryVoltage);
            callStmt.setString(8,chargingStatus);
            callStmt.setInt(9,droneId);

            callStmt.execute();
            closeAll();

        } catch (SQLException e) {
            return false;
        }
        return true;
    }
}
