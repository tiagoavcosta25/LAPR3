package lapr.project.model.registration;

import lapr.project.data.DataHandler;
import lapr.project.model.*;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class InvoiceRegistration extends DataHandler {

    public Invoice getInvoice(int id) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getInvoice(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(id, 1);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {

                int intOrderId = rSet.getInt(1);
                float fltAmount = rSet.getFloat(2);
                float fltTotalWeight = rSet.getFloat(3);
                float fltAdditionalFee = rSet.getFloat(4);
                Date dtOrderDate = rSet.getDate(5);
                String strDescription = rSet.getString(6);
                String strStatus = rSet.getString(7);
                String strName = rSet.getString(8);
                Integer intNIF = rSet.getInt(9);
                String strEmail = rSet.getString(10);
                String strPassword = rSet.getString(11);
                Double fltClientLatitude = rSet.getDouble(12);
                Double fltClientLongitude = rSet.getDouble(13);
                String strClientStreetName = rSet.getString(14);
                String strClientDoorNumber = rSet.getString(15);
                String strClientPostalCode = rSet.getString(16);
                String strClientLocality = rSet.getString(17);
                String strClientCountry = rSet.getString(18);
                long lCreditCardNr = rSet.getLong(19);
                java.util.Date dtValidatyDate = rSet.getDate(20);
                Integer strCCV = rSet.getInt(21);
                Double fltOrderLatitude = rSet.getDouble(22);
                Double fltOrderLongitude = rSet.getDouble(23);
                String strOrderStreetName = rSet.getString(24);
                String strOrderDoorNumber = rSet.getString(25);
                String strOrderPostalCode = rSet.getString(26);
                String strOrderLocality = rSet.getString(27);
                String strOrderCountry = rSet.getString(28);
                int intInvoiceId = rSet.getInt(29);
                Date dtInvoiceDate = rSet.getDate(30);
                float fltTotalPrice = rSet.getFloat(31);

                // FALTA: a lista de prods

                return new Invoice(intInvoiceId, dtInvoiceDate, fltTotalPrice, new Order(intOrderId, fltAmount, fltTotalWeight, fltAdditionalFee, dtOrderDate, strDescription, strStatus,
                        new Client(strName, intNIF, strEmail, strPassword, fltClientLatitude, fltClientLongitude, strClientStreetName, strClientDoorNumber,
                                strClientPostalCode, strClientLocality, strClientCountry, lCreditCardNr, dtValidatyDate, strCCV),
                        new Address(fltOrderLatitude, fltOrderLongitude, strOrderStreetName, strOrderDoorNumber, strOrderPostalCode,
                                strOrderLocality, strOrderCountry), new TreeMap<>()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Order with ID:" + id);
    }

    private void addInvoice(Date dtInvoiceDate, float fltTotalPrice, Order oOrder) {
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
        } catch (SQLException e) {
            e.printStackTrace();
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

    public void registerInvoice(Invoice oInvoice) {
        addInvoice(oInvoice.getInvoiceDate(), oInvoice.getTotalPrice(), oInvoice.getOrder());
    }

    public Invoice newInvoice(Date dtInvoiceDate, float fltTotalPrice, Order oOrder) {
        return new Invoice(dtInvoiceDate, fltTotalPrice, oOrder);
    }
}
