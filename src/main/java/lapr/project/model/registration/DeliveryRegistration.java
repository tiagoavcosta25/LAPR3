package lapr.project.model.registration;

import javafx.util.Pair;
import lapr.project.data.DataHandler;
import lapr.project.model.Address;
import lapr.project.model.Path;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryRegistration extends DataHandler {
    public DeliveryRegistration(String jdbcUrl, String username, String password) {
        super(jdbcUrl, username, password);
    }

    public double getShortestPath(Pair<Address, Address> oPairAddress) {
        return 0;
    }

    public Pair<Address, Address> getStartingAndDeliveryAddressByOrder(int oOrderId) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getStartingAndDeliveryAddressByOrderId(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, oOrderId);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                Address a1 = addressManager(rSet, 1);
                Address a2 = addressManager(rSet, 9);
                return new Pair<Address, Address>(a1, a2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Order with the following id:" + oOrderId);
    }

    public float getDeliveryEnergy() {
        /* ||| IMPLEMENT METHOD ||| */
        return 0;
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
            callStmt = getConnection().prepareCall("{ ? = call getAllAddress() }");

            callStmt.registerOutParameter(1, oracle.jdbc.internal.OracleTypes.CURSOR);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                int intId = rSet.getInt(1);
                double dblLatitude = rSet.getDouble(2);
                double dblLongitude = rSet.getDouble(3);
                String strDoorNumber = rSet.getString(4);
                String strStreetName = rSet.getString(5);
                String strPostalCode = rSet.getString(6);
                String strLocality = rSet.getString(7);
                String strCountry = rSet.getString(8);
                lstAddress.add(new Address(intId, dblLatitude, dblLongitude, strDoorNumber, strStreetName,
                        strPostalCode, strLocality, strCountry));
            }
            return lstAddress;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Addresses Avaliable.");
    }
}
