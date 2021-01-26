package lapr.project.ui.console;

import lapr.project.controller.LogoutController;
import lapr.project.model.VehicleType;
import lapr.project.ui.Menu;
import lapr.project.ui.UI;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdministratorUI implements UI {
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

            Menu.clear();

            switch(intOp){
                case 1: {
                    RegisterPharmacyUI ui = new RegisterPharmacyUI();
                    ui.run();
                    break;
                }
                case 2: {
                    RegisterProductUI ui = new RegisterProductUI();
                    ui.run();
                    break;
                }
                case 3: {
                    RemoveProductUI ui = new RemoveProductUI();
                    ui.run();
                    break;
                }
                case 4: {
                    UpdateProductUI ui = new UpdateProductUI();
                    ui.run();
                    break;
                }
                case 5: {
                    ProductInformationUI ui = new ProductInformationUI();
                    ui.run();
                    break;
                }
                case 6: {
                    AddPharmacyProductUI ui = new AddPharmacyProductUI();
                    ui.run();
                    break;
                }
                case 7: {
                    RegisterVehicleUI ui = new RegisterVehicleUI();
                    ui.run(VehicleType.SCOOTER);
                    break;
                }
                case 8: {
                    RemoveVehicleUI ui = new RemoveVehicleUI();
                    ui.run(VehicleType.SCOOTER);
                    break;
                }
                case 9: {
                    UpdateVehicleUI ui = new UpdateVehicleUI();
                    ui.run(VehicleType.SCOOTER);
                    break;
                }
                case 10: {
                    ScooterInformationUI ui = new ScooterInformationUI();
                    ui.run();
                    break;
                }
                case 11: {
                    RegisterVehicleUI ui = new RegisterVehicleUI();
                    ui.run(VehicleType.DRONE);
                    break;
                }
                case 12: {
                    RemoveVehicleUI ui = new RemoveVehicleUI();
                    ui.run(VehicleType.DRONE);
                    break;
                }
                case 13: {
                    UpdateVehicleUI ui = new UpdateVehicleUI();
                    ui.run(VehicleType.DRONE);
                    break;
                }
                case 14: {
                    IssueDeliveryNoteUI ui = new IssueDeliveryNoteUI();
                    ui.run();
                    break;
                }
                case 15: {
                    RegisterDeliveryRunUI ui = new RegisterDeliveryRunUI();
                    ui.run();
                    break;
                }
                case 16: {
                    RegisterPathUI ui = new RegisterPathUI();
                    ui.run();
                    break;
                }
                case 17: {
                    RegisterParkUI ui = new RegisterParkUI();
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
