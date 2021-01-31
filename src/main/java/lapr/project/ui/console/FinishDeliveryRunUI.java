package lapr.project.ui.console;

import lapr.project.controller.FinishDeliveryRunController;
import lapr.project.controller.VehiclePayloadController;
import lapr.project.ui.Menu;
import lapr.project.ui.UI;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FinishDeliveryRunUI implements UI {

    public static final Logger LOGGER = Logger.getLogger(FinishDeliveryRunUI.class.getName());

    public void run() {
        FinishDeliveryRunController oCtrl = new FinishDeliveryRunController();
        Scanner sc = new Scanner(System.in);

            try {
                System.out.print("Finish Delivery Run:\n\nDelivery Run' ID: ");
                int intId = Integer.parseInt(sc.nextLine());
                System.out.print("Vehicle's Current Battery Percentage: ");
                int intBatteryPerc = Integer.parseInt(sc.nextLine());
                Menu.clear();

                if(intBatteryPerc < 0 || intBatteryPerc > 100){
                    throw new Exception();
                }

                if(oCtrl.finishDeliveryRun(intId, intBatteryPerc)){
                    LOGGER.log(Level.INFO, "Delivery Run Finished. Thank you for your service.");
                }else{
                    throw new Exception();
                }
                Menu.sleep();
            } catch (Exception e) {
                LOGGER.log(Level.WARNING, "There was an error finishing your delivery run.");
            }
    }

}
