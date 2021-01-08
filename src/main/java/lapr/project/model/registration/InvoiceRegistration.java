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
                Float fltClientLatitude = rSet.getFloat(12);
                Float fltClientLongitude = rSet.getFloat(13);
                String strClientStreetName = rSet.getString(14);
                String strClientDoorNumber = rSet.getString(15);
                String strClientPostalCode = rSet.getString(16);
                String strClientLocality = rSet.getString(17);
                String strClientCountry = rSet.getString(18);
                Double dblCreditCardNr = rSet.getDouble(19);
                java.util.Date dtValidatyDate = rSet.getDate(20);
                Integer strCCV = rSet.getInt(21);
                Float fltOrderLatitude = rSet.getFloat(22);
                Float fltOrderLongitude = rSet.getFloat(23);
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
                                strClientPostalCode, strClientLocality, strClientCountry, dblCreditCardNr, dtValidatyDate, strCCV),
                        new Address(fltOrderLatitude, fltOrderLongitude, strOrderStreetName, strOrderDoorNumber, strOrderPostalCode,
                                strOrderLocality, strOrderCountry), new TreeMap<>()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Order with ID:" + id);
    }

    private void addInvoice(int intId, Date dtInvoiceDate, float fltTotalPrice, Order oOrder) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call addInvoice(?,?,?,?) }");

            callStmt.setFloat(1, intId);
            callStmt.setDate(2, dtInvoiceDate);
            callStmt.setFloat(3, fltTotalPrice);
            callStmt.setInt(4, oOrder.getId());

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

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
        addInvoice(oInvoice.getId(), oInvoice.getInvoiceDate(), oInvoice.getTotalPrice(), oInvoice.getOrder());
    }

    public Invoice newInvoice(int intId, Date dtInvoiceDate, float fltTotalPrice, Order oOrder) {
        return new Invoice(intId, dtInvoiceDate, fltTotalPrice, oOrder);
    }
}
