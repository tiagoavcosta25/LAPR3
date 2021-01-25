package lapr.project.ui.console;

import lapr.project.controller.RegisterCourierController;
import lapr.project.controller.RemoveCourierController;
import lapr.project.utils.FileReader;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RemoveCourierUI {

    private static final Logger LOGGER = Logger.getLogger(FileReader.class.getName());

    public void run() {
        try {
            Scanner reader = new Scanner(System.in);
            boolean flag = true;
            Integer input;
            while (flag) {
                System.out.println("Introduce the Courier's email from the Courier you would like to remove:\n");
                System.out.println("Email: ");
                String email = reader.next();
                System.out.println("Do you wish to remove the courier with the following email?");
                System.out.println("Email: " + email);
                System.out.println();
                System.out.println("YES[1]");
                System.out.println("NO[2]");
                System.out.println("CANCEL[3]");
                input = reader.nextInt();
                switch (input) {
                    case 1:
                        RemoveCourierController ctrl = new RemoveCourierController();
                        if (ctrl.validateCourier(email)) {
                            if (ctrl.removeCourier(email)) {
                                LOGGER.log(Level.INFO, "Courier was removed with success!");
                            } else LOGGER.log(Level.INFO, "There was a problem removing the Courier");
                        } else LOGGER.log(Level.WARNING, "The input may not be in the correct format");
                        flag = false;
                        break;
                    case 3:
                        LOGGER.log(Level.INFO, "Operation Cancelled!");
                        flag = false;
                        break;
                }
            }

        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "The input may not be in the correct format");
        }
    }
}
