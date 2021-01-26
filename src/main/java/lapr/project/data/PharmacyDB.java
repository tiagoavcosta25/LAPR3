package lapr.project.data;

import lapr.project.model.*;
import oracle.jdbc.OracleTypes;

import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PharmacyDB extends DataHandler {

    private static final String GETSTOCKBYPHARMACY = "{ ? = call getStockByPharmacy(?) }";

    public Pharmacy getPharmacy(String strEmail) {

        try(CallableStatement callStmt = getConnection().prepareCall("{ ? = call getPharmacy(?) }");
            CallableStatement callStmt2 = getConnection().prepareCall(GETSTOCKBYPHARMACY);) {
            openConnection();

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2,strEmail);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {

                Pharmacy oPharmacy = pharmacyManager(rSet, 1);

                callStmt2.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt2.setInt(2,oPharmacy.getId());

                callStmt2.execute();

                ResultSet rSetProducts = (ResultSet) callStmt2.getObject(1);

                while (rSet.next()){
                    oPharmacy = pharmacyProductManager(rSetProducts, 1, oPharmacy);
                }
                return oPharmacy;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Pharmacy with Email:" + strEmail);
    }

    private boolean addPharmacy(String strName, String strEmail, Address oAddress) {
        try(CallableStatement callStmt = getConnection().prepareCall("{ call addPharmacy(?,?,?,?,?,?,?,?,?,?) }");) {
            callStmt.setString(1, strName);
            callStmt.setString(2, strEmail);
            callStmt.setDouble(3, oAddress.getLatitude());
            callStmt.setDouble(4, oAddress.getLongitude());
            callStmt.setDouble(5, oAddress.getAltitude());
            callStmt.setString(6, oAddress.getStreetName());
            callStmt.setString(7, oAddress.getDoorNumber());
            callStmt.setString(8, oAddress.getPostalCode());
            callStmt.setString(9, oAddress.getLocality());
            callStmt.setString(10, oAddress.getCountry());
            callStmt.execute();

            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            closeAll();
        }
    }

    public boolean removePharmacy(String strEmail) {

        try(CallableStatement callStmt = getConnection().prepareCall("{ call removePharmacy(?) }");) {

            callStmt.setString(1, strEmail);

            callStmt.execute();

            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            closeAll();
        }

    }

    public boolean registerPharmacy(Pharmacy oPharmacy) {
        return addPharmacy(oPharmacy.getName(), oPharmacy.getEmail(), oPharmacy.getAddress());
    }

    public boolean registerPharmacyProduct(Pharmacy moPharmacy, Product moProduct, Integer mintStock) {
        try(CallableStatement callStmt = getConnection().prepareCall("{ call addPharmacyProduct(?,?,?) }");) {

            callStmt.setString(1, moPharmacy.getEmail());
            callStmt.setInt(2, moProduct.getId());
            callStmt.setInt(3, mintStock);

            callStmt.execute();

            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            closeAll();
        }
    }

    public List<Pharmacy> getPharmacies() {
        List<Pharmacy> lstPharmacies = new ArrayList<>();
        try(CallableStatement callStmt = getConnection().prepareCall("{ ? = call getPharmacies() }");
            CallableStatement callStmt2 = getConnection().prepareCall(GETSTOCKBYPHARMACY);) {

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while(rSet.next()){
                Pharmacy oPharmacy = pharmacyManager(rSet, 1);

                callStmt2.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt2.setInt(2, oPharmacy.getId());

                callStmt2.execute();

                ResultSet rSetProducts = (ResultSet) callStmt2.getObject(1);

                while (rSetProducts.next()){
                    oPharmacy = pharmacyProductManager(rSetProducts, 1, oPharmacy);
                }

                lstPharmacies.add(oPharmacy);
            }
            return lstPharmacies;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Pharmacies Avaliable.");
    }

    public List<Order> getOrdersByPharmacyEmail(Pharmacy oPharmacy) {
        try(CallableStatement callStmt = getConnection().prepareCall("{ ? = call getOrdersByPharmacyEmail(?) }");
            CallableStatement callStmt2 = getConnection().prepareCall("{ ? = call getProductsByOrder(?) }");) {

            callStmt.setString(2, oPharmacy.getEmail());
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            List<Order> lstOrders = new ArrayList<>();
            while (rSet.next()) {
                Order oOrder = orderManager(rSet, 1);

                callStmt2.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt2.setInt(2, oPharmacy.getId());

                callStmt2.execute();

                ResultSet rSetProducts = (ResultSet) callStmt2.getObject(1);

                while (rSetProducts.next()) {
                    oOrder = orderProductManager(rSetProducts, 1, oOrder);
                }
                lstOrders.add(oOrder);
            }
            return lstOrders;

        } catch (SQLException e) {
            return Collections.emptyList();
        } finally {
            closeAll();
        }
    }


    public List<Pharmacy> getPharmaciesWithStock(Order oOrder, Product oProduct, Integer intQuantity) {
        List<Pharmacy> lstPharmacies = new ArrayList<>();
        try(CallableStatement callStmt = getConnection().prepareCall("{ ? = call getPharmaciesWithStock(?,?,?) }");
            CallableStatement callStmt2 = getConnection().prepareCall(GETSTOCKBYPHARMACY);) {

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, oOrder.getId());
            callStmt.setInt(3, oProduct.getId());
            callStmt.setInt(4, intQuantity);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {

                Pharmacy oPharmacy = pharmacyManager(rSet, 1);

                callStmt2.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt2.setInt(2, oPharmacy.getId());

                callStmt2.execute();

                ResultSet rSetProducts = (ResultSet) callStmt2.getObject(1);

                while (rSetProducts.next()){
                    oPharmacy = pharmacyProductManager(rSetProducts, 1, oPharmacy);
                }

                lstPharmacies.add(oPharmacy);
            }
            return lstPharmacies;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Pharmacy with enough stock.");
    }

    public Pharmacy getPharmacyByManagerEmail(String email) {

        try(CallableStatement callStmt = getConnection().prepareCall("{ ? = call getPharmacyByManagerEmail(?) }");
            CallableStatement callStmt2 = getConnection().prepareCall(GETSTOCKBYPHARMACY);) {

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, email);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                Pharmacy oPharmacy = pharmacyManager(rSet, 1);

                callStmt2.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt2.setInt(oPharmacy.getId(), 2);

                callStmt2.execute();

                ResultSet rSetProducts = (ResultSet) callStmt2.getObject(1);

                while (rSet.next()){
                    oPharmacy = pharmacyProductManager(rSetProducts, 1, oPharmacy);
                }
                return oPharmacy;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Pharmacy with Manager Email: " + email);
    }


    public Courier getSuitableCourier() {
        try(CallableStatement callStmt = getConnection().prepareCall("{ ? = call getSuitableCourier() }");) {

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
                Pharmacy pharmacy = getPharmacy(rSet.getString(7));
                oResult = new Courier(id,name,email,pw,nif,iban,pharmacy);
            }
            return oResult;
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeAll();
        }
    }
}
