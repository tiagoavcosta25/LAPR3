package lapr.project.ui.console;

import lapr.project.controller.UpdateCourierController;
import lapr.project.model.Courier;
import lapr.project.ui.FileReader;
import lapr.project.ui.UI;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateCourierUI implements UI {

    private static final Logger LOGGER = Logger.getLogger(UpdateCourierUI.class.getName());

    public void run() {
        try {
            Scanner reader = new Scanner(System.in);
            boolean flag = true;
            Integer input;
            while (flag) {
                System.out.println("Introduce the Courier's email from the User you want to update:\n");
                System.out.println("Email: ");
                String email = reader.next();
                UpdateCourierController ctrl = new UpdateCourierController();
                Courier c = ctrl.getCourierByEmail(email);
                if (c != null) {
                    LOGGER.log(Level.INFO, "Courier was found with success!");
                } else {
                    LOGGER.log(Level.WARNING, "There was a problem founding a Courier");
                    break;
                }
                System.out.println("Introduce the new Courier's email data to update:\n");
                System.out.println("Name: ");
                String name = reader.next();
                System.out.println("Nif: ");
                Integer nif = Integer.parseInt(reader.next());
                System.out.println("IBAN: ");
                String iban = reader.next();
                System.out.println("Pharmacy's email: ");
                String pharmacyEmail = reader.next();
                System.out.println("Do you wish to update a courier with the following data?");
                System.out.println("Name: " + name + "\nEmail: " + email + "\nNif: " + nif + "\nIban: " + iban + "\nPharmacy's Email: " + pharmacyEmail);
                System.out.println();
                System.out.println("YES[1]");
                System.out.println("NO[2]");
                System.out.println("CANCEL[3]");
                input = reader.nextInt();
                switch (input) {
                    case 1:
                        Courier c2 = ctrl.updateCourier(c, name, email, nif, iban, pharmacyEmail);
                        if (c2 != c) {
                            ctrl.updateCourierDB();
                            LOGGER.log(Level.INFO, "Courier was updated with success!");
                        } else LOGGER.log(Level.INFO, "There was a problem updating the Courier!");
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
