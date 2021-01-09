package lapr.project.model.registration;

import javafx.util.Pair;
import lapr.project.data.DataHandler;
import lapr.project.model.Address;
import lapr.project.model.Path;
import lapr.project.model.Product;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TempRegistration extends DataHandler {
    public TempRegistration(String jdbcUrl, String username, String password) {
        super(jdbcUrl, username, password);
    }

    public boolean addPathToDB(Path p) {
        return addPathToDB(p.getIdAddressA(), p.getIdAddressB());
    }

    private boolean addPathToDB(int intIdAddressA, int intIdAddressB) {
        boolean flag = true;
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call addPath(?,?) }");

            callStmt.setInt(1, intIdAddressA);
            callStmt.setInt(2, intIdAddressB);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

    /*public List<Pair<List<Address>, String>> getAllPaths() {
        CallableStatement callStmt = null;
        List<Pair<List<Address>, String>> result = new ArrayList<>();
        try {
            callStmt = getConnection().prepareCall("{ ? = call getAllPaths() }");

            callStmt.registerOutParameter(1, oracle.jdbc.internal.OracleTypes.CURSOR);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                int intIdAddress1 = rSet.getInt(1);
                int intIdAddress2 = rSet.getInt(2);


                lstProducts.add(new Product(intId, strName, strDescription, fltUnitaryPrice, fltUnitaryWeight));

                rSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Products Avaliable.");
    }*/

}
