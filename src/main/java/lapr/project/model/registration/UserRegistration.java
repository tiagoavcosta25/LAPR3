package lapr.project.model.registration;


import lapr.project.controller.UserSession;
import lapr.project.data.DataHandler;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.SQLException;

public class UserRegistration extends DataHandler {

    public UserRegistration(String jdbcUrl, String username, String password) {
        super(jdbcUrl, username, password);
    }



    /**
     * Checks if the User that has the 'email' given by parameter
     * is registered in the DataBase, and if so, logs him in
     *
     * @param email     User's email
     * @param password  User's password
     * @return          True if User exists in the DB, false if otherwise
     */
    public boolean login(String email, String password) {
        boolean flag = true;
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call logIn(?,?) }");

            callStmt.setString(1, email);
            callStmt.setString(2, password);
            callStmt.registerOutParameter(3, OracleTypes.INTEGER);
            callStmt.execute();

            Integer role = (Integer) callStmt.getObject(3);
            new UserSession(email,role);

            closeAll();
        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }
    
}
