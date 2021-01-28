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
            Menu.sleep();
            Menu.clear();
            Menu.displayMenu("ADMIN", "[1] Register Pharmacy\n[2] Register Product\n[3] Remove Product\n[4] Update Product\n" +
                    "[5] Show Product Info\n[6] Add Stock to Pharmacy\n[7] Register Scooter\n" +
                    "[8] Remove Scooter\n[9] Update Scooter\n[10] Show Scooter Info\n[11] Register Drone\n[12] Remove Drone\n" +
                    "[13] Update Drone\n[14] Issue Delivery Note\n[15] Register Delivery Run\n[16] Register Path\n[17] Register Park\n" +
                    "[18] Register Courier\n[19] Update Courier\n[20] Remove Courier\n\n[0] Log Out");
            intOp = sc.nextInt();

            Menu.clear();

            switch(intOp){
                case 1:
                    Menu.runUI(new RegisterPharmacyUI());
                    break;

                case 2:
                    Menu.runUI(new RegisterProductUI());
                    break;

                case 3:
                    Menu.runUI(new RemoveProductUI());
                    break;

                case 4:
                    Menu.runUI(new UpdateProductUI());
                    break;

                case 5:
                    Menu.runUI(new ProductInformationUI());
                    break;

                case 6:
                    Menu.runUI(new AddPharmacyProductUI());
                    break;

                case 7:
                    RegisterVehicleUI regVUI = new RegisterVehicleUI();
                    regVUI.run(VehicleType.SCOOTER);
                    break;

                case 8:
                    RemoveVehicleUI remVUI = new RemoveVehicleUI();
                    remVUI.run(VehicleType.SCOOTER);
                    break;

                case 9:
                    UpdateVehicleUI updVUI = new UpdateVehicleUI();
                    updVUI.run(VehicleType.SCOOTER);
                    break;

                case 10:
                    Menu.runUI(new ScooterInformationUI());
                    break;

                case 11:
                    regVUI = new RegisterVehicleUI();
                    regVUI.run(VehicleType.DRONE);
                    break;

                case 12:
                    remVUI = new RemoveVehicleUI();
                    remVUI.run(VehicleType.DRONE);
                    break;

                case 13:
                    updVUI = new UpdateVehicleUI();
                    updVUI.run(VehicleType.DRONE);
                    break;

                case 14:
                    Menu.runUI(new IssueDeliveryNoteUI());
                    break;

                case 15:
                    Menu.runUI(new RegisterDeliveryRunUI());
                    break;

                case 16:
                    Menu.runUI(new RegisterPathUI());
                    break;

                case 17:
                    Menu.runUI(new RegisterParkUI());
                    break;

                case 18:
                    Menu.runUI(new RegisterCourierUI());
                    break;

                case 19:
                    Menu.runUI(new UpdateCourierUI());
                    break;

                case 20:
                    Menu.runUI(new RemoveCourierUI());
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
