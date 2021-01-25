package lapr.project.ui.console;

import lapr.project.controller.LogoutController;
import lapr.project.utils.Menu;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CourierUI {
    private static final Logger LOGGER = Logger.getLogger(CourierUI.class.getName());

    public void run() {
        Scanner sc = new Scanner(System.in);
        int intOp;

        do{
            Menu.clear();
            Menu.displayMenu("COURIER", "[1] Start Delivery Run\n[2] Park Scooter\n\n[0] Log Out");
            intOp = sc.nextInt();

            switch(intOp){
                case 1: {
                    Menu.clear();
                    StartDeliveryRunUI UI = new StartDeliveryRunUI();
                    UI.run();
                    break;
                }
                case 2: {
                    Menu.clear();
                    ParkScooterUI UI = new ParkScooterUI();
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
