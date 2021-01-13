package lapr.project.controller;

import lapr.project.model.service.UserService;

public class LogoutController {

    private UserService m_oUserService;

    public LogoutController() {
        m_oUserService = new UserService();
    }
    /**
     * Logs out of the Account in the Current User Session
     *
     */
    public void logout() {
        m_oUserService.newUserSession();
    }
}
