package lapr.project.ui.console;

import lapr.project.controller.LogoutController;
import lapr.project.utils.Menu;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdministratorUI {
    private static final Logger LOGGER = Logger.getLogger(AdministratorUI.class.getName());

    public void run() {
        Scanner sc = new Scanner(System.in);
        int intOp;

        do{
            Menu.clear();
            Menu.displayMenu("ADMIN", "[1] Register Pharmacy\n[2] Register Product\n[3] Remove Product\n[4] Update Product\n" +
                    "[5] Show Product Info\n[6] Add Stock to Pharmacy\n[7] Register Scooter\n" +
                    "[8] Remove Scooter\n[9] Update Scooter\n[10] Show Scooter Info\n[11] Register Drone\n[12] Remove Drone\n" +
                    "[13] Update Drone\n[14] Issue Delivery Note\n[15] Register Delivery Run\n[16] Register Path\n[17] Register Park\n\n[0] Log Out");
            intOp = sc.nextInt();

            switch(intOp){
                case 1: {
                    Menu.clear();
                    RegisterPharamacyUI UI = new RegisterPharamacyUI();
                    UI.run();
                    break;
                }
                case 2: {
                    Menu.clear();
                    //RegisterProductUI UI = new RegisterProductUI();
                    //UI.run();
                    break;
                }
                case 3: {
                    Menu.clear();
                    //RemoveProductUI UI = new RemoveProductUI();
                    //UI.run();
                    break;
                }
                case 4: {
                    Menu.clear();
                    //UpdateProductUI UI = new UpdateProductUI();
                    //UI.run();
                    break;
                }
                case 5: {
                    Menu.clear();
                    //ProductInformationUI UI = new ProductInformationUI();
                    //UI.run();
                    break;
                }
                case 6: {
                    Menu.clear();
                    AddPharmacyProductUI UI = new AddPharmacyProductUI();
                    UI.run();
                    break;
                }
                case 7: {
                    Menu.clear();
                    RegisterScooterUI UI = new RegisterScooterUI();
                    UI.run();
                    break;
                }
                case 8: {
                    Menu.clear();
                    RemoveScooterUI UI = new RemoveScooterUI();
                    UI.run();
                    break;
                }
                case 9: {
                    Menu.clear();
                    UpdateScooterUI UI = new UpdateScooterUI();
                    UI.run();
                    break;
                }
                case 10: {
                    Menu.clear();
                    ScooterInformationUI UI = new ScooterInformationUI();
                    UI.run();
                    break;
                }
                case 11: {
                    Menu.clear();
                    RegisterDroneUI UI = new RegisterDroneUI();
                    UI.run();
                    break;
                }
                case 12: {
                    Menu.clear();
                    RemoveDroneUI UI = new RemoveDroneUI();
                    UI.run();
                    break;
                }
                case 13: {
                    Menu.clear();
                    UpdateDroneUI UI = new UpdateDroneUI();
                    UI.run();
                    break;
                }
                case 14: {
                    Menu.clear();
                    IssueDeliveryNoteUI UI = new IssueDeliveryNoteUI();
                    UI.run();
                    break;
                }
                case 15: {
                    Menu.clear();
                    RegisterDeliveryRunUI UI = new RegisterDeliveryRunUI();
                    UI.run();
                    break;
                }
                case 16: {
                    Menu.clear();
                    RegisterPathUI UI = new RegisterPathUI();
                    UI.run();
                    break;
                }
                case 17: {
                    Menu.clear();
                    RegisterParkUI UI = new RegisterParkUI();
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
