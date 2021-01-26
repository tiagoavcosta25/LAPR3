package lapr.project.controller;

import lapr.project.model.service.UserService;

/**
 * Logout Controller.
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
public class LogoutController {

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
     * Empty constructor of LogoutController, which instantiates a new
     * User Service
     */
    public LogoutController() {
        moUserService = new UserService();
    }

    /**
     * Logs out of the Account in the Current User Session
     */
    public void logout() {
        moUserService.newUserSession();
    }
}
