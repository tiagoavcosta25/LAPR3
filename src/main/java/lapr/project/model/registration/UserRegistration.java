package lapr.project.model.registration;


import lapr.project.controller.UserSession;
import lapr.project.data.DataHandler;
import lapr.project.utils.EncryptPassword;
import oracle.jdbc.internal.OracleTypes;

import java.security.NoSuchAlgorithmException;
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

            CallableStatement callStmt = getConnection().prepareCall("{ ? = call logIn(?,?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.


            callStmt.setString(2, email);
            callStmt.setString(3, EncryptPassword.encryptPasswordMD5(password));
            callStmt.registerOutParameter(1 , OracleTypes.INTEGER);


            callStmt.execute();

            Integer role = callStmt.getInt(1);

            if (role != -1) {
                new UserSession(email,role);
            }else flag = false;

            closeAll();
        } catch (SQLException | NoSuchAlgorithmException e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }
    
}
