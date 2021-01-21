package lapr.project.data;

import lapr.project.model.*;
import oracle.jdbc.OracleTypes;
import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

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

    private boolean addOrder(Double dblAmount, Double dblTotalWeight, Double dblAdditionalFee, Date dtOrderDate,
                          String strDescription, String strStatus, boolean blnIsHomeDelivery, Client oClient, int intPharmacyId, Map<Product, Integer> mapProducts) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ ? = call addOrder(?,?,?,?,?,?,?,?,?,?) }");

            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setDouble(2, dblAmount);
            callStmt.setDouble(3, dblTotalWeight);
            callStmt.setDouble(4, dblAdditionalFee);
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


    public Map<Product, Integer> notifyAndRemove(Order order) {
        CallableStatement callStmt = null;
        Map<Product, Integer> lstProducts = new TreeMap<>();
        for (Map.Entry<Product, Integer> entry : order.getProducts().entrySet()) {
            try {
                openConnection();
                callStmt = getConnection().prepareCall("{ ? = call removeProductsPharmacy(?,?,?) }");

                callStmt.registerOutParameter(1, oracle.jdbc.internal.OracleTypes.CURSOR);
                callStmt.setString(2, order.getPharmacy().getEmail());
                callStmt.setInt(3, entry.getKey().getId());
                callStmt.setInt(4, entry.getValue().intValue());

                callStmt.execute();

                ResultSet rSet = (ResultSet) callStmt.getObject(1);

                if (rSet == null) {
                    return null;
                }
                while (rSet.next()) {
                    Product product = productManager(rSet, 1);
                    Integer intAskQuantity = rSet.getInt(6);
                    lstProducts.put(product, intAskQuantity);
                    rSet.next();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeAll();
            }
            throw new IllegalArgumentException("No Products Avaliable.");
        }

        return lstProducts;

    }
}
