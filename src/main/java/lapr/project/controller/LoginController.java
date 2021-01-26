package lapr.project.controller;

import lapr.project.model.service.UserService;

/**
 * Login Controller.
 *
 * Group: Team Lisa [G-021]
 * ______________________________________________________
 * @author António Barbosa <1190404@isep.ipp.pt>
 * @author Ernesto Rodrigues <1190560@isep.ipp.pt>
 * @author Jessica Alves <1190682@isep.ipp.pt>
 * @author Pedro Santos <1190967@isep.ipp.pt>
 * @author Rodrigo Costa <1191014@isep.ipp.pt>
 * @author Tiago Costa <1191460@isep.ipp.pt>
 */
public class LoginController {

    /**
     * User Service
     */
    private UserService moUserService;

    /**
     * Returns the User Service
     *
     * @return User Service
     */
    public UserService getUserService() {
        return moUserService;
    }

    /**
     * Sets the User Service to the one given by parameter
     *
     * @param oUserService new User Service
     */
    public void setUserService(UserService oUserService) {
        this.moUserService = oUserService;
    }

    /**
     * Empty constructor of LoginController, which instantiates a mew
     * User Service
     */
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
