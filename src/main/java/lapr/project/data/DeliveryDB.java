package lapr.project.data;

import lapr.project.model.*;
import oracle.jdbc.OracleTypes;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DeliveryDB extends DataHandler {


    //TODO: IMPLEMENTAR
    public float getMaxPayload(String email) {
        CallableStatement callStmt = null;
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{ ? = call getMaxPayload(?) }");

            callStmt.registerOutParameter(1, OracleTypes.FLOAT);
            callStmt.setString(2, email);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            float maxPayload = 0;
            if (rSet.next()) {
                maxPayload= rSet.getFloat(1);
            }
            return maxPayload;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No payload found for the DR with the courier with the following email:" + email);

    }

    public Map<String,String> startDeliveryRun(Vehicle vehicle, String currentUserEmail) {
        CallableStatement callStmt = null;
        Map<String,String> lstClients = new TreeMap<>();
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{ ? = call startDeliveryRun(?,?) }");

            callStmt.registerOutParameter(1, oracle.jdbc.internal.OracleTypes.CURSOR);
            callStmt.setInt(2, vehicle.getId());
            callStmt.setString(3, currentUserEmail);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while(rSet.next()){
                String clientEmail = rSet.getString(1);
                Integer orderID = rSet.getInt(2);
                String orderDesc = rSet.getString(3);
                String info = ("Tracking number: " + orderID + "\nOrder description: " + orderDesc);
                lstClients.put(clientEmail,info);
                rSet.next();
            }
            return lstClients;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Scooters Avaliable.");
    }
}
