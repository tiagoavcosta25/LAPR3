package lapr.project.model.registration;

import lapr.project.data.DataHandler;
import lapr.project.model.Address;
import lapr.project.model.Client;
import lapr.project.model.Order;
import oracle.jdbc.OracleTypes;

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
    public boolean registerNewClient(String name, String email, String password, float latitude, float longitude, String streetName,
                                     String doorNumber, String postalCode, String locality, String country, Integer creditCardNr,
                                     String validityDate, Integer CCV) {
        Client client = new Client(name, email, password, latitude, longitude, streetName, doorNumber, postalCode, locality, country,
                creditCardNr, validityDate, CCV);
        return addClientToDB(client);

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

            CallableStatement callStmt = getConnection().prepareCall("{ call addProduct(?,?,?,?,?,?,?,?,?,?,?) }");

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

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
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
                Add fltAdditionalFee = rSet.getFloat(4);
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
