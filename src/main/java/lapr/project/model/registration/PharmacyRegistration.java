package lapr.project.model.registration;

import lapr.project.data.DataHandler;
import lapr.project.model.*;
import oracle.jdbc.OracleTypes;

import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class PharmacyRegistration extends DataHandler {

    public PharmacyRegistration(String jdbcUrl, String username, String password) {
        super(jdbcUrl, username, password);
    }

    public Pharmacy getPharmacy(int id) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getPharmacy(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(id, 1);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {

                // IMPLEMENTAR

                return new Pharmacy();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Order with ID:" + id);
    }

    private void addPharmacy(String strName, Integer intManagerId, Address oAddress) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call addPharmacy(?,?,?,?,?,?,?,?,?) }");

            callStmt.setString(1, strName);
            callStmt.setFloat(2, intManagerId);
            callStmt.setDouble(3, oAddress.getM_latitude());
            callStmt.setDouble(4, oAddress.getM_longitude());
            callStmt.setString(5, oAddress.getM_streetName());
            callStmt.setString(6, oAddress.getM_doorNumber());
            callStmt.setString(7, oAddress.getM_postalCode());
            callStmt.setString(8, oAddress.getM_locality());
            callStmt.setString(9, oAddress.getM_country());

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeOrder(int intId) {

        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call removePharmacy(?) }");

            callStmt.setInt(1, intId);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void registerPharmacy(Pharmacy oPharmacy) {
        addPharmacy(oPharmacy.getName(), oPharmacy.getPharmacyManager().getM_id(), oPharmacy.getAddress());
    }

    public Pharmacy newPharmacy(String strName, PharmacyManager oPharmacyManager,Double dblLatitude,Double dblLongitude,
                             String strStreetName, String strDoorNumber, String strPostalCode, String strLocality, String strCountry) {
        return new Pharmacy(strName, oPharmacyManager, new Address(dblLatitude, dblLongitude, strStreetName, strDoorNumber, strPostalCode,
                strLocality, strCountry));
    }


}
