package lapr.project.model.service;

import lapr.project.controller.LoginController;
import lapr.project.data.UserDB;
import lapr.project.model.UserSession;
import lapr.project.utils.EncryptPassword;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserService {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class.getName());
    private UserDB m_oUserDB;

    public UserService() {
        m_oUserDB = new UserDB();
    }

    public boolean login(String email, String password){
        try {
            String encryptedPassword = EncryptPassword.encryptPasswordMD5(password);
            return m_oUserDB.login(email,encryptedPassword);
        }catch (NoSuchAlgorithmException e) {
            LOGGER.log(Level.WARNING,"No algorithm found for encryptation!");
        }
        return false;
    }

    public boolean newUserSession() {
        new UserSession();
        return true;
    }
}
