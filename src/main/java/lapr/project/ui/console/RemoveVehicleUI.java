package lapr.project.ui.console;

import lapr.project.controller.RemoveDroneController;
import lapr.project.controller.RemoveScooterController;
import lapr.project.model.Drone;
import lapr.project.model.Pharmacy;
import lapr.project.model.Scooter;
import lapr.project.model.VehicleType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RemoveVehicleUI {

    private static final Logger LOGGER = Logger.getLogger(RemoveVehicleUI.class.getName());

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

            RemoveScooterController oCtrlScooter = new RemoveScooterController();
            RemoveDroneController oCtrlDrone = new RemoveDroneController();

            List<Pharmacy> lstPharmacies = oCtrlScooter.showPharmacies();
            List<Scooter> lstScooters;
            List<Drone> lstDrones;

            for (Pharmacy p : lstPharmacies) {
                System.out.println(p.toString());
            }

            System.out.println("\nChoose the Pharamcies's Email: ");
            String strPharmacyEmail = sc.nextLine();
            System.out.println();

            Pharmacy oPharmacy = new Pharmacy();
            for (Pharmacy p : lstPharmacies) {
                if (p.hasEmail(strPharmacyEmail)) {
                    oPharmacy = p;
                    break;
                }
            }

            if (oPharmacy.getName().equalsIgnoreCase("No name.")) {
                throw new Exception();
            }

            if(flag){
                lstScooters = oCtrlScooter.showScootersList(oPharmacy.getEmail());

                for (Scooter s : lstScooters) {
                    System.out.println(s.toString());
                }
            } else{
                lstDrones = oCtrlDrone.showDronesList(oPharmacy.getEmail());

                for (Drone d : lstDrones) {
                    System.out.println(d.toString());
                }
            }

            System.out.println("\nChoose the Vehicle's ID: ");
            Integer intID = Integer.parseInt(sc.nextLine());
            System.out.println();

            if(flag){
                if (oCtrlScooter.removeScooter(intID)) {
                    LOGGER.log(Level.INFO, "Operation was Successfull!");
                } else{
                    throw new Exception();
                }
            } else{
                if (oCtrlDrone.removeDrone(intID)) {
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
