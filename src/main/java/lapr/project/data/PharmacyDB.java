package lapr.project.data;

import lapr.project.data.DataHandler;
import lapr.project.model.*;
import oracle.jdbc.OracleTypes;

import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PharmacyDB extends DataHandler {

    public PharmacyDB(String jdbcUrl, String username, String password) {
        super(jdbcUrl, username, password);
    }

    public PharmacyDB() {
        super();
    }

    public Pharmacy getPharmacy(int id) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getPharmacy(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2,id);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {

                Pharmacy oPharmacy = pharmacyManager(rSet, 1);

                callStmt = getConnection().prepareCall("{ ? = call getStockByPharmacy(?) }");
                callStmt.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt.setInt(2,oPharmacy.getId());

                callStmt.execute();

                ResultSet rSetProducts = (ResultSet) callStmt.getObject(1);

                while (rSet.next()){
                    oPharmacy = pharmacyProductManager(rSetProducts, 1, oPharmacy);
                }
                return oPharmacy;
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Pharmacy with ID:" + id);
    }

    private boolean addPharmacy(String strName, String strEmail, Address oAddress) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call addPharmacy(?,?,?,?,?,?,?,?,?) }");

            callStmt.setString(1, strName);
            callStmt.setString(2, strEmail);
            callStmt.setDouble(3, oAddress.getLatitude());
            callStmt.setDouble(4, oAddress.getLongitude());
            callStmt.setString(5, oAddress.getStreetName());
            callStmt.setString(6, oAddress.getDoorNumber());
            callStmt.setString(7, oAddress.getPostalCode());
            callStmt.setString(8, oAddress.getLocality());
            callStmt.setString(9, oAddress.getCountry());

            callStmt.execute();

            closeAll();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removePharmacy(int intId) {

        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call removePharmacy(?) }");

            callStmt.setInt(1, intId);

            callStmt.execute();

            closeAll();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean registerPharmacy(Pharmacy oPharmacy) {
        return addPharmacy(oPharmacy.getName(), oPharmacy.getEmail(), oPharmacy.getAddress());
    }

    public boolean registerPharmacyProduct(Pharmacy m_oPharmacy, Product m_oProduct, Integer m_intStock) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call addPharmacyProduct(?,?,?) }");

            callStmt.setInt(1, m_oPharmacy.getId());
            callStmt.setInt(2, m_oProduct.getId());
            callStmt.setInt(3, m_intStock);

            callStmt.execute();

            closeAll();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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
                    oPharmacy = pharmacyProductManager(rSetProducts, 1, oPharmacy);
                }

                lstPharmacies.add(oPharmacy);
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Pharmacies Avaliable.");
    }



    public Pharmacy getClosestPharmacyWithStock(Order oOrder, Product oProduct, Integer intQuantity) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getClosestPharmacyWithStock(?, ?, ?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(oOrder.getId(), 2);
            callStmt.setInt(oProduct.getId(), 3);
            callStmt.setInt(intQuantity, 4);

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
                    oPharmacy = pharmacyProductManager(rSetProducts, 1, oPharmacy);
                }
                return oPharmacy;
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Pharmacy with enough stock.");
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
                    oPharmacy = pharmacyProductManager(rSetProducts, 1, oPharmacy);
                }
                return oPharmacy;
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Pharmacy with Manager Email: " + email);
    }


    public Courier getSuitableCourier() {
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ ? = call getSuitableCourier() }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            Courier oResult = null;
            if (rSet.next()) {
                Integer id = rSet.getInt(1);
                String name = rSet.getString(2);
                String email = rSet.getString(3);
                String pw = rSet.getString(4);
                Integer nif = rSet.getInt(5);
                String iban = rSet.getString(6);
                Pharmacy pharmacy = getPharmacy(rSet.getInt(7));
                oResult = new Courier(id,name,email,pw,nif,iban,pharmacy);
            }

            closeAll();

            return oResult;
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
