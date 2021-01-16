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

    public InvoiceDB(String jdbcUrl, String username, String password) {
        super(jdbcUrl, username, password);
    }

    public InvoiceDB() {
        super();
    }

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
            closeAll();
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Invoice with ID:" + id);
    }

    private boolean addInvoice(Date dtInvoiceDate, float fltTotalPrice, Order oOrder, Map<CreditCard, Float> mapPayments) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call addInvoice(?,?,?) }");

            callStmt.setDate(1, dtInvoiceDate);
            callStmt.setFloat(2, fltTotalPrice);
            callStmt.setFloat(3, oOrder.getId());

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            Integer intInvoiceId = rSet.getInt(1);

            int c = 1;

            if (intInvoiceId != null) {
                for (Product oProduct : oOrder.getProducts().keySet()) {
                    callStmt = getConnection().prepareCall("{ call addInvoiceLine(?,?,?,?,?,?) }");

                    callStmt.setInt(1, c);
                    callStmt.setInt(2, intInvoiceId);
                    callStmt.setInt(3, oOrder.getId());
                    callStmt.setInt(4, oProduct.getId());
                    callStmt.setString(5, oProduct.getName());
                    callStmt.setString(6, oProduct.getDescription());
                    callStmt.setFloat(7, oProduct.getUnitaryPrice());
                    callStmt.setFloat(8, oProduct.getUnitaryWeight());
                    callStmt.setFloat(9, oOrder.getProducts().get(oProduct) * oProduct.getUnitaryPrice());
                    callStmt.execute();

                    c++;
                }

                for (CreditCard oCreditCard : mapPayments.keySet()) {
                    callStmt = getConnection().prepareCall("{ call addPayment(?,?,?) }");

                    callStmt.setInt(1, intInvoiceId);
                    callStmt.setLong(2, oCreditCard.getCreditCardNr());
                    callStmt.setFloat(3, mapPayments.get(oCreditCard));
                    callStmt.execute();
                }
            }

            closeAll();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeInvoice(int intId) {

        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call removeInvoice(?) }");

            callStmt.setInt(1, intId);

            callStmt.execute();

            closeAll();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean registerInvoice(Invoice oInvoice) {
        return addInvoice(oInvoice.getInvoiceDate(), oInvoice.getTotalPrice(), oInvoice.getOrder(), oInvoice.getPayments());
    }


}
