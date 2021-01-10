package lapr.project.controller;

import lapr.project.model.Platform;
import lapr.project.data.registration.UserRegistration;

public class LoginController {


    /**
     * Logs a User in, by checking if there's any User in the DataBase that has
     * the 'email' and 'password' given by parameter
     *
     * @param email         User's email
     * @param password      User's password
     * @return              True if the login operation was successful, false if otherwise
     */
    public boolean login(String email, String password) {

        ApplicationPOT app = ApplicationPOT.getInstance();
        Platform plat = app.getPlatform();
        UserRegistration userReg = plat.getUserReg();
        return userReg.login(email,password);
    }
}
