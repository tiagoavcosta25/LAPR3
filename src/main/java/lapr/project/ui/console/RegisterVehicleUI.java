package lapr.project.ui.console;

import lapr.project.controller.RegisterDroneController;
import lapr.project.controller.RegisterScooterController;
import lapr.project.model.Pharmacy;
import lapr.project.model.VehicleType;
import lapr.project.ui.Menu;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterVehicleUI {

    private static final Logger LOGGER = Logger.getLogger(RegisterVehicleUI.class.getName());
    private static Scanner sc = new Scanner(System.in);

    public void run(VehicleType oVehicleType) {
        try {
            RegisterScooterController oCtrlScooter = new RegisterScooterController();
            RegisterDroneController oCtrlDrone = new RegisterDroneController();
            boolean flag;

            if(oVehicleType.getDesignation().equalsIgnoreCase(VehicleType.SCOOTER.getDesignation()) ){
                flag = true; // true if it's a scooter
            } else if(oVehicleType.getDesignation().equalsIgnoreCase(VehicleType.DRONE.getDesignation())){
                flag = false; // false if it's a drone
            }else {
                throw new Exception();
            }

            Pharmacy oPharmacy = choosePharmacy(oCtrlScooter);
            Menu.clear();

            if (oPharmacy.getName().equalsIgnoreCase("No name.")) {
                throw new Exception();
            }

            System.out.print("Do you want to create a new vehicle model? (Y/N): ");
            String strModelCreation = sc.nextLine();
            Menu.clear();

            if(strModelCreation.equalsIgnoreCase("N")){
                System.out.print("Model designation: ");
                String strModelDesignation = sc.nextLine();
                if (flag) {
                    oCtrlScooter.setPharmacy(oPharmacy.getEmail());
                    oCtrlScooter.setVehicleModel(strModelDesignation);
                } else {
                    oCtrlDrone.setPharmacy(oPharmacy.getEmail());
                    oCtrlDrone.setVehicleModel(strModelDesignation);
                }
            } else{
                oCtrlScooter.setPharmacy(oPharmacy.getEmail());
                oCtrlDrone.setPharmacy(oPharmacy.getEmail());
                createVehicleModel(flag, oCtrlScooter, oCtrlDrone);
            }
            Menu.clear();

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
            LOGGER.log(Level.WARNING, "There was a problem registering the Vehicle.");
        }
    }

    public static Pharmacy choosePharmacy(RegisterScooterController oCtrlScooter){
        List<Pharmacy> lstPharmacies = oCtrlScooter.showPharmacies();

        for (Pharmacy p : lstPharmacies) {
            System.out.printf("[%d] %s\n", p.getId(), p.getName());
        }

        System.out.print("\nChoose the Pharamcy's id: ");
        int intPharmacyID = Integer.parseInt(sc.nextLine());
        System.out.println();

        Pharmacy oPharmacy = new Pharmacy();
        for (Pharmacy p : lstPharmacies) {
            if (p.hasId(intPharmacyID)) {
                oPharmacy = p;
                break;
            }
        }

        return oPharmacy;
    }

    public static void createVehicleModel(boolean flag, RegisterScooterController oCtrlScooter, RegisterDroneController oCtrlDrone){
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
}
