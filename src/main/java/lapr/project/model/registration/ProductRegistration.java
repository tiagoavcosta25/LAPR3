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

    public boolean registerProduct(String name, String description, double unitaryPrice,
                                   double unitaryWeight) {
        return addProductToDB(new Product(name, description, unitaryPrice, unitaryWeight));
    }

    public Product getProduct(String productId) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getProduct(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            // Especifica o parâmetro de entrada da função "getProduct".
            callStmt.setString(2, productId);

            // Executa a invocação da função "getProduct".
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                String productID = rSet.getString(1);
                String name = rSet.getString(2);
                String description = rSet.getString(3);
                double unitaryPrice = rSet.getDouble(4);
                double unitaryWeight = rSet.getDouble(5);

                return new Product(productID, name, description, unitaryPrice, unitaryWeight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Product with id:" + productId);
    }

    public boolean addProductToDB(Product p) {
        return addProductToDB(p.getName(), p.getDescription(), p.getUnitaryPrice(), p.getUnitaryWeight());
    }

    private boolean addProductToDB(String name, String description, double unitaryPrice, double unitaryWeight) {
        boolean flag = true;
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call addProduct(?,?,?,?) }");

            callStmt.setString(1, name);
            callStmt.setString(2, description);
            callStmt.setDouble(3, unitaryPrice);
            callStmt.setDouble(4, unitaryWeight);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

    public boolean removeProduct(String productId) {
        boolean flag = true;
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call removeProduct(?) }");

            callStmt.setString(1, productId);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

    public boolean updateProduct(String productId, String name, String description, Double unitaryPrice, Double unitaryWeight) {
        /* Objeto "callStmt" para invocar a função "updateProduct" armazenada na BD.
         *
         */
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{call updateProduct(?,?,?,?, ?)}");

            //Especifica o parâmetro de entrada da função "updateProduct".
            callStmt.setString(1, productId);
            callStmt.setString(2, name);
            callStmt.setString(3, description);
            callStmt.setDouble(4, unitaryPrice);
            callStmt.setDouble(5, unitaryWeight);
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
            callStmt.setString(1, product.getProductId());
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
                    String productID = rSet.getString(1);
                    String name = rSet.getString(2);
                    String description = rSet.getString(3);
                    double unitaryPrice = rSet.getDouble(4);
                    double unitaryWeight = rSet.getDouble(5);

                    lstProducts.add(new Product(productID, name, description, unitaryPrice, unitaryWeight));

                    rSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Products Avaliable.");
    }

}
