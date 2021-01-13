package lapr.project.model.service;

import lapr.project.data.UserDB;
import lapr.project.model.UserSession;
import lapr.project.utils.EncryptPassword;

import java.security.NoSuchAlgorithmException;

public class UserService {

    private UserDB m_oUserDB;

    public UserService() {
        m_oUserDB = new UserDB();
    }

    public boolean login(String email, String password) throws NoSuchAlgorithmException {
        String encryptedPassword = EncryptPassword.encryptPasswordMD5(password);
        return m_oUserDB.login(email,encryptedPassword);
    }

    public boolean newUserSession() {
        new UserSession();
        return true;
    }
}
