package lapr.project.model.registration;

import lapr.project.data.DataHandler;
import lapr.project.model.Address;
import lapr.project.model.Client;
import oracle.jdbc.OracleTypes;
import lapr.project.model.CreditCard;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                                     String doorNumber, String postalCode, String locality, String country, Integer creditCardNr,
                                     String validityDate, Integer CCV) {
        Client client = new Client(name, nif,email, password, latitude, longitude, streetName, doorNumber, postalCode, locality, country,
                creditCardNr, validityDate, CCV);
        return addClientToDB(client);

    }


    /**
     * DATABASE
     */


    public boolean addClientToDB(Client c) {
        return addClientToDB(c.getM_name(), c.getM_nif(), c.getM_credits(), c.getM_address(), c.getM_creditCard());
    }

    private boolean addClientToDB(String name, Integer nif, Integer credits, Address address, CreditCard creditCard) {

        boolean flag = true;
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call addClient(?,?,?,?,?,?,?,?,?,?,?,?,?) }");

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
            callStmt.setFloat(11, creditCard.getM_creditCardNr());
            callStmt.setString(12, creditCard.getM_validityDate());
            callStmt.setInt(13, creditCard.getM_CCV());

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }
    }

    private Client getClientByEmail(String strEmail) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getOrder(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(1, strEmail);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {

                int intId = rSet.getInt(1);
                String strNif = rSet.getString(2);
                Integer intCredits = rSet.getFloat(3);
                Float fltAdditionalFee = rSet.getFloat(4);
                Date dtOrderDate = rSet.getDate(5);
                String strDescription = rSet.getString(6);
                String strStatus = rSet.getString(7);

                // FALTA: getAddressById e getClientById

                return new Client(intId, fltAmount, fltTotalWeight, fltAdditionalFee, dtOrderDate, strDescription, strStatus, new Client(), new Address());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Order with ID:" + id);
    }
}
