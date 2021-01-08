package lapr.project.model.registration;

import lapr.project.data.DataHandler;
import lapr.project.model.Address;
import lapr.project.model.Pharmacy;
import lapr.project.model.PharmacyManager;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PharmacyManagerRegistration extends DataHandler {

    public Pharmacy getPharmacyManager(int id) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getPharmacyManager(?) }");

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

            CallableStatement callStmt = getConnection().prepareCall("{ call removeOrder(?) }");

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
