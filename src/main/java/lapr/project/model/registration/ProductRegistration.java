package lapr.project.model.registration;

import lapr.project.data.DataHandler;
import lapr.project.model.Product;

import java.sql.CallableStatement;
import java.sql.SQLException;

public class ProductRegistration extends DataHandler {

    public boolean registerProduct(String name, String description, double unitaryPrice,
                                   double unitaryWeight) {
        return addProductToDB(new Product(name, description, unitaryPrice, unitaryWeight));
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

}
