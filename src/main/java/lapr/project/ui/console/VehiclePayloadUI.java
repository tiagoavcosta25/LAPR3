package lapr.project.ui.console;

import lapr.project.controller.VehiclePayloadController;
import lapr.project.ui.Menu;
import lapr.project.ui.UI;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VehiclePayloadUI implements UI {

    public static final Logger LOGGER = Logger.getLogger(VehiclePayloadUI.class.getName());

    public void run() {
        VehiclePayloadController oCtrl = new VehiclePayloadController();
        Scanner sc = new Scanner(System.in);

            try {
                System.out.print("Get Max Payload:\n\nVehicle's id: ");
                String strId = sc.nextLine();
                Menu.clear();
                double payload = oCtrl.getVehiclePayload(Integer.parseInt(strId));

                if(payload < 0d){
                    throw new Exception();
                }
                System.out.printf("For the Vehicle with the id %s, the max payload is: %.2f kg%n", strId, payload);
                Menu.sleep();
            } catch (Exception e) {
                LOGGER.log(Level.WARNING, "Error finding the vehicle.");
            }
    }

}
