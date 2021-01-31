package lapr.project.data;

import lapr.project.model.Product;
import oracle.jdbc.internal.OracleTypes;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * ProductDB.
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
public class ProductDB extends DataHandler {

    /**
     * Returns a Product basing on it's name, given by parameter
     *
     * @param strName Product's name
     * @return Product which has the same name as the one given by parameter
     */
    public Product getProductFromBD(String strName) {

        try(CallableStatement callStmt = getConnection().prepareCall("{ ? = call getProduct(?) }");) {

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, strName);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            rSet.next();
            return productManager(rSet, 1);
        } catch (SQLException e) {
            // Do Nothing
        } finally {
            closeAll();
        }
        return null;
    }

    /**
     * Registers a Product into the Database
     *
     * @param p Product to be added to the Database
     * @return  True if the Product was registered, false if otherwise
     */
    public boolean addProductToDB(Product p) {
        return addProductToDB(p.getName(), p.getDescription(), p.getUnitaryPrice(), p.getUnitaryWeight());
    }

    /**
     * Registers a Product into the Database
     *
     * @param strName          Product name
     * @param strDescription   Product description
     * @param dblUnitaryPrice  Product unitary price
     * @param dblUnitaryWeight Product unitary weight
     * @return True if the Product was registered, false if otherwise
     */
    private boolean addProductToDB(String strName, String strDescription, Double dblUnitaryPrice, Double dblUnitaryWeight) {
        boolean flag = true;
        try (CallableStatement callStmt = getConnection().prepareCall("{ call addProduct(?,?,?,?) }");)
        {

            callStmt.setString(1, strName);
            callStmt.setString(2, strDescription);
            callStmt.setDouble(3, dblUnitaryPrice);
            callStmt.setDouble(4, dblUnitaryWeight);

            callStmt.execute();

        } catch (SQLException e) {
            flag = false;
        } finally {
            closeAll();
        }
        return flag;
    }

    /**
     * Removes a Product from the Database
     *
     * @param strName Product's name
     * @return True if the Product was removed, false if otherwise
     */
    public boolean removeProductFromDB(String strName) {
        boolean flag = true;
        try(CallableStatement callStmt = getConnection().prepareCall("{ call removeProduct(?) }");) {

            callStmt.setString(1, strName);

            callStmt.execute();

        } catch (SQLException e) {
            flag = false;
        } finally {
            closeAll();
        }
        return flag;
    }

    /**
     * Updates a Product from the Database
     *
     * @param strProductName   Product original name
     * @param strName          Product new name
     * @param strDescription   Product new description
     * @param dblUnitaryPrice  Product new unitary price
     * @param dblUnitaryWeight Product new unitary weight
     * @return True if Product was updated, false if otherwise
     */
    public boolean updateProductFromDB(String strProductName, String strName, String strDescription, Double dblUnitaryPrice,
                                       Double dblUnitaryWeight) {
        try(CallableStatement callStmt = getConnection().prepareCall("{call updateProduct(?,?,?,?,?)}");) {

            callStmt.setString(1, strProductName);
            callStmt.setString(2, strName);
            callStmt.setString(3, strDescription);
            callStmt.setDouble(4, dblUnitaryPrice);
            callStmt.setDouble(5, dblUnitaryWeight);
            callStmt.execute();

        } catch (SQLException e) {
            return false;
        } finally {
            closeAll();
        }
        return true;
    }

    /**
     * Returns the List of Products in the Database
     *
     * @return List of Products in the Database
     */
    public List<Product> getProducts() {
        List<Product> lstProducts = new ArrayList<>();
        try(CallableStatement callStmt = getConnection().prepareCall("{ ? = call getProducts() }");) {

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                lstProducts.add(productManager(rSet, 1));
            }
            return lstProducts;
        } catch (SQLException e) {
            throw new IllegalArgumentException("No Products Avaliable.");
        } finally {
            closeAll();
        }
    }

    /**
     * Returns the List of Available Products in the Database regarding a certain Pharmacy
     *
     * @param intPharmacyId Pharmacy ID
     * @return List of Products regarding a certain Pharmacy
     */
    public List<Product> getAvailableProducts(int intPharmacyId) {
        List<Product> lstProducts = new ArrayList<>();
        try(CallableStatement callStmt = getConnection().prepareCall("{ ? = call getAvailableProducts(?) }");) {
            callStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            callStmt.setInt(2, intPharmacyId);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                Product oProduct = productManager(rSet, 1);

                lstProducts.add(oProduct);
            }
            return lstProducts;
        } catch (SQLException e) {
            throw new IllegalArgumentException("No Products Avaliable.");
        } finally {
            closeAll();
        }
    }
}
