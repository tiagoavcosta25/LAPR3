package lapr.project.data;

import lapr.project.model.*;
import oracle.jdbc.OracleTypes;

import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PharmacyTransferDB extends DataHandler {

    public PharmacyTransfer getPharmacyTransfer(int id) {

        CallableStatement callStmt = null;
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{ ? = call getPharmacyTransfer(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(id, 2);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                return pharmacyTransferManager(rSet, 1);
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Pharmacy Transfer with ID:" + id);
    }

    private boolean addPharmacyTransfer(Order oOrder, Product oProduct, Integer intQuantity, Pharmacy oPharmacy) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call addPharmacyTransfer(?,?,?,?) }");

            callStmt.setInt(1, oOrder.getId());
            callStmt.setInt(2, oProduct.getId());
            callStmt.setInt(3, intQuantity);
            callStmt.setInt(4, oPharmacy.getId());

            callStmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll();
        }
    }

    public boolean removePharmacyTransfer(int intId) {

        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call removePharmacyTransfer(?) }");

            callStmt.setInt(1, intId);

            callStmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll();
        }

    }

    public boolean registerPharmacyTransfer(PharmacyTransfer oPharmacyTransfer) {
        return addPharmacyTransfer(oPharmacyTransfer.getOrder(), oPharmacyTransfer.getProduct(), oPharmacyTransfer.getQuantity(), oPharmacyTransfer.getNearbyPharmacy());
    }

    public boolean updateStockFromTransfer(int intPharmacyTransferId) {
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call updateStockFromTransfer(?) }");

            callStmt.setInt(1, intPharmacyTransferId);

            callStmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll();
        }
    }
}
