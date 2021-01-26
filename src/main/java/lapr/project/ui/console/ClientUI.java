package lapr.project.ui.console;

import lapr.project.controller.LogoutController;
import lapr.project.ui.Menu;
import lapr.project.ui.UI;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientUI implements UI {
    private static final Logger LOGGER = Logger.getLogger(ClientUI.class.getName());

    public void run() {
        Scanner sc = new Scanner(System.in);
        int intOp;

        do{
            Menu.clear();
            Menu.displayMenu("CLIENT", "[1] Make An Order\n\n[0] Log Out");
            intOp = sc.nextInt();

            Menu.clear();

            switch(intOp){
                case 1:
                    Menu.runUI(new MakeAnOrderUI());
                    break;
                case 0:
                    LogoutController oLogOut = new LogoutController();
                    oLogOut.logout();
                    break;
                default:
                    LOGGER.log(Level.WARNING, "Choose a valid option.");
                    break;
            }
        } while (intOp != 0);
    }
}
