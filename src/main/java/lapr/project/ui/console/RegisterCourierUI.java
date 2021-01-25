package lapr.project.ui.console;

import lapr.project.controller.RegisterCourierController;
import lapr.project.ui.FileReader;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterCourierUI {

    private static final Logger LOGGER = Logger.getLogger(FileReader.class.getName());

    public void run() {
        try {
            Scanner reader = new Scanner(System.in);
            boolean flag = true;
            Integer input;
            while (flag) {

                System.out.println("Introduce a new Courier's information:\n");
                System.out.println("Name: ");
                String name = reader.next();
                System.out.println("Email: ");
                String email = reader.next();
                System.out.println("Nif: ");
                Integer nif = Integer.parseInt(reader.next());
                System.out.println("IBAN: ");
                String iban = reader.next();
                System.out.println("Pharmacy's email: ");
                String pharmacyEmail = reader.next();
                System.out.println("Do you wish to create a courier with the following data?");
                System.out.println("Name: " + name + "\nEmail: " + email + "\nNif: " + nif + "\nIban: " + iban + "\nPharmacy's Email: " + pharmacyEmail);
                System.out.println();
                System.out.println("YES[1]");
                System.out.println("NO[2]");
                System.out.println("CANCEL[3]");
                input = reader.nextInt();
                switch (input) {
                    case 1:
                        RegisterCourierController ctrl = new RegisterCourierController();
                        if (ctrl.newCourier(name, email, nif, iban, pharmacyEmail)) {
                            LOGGER.log(Level.INFO, "Courier was created with success!");
                            if (ctrl.registersCourier()) {
                                LOGGER.log(Level.INFO, "Courier was registered with success!");
                            } else LOGGER.log(Level.INFO, "There was a problem registering a Courier");
                        } else LOGGER.log(Level.WARNING, "There was a problem creating a Courier");
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
