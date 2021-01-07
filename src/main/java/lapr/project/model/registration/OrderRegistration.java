package lapr.project.model.registration;

import lapr.project.data.DataHandler;
import lapr.project.model.*;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class OrderRegistration extends DataHandler {

    public Order getOrder(int id) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getOrder(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(id, 1);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {

                int intId = rSet.getInt(1);
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
                Integer strCreditCardNr = rSet.getInt(19);
                java.util.Date dtValidatyDate = rSet.getDate(20);
                Integer strCCV = rSet.getInt(21);
                Float fltOrderLatitude = rSet.getFloat(22);
                Float fltOrderLongitude = rSet.getFloat(23);
                String strOrderStreetName = rSet.getString(24);
                String strOrderDoorNumber = rSet.getString(25);
                String strOrderPostalCode = rSet.getString(26);
                String strOrderLocality = rSet.getString(27);
                String strOrderCountry = rSet.getString(28);

                // FALTA: a lista de prods

                return new Order(intId, fltAmount, fltTotalWeight, fltAdditionalFee, dtOrderDate, strDescription, strStatus,
                        new Client(strName, intNIF, strEmail, strPassword, fltClientLatitude, fltClientLongitude, strClientStreetName, strClientDoorNumber,
                                strClientPostalCode, strClientLocality, strClientCountry, strCreditCardNr, dtValidatyDate, strCCV),
                        new Address(fltOrderLatitude, fltOrderLongitude, strOrderStreetName, strOrderDoorNumber, strOrderPostalCode,
                                strOrderLocality, strOrderCountry), new TreeMap<>());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Order with ID:" + id);
    }

    private void addOrder(float fltAmount, float fltTotalWeight, float fltAdditionalFee, Date dtOrderDate,
                              String strDescription, String strStatus, Client oClient, Address oAddress, Map<Product, Integer> mapProducts) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call addOrder(?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");

            callStmt.setFloat(1, fltAmount);
            callStmt.setFloat(2, fltTotalWeight);
            callStmt.setFloat(3, fltAdditionalFee);
            callStmt.setString(4, strDescription);
            callStmt.setDate(5, dtOrderDate);
            callStmt.setInt(6, oClient.getM_id());
            callStmt.setFloat(7, oAddress.getM_latitude());
            callStmt.setFloat(8, oAddress.getM_longitude());
            callStmt.setString(9, oAddress.getM_streetName());
            callStmt.setString(10, oAddress.getM_doorNumber());
            callStmt.setString(11, oAddress.getM_postalCode());
            callStmt.setString(12, oAddress.getM_locality());
            callStmt.setString(13, oAddress.getM_country());

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            Integer intId = rSet.getInt(1);

            if(intId != null){
                for (Product oProduct : mapProducts.keySet()){
                    callStmt = getConnection().prepareCall("{ call addProductToOrder(?,?,?,?,?,?) }");

                    callStmt.setInt(1, intId);
                    callStmt.setInt(2, oProduct.getId());
                    callStmt.setString(3, oProduct.getName());
                    callStmt.setString(4, oProduct.getDescription());
                    callStmt.setFloat(5, oProduct.getUnitaryPrice());
                    callStmt.setFloat(6, oProduct.getUnitaryWeight());
                    callStmt.setInt(6, mapProducts.get(oProduct));
                    callStmt.execute();
                }
            }

            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeOrder(int intId) {

        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call removeOrder(?) }");

            callStmt.setInt(1, intId);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void registerOrder(Order oOrder) {
        addOrder(oOrder.getAmount(), oOrder.getTotalWeight(), oOrder.getAdditionalFee(), oOrder.getOrderDate(),
                oOrder.getDescription(), oOrder.getStatus(), oOrder.getClient(), oOrder.getAddress(), oOrder.getProducts());
    }

    public Order newOrder(float fltAmount, float fltTotalWeight, float fltAdditionalFee, Date dtOrderDate,
                         String strDescription, String strStatus, Client oClient, float latitude, float longitude, String streetName,
                         String doorNumber, String postalCode, String locality, String country, Map<Product, Integer> mapProducts) {
        return new Order(fltAmount, fltTotalWeight, fltAdditionalFee, dtOrderDate,
                strDescription, strStatus, oClient, new Address(latitude, longitude, streetName, doorNumber, postalCode, locality, country), mapProducts);
    }
    public Order getOrderByCourier(String strEmail){

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getOrderByCourier(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(1, strEmail);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {

                int intId = rSet.getInt(1);
                // String strEmail = rSet.getString(2);
                float fltAmount = rSet.getFloat(3);
                float fltTotalWeight = rSet.getFloat(4);
                float fltAdditionalFee = rSet.getFloat(5);
                Date dtOrderDate = rSet.getDate(6);
                String strDescription = rSet.getString(7);
                String strStatus = rSet.getString(8);
                Client oClient = new Client();//rSet.getString(9);
                Address oAddress= new Address();// rSet.getString(10);
                return new Order(intId, fltAmount, fltTotalWeight, fltAdditionalFee, dtOrderDate, strDescription,
                        strStatus, oClient,oAddress, new TreeMap<>());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Client with the following email:" + strEmail);
    }
}
