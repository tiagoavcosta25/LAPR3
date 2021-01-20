package lapr.project.controller;

import lapr.project.model.service.UserService;

public class LoginController {

    private UserService moUserService;


    public LoginController() {
        moUserService = new UserService();
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
        return moUserService.login(email, password);
    }
}
