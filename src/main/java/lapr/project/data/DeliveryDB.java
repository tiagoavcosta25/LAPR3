package lapr.project.data;

import lapr.project.model.*;
import oracle.jdbc.OracleTypes;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DeliveryDB extends DataHandler {


    public Map<String,String> startDeliveryRun(String currentUserEmail) {
        CallableStatement callStmt = null;
        Map<String,String> lstClients = new TreeMap<>();
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{ ? = call getClientsEmail(?) }");

            callStmt.registerOutParameter(1, oracle.jdbc.internal.OracleTypes.CURSOR);
            callStmt.setString(2, currentUserEmail);
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
