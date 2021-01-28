package lapr.project.ui.console;

import lapr.project.controller.RegisterClientController;
import lapr.project.model.CreditCard;
import lapr.project.ui.UI;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterClientUI implements UI {

    private static final Logger LOGGER = Logger.getLogger(RegisterClientUI.class.getName());

    public void run() {
        try {
            RegisterClientController oCtrl = new RegisterClientController();
            Scanner sc = new Scanner(System.in);
            boolean flag = true;
            Integer op;
            while (flag) {

                System.out.print("New Client:\n\n\n");
                System.out.print("Name: ");
                String strName = sc.nextLine();
                System.out.print("Email: ");
                String strEmail = sc.nextLine();
                System.out.print("Nif: ");
                Integer intNIF = Integer.parseInt(sc.nextLine());
                System.out.print("Password: ");
                String strPassword = sc.nextLine();
                System.out.print("Latitude: ");
                Double dblLatitude = Double.parseDouble(sc.nextLine());
                System.out.print("Longitude: ");
                Double dblLongitude = Double.parseDouble(sc.nextLine());
                System.out.print("Altitude: ");
                Double dblAltitude = Double.parseDouble(sc.nextLine());
                System.out.print("Street Name: ");
                String strStreetName = sc.nextLine();
                System.out.print("Door Number: ");
                String strDoorNumber = sc.nextLine();
                System.out.print("Postal Code: ");
                String strPostalCode = sc.nextLine();
                System.out.print("Locality: ");
                String strLocality = sc.nextLine();
                System.out.print("Country: ");
                String strCountry = sc.nextLine();

                List<CreditCard> lstCCs = new ArrayList<>();
                do {
                    System.out.print("Do you want to add a credit card? (Y/N): ");
                    String strCheck = sc.nextLine();

                    if (strCheck.equalsIgnoreCase("Y")) {
                        System.out.print("Number: ");
                        Long lngNumber = Long.parseLong(sc.nextLine());
                        System.out.print("validaty Date (MM-YY): ");
                        Date dtDate = new SimpleDateFormat("MM-yy").parse(sc.nextLine());
                        System.out.print("CCV: ");
                        Integer strCCV = Integer.parseInt(sc.nextLine());

                        lstCCs.add(new CreditCard(lngNumber, dtDate, strCCV));
                    }else{
                        break;
                    }
                }while(true);

                System.out.println("Do you want to register with this information?");
                System.out.println("Name: " + strName + "\nEmail: " + strEmail + "\nNif: " + intNIF + "\nLatitude: " + dblLatitude + "\nLongitude: " + dblLongitude);
                System.out.println("\n[1] YES");
                System.out.println("[2] NO");
                System.out.println("[3] CANCEL");
                System.out.print("\nYour Option: ");
                op = sc.nextInt();
                System.out.println();
                switch (op) {
                    case 1:
                        if (oCtrl.registerNewClient(strName, intNIF, strEmail, strPassword, dblLatitude, dblLongitude, dblAltitude,
                                strStreetName, strDoorNumber, strPostalCode, strLocality, strCountry, lstCCs)) {
                            LOGGER.log(Level.INFO, "Account was registered with success!");
                        } else LOGGER.log(Level.WARNING, "There was a problem registering your account.");
                        flag = false;
                        break;
                    case 2:
                    case 3:
                        LOGGER.log(Level.INFO, "Operation Cancelled!");
                        flag = false;
                        break;
                    default:
                        break;
                }
            }

        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Error, please contact our support.");
        }
    }
}
