package lapr.project.ui;

import lapr.project.controller.ApplicationPOT;
import lapr.project.controller.LogoutController;

public class LogoutUI {

    public static void main(String[] args) throws Exception {

        LogoutController m_ctrl = new LogoutController();

        m_ctrl.logout();

        System.out.println(ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail());
    }
}
