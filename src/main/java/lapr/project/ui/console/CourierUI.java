package lapr.project.ui.console;

import lapr.project.controller.LogoutController;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CourierUI {
    private static final Logger LOGGER = Logger.getLogger(CourierUI.class.getName());

    public void run() {
        Scanner sc = new Scanner(System.in);
        int intOp;

        do{
            clear();
            displayMenu();
            intOp = sc.nextInt();

            switch(intOp){
                case 1: {
                    clear();
                    //StartDeliveryRun UI = new StartDeliveryRun();
                    //UI.run();
                    break;
                }
                case 2: {
                    clear();
                    ParkScooterUI UI = new ParkScooterUI();
                    UI.run();
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

    public static void displayMenu() {
        System.out.println("****************COURIER*****************");
        System.out.println("[1] Start Delivery Run\n[2] Park Scooter\n\n[0] Log Out");
        System.out.println("**************************************");
        System.out.print("Choose An Option: ");
    }

    public static void clear() {
        for (int i = 0; i < 100; i++){
            System.out.println();
        }
    }
}
