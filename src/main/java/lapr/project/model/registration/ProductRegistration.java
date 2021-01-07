package lapr.project.model.registration;

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

    public boolean registerProduct(String strName, String strDescription, float fltUnitaryPrice,
                                   float fltUnitaryWeight) {
        return addProductToDB(new Product(strName, strDescription, fltUnitaryPrice, fltUnitaryPrice));
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
        throw new IllegalArgumentException("No Product with id:" + intId);
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

    public boolean removeProduct(int intId) {
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

    public boolean updateProduct(int intId, String strName, String strDescription, float fltUnitaryPrice, float fltUnitaryWeight) {
        /* Objeto "callStmt" para invocar a função "updateProduct" armazenada na BD.
         *
         */
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{call updateProduct(?,?,?,?, ?)}");

            //Especifica o parâmetro de entrada da função "updateProduct".
            callStmt.setInt(1, intId);
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

    public boolean updateProduct(Product product) {
        /* Objeto "callStmt" para invocar a função "updateProduct" armazenada na BD.
         *
         */
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{call updateProduct(?,?,?,?,?)}");

            //Especifica o parâmetro de entrada da função "updateProduct".
            callStmt.setInt(1, product.getId());
            callStmt.setString(2, product.getName());
            callStmt.setString(3, product.getDescription());
            callStmt.setDouble(4, product.getUnitaryPrice());
            callStmt.setDouble(5, product.getUnitaryWeight());
            //Executa a invocação da função "updateProduct".
            callStmt.execute();
            closeAll();

        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public List<Product> getAvailableProducts() {
        CallableStatement callStmt = null;
        List<Product> lstProducts = new ArrayList<>();
        try {
            callStmt = getConnection().prepareCall("{ ? = call getAvailableProducts() }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while(rSet.next()){
                    int intId = rSet.getInt(1);
                    String strName = rSet.getString(2);
                    String strDescription = rSet.getString(3);
                    float fltUnitaryPrice = rSet.getFloat(4);
                    float fltUnitaryWeight = rSet.getFloat(5);

                    lstProducts.add(new Product(intId, strName, strDescription, fltUnitaryPrice, fltUnitaryWeight));

                    rSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Products Avaliable.");
    }

}
