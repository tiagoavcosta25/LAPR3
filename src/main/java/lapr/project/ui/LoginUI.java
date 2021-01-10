package lapr.project.ui;

import lapr.project.controller.LoginController;
import lapr.project.controller.UserSession;

public class LoginUI {


    public static void main(String[] args) {

        LoginController m_ctrl = new LoginController();

        if (m_ctrl.login("slow@gmail.com","pw1234")) {
            System.out.println("Operation WAS Successfull!");
        } else {
            new UserSession();
            System.out.println("Operation was NOT Successfull!");
        }
    }



}
