package lapr.project.model.registration;

import lapr.project.data.DataHandler;
import lapr.project.model.Courier;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRegistration extends DataHandler {

    public Courier getCourier(int id) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getCourier(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(id, 1);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int courierID = rSet.getInt(1);
                String clientName = rSet.getString(2);
                String nif = rSet.getString(3);
                String iban = rSet.getString(4);

                return new Courier(courierID, clientName, nif, iban);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Order with ID:" + id);
    }

    private void addOrderToDB(String strName, String strNif, String strIban) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call addOrder(?,?,?) }");

            callStmt.setString(1, strName);
            callStmt.setString(2, strNif);
            callStmt.setString(3, strIban);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeOrder(int intId) {

        try {
            openConnection();
            /*
             *  Objeto "callStmt" para invocar o procedimento "removeSailor"
             *  armazenado na BD.
             *
             *  PROCEDURE removeSailor(sid NUMBER)
             *  PACKAGE pkgSailors AS TYPE ref_cursor IS REF CURSOR; END pkgSailors;
             */
            CallableStatement callStmt = getConnection().prepareCall("{ call removeCourier(?) }");

            callStmt.setInt(1, intId);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Courier newCourier(String strName, String strEmail, String strNIF, String strIBAN) {
        String password = "";
        return new Courier(strName,strEmail,password,strNIF,strIBAN);
    }

    public void registersCourier(Courier oCourier) {
        addOrderToDB(oCourier.getM_name(), oCourier.getM_nif(), oCourier.getM_iban());
    }
}
