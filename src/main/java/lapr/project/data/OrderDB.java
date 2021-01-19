package lapr.project.data;

import lapr.project.model.*;
import oracle.jdbc.OracleTypes;
import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class OrderDB extends DataHandler {

    public Order getOrder(int id) {

        CallableStatement callStmt = null;
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{ ? = call getOrder(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2,id);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                Order oOrder = orderManager(rSet, 1);

                callStmt = getConnection().prepareCall("{ ? = call getProductsByOrder(?) }");
                callStmt.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt.setInt(2,oOrder.getId());

                callStmt.execute();

                ResultSet rSetProducts = (ResultSet) callStmt.getObject(1);

                while (rSet.next()){
                    oOrder = orderProductManager(rSetProducts, 1, oOrder);
                }
                return oOrder;
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return null;
    }

    private boolean addOrder(float fltAmount, float fltTotalWeight, float fltAdditionalFee, Date dtOrderDate,
                          String strDescription, String strStatus, boolean blnIsHomeDelivery, Client oClient, int intPharmacyId, Map<Product, Integer> mapProducts) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ ? = call addOrder(?,?,?,?,?,?,?,?,?,?) }");

            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setFloat(2, fltAmount);
            callStmt.setFloat(3, fltTotalWeight);
            callStmt.setFloat(4, fltAdditionalFee);
            callStmt.setString(5, strDescription);
            callStmt.setDate(6, dtOrderDate);
            callStmt.setString(7, strStatus);
            callStmt.setBoolean(8, blnIsHomeDelivery);
            callStmt.setInt(9, oClient.getId());
            callStmt.setInt(10, oClient.getCredits());
            callStmt.setInt(11, intPharmacyId);

            callStmt.execute();

            Integer intId = (Integer) callStmt.getObject(1);

            if (intId != null) {
                for (Product oProduct : mapProducts.keySet()) {
                    callStmt = getConnection().prepareCall("{ call addProductToOrder(?,?,?) }");

                    callStmt.setInt(1, intId);
                    callStmt.setInt(2, oProduct.getId());
                    callStmt.setInt(3, mapProducts.get(oProduct));
                    callStmt.execute();
                }
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll();
        }
    }

    public boolean removeOrder(int intId) {

        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call removeOrder(?) }");

            callStmt.setInt(1, intId);

            callStmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll();
        }
    }

    public boolean registerOrder(Order oOrder) {
         return addOrder(oOrder.getAmount(), oOrder.getTotalWeight(), oOrder.getAdditionalFee(), oOrder.getOrderDate(),
                oOrder.getDescription(), oOrder.getStatus(), oOrder.isHomeDelivery(), oOrder.getClient(), oOrder.getPharmacy().getId(), oOrder.getProducts());
    }

    public Order getLatestOrder(Client oClient) {

        CallableStatement callStmt = null;
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{ ? = call getLatestOrder(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, oClient.getEmail());

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
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Order with the following client:" + oClient.getName());
    }

    public Order getOrderByCourier(String strEmail) {

        CallableStatement callStmt = null;
        try {
            openConnection();
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
        } finally {
            closeAll();
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
        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return flag;
    }
}
