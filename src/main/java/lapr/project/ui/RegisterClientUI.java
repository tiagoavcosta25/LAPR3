package lapr.project.ui;

import lapr.project.controller.RegisterClientController;

public class RegisterClientUI {


    public static void main(String[] args) throws Exception {

        RegisterClientController m_ctrl = new RegisterClientController();
        System.out.println(Long.MAX_VALUE);

        if(m_ctrl.registerNewClient("Rodrigo", 123456789, "slow@gmail.com", "123", 180090980d,
                234567899d, "Street", "2esq", "4430-183", "Mafamude",
                "Portugal", 1234123412341234L, "10/23", 123456789)) {
            System.out.println("Operation WAS Successfull!");

        }else System.out.println("Operation was NOT Successfull!");

        /**Scanner sc = new Scanner(System.in);
         try {
         System.out.println("Please input the following information:");
         System.out.println("Name:");
         String name = sc.nextLine();
         System.out.println("NIF:");
         Integer nif = Integer.parseInt(sc.nextLine());
         System.out.println("Email:");
         String email = sc.nextLine();
         System.out.println("Password:");
         String password = sc.nextLine();
         System.out.println("Latitude:");
         float latitude = Float.parseFloat(sc.nextLine());
         System.out.println("Longitude:");
         float longitude = Float.parseFloat(sc.nextLine());
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
         Integer creditCardNr = Integer.parseInt(sc.nextLine());
         System.out.println("Validity Date:");
         String validityDate = sc.nextLine();
         System.out.println("CCV:");
         Integer ccv = Integer.parseInt(sc.nextLine());

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
         }*/


    }
}

