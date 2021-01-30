package lapr.project.data;

import lapr.project.model.*;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Order DB.
 * <p>
 * Group: Team Lisa [G-021]
 * ______________________________________________________
 *
 * @author António Barbosa <1190404@isep.ipp.pt>
 * @author Ernesto Rodrigues <1190560@isep.ipp.pt>
 * @author Jessica Alves <1190682@isep.ipp.pt>
 * @author Pedro Santos <1190967@isep.ipp.pt>
 * @author Rodrigo Costa <1191014@isep.ipp.pt>
 * @author Tiago Costa <1191460@isep.ipp.pt>
 */
public class OrderDB extends DataHandler {

    /**
     * Method that gets an order from the database.
     *
     * @param id Order's ID.
     * @return Order.
     */
    public Order getOrder(int id) {

        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call getOrder(?) }");
             CallableStatement callStmt2 = getConnection().prepareCall("{ ? = call getProductsByOrder(?) }");) {

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, id);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                Order oOrder = orderManager(rSet, 1);

                callStmt2.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt2.setInt(2, id);

                callStmt2.execute();

                ResultSet rSetProducts = (ResultSet) callStmt2.getObject(1);

                while (rSetProducts.next()) {
                    oOrder = orderProductManager(rSetProducts, 1, oOrder);
                }
                return oOrder;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return null;
    }

    /**
     * Adds an Order to the database.
     *
     * @param dblAmount         Amount.
     * @param dblTotalWeight    Total Weight.
     * @param dblAdditionalFee  Additional Fee.
     * @param dtOrderDate       Date.
     * @param strDescription    Description.
     * @param strStatus         Status.
     * @param blnIsHomeDelivery Is Home Delivery.
     * @param oClient           Client.
     * @param intPharmacyId     Pharmacy's Id.
     * @param mapProducts       Products.
     * @return Order's Id.
     */
    private int addOrder(Double dblAmount, Double dblTotalWeight, Double dblAdditionalFee, Date dtOrderDate,
                         String strDescription, String strStatus, boolean blnIsHomeDelivery, Client oClient, int intPharmacyId, Map<Product, Integer> mapProducts) {
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call addOrder(?,?,?,?,?,?,?,?,?,?) }");
             CallableStatement callStmt2 = getConnection().prepareCall("{ call addProductToOrder(?,?,?) }")) {

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
                for (Map.Entry<Product, Integer> entry : mapProducts.entrySet()) {
                    callStmt2.setInt(1, intId);
                    callStmt2.setInt(2, entry.getKey().getId());
                    callStmt2.setInt(3, entry.getValue());
                    callStmt2.execute();
                }
            }

            return intId;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            closeAll();
        }
    }

    /**
     * Removes an Order from the database.
     *
     * @param intId Order's Id.
     * @return true if everything works out, false if it doesn't.
     */
    public boolean removeOrder(int intId) {

        try (CallableStatement callStmt = getConnection().prepareCall("{ call removeOrder(?) }");) {

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

    /**
     * Calls the private method addOrder().
     *
     * @param oOrder Order.
     * @return The id.
     */
    public int registerOrder(Order oOrder) {
        return addOrder(oOrder.getAmount(), oOrder.getTotalWeight(), oOrder.getAdditionalFee(), oOrder.getOrderDate(),
                oOrder.getDescription(), oOrder.getStatus(), oOrder.isHomeDelivery(), oOrder.getClient(), oOrder.getPharmacy().getId(), oOrder.getProducts());
    }

    /**
     * Gets the latest Order For a Specific Client.
     *
     * @param oClient Client.
     * @return Order.
     */
    public Order getLatestOrder(Client oClient) {

        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call getLatestOrder(?) }");
             CallableStatement callStmt2 = getConnection().prepareCall("{ ? = call getProductsByOrder(?) }");) {

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, oClient.getEmail());

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                Order oOrder = orderManager(rSet, 1);

                callStmt2.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt2.setInt(oOrder.getId(), 2);

                callStmt2.execute();

                ResultSet rSetProducts = (ResultSet) callStmt2.getObject(1);

                while (rSet.next()) {
                    oOrder = orderProductManager(rSetProducts, 1, oOrder);
                }
                return oOrder;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Order with the following client:" + oClient.getName());
    }

    /**
     * Gets An Order By Courier
     *
     * @param strEmail Courier Email.
     * @return Order.
     */
    public Order getOrderByCourier(String strEmail) {

        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call getOrderByCourier(?) }");) {

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, strEmail);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                return orderManager(rSet, 1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Order with the following courier email:" + strEmail);
    }

    /**
     * Removes a product from a PharmacyProduct ifd it has zero stock, verifies wich Pharmacy has the products
     * for the order and if the order can be fulfilled. Manages the order status.
     *
     * @param order Order.
     * @return Map with Product and Quantities.
     */
    public Map<Product, Integer> notifyAndRemove(Order order) {
        Map<Product, Integer> lstProducts = new TreeMap<>();
        Map<Product, Integer> updateProducts = new TreeMap<>();
        boolean flag = false;
        for (Map.Entry<Product, Integer> entry : order.getProducts().entrySet()) {
            try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call removeProductsPharmacy(?,?,?) }")) {

                callStmt.registerOutParameter(1, oracle.jdbc.internal.OracleTypes.CURSOR);
                callStmt.setString(2, order.getPharmacy().getEmail());
                callStmt.setInt(3, entry.getKey().getId());
                callStmt.setInt(4, entry.getValue());

                callStmt.execute();

                ResultSet rSet = (ResultSet) callStmt.getObject(1);

                if (rSet == null) {
                    flag = true;
                }
                while (rSet.next()) {
                    Product product = productManager(rSet, 1);
                    Integer intAskQuantity = rSet.getInt(6);
                    if (intAskQuantity.equals(-1)) {
                        updateProducts.put(product, entry.getValue());
                        continue;
                    } else {
                        lstProducts.put(product, intAskQuantity);
                        updateProducts.put(product, entry.getValue() - intAskQuantity);
                    }
                    rSet.next();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeAll();
            }
        }
        if (flag) {
            try (CallableStatement callStmt = getConnection().prepareCall("{ call updateOrderStatus(?) }")) {

                callStmt.setInt(1, order.getId());
                callStmt.execute();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeAll();
            }
            return null;
        } else if (!(updateProducts.isEmpty())) {
            for (Map.Entry<Product, Integer> entry2 : updateProducts.entrySet()) {

                try (CallableStatement callStmt = getConnection().prepareCall("{ call updatePharmacyStock(?,?,?) }")) {

                    callStmt.setString(1, order.getPharmacy().getEmail());
                    callStmt.setInt(2, entry2.getKey().getId());
                    callStmt.setInt(3, entry2.getValue());

                    callStmt.execute();

                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    closeAll();
                }
            }
        }
        return lstProducts;
    }
}
