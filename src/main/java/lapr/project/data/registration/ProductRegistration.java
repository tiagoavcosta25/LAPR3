package lapr.project.data.registration;

import lapr.project.data.DataHandler;
import lapr.project.model.Product;
import oracle.jdbc.internal.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRegistration extends DataHandler {

    public ProductRegistration(String jdbcUrl, String username, String password) {
        super(jdbcUrl, username, password);
    }

    public Product getProductFromBD(int intId) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getProduct(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            // Especifica o parâmetro de entrada da função "getProduct".
            callStmt.setInt(2, intId);

            // Executa a invocação da função "getProduct".
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int intID = rSet.getInt(1);
                String strName = rSet.getString(2);
                String strDescription = rSet.getString(3);
                float fltUnitaryPrice = rSet.getFloat(4);
                float fltUnitaryWeight = rSet.getFloat(5);

                return new Product(intID, strName, strDescription, fltUnitaryPrice, fltUnitaryWeight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(("No Product with id:" + intId));
        return new Product(-1, "", "", 0, 0);
    }

    public boolean addProductToDB(Product p) {
        return addProductToDB(p.getName(), p.getDescription(), p.getUnitaryPrice(), p.getUnitaryWeight());
    }

    private boolean addProductToDB(String strName, String strDescription, float fltUnitaryPrice, float fltUnitaryWeight) {
        boolean flag = true;
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call addProduct(?,?,?,?) }");

            callStmt.setString(1, strName);
            callStmt.setString(2, strDescription);
            callStmt.setFloat(3, fltUnitaryPrice);
            callStmt.setFloat(4, fltUnitaryWeight);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

    public boolean removeProductFromDB(int intId) {
        boolean flag = true;
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call removeProduct(?) }");

            callStmt.setInt(1, intId);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

    public boolean updateProductFromDB(int intId, String strName, String strDescription, float fltUnitaryPrice, float fltUnitaryWeight) {
        /* Objeto "callStmt" para invocar a função "updateProduct" armazenada na BD.
         *
         */
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{call updateProduct(?,?,?,?,?)}");

            //Especifica o parâmetro de entrada da função "updateProduct".

            callStmt.setString(2, strName);
            callStmt.setString(3, strDescription);
            callStmt.setFloat(4, fltUnitaryPrice);
            callStmt.setFloat(5, fltUnitaryWeight);
            //Executa a invocação da função "updateProduct".
            callStmt.execute();
            closeAll();

        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public List<Product> getProducts() {
        CallableStatement callStmt = null;
        List<Product> lstProducts = new ArrayList<>();
        try {
            callStmt = getConnection().prepareCall("{ ? = call getProducts() }");

            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while(rSet.next()){
                int intId = rSet.getInt(1);
                String strName = rSet.getString(2);
                String strDescription = rSet.getString(3);
                float fltUnitaryPrice = rSet.getFloat(4);
                float fltUnitaryWeight = rSet.getFloat(5);

                lstProducts.add(new Product(intId, strName, strDescription, fltUnitaryPrice, fltUnitaryWeight));
            }
            return lstProducts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Products Avaliable.");
    }

    public List<Product> getAvailableProducts(int intPharmacyId) {
        CallableStatement callStmt = null;
        List<Product> lstProducts = new ArrayList<>();
        try {
            callStmt = getConnection().prepareCall("{ ? = call getAvailableProducts(?) }");

            callStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            callStmt.setInt(2, intPharmacyId);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while(rSet.next()){
                Product oProduct = productManager(rSet, 1);

                lstProducts.add(oProduct);
            }
            return lstProducts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Products Avaliable.");
    }
}
