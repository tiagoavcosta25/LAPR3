package lapr.project.model.registration;

import javafx.util.Pair;
import lapr.project.data.DataHandler;
import lapr.project.model.Address;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeliveryRegistration extends DataHandler {
    public DeliveryRegistration(String jdbcUrl, String username, String password) {
        super(jdbcUrl, username, password);
    }

    public double getShortestPath(Pair<Address, Address> oPairAddress) {
        return 0;
    }

    public Pair<Address, Address> getStartingAndDeliveryAddressByOrder(int oOrderId) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getAddressesByDeliveryRunId(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, oOrderId);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                Address a1 = addressManager(rSet, 1);
                Address a2 = addressManager(rSet, 9);
                return new Pair<Address, Address>(a1, a2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Order with the following id:" + oOrderId);


    }


    public float getDeliveryEnergy() {
        /* ||| IMPLEMENT METHOD ||| */
        return 0;
    }
}
