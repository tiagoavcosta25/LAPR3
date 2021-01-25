package lapr.project.ui.console;

import lapr.project.controller.RegisterDroneController;
import lapr.project.model.Pharmacy;
import lapr.project.utils.FileReader;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterDroneUI {
    private static final Logger LOGGER = Logger.getLogger(FileReader.class.getName());

    public void run(){
        try {
            Scanner sc = new Scanner(System.in);
            RegisterDroneController oCtrl = new RegisterDroneController();
            List<Pharmacy> lstPharmacies = oCtrl.showPharmacies();

            for (Pharmacy p : lstPharmacies) {
                System.out.println(p.toString());
            }

            System.out.println("\nChoose the Pharamcies's Email: ");
            String strPharmacyEmail = sc.nextLine();
            System.out.println();

            Pharmacy oPharmacy = new Pharmacy();
            for(Pharmacy p : lstPharmacies){
                if (p.hasEmail(strPharmacyEmail)){
                    oCtrl.setPharmacy(strPharmacyEmail);
                    oPharmacy = p;
                    break;
                }
            }

            if(oPharmacy.getName().equalsIgnoreCase("No name.")){
                throw new Exception();
            }

            System.out.print("Do you want to create a new scooter model? (Y/N): ");
            String strModelCreation = sc.nextLine();

            if(strModelCreation.equalsIgnoreCase("N")){
                System.out.print("Model designation: ");
                String strModelDesignation = sc.nextLine();
                oCtrl.setVehicleModel(strModelDesignation);
            } else{
                System.out.println("Please input the following information:");
                System.out.print("Designation: ");
                String strDesignation = sc.nextLine();
                System.out.print("Potency: ");
                double dblPotency = Double.parseDouble(sc.nextLine());
                System.out.print("Weight: ");
                double dblWeight = Double.parseDouble(sc.nextLine());
                System.out.print("Maximum Payload: ");
                double dblMaxPayload = Double.parseDouble(sc.nextLine());
                System.out.print("Battery Capacity: ");
                int intBatteryCapacity = Integer.parseInt(sc.nextLine());
                System.out.print("Battery Voltage: ");
                double dblBatteryVoltage = Double.parseDouble(sc.nextLine());
                System.out.print("Battery Efficiency: ");
                double dblEfficiency = Double.parseDouble(sc.nextLine());

                oCtrl.newVehicleModel(strDesignation, dblPotency, dblWeight, dblMaxPayload, intBatteryCapacity,
                        dblBatteryVoltage, dblEfficiency);
            }

            if (oCtrl.newDrone()) {
                if (oCtrl.registersDrone()) {
                    System.out.println("Operation was Successfull!");
                }else LOGGER.log(Level.WARNING, "There was a problem registering a Drone");
            } else LOGGER.log(Level.WARNING, "There was a problem creating a new Drone");
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "There was a problem registering the Drone");
        }
    }
}
