package lapr.project.ui.console;

import lapr.project.controller.UpdateDroneController;
import lapr.project.controller.UpdateScooterController;
import lapr.project.model.Drone;
import lapr.project.model.Pharmacy;
import lapr.project.model.Scooter;
import lapr.project.model.VehicleType;
import lapr.project.ui.Menu;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateVehicleUI {

    private static final Logger LOGGER = Logger.getLogger(UpdateVehicleUI.class.getName());

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

            UpdateScooterController oCtrlScooter = new UpdateScooterController();
            UpdateDroneController oCtrlDrone = new UpdateDroneController();

            List<Pharmacy> lstPharmacies = oCtrlScooter.showPharmacies();
            List<Scooter> lstScooters;
            List<Drone> lstDrones;

            for (Pharmacy p : lstPharmacies) {
                System.out.printf("[%d] %s\n", p.getId(), p.getName());
            }

            System.out.print("\nChoose the Pharmacy's ID: ");
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

            Menu.clear();

            if(flag){
                lstScooters = oCtrlScooter.showScootersList(oPharmacy.getEmail());

                for (Scooter s : lstScooters) {
                    System.out.printf("[%d] %s (%d%)\n", s.getId(), s.getModel().getDesignation(), s.getBatteryPerc());
                }
            } else{
                lstDrones = oCtrlDrone.showDronesList(oPharmacy.getEmail());

                for (Drone d : lstDrones) {
                    System.out.printf("[%d] %s (%d%)\n", d.getId(), d.getModel().getDesignation(), d.getBatteryPerc());
                }
            }

            System.out.print("\nChoose the Vehicle's ID: ");
            Integer intID = Integer.parseInt(sc.nextLine());
            System.out.println();

            Menu.clear();
            System.out.println("Please input the following information:\n");
            System.out.print("Designation: ");
            String strDesignation = sc.nextLine();
            System.out.print("Potency: ");
            double dblPotency = Double.parseDouble(sc.nextLine());
            System.out.print("Weight: ");
            double dblWeight = Double.parseDouble(sc.nextLine());
            System.out.print("Maximum Payload: ");
            double dblMaxPayload = Double.parseDouble(sc.nextLine());
            System.out.print("Battery Percentage: ");
            double dblBatteryPerc = Double.parseDouble(sc.nextLine());
            System.out.print("Battery Capacity: ");
            int intBatteryCapacity = Integer.parseInt(sc.nextLine());
            System.out.print("Battery Voltage: ");
            double dblBatteryVoltage = Double.parseDouble(sc.nextLine());
            System.out.print("Battery Efficiency: ");
            double dblEfficiency = Double.parseDouble(sc.nextLine());

            Menu.clear();
            if(flag){
                if (oCtrlScooter.updateScooter(intID, dblBatteryPerc, strDesignation, dblPotency, dblWeight, dblMaxPayload,
                        intBatteryCapacity, dblBatteryVoltage, dblEfficiency)) {
                    LOGGER.log(Level.INFO, "Operation was Successfull!");
                } else{
                    throw new Exception();
                }
            } else{
                if (oCtrlDrone.updateDrone(intID, dblBatteryPerc, strDesignation, dblPotency, dblWeight, dblMaxPayload,
                        intBatteryCapacity, dblBatteryVoltage, dblEfficiency)) {
                    LOGGER.log(Level.INFO, "Operation was Successfull!");
                } else{
                    throw new Exception();
                }
            }
        }catch (Exception e) {
            LOGGER.log(Level.WARNING, "There was a problem removing the Vehicle.");
        }
    }
}
