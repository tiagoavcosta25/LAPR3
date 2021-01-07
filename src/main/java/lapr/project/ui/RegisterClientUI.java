package lapr.project.ui;

import java.util.Scanner;

public class RegisterClientUI {

    public void registerNewUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input the following information:");
        System.out.println("Name:");
        String name = sc.nextLine();
        System.out.println("NIF:");
        Integer nif = Integer.parseInt(sc.nextLine());
        System.out.println("Email:");
        System.out.println("Password:");
        System.out.println("Latitude:");
        System.out.println("Longitude:");
        System.out.println("Street Name:");
        System.out.println("Door Number:");
        System.out.println("Postal Code:");
        System.out.println("Locality:");
        System.out.println("Country:");
        System.out.println("Credit Card Nr:");
        System.out.println("Validity Date:");
        System.out.println("CCV:");
        // UI --> U : Asks for all the information required
        // for the register (i.e. name,nif,email,password,latitude,longitude,streetName,\ndoorNumber,postalCode,locality,
        // country,creditCardNr,validityDate,CCV)
    }
}
