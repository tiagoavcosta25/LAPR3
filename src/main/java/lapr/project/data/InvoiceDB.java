package lapr.project.data;

import lapr.project.model.*;
import oracle.jdbc.OracleTypes;

import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class InvoiceDB extends DataHandler {

    public Invoice getInvoice(int id) {

        CallableStatement callStmt = null;
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{ ? = call getInvoice(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(id, 1);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {

                return invoiceManager(rSet, 1);
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Invoice with ID:" + id);
    }

    private boolean addInvoice(Date dtInvoiceDate, Double fltTotalPrice, Order oOrder, Map<CreditCard, Double> mapPayments) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ ? = call addInvoice(?,?,?) }");


            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setDate(2, dtInvoiceDate);
            callStmt.setDouble(3, fltTotalPrice);
            callStmt.setDouble(4, oOrder.getId());

            callStmt.execute();

            Integer intInvoiceId = (Integer) callStmt.getObject(1);

            int c = 1;

            if (intInvoiceId != null) {
                for (Product oProduct : oOrder.getProducts().keySet()) {
                    callStmt = getConnection().prepareCall("{ call addInvoiceLine(?,?,?,?,?) }");

                    callStmt.setInt(1, c);
                    callStmt.setInt(2, intInvoiceId);
                    callStmt.setInt(3, oOrder.getId());
                    callStmt.setInt(4, oProduct.getId());
                    callStmt.setDouble(5, oOrder.getProducts().get(oProduct) * oProduct.getUnitaryPrice());
                    callStmt.execute();

                    c++;
                }

                for (CreditCard oCreditCard : mapPayments.keySet()) {
                    callStmt = getConnection().prepareCall("{ call addPayment(?,?,?) }");

                    callStmt.setInt(1, intInvoiceId);
                    callStmt.setLong(2, oCreditCard.getCreditCardNr());
                    callStmt.setDouble(3, mapPayments.get(oCreditCard));
                    callStmt.execute();
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll();
        }
    }

    public boolean removeInvoice(int intId) {

        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call removeInvoice(?) }");

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

    public boolean registerInvoice(Invoice oInvoice) {
        return addInvoice(oInvoice.getInvoiceDate(), oInvoice.getTotalPrice(), oInvoice.getOrder(), oInvoice.getPayments());
    }


}
