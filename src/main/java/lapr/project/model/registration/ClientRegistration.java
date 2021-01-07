package lapr.project.model.registration;

import lapr.project.data.DataHandler;
import lapr.project.model.Address;
import lapr.project.model.Client;
import oracle.jdbc.OracleTypes;
import lapr.project.model.CreditCard;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ClientRegistration extends DataHandler {


    public ClientRegistration(String jdbcUrl, String username, String password) {
        super(jdbcUrl, username, password);
    }

    /**
     * Registers a new Client in the Database
     *
     * @param name         Client's name
     * @param email        Client's email
     * @param password     Client's password
     * @param latitude     Client's latitude
     * @param longitude    Client's longitude
     * @param streetName   Client's street name
     * @param doorNumber   Client's door number
     * @param postalCode   Client's postal code
     * @param locality     Client's locality
     * @param country      Client's country
     * @param creditCardNr Client's credit card number
     * @param validityDate Client's credit card's validity date
     * @param CCV          Client's credit card's CCV
     * @return True if Client was registered, false if otherwise
     */
    public boolean registerNewClient(String name, Integer nif, String email, String password, float latitude, float longitude, String streetName,
                                     String doorNumber, String postalCode, String locality, String country, Double creditCardNr,
                                     Date validityDate, Integer CCV) {
        Client client = new Client(name, nif, email, password, latitude, longitude, streetName, doorNumber, postalCode, locality, country,
                creditCardNr, validityDate, CCV);
        return addClientToDB(client);

    }


    /**
     * DATABASE
     */


    public boolean addClientToDB(Client c) {
        return addClientToDB(c.getM_name(), c.getM_nif(), c.getM_credits(), c.getM_address(), c.getM_creditCard(),
                c.getStrEmail(), c.getPw());
    }


    private boolean addClientToDB(String name, Integer nif, Integer credits, Address address, CreditCard creditCard,
                                  String email, String password) {

        boolean flag = true;
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call addClient(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");

            callStmt.setString(1, name);
            callStmt.setInt(2, nif);
            callStmt.setInt(3, credits);
            callStmt.setFloat(4, address.getM_latitude());
            callStmt.setFloat(5, address.getM_longitude());
            callStmt.setString(6, address.getM_streetName());
            callStmt.setString(7, address.getM_doorNumber());
            callStmt.setString(8, address.getM_postalCode());
            callStmt.setString(9, address.getM_locality());
            callStmt.setString(10, address.getM_country());
            callStmt.setDouble(11, creditCard.getM_creditCardNr());
            callStmt.setDate(12, (java.sql.Date) creditCard.getM_validityDate());
            callStmt.setInt(13, creditCard.getM_CCV());
            callStmt.setString(14, email);
            callStmt.setString(15, password);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

    public Client getClientByEmail(String strEmail) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getClientByEmail(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(1, strEmail);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {

                int intId = rSet.getInt(1);
                // String strEmail = rSet.getString(2);
                String strPassword = rSet.getString(3);
                String strName = rSet.getString(4);
                Integer strNif = rSet.getInt(5);
                Integer intCredits = rSet.getInt(6);
                Float fltLatitude = rSet.getFloat(7);
                Float fltLongitude = rSet.getFloat(8);
                String strStreetName = rSet.getString(9);
                String strDoorNumber = rSet.getString(10);
                String strPostalCode = rSet.getString(10);
                String strLocality = rSet.getString(12);
                String strCountry = rSet.getString(13);
                Double dblCreditCardNr = rSet.getDouble(14);
                Date dtValidatyDate = rSet.getDate(15);
                Integer strCCV = rSet.getInt(16);

                return new Client(intId, strName, strNif, strEmail, strPassword, intCredits, fltLatitude, fltLongitude, strStreetName, strDoorNumber, strPostalCode,
                        strLocality, strCountry, dblCreditCardNr, dtValidatyDate, strCCV);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Client with the following email:" + strEmail);
    }
}
