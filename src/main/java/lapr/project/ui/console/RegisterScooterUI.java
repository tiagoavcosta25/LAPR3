package lapr.project.ui.console;

import lapr.project.controller.RegisterScooterController;
import lapr.project.model.*;
import lapr.project.ui.FileReader;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterScooterUI {

    private static final Logger LOGGER = Logger.getLogger(FileReader.class.getName());

    public void run(){
        try {
            Scanner sc = new Scanner(System.in);
            RegisterScooterController oCtrl = new RegisterScooterController();
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

            if (oCtrl.newScooter()) {
                if (oCtrl.registersScooter()) {
                    LOGGER.log(Level.INFO,"Operation was Successfull!");
                }else LOGGER.log(Level.WARNING, "There was a problem registering a Scooter");
            } else LOGGER.log(Level.WARNING, "There was a problem creating a new Scooter");
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "There was a problem registering a Scooter");
        }
    }
}
