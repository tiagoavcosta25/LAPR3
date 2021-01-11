package lapr.project.data.registration;

import lapr.project.data.DataHandler;
import lapr.project.model.*;
import oracle.jdbc.OracleTypes;

import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InvoiceRegistration extends DataHandler {

    public InvoiceRegistration(String jdbcUrl, String username, String password) {
        super(jdbcUrl, username, password);
    }

    public Invoice getInvoice(int id) {

        CallableStatement callStmt = null;
        try {
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
        }
        throw new IllegalArgumentException("No Invoice with ID:" + id);
    }

    private boolean addInvoice(Date dtInvoiceDate, float fltTotalPrice, Order oOrder) {
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
            }

            closeAll();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void removeInvoice(int intId) {

        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call removeInvoice(?) }");

            callStmt.setInt(1, intId);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean registerInvoice(Invoice oInvoice) {
        return addInvoice(oInvoice.getInvoiceDate(), oInvoice.getTotalPrice(), oInvoice.getOrder());
    }

    public Invoice newInvoice(Date dtInvoiceDate, float fltTotalPrice, Order oOrder) {
        return new Invoice(dtInvoiceDate, fltTotalPrice, oOrder);
    }
}
