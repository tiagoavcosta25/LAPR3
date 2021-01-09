package lapr.project.model.registration;

import lapr.project.data.DataHandler;
import lapr.project.model.*;
import oracle.jdbc.OracleTypes;

import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

    public Pharmacy getPharmacyByManagerEmail(String email) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getPharmacyByManagerEmail(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, email);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                Integer pharmacyID = rSet.getInt(1);
                String pharmacyName = rSet.getString(2);
                //address
                Address oAddress = addressManager(rSet, 3);
                //User
                Integer id = rSet.getInt(11);
                String emailManager = rSet.getString(12);
                String password = rSet.getString(13);
                Integer nif = rSet.getInt(14);
                String name = rSet.getString(15);

                return new Pharmacy(pharmacyID,pharmacyName,new PharmacyManager(id,emailManager,password,nif,name),
                        oAddress);
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Pharmacy with Manager Email: " + email);
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

    public void removePharmacy(int intId) {

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

    public void registerPharmacyProduct(Pharmacy m_oPharmacy, Product m_oProduct, Integer m_intStock) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call addPharmacyProduct(?,?,?) }");

            callStmt.setInt(1, m_oPharmacy.getId());
            callStmt.setInt(2, m_oProduct.getId());
            callStmt.setInt(3, m_intStock);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Pharmacy> getPharmacies() {
        CallableStatement callStmt = null;
        List<Pharmacy> lstPharmacies = new ArrayList<>();
        try {
            callStmt = getConnection().prepareCall("{ ? = call getPharmacies() }");

            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while(rSet.next()){
                int intPharmacyId = rSet.getInt(1);
                String strPharmacyName = rSet.getString(2);
                int intManagerId = rSet.getInt(3);
                String strEmail = rSet.getString(4);
                String strPassword = rSet.getString(5);
                Integer strNif = rSet.getInt(6);
                String strManagerName = rSet.getString(7);
                Address oAddress = addressManager(rSet, 3);

                lstPharmacies.add(new Pharmacy(intPharmacyId, strPharmacyName, new PharmacyManager(intManagerId, strEmail, strPassword, strNif, strManagerName), oAddress));

                rSet.next();
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Pharmacies Avaliable.");
    }
}
