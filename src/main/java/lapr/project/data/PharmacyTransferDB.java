package lapr.project.data;

import lapr.project.model.*;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Pharmacy Transfer DB.
 *
 * Group: Team Lisa [G-021]
 * ______________________________________________________
 * @author António Barbosa <1190404@isep.ipp.pt>
 * @author Ernesto Rodrigues <1190560@isep.ipp.pt>
 * @author Jessica Alves <1190682@isep.ipp.pt>
 * @author Pedro Santos <1190967@isep.ipp.pt>
 * @author Rodrigo Costa <1191014@isep.ipp.pt>
 * @author Tiago Costa <1191460@isep.ipp.pt>
 */
public class PharmacyTransferDB extends DataHandler {

    /**
     * GEts a Pharmacy Transfer By ID.
     * @param id ID.
     * @return Pharmacy Transfer.
     */
    public PharmacyTransfer getPharmacyTransfer(int id) {

        try(CallableStatement callStmt = getConnection().prepareCall("{ ? = call getPharmacyTransfer(?) }");) {

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, id);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                return pharmacyTransferManager(rSet, 1);
            }
        } catch (SQLException e) {
            // Do Nothing
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Pharmacy Transfer with ID:" + id);
    }

    /**
     * Adds a Pharmacy Transfer to the Database.
     * @param oOrder Order.
     * @param oProduct Product.
     * @param intQuantity Quantity.
     * @param oPharmacy Pharmacy.
     * @return true if everything works out, false if it doesn't.
     */
    private boolean addPharmacyTransfer(Order oOrder, Product oProduct, Integer intQuantity, Pharmacy oPharmacy) {
        try(CallableStatement callStmt = getConnection().prepareCall("{ call addPharmacyTransfer(?,?,?,?,?) }");) {

            callStmt.setInt(1, oOrder.getId());
            callStmt.setDate(2, oOrder.getOrderDate());
            callStmt.setInt(3, oProduct.getId());
            callStmt.setInt(4, intQuantity);
            callStmt.setInt(5, oPharmacy.getId());

            callStmt.execute();

            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            closeAll();
        }
    }

    /**
     * Remove a Pharmacy Transfer By Its ID.
     * @param intId ID.
     * @return true if everything works out, false if it doesn't.
     */
    public boolean removePharmacyTransfer(int intId) {

        try(CallableStatement callStmt = getConnection().prepareCall("{ call removePharmacyTransfer(?) }");) {

            callStmt.setInt(1, intId);

            callStmt.execute();

            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            closeAll();
        }

    }

    /**
     * Calls addPharmacyTransfer.
     * @param oPharmacyTransfer Pharmacy Transfer.
     * @return true if everything works out, false if it doesn't.
     */
    public boolean registerPharmacyTransfer(PharmacyTransfer oPharmacyTransfer) {
        return addPharmacyTransfer(oPharmacyTransfer.getOrder(), oPharmacyTransfer.getProduct(), oPharmacyTransfer.getQuantity(), oPharmacyTransfer.getNearbyPharmacy());
    }

    /**
     * Updates The Stock From a Transfer.
     * @param intPharmacyTransferId Transfer Id.
     * @return true if everything works out, false if it doesn't.
     */
    public boolean updateStockFromTransfer(int intPharmacyTransferId) {
        try(CallableStatement callStmt = getConnection().prepareCall("{ call updateStockFromTransfer(?) }");) {

            callStmt.setInt(1, intPharmacyTransferId);

            callStmt.execute();

            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            closeAll();
        }
    }
}
