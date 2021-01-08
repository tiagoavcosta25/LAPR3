package lapr.project.ui;

import lapr.project.controller.LogoutController;

public class LogoutUI implements Runnable{

    public void run() {

        LogoutController m_ctrl = new LogoutController();

        m_ctrl.logout();

    }
}
