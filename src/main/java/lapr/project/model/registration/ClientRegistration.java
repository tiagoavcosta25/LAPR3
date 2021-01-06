package lapr.project.model.registration;

import lapr.project.data.DataHandler;
import lapr.project.model.Address;
import lapr.project.model.Client;

import java.sql.CallableStatement;
import java.sql.SQLException;

public class ClientRegistration extends DataHandler {


    /**
     * Registers a new Client in the Database
     *
     * @param name          Client's name
     * @param email         Client's email
     * @param password      Client's password
     * @param latitude      Client's latitude
     * @param longitude     Client's longitude
     * @param streetName    Client's street name
     * @param doorNumber    Client's door number
     * @param postalCode    Client's postal code
     * @param locality      Client's locality
     * @param country       Client's country
     * @param creditCardNr  Client's credit card number
     * @param validityDate  Client's credit card's validity date
     * @param CCV           Client's credit card's CCV
     * @return              True if Client was registered, false if otherwise
     */
    public boolean registerNewClient(String name, String email, String password, float latitude, float longitude, String streetName,
                                     Integer doorNumber, String postalCode, String locality, String country, Integer creditCardNr,
                                     String validityDate, Integer CCV) {
        Client client = new Client(name, email, password, latitude, longitude, streetName, doorNumber, postalCode, locality, country,
                creditCardNr, validityDate, CCV);
        addClientToDB(client);

        return true;

    }



    /**
     * DATABASE
     */


    public boolean addClientToDB(Client c) {
        return addClientToDB(c.getM_name(), c.getM_address(), c.getM_creditCardNr(), c.getM_validityDate(), c.getM_CCV());
    }

    private boolean addClientToDB(String name, Address address, Integer m_creditCardNr,
                                  String m_validityDate, Integer m_CCV) {

        boolean flag = true;
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call addProduct(?,?,?) }");

            callStmt.setString(1, name);
            callStmt.setString(2, m_validityDate);
            callStmt.setDouble(3, m_creditCardNr);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }
}
