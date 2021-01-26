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
            Menu.clear();
            Menu.displayMenu("COURIER", "[1] Start Delivery Run\n[2] Park Scooter\n[3] Know Delivery\n" +
                    "[4] Check Vehicle Payload\n\n[0] Log Out");
            intOp = sc.nextInt();
            Menu.clear();

            switch(intOp){
                case 1: {
                    StartDeliveryRunUI ui = new StartDeliveryRunUI();
                    ui.run();
                    break;
                }
                case 2: {
                    ParkScooterUI ui = new ParkScooterUI();
                    ui.run();
                    break;
                }
                case 3: {
                    KnowDeliveryUI ui = new KnowDeliveryUI();
                    ui.run();
                    break;
                }
                case 4: {
                    VehiclePayloadUI ui = new VehiclePayloadUI();
                    ui.run();
                    break;
                }
                case 0: {
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
