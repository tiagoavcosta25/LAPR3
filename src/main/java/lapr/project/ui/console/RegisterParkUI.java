package lapr.project.ui.console;

import lapr.project.controller.RegisterParkController;
import lapr.project.model.VehicleType;
import lapr.project.ui.UI;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterParkUI implements UI {

    private static final Logger LOGGER = Logger.getLogger(RegisterParkUI.class.getName());

    public void run(){
            RegisterParkController oCtrl = new RegisterParkController();
            Scanner sc = new Scanner(System.in);

            do{
                try{
                    System.out.print("Register a Park:\n\nPharmacy's Email: ");
                    String strEmail = sc.nextLine();
                    System.out.print("Max Number of Parking Slots: ");
                    Integer intMaxSlots = Integer.parseInt(sc.nextLine());
                    System.out.print("Output Current: ");
                    Double dblOutputCurrent = Double.parseDouble(sc.nextLine());
                    System.out.print("Number Of Charging Slots: ");
                    Integer intNumberOfChargingSlots = Integer.parseInt(sc.nextLine());
                    System.out.print("Number Of Non Charging Slots: ");
                    Integer intNumberOfNonChargingSlots = Integer.parseInt(sc.nextLine());
                    System.out.print("Vehicle Type: ");
                    VehicleType oVehicleType = VehicleType.getTypeByDesignation(sc.nextLine());

                    if(oCtrl.addPark(strEmail, intMaxSlots, dblOutputCurrent, oVehicleType, intNumberOfNonChargingSlots, intNumberOfChargingSlots)){
                        LOGGER.log(Level.INFO, "Park Registered with success.");
                        break;
                    }
                    LOGGER.log(Level.WARNING, "Error Registering a Park.");
                } catch (Exception e){
                    LOGGER.log(Level.WARNING, "Error Registering a Park.");
                }
            } while(true);
    }
}
