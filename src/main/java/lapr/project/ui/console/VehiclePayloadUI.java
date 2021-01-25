package lapr.project.ui.console;

import lapr.project.controller.ApplicationPOT;
import lapr.project.controller.VehiclePayloadController;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VehiclePayloadUI {

    public static final Logger LOGGER = Logger.getLogger(VehiclePayloadUI.class.getName());

    public void run() {
        VehiclePayloadController oCtrl = new VehiclePayloadController();
        Scanner sc = new Scanner(System.in);

        do {
            try {
                System.out.print("Get Max Payload:\n\nVehicle's id: ");
                String strId = sc.nextLine();
                double payload = oCtrl.getVehiclePayload(Integer.parseInt(strId));
                System.out.printf("For the Vehicle with the id %s, the max payload is:%f%n", strId, payload);
            } catch (Exception e) {
                LOGGER.log(Level.WARNING, "Error Finding the vehicle.");
            }
        } while (true);
    }

}
