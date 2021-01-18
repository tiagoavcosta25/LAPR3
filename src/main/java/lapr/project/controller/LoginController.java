package lapr.project.controller;

import lapr.project.model.service.UserService;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController {

    private UserService m_oUserService;


    public LoginController() {
        m_oUserService = new UserService();
    }

    /**
     * Logs a User in, by checking if there's any User in the DataBase that has
     * the 'email' and 'password' given by parameter
     *
     * @param email    User's email
     * @param password User's password
     * @return True if the login operation was successful, false if otherwise
     */
    public boolean login(String email, String password) {
        return m_oUserService.login(email, password);
    }
}
