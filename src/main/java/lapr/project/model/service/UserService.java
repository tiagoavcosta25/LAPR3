package lapr.project.model.service;

import lapr.project.data.UserDB;
import lapr.project.model.UserSession;
import lapr.project.utils.EncryptPassword;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserService {

    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());
    private UserDB m_oUserDB;

    public UserService() {
        m_oUserDB = new UserDB();
    }

    public boolean login(String email, String password){
        try {
            String encryptedPassword = EncryptPassword.encryptPasswordMD5(password);
            if (m_oUserDB.login(email,encryptedPassword)) {
                LOGGER.log(Level.INFO,"Logged In!");
                return true;
            }else return false;
        }catch (NoSuchAlgorithmException e) {
            LOGGER.log(Level.WARNING,"Encryptation Algorithm failed!");
            return false;
        }
    }

    public boolean newUserSession() {
        new UserSession();
        return true;
    }
}
