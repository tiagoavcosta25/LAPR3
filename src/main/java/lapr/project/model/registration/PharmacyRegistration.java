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
            callStmt.setInt(id, 2);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {

                Pharmacy oPharmacy = pharmacyManager(rSet, 1);

                callStmt = getConnection().prepareCall("{ ? = call getStockByPharmacy(?) }");
                callStmt.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt.setInt(oPharmacy.getId(), 2);

                callStmt.execute();

                ResultSet rSetProducts = (ResultSet) callStmt.getObject(1);

                while (rSet.next()){
                    oPharmacy = pharamcyProductManager(rSetProducts, 1, oPharmacy);
                }
                return oPharmacy;
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Pharmacy with ID:" + id);
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

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while(rSet.next()){
                Pharmacy oPharmacy = pharmacyManager(rSet, 1);

                callStmt = getConnection().prepareCall("{ ? = call getStockByPharmacy(?) }");
                callStmt.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt.setInt(oPharmacy.getId(), 1);

                callStmt.execute();

                ResultSet rSetProducts = (ResultSet) callStmt.getObject(1);

                while (rSet.next()){
                    oPharmacy = pharamcyProductManager(rSetProducts, 1, oPharmacy);
                }

                lstPharmacies.add(oPharmacy);
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Pharmacies Avaliable.");
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
                Pharmacy oPharmacy = pharmacyManager(rSet, 1);

                callStmt = getConnection().prepareCall("{ ? = call getStockByPharmacy(?) }");
                callStmt.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt.setInt(oPharmacy.getId(), 2);

                callStmt.execute();

                ResultSet rSetProducts = (ResultSet) callStmt.getObject(1);

                while (rSet.next()){
                    oPharmacy = pharamcyProductManager(rSetProducts, 1, oPharmacy);
                }
                return oPharmacy;
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Pharmacy with Manager Email: " + email);
    }
}
