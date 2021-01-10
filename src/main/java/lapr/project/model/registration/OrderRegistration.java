package lapr.project.model.registration;

import lapr.project.data.DataHandler;
import lapr.project.model.*;
import oracle.jdbc.OracleTypes;
import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class OrderRegistration extends DataHandler {

    public OrderRegistration(String jdbcUrl, String username, String password) {
        super(jdbcUrl, username, password);
    }

    public Order getOrder(int id) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getOrder(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(id, 2);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                Order oOrder = orderManager(rSet, 1);

                callStmt = getConnection().prepareCall("{ ? = call getProductsByOrder(?) }");
                callStmt.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt.setInt(oOrder.getId(), 2);

                callStmt.execute();

                ResultSet rSetProducts = (ResultSet) callStmt.getObject(1);

                while (rSet.next()){
                    oOrder = orderProductManager(rSetProducts, 1, oOrder);
                }
                return oOrder;
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Order with ID:" + id);
    }

    private void addOrder(float fltAmount, float fltTotalWeight, float fltAdditionalFee, Date dtOrderDate,
                          String strDescription, String strStatus, Client oClient, Address oAddress, int intPharmacyId, Map<Product, Integer> mapProducts) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call addOrder(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");

            callStmt.setFloat(1, fltAmount);
            callStmt.setFloat(2, fltTotalWeight);
            callStmt.setFloat(3, fltAdditionalFee);
            callStmt.setString(4, strDescription);
            callStmt.setDate(5, dtOrderDate);
            callStmt.setString(6, strStatus);
            callStmt.setInt(7, oClient.getM_id());
            callStmt.setInt(8, oClient.getM_credits());
            callStmt.setDouble(9, oAddress.getM_latitude());
            callStmt.setDouble(10, oAddress.getM_longitude());
            callStmt.setString(11, oAddress.getM_streetName());
            callStmt.setString(12, oAddress.getM_doorNumber());
            callStmt.setString(13, oAddress.getM_postalCode());
            callStmt.setString(14, oAddress.getM_locality());
            callStmt.setString(15, oAddress.getM_country());
            callStmt.setInt(16, intPharmacyId);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            Integer intId = rSet.getInt(1);

            if (intId != null) {
                for (Product oProduct : mapProducts.keySet()) {
                    callStmt = getConnection().prepareCall("{ call addProductToOrder(?,?,?,?,?,?) }");

                    callStmt.setInt(1, intId);
                    callStmt.setInt(2, oProduct.getId());
                    callStmt.setString(3, oProduct.getName());
                    callStmt.setString(4, oProduct.getDescription());
                    callStmt.setFloat(5, oProduct.getUnitaryPrice());
                    callStmt.setFloat(6, oProduct.getUnitaryWeight());
                    callStmt.setInt(6, mapProducts.get(oProduct));
                    callStmt.execute();
                }
            }

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
        addOrder(oOrder.getAmount(), oOrder.getTotalWeight(), oOrder.getAdditionalFee(), oOrder.getOrderDate(),
                oOrder.getDescription(), oOrder.getStatus(), oOrder.getClient(), oOrder.getAddress(), oOrder.getPharmacy().getId(), oOrder.getProducts());
    }

    public Order newOrder(float fltAmount, float fltTotalWeight, float fltAdditionalFee, Date dtOrderDate,
                          String strDescription, String strStatus, Client oClient, Double latitude, Double longitude, String streetName,
                          String doorNumber, String postalCode, String locality, String country, Pharmacy oPharmacy, Map<Product, Integer> mapProducts) {
        return new Order(fltAmount, fltTotalWeight, fltAdditionalFee, dtOrderDate,
                strDescription, strStatus, oClient, new Address(latitude, longitude, streetName, doorNumber, postalCode, locality, country), oPharmacy, mapProducts);
    }

    public Order getLatestOrder(Client oClient) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getLatestOrder(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, oClient.getStrEmail());

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                Order oOrder = orderManager(rSet, 1);

                callStmt = getConnection().prepareCall("{ ? = call getProductsByOrder(?) }");
                callStmt.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt.setInt(oOrder.getId(), 2);

                callStmt.execute();

                ResultSet rSetProducts = (ResultSet) callStmt.getObject(1);

                while (rSet.next()){
                    oOrder = orderProductManager(rSetProducts, 1, oOrder);
                }
                return oOrder;
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Order with the following client:" + oClient.getM_name());
    }

    public Order getOrderByCourier(String strEmail) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getOrderByCourier(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, strEmail);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                return orderManager(rSet, 1);
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        throw new IllegalArgumentException("No Order with the following courier email:" + strEmail);
    }


    public boolean notifyAndRemove(Order order) {
        boolean flag = true;
        try {
            openConnection();
            /*
             *  Objeto "callStmt" para invocar o procedimento "addSailor" armazenado
             *  na BD.
             *
             *  PROCEDURE addSailor(sid NUMBER, sname VARCHAR, rating NUMBER, age NUMBER)
             *  PACKAGE pkgSailors AS TYPE ref_cursor IS REF CURSOR; END pkgSailors;
             */
            CallableStatement callStmt = getConnection().prepareCall("{ call removeProductPharmacy(?) }");

            Integer id = order.getId();
            callStmt.setInt(1, id);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }
}
