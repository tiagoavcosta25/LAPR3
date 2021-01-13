package lapr.project.data;

import lapr.project.model.*;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryRunDB extends DataHandler {

    public DeliveryRunDB(String jdbcUrl, String username, String password) {
        super(jdbcUrl, username, password);
    }

    public DeliveryRunDB() {
        super();
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
                lstAddress.add(addressManager(rSet, 1));
            }
            return lstAddress;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Addresses Avaliable.");
    }

    public boolean addNewDeliveryRun(DeliveryRun oDeliveryRun) {
        return addNewDeliveryRun(oDeliveryRun.getId(),oDeliveryRun.getCourier(),
                oDeliveryRun.getOrderList(),oDeliveryRun.getStatus());
    }

    public boolean addNewDeliveryRun(Integer id, Courier courier, List<Order> lst, DeliveryStatus status) {
        try {
            openConnection();
            List<Address> oResult = new ArrayList<>();


            CallableStatement callStmt = getConnection().prepareCall("{ ? = call addNewDeliveryRun(?,?,?) }");

            callStmt.setInt(2, id);
            callStmt.setInt(3,courier.getId());
            callStmt.setString(4,status.getDesignation());

            callStmt.execute();

            Integer deliveryRunId = callStmt.getInt(1);

            for (Order o : lst) {
                CallableStatement callStmt2 = getConnection().prepareCall("{ call addOrderToDeliveryRun(?,?) }");
                callStmt2.setInt(1,deliveryRunId);
                callStmt2.setInt(2,o.getId());

                callStmt2.execute();
            }


            closeAll();

            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    public List<Address> getAddressesByDeliveryRunId(String email) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getDeliveryRunIdByCourierEmail(?) }");

            callStmt.registerOutParameter(1, OracleTypes.NUMBER);
            callStmt.setString(2, email);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            int delRunId = 0;
            if (rSet.next()) {
                delRunId = rSet.getInt(1);
            }
            callStmt = getConnection().prepareCall("{ ? = call getAddressesByDeliveryRunId(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, delRunId);

            callStmt.execute();

            rSet = (ResultSet) callStmt.getObject(1);
            List<Address> list = new ArrayList<>();
            while (rSet.next()) {
                Address a = addressManager(rSet, 1);
                list.add(a);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Addresses in the delivery run associated with the courier with the following email:" + email);

    }


}
