package lapr.project.model.registration;

import lapr.project.data.DataHandler;
import lapr.project.model.Address;
import lapr.project.model.Pharmacy;
import lapr.project.model.PharmacyManager;
import oracle.jdbc.OracleTypes;

import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PharmacyManagerRegistration extends DataHandler {

    public PharmacyManagerRegistration(String jdbcUrl, String username, String password) {
        super(jdbcUrl, username, password);
    }

    public PharmacyManager getPharmacyManager(int id) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getPharmacyManager(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(id, 1);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {

                int intId = rSet.getInt(1);
                String strEmail = rSet.getString(2);
                String strPassword = rSet.getString(3);
                Integer intNIF = rSet.getInt(4);
                String strName = rSet.getString(5);

                return new PharmacyManager(intId, strEmail, strPassword, intNIF, strName);
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Order with ID:" + id);
    }

    private void addPharmacyManager(String strEmail, String strPassword, Integer intNIF, String strName) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call addPharmacy(?,?,?,?) }");

            callStmt.setString(1, strEmail);
            callStmt.setString(2, strPassword);
            callStmt.setInt(3, intNIF);
            callStmt.setString(4, strName);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeOrder(int intId) {

        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call removePharmacyManager(?) }");

            callStmt.setInt(1, intId);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void registerPharmacy(PharmacyManager oPharmacyManager) {
        addPharmacyManager(oPharmacyManager.getStrEmail(), oPharmacyManager.getPw(), oPharmacyManager.getM_nif(), oPharmacyManager.getM_name());
    }

    public PharmacyManager newPharmacyManager(String strEmail, String strPassword, Integer intNIF, String strName) throws NoSuchAlgorithmException {
        return new PharmacyManager(strEmail, strPassword, intNIF, strName);
    }


}
