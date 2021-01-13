package lapr.project.data;

import lapr.project.model.Address;
import lapr.project.model.Courier;
import lapr.project.model.DeliveryRun;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryRunDB extends DataHandler {

    public DeliveryRunDB(String jdbcUrl, String username, String password) {
        super(jdbcUrl, username, password);
    }

    public DeliveryRunDB() {
        super();
    }

    public List<Address> getAddressesFromOrdersList(List<Integer> lstOrderId) {
        try {
            openConnection();
            List<Address> oResult = new ArrayList<>();

            for (Integer id : lstOrderId) {
                CallableStatement callStmt = getConnection().prepareCall("{ ? = call getAddressesFromOrdersList(?) }");

                callStmt.setInt(2, id);
                callStmt.registerOutParameter(1, OracleTypes.CURSOR);

                callStmt.execute();

                ResultSet rSetAddresses = (ResultSet) callStmt.getObject(1);


                if (rSetAddresses.next()) {
                    oResult.add(addressManager(rSetAddresses, 1));
                }

            }

            closeAll();

            return oResult;
        } catch (SQLException e) {
            return null;
        }
    }



    public boolean addNewDeliveryRun(DeliveryRun oDeliveryRun) {
        return true;
    }


}
