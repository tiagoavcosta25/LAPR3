package lapr.project.ui.console;

import lapr.project.controller.LogoutController;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdministratorUI {
    private static final Logger LOGGER = Logger.getLogger(AdministratorUI.class.getName());

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
                    RegisterPharamacyUI UI = new RegisterPharamacyUI();
                    UI.run();
                    break;
                }
                case 2: {
                    //RegisterProductUI UI = new RegisterProductUI();
                    //UI.run();
                    break;
                }
                case 3: {
                    clear();
                    AddPharmacyProductUI UI = new AddPharmacyProductUI();
                    UI.run();
                    break;
                }
                case 4: {
                    //RegisterScooterUI UI = new RegisterScooterUI();
                    //UI.run();
                    break;
                }
                case 5: {
                    //RemoveScooterUI UI = new RemoveScooterUI();
                    //UI.run();
                    break;
                }
                case 6: {
                    //UpdateScooterUI UI = new UpdateScooterUI();
                    //UI.run();
                    break;
                }
                case 7: {
                    //ScooterInformationUI UI = new ScooterInformationUI();
                    //UI.run();
                    break;
                }
                case 8: {
                    //RegisterDroneUI UI = new RegisterDroneUI();
                    //UI.run();
                    break;
                }
                case 9: {
                    //RemoveDroneUI UI = new RemoveDroneUI();
                    //UI.run();
                    break;
                }
                case 10: {
                    //UpdateDroneUI UI = new UpdateDroneUI();
                    //UI.run();
                    break;
                }
                case 11: {
                    //IssueDeliveryNoteUI UI = new IssueDeliveryNoteUI();
                    //UI.run();
                    break;
                }
                case 12: {
                    RegisterDeliveryRunUI UI = new RegisterDeliveryRunUI();
                    UI.run();
                    break;
                }
                case 13: {
                    RegisterPathUI UI = new RegisterPathUI();
                    UI.run();
                    break;
                }
                case 14: {
                    RegisterParkUI UI = new RegisterParkUI();
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
        System.out.println("****************ADMIN*****************");
        System.out.println("[1] Register Pharmacy\n[2] Register Product\n[3] Add Stock to Pharmacy\n[4] Register Scooter\n" +
                "[5] Remove Scooter\n[6] Update Scooter\n[7] Show Scooter Info\n[8] Register Drone\n[9] Remove Drone\n" +
                "[10] Update Drone\n[11] Issue Delivery Note\n[12] Register Delivery Run\n[13] Register Path\n[14] Register Park\n\n[0] Log Out");
        System.out.println("**************************************");
        System.out.print("Choose An Option: ");
    }

    public static void clear() {
        for (int i = 0; i < 100; i++){
            System.out.println();
        }
    }
}
