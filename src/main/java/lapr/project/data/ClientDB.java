package lapr.project.data;

import lapr.project.model.Address;
import lapr.project.model.Client;
import oracle.jdbc.OracleTypes;
import lapr.project.model.CreditCard;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDB extends DataHandler {


    public ClientDB(String jdbcUrl, String username, String password) {
        super(jdbcUrl, username, password);
    }

    public ClientDB() {
        super();
    }


    /**
     * DATABASE
     */

    public boolean addClientToDB(Client c) {
        return addClientToDB(c.getName(), c.getNif(), c.getCredits(), c.getAddress(), c.getLstCreditCard(),
                c.getEmail(), c.getPw());
    }


    private boolean addClientToDB(String name, Integer nif, Integer credits, Address address, List<CreditCard> lstCreditCard,
                                  String email, String password) {

        boolean flag = true;
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ ? = call addClient(?,?,?,?,?,?,?,?,?,?,?,?) }");

            callStmt.setString(2, name);
            callStmt.setInt(3, nif);
            callStmt.setInt(4, credits);
            callStmt.setDouble(5, address.getLatitude());
            callStmt.setDouble(6, address.getLongitude());
            callStmt.setString(7, address.getStreetName());
            callStmt.setString(8, address.getDoorNumber());
            callStmt.setString(9, address.getPostalCode());
            callStmt.setString(10, address.getLocality());
            callStmt.setString(11, address.getCountry());
            callStmt.setString(12, email);
            callStmt.setString(13, password);
            callStmt.registerOutParameter(1, OracleTypes.INTEGER);

            int userId = callStmt.getInt(1);

            if (userId == -1) return false;

            for (CreditCard cc : lstCreditCard) {
                CallableStatement callStmt2 = getConnection().prepareCall("{ call addCreditCardToClient(?,?,?,?) }");

                callStmt2.setInt(1,userId);
                callStmt2.setDouble(2, cc.getCreditCardNr());
                java.util.Date utilStartDate = cc.getValidityDate();
                java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
                callStmt2.setDate(3, sqlStartDate);
                callStmt2.setInt(4, cc.getCCV());
            }

            callStmt.execute();

        } catch (SQLException e) {
            flag = false;
        } finally {
            closeAll();
        }
        return flag;
    }

    public Client getClientByEmail(String strEmail) {

        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ ? = call getClientByEmail(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, strEmail);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                return clientManager(rSet, 1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Client with the following email:" + strEmail);
    }

    public List<CreditCard> getCreditCardsByClient(String strEmail) {
        List<CreditCard> lstCreditCards = new ArrayList<>();
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ ? = call getCreditCardsByClient(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, strEmail);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                lstCreditCards.add(creditCardManager(rSet, 1));
            }
            return lstCreditCards;
        } catch (SQLException e) {
            throw new IllegalArgumentException("No Credit Cards Avaliable.");
        } finally {
            closeAll();
        }
    }
}
