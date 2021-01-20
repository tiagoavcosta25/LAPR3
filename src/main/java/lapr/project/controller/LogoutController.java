package lapr.project.controller;

import lapr.project.model.service.UserService;

public class LogoutController {

    private UserService moUserService;

    public LogoutController() {
        moUserService = new UserService();
    }
    /**
     * Logs out of the Account in the Current User Session
     *
     */
    public void logout() {
        moUserService.newUserSession();
    }
}
