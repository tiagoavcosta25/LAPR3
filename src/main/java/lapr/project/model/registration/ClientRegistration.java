package lapr.project.model.registration;

import lapr.project.data.DataHandler;
import lapr.project.model.Address;
import lapr.project.model.Client;
import lapr.project.model.CreditCard;

import java.sql.CallableStatement;
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

            CallableStatement callStmt = getConnection().prepareCall("{ call addClient(?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");

            callStmt.setString(1, name);
            callStmt.setFloat(2, address.getM_latitude());
            callStmt.setFloat(3, address.getM_longitude());
            callStmt.setString(4, address.getM_streetName());
            callStmt.setString(5, address.getM_doorNumber());
            callStmt.setString(6, address.getM_postalCode());
            callStmt.setString(7, address.getM_locality());
            callStmt.setString(8, address.getM_country());
            callStmt.setInt(9, m_creditCardNr);
            callStmt.setString(10, m_validityDate);
            callStmt.setFloat(11, m_CCV);
            callStmt.setInt(12, m_creditCardNr);
            callStmt.setInt(13, m_creditCardNr);
            callStmt.setInt(14, m_creditCardNr);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }
}
