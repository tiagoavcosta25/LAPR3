package lapr.project.data;

import lapr.project.graph.map.Graph;
import lapr.project.model.*;
import oracle.jdbc.OracleTypes;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DeliveryDB extends DataHandler {

    private Graph<Address, String> m_graph;

    public DeliveryDB(String jdbcUrl, String username, String password) {
        super(jdbcUrl, username, password);
        m_graph = new Graph<>(true);
    }
    public DeliveryDB(){
        super();
    }

    public Graph<Address, String> getM_graph() {
        return m_graph;
    }


    public boolean addPathToDB(Path p) {
        return addPathToDB(p.getIdAddressA(), p.getIdAddressB(), p.getName());
    }

    private boolean addPathToDB(int intIdAddressA, int intIdAddressB, String strName) {
        boolean flag = true;
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call addPath(?,?) }");

            callStmt.setInt(1, intIdAddressA);
            callStmt.setInt(2, intIdAddressB);
            callStmt.setString(3, strName);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

    public List<Path> getAllPaths() {
        CallableStatement callStmt = null;
        List<Path> lstPaths = new ArrayList<>();
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{ ? = call getPaths() }");

            callStmt.registerOutParameter(1, oracle.jdbc.internal.OracleTypes.CURSOR);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                int intIdAddress1 = rSet.getInt(1);
                int intIdAddress2 = rSet.getInt(2);
                String strName = rSet.getString(3);
                lstPaths.add(new Path(intIdAddress1, intIdAddress2, strName));
            }
            closeAll();
            return lstPaths;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Paths Avaliable.");
    }


    public List<Address> getAllAddresses() {
        CallableStatement callStmt = null;
        List<Address> lstAddress = new ArrayList<>();
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{ ? = call getAllAddress() }");

            callStmt.registerOutParameter(1, oracle.jdbc.internal.OracleTypes.CURSOR);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                lstAddress.add(addressManager(rSet, 1));
            }
            closeAll();
            return lstAddress;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Addresses Avaliable.");
    }


    //TO IMPLEMENT
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
            closeAll();
            return maxPayload;

        } catch (SQLException e) {
            e.printStackTrace();
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
            closeAll();
            return lstClients;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Scooters Avaliable.");
    }
}
