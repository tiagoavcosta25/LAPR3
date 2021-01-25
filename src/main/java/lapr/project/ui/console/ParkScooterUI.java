package lapr.project.ui.console;

import lapr.project.controller.ParkScooterController;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParkScooterUI {
    private static final Logger LOGGER = Logger.getLogger(ParkScooterUI.class.getName());

    public void run(){
        ParkScooterController oCtrl = new ParkScooterController();
        Scanner sc = new Scanner(System.in);

        do{
            try{
                System.out.print("Park a Scooter:\n\nScooter's ID: ");
                Integer intId = Integer.parseInt(sc.nextLine());

                if(oCtrl.parkScooter(intId)){
                    LOGGER.log(Level.INFO, "Scooter Parked with success.");
                    break;
                }
                LOGGER.log(Level.WARNING, "Error Parking a Scooter.");
            } catch (Exception e){
                LOGGER.log(Level.WARNING, "Error Parking a Scooter.");
            }
        } while(true);
    }
}
