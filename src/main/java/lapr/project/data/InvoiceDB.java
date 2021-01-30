package lapr.project.data;

import lapr.project.model.*;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Invoice DB.
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
public class InvoiceDB extends DataHandler {

    /**
     * Method that gets an invoice from the database.
     * @param id Invoice's ID.
     * @return Invoice.
     */
    public Invoice getInvoice(int id) {

        try(CallableStatement callStmt = getConnection().prepareCall("{ ? = call getInvoice(?) }");) {

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(id, 1);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {

                return invoiceManager(rSet, 1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Invoice with ID:" + id);
    }

    /**
     * Adds an invoice to the databse.
     * @param dtInvoiceDate Invoice's Date.
     * @param fltTotalPrice Invoice's Total Price.
     * @param oOrder Invoice's Order.
     * @param mapPayments Invoice's Payment map.
     * @return true if everything works out, false if it doesn't.
     */
    private int addInvoice(Date dtInvoiceDate, Double fltTotalPrice, Order oOrder, Map<CreditCard, Double> mapPayments) {
        try(CallableStatement callStmt = getConnection().prepareCall("{ ? = call addInvoice(?,?,?) }");
            CallableStatement callStmt2 = getConnection().prepareCall("{ call addInvoiceLine(?,?,?,?,?) }");
            CallableStatement callStmt3 = getConnection().prepareCall("{ call addPayment(?,?,?) }");) {

            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setDate(2, dtInvoiceDate);
            callStmt.setDouble(3, fltTotalPrice);
            callStmt.setDouble(4, oOrder.getId());

            callStmt.execute();

            Integer intInvoiceId = (Integer) callStmt.getObject(1);

            int c = 1;

            if (intInvoiceId != null) {
                for (Product oProduct : oOrder.getProducts().keySet()) {
                    callStmt2.setInt(1, c);
                    callStmt2.setInt(2, intInvoiceId);
                    callStmt2.setInt(3, oOrder.getId());
                    callStmt2.setInt(4, oProduct.getId());
                    callStmt2.setDouble(5, oOrder.getProducts().get(oProduct) * oProduct.getUnitaryPrice());
                    callStmt2.execute();

                    c++;
                }

                for (Map.Entry<CreditCard, Double> entry : mapPayments.entrySet()) {
                    callStmt3.setInt(1, intInvoiceId);
                    callStmt3.setLong(2, entry.getKey().getCreditCardNr());
                    callStmt3.setDouble(3, entry.getValue());
                    callStmt3.execute();
                }
            }
            return intInvoiceId;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            closeAll();
        }
    }

    /**
     * Removes an invoice from the database.
     * @param intId Invoice's Id.
     * @return true if everything works out, false if it doesn't.
     */
    public boolean removeInvoice(int intId) {

        try(CallableStatement callStmt = getConnection().prepareCall("{ call removeInvoice(?) }");) {
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

    /**
     * Calls the Private Method to add the invoice to the database.
     * @param oInvoice Invoice.
     * @return true if everything works out, false if it doesn't.
     */
    public int registerInvoice(Invoice oInvoice) {
        return addInvoice(oInvoice.getInvoiceDate(), oInvoice.getTotalPrice(), oInvoice.getOrder(), oInvoice.getPayments());
    }


}
