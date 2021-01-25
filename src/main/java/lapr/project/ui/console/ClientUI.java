package lapr.project.ui.console;

import lapr.project.controller.LogoutController;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientUI {
    private static final Logger LOGGER = Logger.getLogger(ClientUI.class.getName());

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
                    MakeAnOrderUI UI = new MakeAnOrderUI();
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
        System.out.println("****************CLIENT*****************");
        System.out.println("[1] Make An Order\n\n[0] Log Out");
        System.out.println("**************************************");
        System.out.print("Choose An Option: ");
    }

    public static void clear() {
        for (int i = 0; i < 100; i++){
            System.out.println();
        }
    }
}
