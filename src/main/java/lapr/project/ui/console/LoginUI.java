package lapr.project.ui.console;

import lapr.project.controller.ApplicationPOT;
import lapr.project.controller.LoginController;
import lapr.project.ui.Menu;
import lapr.project.ui.UI;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginUI implements UI {
    private static final Logger LOGGER = Logger.getLogger(LoginUI.class.getName());

    public void run() {
        try {
            LoginController oCtrl = new LoginController();
            Scanner sc = new Scanner(System.in);

            System.out.print("Login:\n\nEmail: ");
            String strEmail = sc.nextLine();
            System.out.print("Password: ");
            String strPassword = sc.nextLine();
            Menu.clear();

            if (!oCtrl.login(strEmail, strPassword)) {
                throw new Exception();
            }

            String strRole = ApplicationPOT.getInstance().getCurrentSession().getRole().getRole();

            switch (strRole) {
                case "Administrator":
                    AdministratorUI admUI = new AdministratorUI();
                    admUI.run();
                    break;
                case "Courier":
                    CourierUI couUI = new CourierUI();
                    couUI.run();
                    break;
                case "Client":
                    ClientUI clnUI = new ClientUI();
                    clnUI.run();
                    break;
                default:
                    throw new Exception();
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Error Loggin In.");
        }
    }
}
