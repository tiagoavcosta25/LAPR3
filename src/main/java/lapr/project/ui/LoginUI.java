package lapr.project.ui;

import lapr.project.controller.ApplicationPOT;
import lapr.project.controller.LoginController;
import lapr.project.controller.UserSession;

public class LoginUI implements Runnable {


    public void run() {

        LoginController m_ctrl = new LoginController();

        if (m_ctrl.login("slow@gmail.com","abcdef")) {
            System.out.println("Operation WAS Successfull!");
            loggedIn();
        } else {
            new UserSession();
            System.out.println("Operation was NOT Successfull!");
            Menu.menu();
        }
    }

    public void loggedIn() {
        System.out.println(String.format("Current Session: %s", ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail()));
        System.out.println(String.format("Current role: %s", ApplicationPOT.getInstance().getCurrentSession().getRole().toString()));

        System.out.println("1- Logout");

        LogoutUI logoutUI = new LogoutUI();

        try {
            int choice = Integer.parseInt(Menu.sc.nextLine());
            while (choice != 1 && choice != 2) {
                choice = Integer.parseInt(Menu.sc.nextLine());
            }
            switch (choice) {
                case 1:
                    logoutUI.run();
                    Menu.menu();
                    break;
                case 2:

                    break;
            }
        } catch (Exception e) {
            System.out.println("There has been an error with your request!");
        }
    }



}
