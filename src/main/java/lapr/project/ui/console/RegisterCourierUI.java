package lapr.project.ui.console;

import lapr.project.controller.RegisterCourierController;
import lapr.project.ui.FileReader;
import lapr.project.ui.Menu;
import lapr.project.ui.UI;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterCourierUI implements UI {

    private static final Logger LOGGER = Logger.getLogger(FileReader.class.getName());

    public void run() {
        try {
            Scanner reader = new Scanner(System.in);
            boolean flag = true;
            Integer input;
            while (flag) {

                System.out.println("Introduce a new Courier's information:\n");
                System.out.print("Name: ");
                String name = reader.nextLine();
                System.out.print("Email: ");
                String email = reader.nextLine();
                System.out.print("Nif: ");
                Integer nif = Integer.parseInt(reader.nextLine());
                System.out.print("IBAN: ");
                String iban = reader.nextLine();
                Menu.clear();
                System.out.print("Pharmacy's email: ");
                String pharmacyEmail = reader.nextLine();
                Menu.clear();
                System.out.print("Do you wish to create a courier with the following data?");
                System.out.println("\n\nName: " + name + "\nEmail: " + email + "\nNif: " + nif + "\nIban: " + iban + "\nPharmacy's Email: " + pharmacyEmail);
                System.out.println();
                System.out.println("[1] YES");
                System.out.println("[2] NO");
                System.out.printf("[3] CANCEL%n%n");
                System.out.print("Your option: ");
                input = Integer.parseInt(reader.nextLine());
                Menu.clear();
                switch (input) {
                    case 1:
                        RegisterCourierController ctrl = new RegisterCourierController();
                        if (ctrl.newCourier(name, email, nif, iban, pharmacyEmail)) {
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
                    default:
                        break;
                }
            }

        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "The input may not be in the correct format");
        }
    }
}
