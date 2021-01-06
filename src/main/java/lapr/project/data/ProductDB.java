package lapr.project.data;

import lapr.project.model.Product;
import oracle.jdbc.internal.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDB extends DataHandler {

    public ProductDB() {
    }

    public ProductDB(String jdbcUrl, String username, String password) {
        super(jdbcUrl, username, password);
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
                double price = rSet.getDouble(4);

                return new Product(productID, name, description, price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Product with id:" + productId);
    }

    public boolean addProduct(Product p) {
        return addProduct(p.getName(), p.getDescription(), p.getPrice());
    }

    private boolean addProduct(String name, String description, double price) {
        boolean flag = true;
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call addProduct(?,?,?) }");

            callStmt.setString(1, name);
            callStmt.setString(2, description);
            callStmt.setDouble(3, price);

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

    public boolean updateProduct(String productId, String name, String description, Double price) {
        /* Objeto "callStmt" para invocar a função "updateProduct" armazenada na BD.
         *
         */
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{call updateProduct(?,?,?,?)}");

            //Especifica o parâmetro de entrada da função "updateEletricalBike".
            callStmt.setString(1, productId);
            callStmt.setString(2, name);
            callStmt.setString(3, description);
            callStmt.setDouble(4, price);
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
            CallableStatement callStmt = getConnection().prepareCall("{call updateProduct(?,?,?,?)}");

            //Especifica o parâmetro de entrada da função "updateEletricalBike".
            callStmt.setString(1, product.getProductId());
            callStmt.setString(2, product.getName());
            callStmt.setString(3, product.getDescription());
            callStmt.setDouble(4, product.getPrice());
            //Executa a invocação da função "updateProduct".
            callStmt.execute();
            closeAll();

        } catch (SQLException e) {
            return false;
        }
        return true;
    }
}
