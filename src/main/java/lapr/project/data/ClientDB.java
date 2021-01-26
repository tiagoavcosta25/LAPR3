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

/**
 * ClientDB.
 * <p>
 * Group: Team Lisa [G-021]
 * ______________________________________________________
 *
 * @author António Barbosa <1190404@isep.ipp.pt>
 * @author Ernesto Rodrigues <1190560@isep.ipp.pt>
 * @author Jessica Alves <1190682@isep.ipp.pt>
 * @author Pedro Santos <1190967@isep.ipp.pt>
 * @author Rodrigo Costa <1191014@isep.ipp.pt>
 * @author Tiago Costa <1191460@isep.ipp.pt>
 */
public class ClientDB extends DataHandler {

    /**
     * Adds a Client to the DB
     *
     * @param c Client
     * @return True if Client was added, false if otherwise
     */
    public boolean addClientToDB(Client c) {
        return addClientToDB(c.getName(), c.getNif(), c.getCredits(), c.getAddress(), c.getLstCreditCard(),
                c.getEmail(), c.getPw());
    }

    /**
     * Adds a Client to the DB
     *
     * @param name          Client name
     * @param nif           Client nif
     * @param credits       Client credits
     * @param address       Client address
     * @param lstCreditCard Client's List of Credit Cards
     * @param email         Client email
     * @param password      Client password
     * @return True if Client was added, false if otherwise
     */
    private boolean addClientToDB(String name, Integer nif, Integer credits, Address address, List<CreditCard> lstCreditCard,
                                  String email, String password) {

        boolean flag = true;
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call addClient(?,?,?,?,?,?,?,?,?,?,?,?,?) }");
             CallableStatement callStmt2 = getConnection().prepareCall("{ call addCreditCardToClient(?,?,?,?) }");) {
            callStmt.setString(2, name);
            callStmt.setInt(3, nif);
            callStmt.setInt(4, credits);
            callStmt.setDouble(5, address.getLatitude());
            callStmt.setDouble(6, address.getLongitude());
            callStmt.setDouble(7, address.getAltitude());
            callStmt.setString(8, address.getStreetName());
            callStmt.setString(9, address.getDoorNumber());
            callStmt.setString(10, address.getPostalCode());
            callStmt.setString(11, address.getLocality());
            callStmt.setString(12, address.getCountry());
            callStmt.setString(13, email);
            callStmt.setString(14, password);
            callStmt.registerOutParameter(1, OracleTypes.INTEGER);

            callStmt.execute();

            int userId = callStmt.getInt(1);

            if (userId == -1) return false;

            for (CreditCard cc : lstCreditCard) {
                callStmt2.setInt(1, userId);
                callStmt2.setDouble(2, cc.getCreditCardNr());
                java.util.Date utilStartDate = cc.getValidityDate();
                java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
                callStmt2.setDate(3, sqlStartDate);
                callStmt2.setInt(4, cc.getCCV());

                callStmt2.execute();
            }

        } catch (SQLException e) {
            flag = false;
        } finally {
            closeAll();
        }
        return flag;
    }

    /**
     * Returns the Client regarding the email given by parameter
     *
     * @param strEmail Client email
     * @return Client regarding the email given by parameter
     */
    public Client getClientByEmail(String strEmail) {
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call getClientByEmail(?) }");) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, strEmail);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            if (rSet.next()) {
                Client oClient = clientManager(rSet, 1);
                oClient.setLstCreditCard(getCreditCardsByClient(oClient.getEmail()));
                return oClient;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Client with the following email:" + strEmail);
    }

    /**
     * Returns the List of Credit Cards regarding a Client by it's email,
     * given by parameter
     *
     * @param strEmail Client email
     * @return List of Credit Cards regarding a Client
     */
    public List<CreditCard> getCreditCardsByClient(String strEmail) {
        List<CreditCard> lstCreditCards = new ArrayList<>();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call getCreditCardsByClient(?) }");) {
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

    /**
     * Updates Client Credits to the ones given by parameter, regarding the Client that
     * corresponds to the email also given by parameter
     *
     * @param strEmail   Client email
     * @param intCredits Client credits
     * @return True if the Client was updated, false if otherwise
     */
    public boolean updateClientCredits(String strEmail, Integer intCredits) {
        try (CallableStatement callStmt = getConnection().prepareCall("{ call updateClientCredits(?,?) }");) {
            callStmt.setString(1, strEmail);
            callStmt.setInt(2, intCredits);

            callStmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Client with the following email:" + strEmail);
    }
}
