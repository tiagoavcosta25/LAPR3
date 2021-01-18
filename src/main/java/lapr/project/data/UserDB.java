package lapr.project.data;


import lapr.project.model.UserSession;
import lapr.project.data.DataHandler;
import lapr.project.model.service.UserService;
import lapr.project.utils.EncryptPassword;
import oracle.jdbc.internal.OracleTypes;

import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDB extends DataHandler {

    private static final Logger LOGGER = Logger.getLogger(UserDB.class.getName());

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

            CallableStatement callStmt = getConnection().prepareCall("{ call logIn(?,?,?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.


            callStmt.setString(1, email);
            callStmt.setString(2, password);
            callStmt.registerOutParameter(3 , OracleTypes.INTEGER);


            callStmt.execute();

            int role = callStmt.getInt(3);

            if (role != -1) {
                new UserSession(email,role);
            }else flag = false;

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"User NOT Found!");
            flag = false;
        } finally {
            closeAll();
        }
        return flag;
    }

}
