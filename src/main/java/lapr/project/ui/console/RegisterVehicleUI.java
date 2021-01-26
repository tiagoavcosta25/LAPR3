package lapr.project.ui.console;

import lapr.project.controller.RegisterDroneController;
import lapr.project.controller.RegisterScooterController;
import lapr.project.controller.UpdateDroneController;
import lapr.project.controller.UpdateScooterController;
import lapr.project.model.Drone;
import lapr.project.model.Pharmacy;
import lapr.project.model.Scooter;
import lapr.project.model.VehicleType;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterVehicleUI {

    private static final Logger LOGGER = Logger.getLogger(RegisterVehicleUI.class.getName());

    public void run(VehicleType oVehicleType) {
        try {
            Scanner sc = new Scanner(System.in);
            boolean flag;

            if(oVehicleType.getDesignation().equalsIgnoreCase(VehicleType.SCOOTER.getDesignation()) ){
                flag = true; // true if it's a scooter
            } else if(oVehicleType.getDesignation().equalsIgnoreCase(VehicleType.DRONE.getDesignation())){
                flag = false; // false if it's a drone
            }else {
                throw new Exception();
            }

            RegisterScooterController oCtrlScooter = new RegisterScooterController();
            RegisterDroneController oCtrlDrone = new RegisterDroneController();

            List<Pharmacy> lstPharmacies = oCtrlScooter.showPharmacies();

            for (Pharmacy p : lstPharmacies) {
                System.out.println(p.toString());
            }

            System.out.println("\nChoose the Pharamcy's id: ");
            int intPharmacyID = Integer.parseInt(sc.nextLine());
            System.out.println();

            Pharmacy oPharmacy = new Pharmacy();
            for (Pharmacy p : lstPharmacies) {
                if (p.hasId(intPharmacyID)) {
                    oPharmacy = p;
                    break;
                }
            }

            if (oPharmacy.getName().equalsIgnoreCase("No name.")) {
                throw new Exception();
            }

            System.out.print("Do you want to create a new vehicle model? (Y/N): ");
            String strModelCreation = sc.nextLine();

            if(strModelCreation.equalsIgnoreCase("N")){
                System.out.print("Model designation: ");
                String strModelDesignation = sc.nextLine();
                if (flag) {
                    oCtrlScooter.setVehicleModel(strModelDesignation);
                } else {
                    oCtrlDrone.setVehicleModel(strModelDesignation);
                }
            } else{
                System.out.println("Please input the following information:\n");
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

                if (flag) {
                    oCtrlScooter.newVehicleModel(strDesignation, dblPotency, dblWeight, dblMaxPayload, intBatteryCapacity,
                            dblBatteryVoltage, dblEfficiency);
                } else {
                    oCtrlDrone.newVehicleModel(strDesignation, dblPotency, dblWeight, dblMaxPayload, intBatteryCapacity,
                            dblBatteryVoltage, dblEfficiency);
                }
            }

            if(flag){
                if (oCtrlScooter.newScooter()) {
                    if (oCtrlScooter.registersScooter()) {
                        LOGGER.log(Level.INFO, "Operation was Successfull!");
                    } else {
                        throw new Exception();
                    }
                } else {
                    throw new Exception();
                }
            } else{
                if (oCtrlDrone.newDrone()) {
                    if (oCtrlDrone.registersDrone()) {
                        LOGGER.log(Level.INFO, "Operation was Successfull!");
                    } else {
                        throw new Exception();
                    }
                } else {
                    throw new Exception();
                }
            }
        }catch (Exception e) {
            LOGGER.log(Level.WARNING, "There was a problem removing the Vehicle.");
        }
    }
}
