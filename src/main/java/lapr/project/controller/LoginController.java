package lapr.project.controller;

import lapr.project.model.Platform;
import lapr.project.data.registration.UserRegistration;

public class LoginController {

    private UserRegistration m_userReg;

    public LoginController() {
        ApplicationPOT app = ApplicationPOT.getInstance();
        Platform plat = app.getPlatform();
        m_userReg = plat.getUserReg();
    }

    /**
     * Logs a User in, by checking if there's any User in the DataBase that has
     * the 'email' and 'password' given by parameter
     *
     * @param email         User's email
     * @param password      User's password
     * @return              True if the login operation was successful, false if otherwise
     */
    public boolean login(String email, String password) {
        return m_userReg.login(email,password);
    }
}
