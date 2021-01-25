package lapr.project.ui.console;

import lapr.project.controller.LogoutController;
import lapr.project.utils.Menu;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientUI {
    private static final Logger LOGGER = Logger.getLogger(ClientUI.class.getName());

    public void run() {
        Scanner sc = new Scanner(System.in);
        int intOp;

        do{
            Menu.clear();
            Menu.displayMenu("CLIENT", "[1] Make An Order\n\n[0] Log Out");
            intOp = sc.nextInt();

            switch(intOp){
                case 1: {
                    Menu.clear();
                    MakeAnOrderUI UI = new MakeAnOrderUI();
                    UI.run();
                    break;
                }
                case 0: {
                    Menu.clear();
                    LogoutController oLogOut = new LogoutController();
                    oLogOut.logout();
                    break;
                }
                default:
                    LOGGER.log(Level.WARNING, "Choose a valid option.");
                    break;
            }
        } while (intOp != 0);
    }
}
