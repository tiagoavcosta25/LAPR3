package lapr.project.model.registration;

import lapr.project.data.DataHandler;
import lapr.project.model.Address;
import lapr.project.model.Client;

import java.sql.CallableStatement;
import java.sql.SQLException;

public class ClientRegistration extends DataHandler {


    public void registerNewUser(String name, String email, String password, float latitude, float longitude, String streetName,
                                Integer doorNumber, String postalCode, String locality, String country, Integer creditCardNr,
                                String validityDate, Integer CCV) {
        Client client = new Client(name,email,password,latitude,longitude,streetName,doorNumber,postalCode,locality,country,
                creditCardNr,validityDate,CCV);
        addClientToDB(client);

    }

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
            callStmt.setString(2, address);
            callStmt.setDouble(3, price);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }
}
