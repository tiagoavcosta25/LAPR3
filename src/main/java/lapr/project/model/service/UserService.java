package lapr.project.model.service;

import lapr.project.controller.ApplicationPOT;
import lapr.project.data.UserDB;
import lapr.project.model.UserSession;
import lapr.project.utils.EncryptPassword;
import lapr.project.utils.WriteFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * User Service.
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
public class UserService {

    /**
     * Logger which is used to generate warnings or information, with
     * a custom message
     */
    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

    /**
     * User Database
     */
    private UserDB moUserDB;

    /**
     * Empty constructor of UserService, which instantiates a new
     * User Database
     */
    public UserService() {
        moUserDB = new UserDB();
    }

    /**
     * Returns the User Database
     *
     * @return  User Database
     */
    public UserDB getUserDB() {
        return moUserDB;
    }

    /**
     * Sets the User Database to the one given by parameter
     *
     * @param oUserDB   new User Database
     */
    public void setUserDB(UserDB oUserDB) {
        this.moUserDB = oUserDB;
    }

    /**
     * Logs a User into the System, basing on the User's email and password
     * given by parameter
     *
     * @param email User email
     * @param password  User password
     * @return  True if the User logged in, false if otherwise
     */
    public boolean login(String email, String password){
        try {
            String encryptedPassword = EncryptPassword.encryptPasswordMD5(password);
            if (moUserDB.login(email,encryptedPassword)) {
                LOGGER.log(Level.INFO,"Logged In!");
                String body = String.format("You are now logged in with the following account:\n\n-Email: %s\n-Password: %s\n-Encrypted" +
                        "Password: %s\n",email,password,encryptedPassword);
                WriteFile.write("LoggedIn_" + email,body);
                return true;
            }else return false;
        }catch (Exception e) {
            LOGGER.log(Level.WARNING,"Encryptation Algorithm failed!");
            return false;
        }
    }

    /**
     * Logs the current User out of the System by creating
     * a new default User Session
     *
     * @return  True if the User logged out, false if otherwise
     */
    public boolean newUserSession() {
        String body = String.format("You are now logged out of your account with the corresponding email: %s!",
                ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail());
        WriteFile.write("LoggedOut_" + ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail(),
                body);
        new UserSession();
        LOGGER.log(Level.INFO,"Logged Out!");
        return true;
    }
}
