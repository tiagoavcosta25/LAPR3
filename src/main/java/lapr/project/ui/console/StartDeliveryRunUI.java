package lapr.project.ui.console;

import lapr.project.controller.StartDeliveryRunController;
import lapr.project.ui.UI;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StartDeliveryRunUI implements UI {

    private static final Logger LOGGER = Logger.getLogger(StartDeliveryRunUI.class.getName());

    public void run() {
        try {
            Scanner reader = new Scanner(System.in);
            Integer input;

            System.out.println("Do you wish to start your most recent Delivery Run?");
            System.out.println();
            System.out.println("YES[1]");
            System.out.println("NO[2]");
            input = reader.nextInt();
            if (input == 1) {
                StartDeliveryRunController ctrl = new StartDeliveryRunController();
                if (ctrl.startDeliveryRun()) {
                    LOGGER.log(Level.INFO, "Delivery Run was started with success!");
                } else LOGGER.log(Level.WARNING, "There was a problem starting the Delivery Run");
            } else if (input == 2) {
                LOGGER.log(Level.INFO, "Operation Cancelled!");
            }

        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "The input may not be in the correct format");
        }
    }
}
