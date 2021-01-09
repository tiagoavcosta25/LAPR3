package lapr.project.ui;

import lapr.project.controller.RegisterClientController;

public class RegisterClientUI implements Runnable {


    public void run() {

        RegisterClientController m_ctrl = new RegisterClientController();

        try {
            System.out.println("Please input the following information:");
            System.out.println("Name:");
            String name = Menu.sc.nextLine();
            System.out.println("NIF:");
            Integer nif = Integer.parseInt(Menu.sc.nextLine());
            System.out.println("Email:");
            String email = Menu.sc.nextLine();
            System.out.println("Password:");
            String password = Menu.sc.nextLine();
            System.out.println("Latitude:");
            Double latitude = Double.parseDouble(Menu.sc.nextLine());
            System.out.println("Longitude:");
            Double longitude = Double.parseDouble(Menu.sc.nextLine());
            System.out.println("Street Name:");
            String streetName = Menu.sc.nextLine();
            System.out.println("Door Number:");
            String doorNumber = Menu.sc.nextLine();
            System.out.println("Postal Code:");
            String postalCode = Menu.sc.nextLine();
            System.out.println("Locality:");
            String locality = Menu.sc.nextLine();
            System.out.println("Country:");
            String country = Menu.sc.nextLine();
            System.out.println("Credit Card Nr:");
            Long creditCardNr = Long.parseLong(Menu.sc.nextLine());
            System.out.println("Validity Date:");
            String validityDate = Menu.sc.nextLine();
            System.out.println("CCV:");
            Integer ccv = Integer.parseInt(Menu.sc.nextLine());

            System.out.println(String.format("Name: %s\nNIF: %d\nEmail: %s\nPassword: %s\nLatitude: %h\nLongitude: %h\n" +
                            "Street Name: %s\nDoor Number: %s\nPostal Code: %s\nLocality: %s\nCountry: %s\nCredit Card Nr: %d\n" +
                            "Validity Date: %s\n CCV: %d\nDo you confirm this information? (Y/N)", name, nif, email, password, latitude, longitude, streetName, doorNumber,
                    postalCode, locality, country, creditCardNr, validityDate, ccv));

            String choice = Menu.sc.nextLine();

            while (choice.isEmpty() || (!choice.equalsIgnoreCase("Y") &&
                    !choice.equalsIgnoreCase("N"))) {
                System.out.println("Select a valid choice!");
                choice = Menu.sc.nextLine();
            }

            if (choice.equalsIgnoreCase("Y")) {
                if (m_ctrl.registerNewClient(name, nif, email, password, latitude, longitude, streetName, doorNumber,
                        postalCode, locality, country, creditCardNr, validityDate, ccv)) {
                    System.out.println("Operation WAS Successfull!");

                } else System.out.println("Operation was NOT Successfull!");
            }else{
                Menu.menu();
            }



        } catch (Exception e) {
            System.out.println("Operation was NOT Successfull!");
        } finally {
            Menu.menu();
        }
    }
}

