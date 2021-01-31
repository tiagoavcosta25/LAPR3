package lapr.project.ui.console;

import lapr.project.controller.LogoutController;
import lapr.project.ui.Menu;
import lapr.project.ui.UI;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CourierUI implements UI {
    private static final Logger LOGGER = Logger.getLogger(CourierUI.class.getName());

    public void run() {
        Scanner sc = new Scanner(System.in);
        int intOp;

        do{
            Menu.sleep();
            Menu.clear();
            Menu.displayMenu("COURIER", "[1] Know Delivery Run\n[2] Start Delivery Run\n[3] Park Scooter\n" +
                    "[4] Check Vehicle Payload\n\n[0] Log Out");
            intOp = sc.nextInt();
            Menu.clear();

            switch(intOp){
                case 1:
                    Menu.runUI(new KnowDeliveryUI());
                    break;

                case 2:
                    Menu.runUI(new StartDeliveryRunUI());
                    break;

                case 3:
                    Menu.runUI(new ParkScooterUI());
                    break;

                case 4:
                    Menu.runUI(new VehiclePayloadUI());
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
