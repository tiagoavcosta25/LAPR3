package lapr.project.ui.console;

import lapr.project.controller.RegisterCourierController;
import lapr.project.controller.StartDeliveryRunController;
import lapr.project.utils.FileReader;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StartDeliveryRunUI {

    private static final Logger LOGGER = Logger.getLogger(FileReader.class.getName());

    public void run() {
        try {
            Scanner reader = new Scanner(System.in);
            Integer input;

            System.out.println("Do you wish to start your most recent Delivery Run?");
            System.out.println();
            System.out.println("YES[1]");
            System.out.println("NO[2]");
            input = reader.nextInt();
            switch (input) {
                case 1:
                    StartDeliveryRunController ctrl = new StartDeliveryRunController();
                    if (ctrl.startDeliveryRun()) {
                        LOGGER.log(Level.INFO, "Delivery Run was started with success!");
                    } else LOGGER.log(Level.WARNING, "There was a problem starting the Delivery Run");
                    break;
                case 2:
                    LOGGER.log(Level.INFO, "Operation Cancelled!");
                    break;
            }

        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "The input may not be in the correct format");
        }
    }
}
