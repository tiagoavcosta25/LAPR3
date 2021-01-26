package lapr.project.data;

import lapr.project.model.Product;
import oracle.jdbc.internal.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDB extends DataHandler {

    public Product getProductFromBD(String strName) {

        try(CallableStatement callStmt = getConnection().prepareCall("{ ? = call getProduct(?) }");) {

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, strName);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            rSet.next();
            return productManager(rSet, 1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return null;
    }

    public boolean addProductToDB(Product p) {
        return addProductToDB(p.getName(), p.getDescription(), p.getUnitaryPrice(), p.getUnitaryWeight());
    }

    private boolean addProductToDB(String strName, String strDescription, Double fltUnitaryPrice, Double fltUnitaryWeight) {
        boolean flag = true;
        try (CallableStatement callStmt = getConnection().prepareCall("{ call addProduct(?,?,?,?) }");)
        {

            callStmt.setString(1, strName);
            callStmt.setString(2, strDescription);
            callStmt.setDouble(3, fltUnitaryPrice);
            callStmt.setDouble(4, fltUnitaryWeight);

            callStmt.execute();

        } catch (SQLException e) {
            flag = false;
        } finally {
            closeAll();
        }
        return flag;
    }

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

    public boolean updateProductFromDB(String strProductName, String strName, String strDescription, Double fltUnitaryPrice, Double fltUnitaryWeight) {
        try(CallableStatement callStmt = getConnection().prepareCall("{call updateProduct(?,?,?,?,?)}");) {

            callStmt.setString(1, strProductName);
            callStmt.setString(2, strName);
            callStmt.setString(3, strDescription);
            callStmt.setDouble(4, fltUnitaryPrice);
            callStmt.setDouble(5, fltUnitaryWeight);
            callStmt.execute();

        } catch (SQLException e) {
            return false;
        } finally {
            closeAll();
        }
        return true;
    }

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
