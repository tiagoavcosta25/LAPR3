package lapr.project.ui.console;

import lapr.project.controller.ApplicationPOT;
import lapr.project.controller.LoginController;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginUI {
    private static final Logger LOGGER = Logger.getLogger(LoginUI.class.getName());

    public void run() {
        try {
            LoginController oCtrl = new LoginController();
            Scanner sc = new Scanner(System.in);
            int intOp;

            System.out.print("Login:\n\nEmail: ");
            String strEmail = sc.nextLine();
            System.out.print("Password: ");
            String strPassword = sc.nextLine();

            if (!oCtrl.login(strEmail, strPassword)) {
                throw new Exception();
            }

            String strRole = ApplicationPOT.getInstance().getCurrentSession().getRole().getRole();

            switch (strRole) {
                case "Administrator": {
                    AdministratorUI UI = new AdministratorUI();
                    UI.run();
                    break;
                }
                case "Courier": {
                    CourierUI UI = new CourierUI();
                    UI.run();
                    break;
                }
                case "Client": {
                    ClientUI UI = new ClientUI();
                    UI.run();
                    break;
                }
                default:
                    throw new Exception();
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Error Loggin In.");
        }
    }
}
