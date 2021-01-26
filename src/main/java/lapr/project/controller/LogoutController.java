package lapr.project.controller;

import lapr.project.model.service.UserService;

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
