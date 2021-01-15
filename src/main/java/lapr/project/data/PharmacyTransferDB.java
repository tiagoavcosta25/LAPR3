package lapr.project.data;

import lapr.project.model.*;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PharmacyTransferDB extends DataHandler {

    public PharmacyTransfer getPharmacyTransfer(int id) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getPharmacy(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(id, 2);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {

                // IMPLEMENTAR
                return new PharmacyTransfer();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Pharmacy with ID:" + id);
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

            closeAll();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removePharmacyTransfer(int intId) {

        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call removePharmacyTransfer(?) }");

            callStmt.setInt(1, intId);

            callStmt.execute();

            closeAll();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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

            closeAll();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
