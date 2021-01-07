package lapr.project.ui;

import lapr.project.controller.RegisterClientController;

import java.util.Scanner;

public class RegisterClientUI {

    RegisterClientController m_ctrl = new RegisterClientController();

    public void registerNewUser() throws Exception {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Please input the following information:");
            System.out.println("Name:");
            String name = sc.nextLine();
            System.out.println("NIF:");
            Integer nif = sc.nextInt();
            System.out.println("Email:");
            String email = sc.nextLine();
            System.out.println("Password:");
            String password = sc.nextLine();
            System.out.println("Latitude:");
            float latitude = sc.nextFloat();
            System.out.println("Longitude:");
            float longitude = sc.nextFloat();
            System.out.println("Street Name:");
            String streetName = sc.nextLine();
            System.out.println("Door Number:");
            String doorNumber = sc.nextLine();
            System.out.println("Postal Code:");
            String postalCode = sc.nextLine();
            System.out.println("Locality:");
            String locality = sc.nextLine();
            System.out.println("Country:");
            String country = sc.nextLine();
            System.out.println("Credit Card Nr:");
            Integer creditCardNr = sc.nextInt();
            System.out.println("Validity Date:");
            String validityDate = sc.nextLine();
            System.out.println("CCV:");
            Integer ccv = sc.nextInt();

            if (m_ctrl.registerNewClient(name, nif, email, password, latitude, longitude, streetName, doorNumber, postalCode, locality,
                    country, creditCardNr, validityDate, ccv)) {
                System.out.println("Operation WAS successfull!");
            } else {
                System.out.println("Operation NOT successfull!");
            }
        } catch (Exception e) {
            System.out.println("Format ERROR!");
            System.out.println("Operation NOT successfull!");
        }

    }
}
