package lapr.project.controller;

public class LogoutController {

    /**
     * Logs out of the Account in the Current User Session
     *
     */
    public void logout() {
        ApplicationPOT app = ApplicationPOT.getInstance();
        app.clearCurrentSession();
    }
}
