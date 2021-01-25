package lapr.project.ui.console;

import lapr.project.controller.RegisterPathController;
import lapr.project.model.VehicleType;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterPathUI {

    private static final Logger LOGGER = Logger.getLogger(RegisterPathUI.class.getName());

    public void run(){
        RegisterPathController oCtrl = new RegisterPathController();
            Scanner sc = new Scanner(System.in);

            do{
                try{
                    System.out.print("Register a Path:\n\nFirst Address' Latitude: ");
                    Double dblLatitudeA = Double.parseDouble(sc.nextLine());
                    System.out.print("First Address' Longitude: ");
                    Double dblLongitudeA = Double.parseDouble(sc.nextLine());
                    System.out.print("Second Address' Latitude: ");
                    Double dblLatitudeB = Double.parseDouble(sc.nextLine());
                    System.out.print("Second Address' Longitude: ");
                    Double dblLongitudeB = Double.parseDouble(sc.nextLine());
                    System.out.print("Name: ");
                    String strName = sc.nextLine();
                    System.out.print("Wind Speed: ");
                    Double dblWindSpeed = Double.parseDouble(sc.nextLine());
                    System.out.print("Wind Angle: ");
                    Double dblWindAngle = Double.parseDouble(sc.nextLine());
                    System.out.print("Kinetic Friction Coefficient: ");
                    Double dblKineticFrictionCoefficient = Double.parseDouble(sc.nextLine());
                    System.out.print("Vehicle Type: ");
                    VehicleType oVehicleType = VehicleType.getTypeByDesignation(sc.nextLine());

                    if(oCtrl.registerPath(dblLatitudeA, dblLongitudeA, dblLatitudeB, dblLongitudeB, strName, dblWindSpeed, dblWindAngle, dblKineticFrictionCoefficient, oVehicleType)){
                        LOGGER.log(Level.INFO, "Path Registered with success.");
                        break;
                    }
                    LOGGER.log(Level.WARNING, "Error Registering a Path.");
                } catch (Exception e){
                    LOGGER.log(Level.WARNING, "Error Registering a Park.");
                }
            } while(true);
    }
}
