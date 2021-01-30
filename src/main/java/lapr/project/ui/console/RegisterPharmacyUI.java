package lapr.project.ui.console;

import lapr.project.controller.RegisterPharmacyController;
import lapr.project.ui.Menu;
import lapr.project.ui.UI;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterPharmacyUI implements UI {

    private static final Logger LOGGER = Logger.getLogger(RegisterPharmacyUI.class.getName());

    public void run(){
            RegisterPharmacyController oCtrl = new RegisterPharmacyController();
            Scanner sc = new Scanner(System.in);

            do{
                try{
                    Menu.clear();
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

                    if(!strEmail.contains("@")){throw new Exception();}

                    Menu.clear();
                    oCtrl.newPharmacy(strName, strEmail, dblLatitude, dblLongitude, dblAltitude,
                            strStreetName, strDoorNumber, strPostalCode, strLocality, strCountry);

                    if(oCtrl.registerPharmacy()){
                        LOGGER.log(Level.INFO, "Pharmacy Registered with success.");
                        break;
                    }
                    LOGGER.log(Level.WARNING, "Error Registering a Pharmacy.");
                } catch (Exception e){
                    Menu.clear();
                    LOGGER.log(Level.WARNING, "Error Registering a Pharmacy.");
                }
            } while(true);
    }
}
