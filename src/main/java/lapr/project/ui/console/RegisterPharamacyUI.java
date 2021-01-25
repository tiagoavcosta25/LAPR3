package lapr.project.ui.console;

import lapr.project.controller.RegisterPharmacyController;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterPharamacyUI {

    private static final Logger LOGGER = Logger.getLogger(RegisterPharamacyUI.class.getName());

    public void run(){
            RegisterPharmacyController oCtrl = new RegisterPharmacyController();
            Scanner sc = new Scanner(System.in);

            do{
                try{
                    System.out.print("Register a Pharmacy:\n\nName: ");
                    String strName = sc.nextLine();
                    System.out.print("Email: ");
                    String strEmail = sc.nextLine();
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

                    oCtrl.newPharmacy(strName, strEmail, dblLatitude, dblLongitude, dblAltitude,
                            strStreetName, strDoorNumber, strPostalCode, strLocality, strCountry);

                    if(oCtrl.registerPharmacy()){
                        LOGGER.log(Level.INFO, "Pharmacy Registered with success.");
                        break;
                    }
                    LOGGER.log(Level.WARNING, "Error Registering a Pharmacy.");
                } catch (Exception e){
                    LOGGER.log(Level.WARNING, "Error Registering a Pharmacy.");
                }
            } while(true);
    }
}
