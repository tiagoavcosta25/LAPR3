package lapr.project.model.registration;

import lapr.project.data.DataHandler;
import lapr.project.model.Address;
import lapr.project.model.Client;
import lapr.project.model.Courier;
import lapr.project.model.Order;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRegistration extends DataHandler {

    public Order getOrder(int id) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getOrder(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(id, 1);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {

                int intId = rSet.getInt(1);
                float fltAmount = rSet.getFloat(2);
                float fltTotalWeight = rSet.getFloat(3);
                float fltAdditionalFee = rSet.getFloat(4);
                Date dtOrderDate = rSet.getDate(5);
                String strDescription = rSet.getString(6);
                String strStatus = rSet.getString(7);

                // FALTA: getAddressById e getClientById

                return new Order(intId, fltAmount, fltTotalWeight, fltAdditionalFee, dtOrderDate, strDescription, strStatus, new Client(), new Address());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Order with ID:" + id);
    }

    private void addOrder(float fltAmount, float fltTotalWeight, float fltAdditionalFee, Date dtOrderDate,
                              String strDescription, String strStatus, Client oClient, Address oAddress) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call addOrder(?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");

            callStmt.setFloat(1, fltAmount);
            callStmt.setFloat(2, fltTotalWeight);
            callStmt.setFloat(3, fltAdditionalFee);
            callStmt.setString(4, strDescription);
            callStmt.setDate(5, dtOrderDate);
            callStmt.setInt(6, oClient.getM_id());
            callStmt.setFloat(7, oAddress.getM_latitude());
            callStmt.setFloat(8, oAddress.getM_longitude());
            callStmt.setString(9, oAddress.getM_streetName());
            callStmt.setString(10, oAddress.getM_doorNumber());
            callStmt.setString(11, oAddress.getM_postalCode());
            callStmt.setString(12, oAddress.getM_locality());
            callStmt.setString(13, oAddress.getM_country());

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeOrder(int intId) {

        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call removeOrder(?) }");

            callStmt.setInt(1, intId);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void registerOrder(Order oOrder) {
        addOrder(oOrder.getAmount(), oOrder.getTotalWeight(), oOrder.getAdditionalFee(), oOrder.getOrderDate(), oOrder.getDescription(), oOrder.getStatus(), oOrder.getClient(), oOrder.getAddress());
    }

    public Order newOrder(float fltAmount, float fltTotalWeight, float fltAdditionalFee, Date dtOrderDate,
                         String strDescription, String strStatus, Client oClient, float latitude, float longitude, String streetName,
                         String doorNumber, String postalCode, String locality, String country) {
        return new Order(fltAmount, fltTotalWeight, fltAdditionalFee, dtOrderDate,
                strDescription, strStatus, oClient, new Address(latitude, longitude, streetName, doorNumber, postalCode, locality, country));
    }
    public Order getOrderByCourier(Courier oCourier){
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
