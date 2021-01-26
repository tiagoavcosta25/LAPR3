package lapr.project.ui.console;
/*
import lapr.project.controller.RemoveDroneController;
import lapr.project.model.Drone;
import lapr.project.model.Pharmacy;
import lapr.project.ui.FileReader;
import lapr.project.ui.UI;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RemoveDroneUI implements UI {

    private static final Logger LOGGER = Logger.getLogger(RemoveDroneUI.class.getName());

    public void run() {
        try {
            Scanner sc = new Scanner(System.in);
            RemoveDroneController oCtrl = new RemoveDroneController();
            List<Pharmacy> lstPharmacies = oCtrl.showPharmacies();

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

            List<Drone> lstDrones = oCtrl.showDronesList(oPharmacy.getEmail());
            for (Drone d : lstDrones) {
                System.out.println(d.toString());
            }

            System.out.println("\nChoose the Drone's ID: ");
            Integer intDroneID = Integer.parseInt(sc.nextLine());
            System.out.println();

            for (Drone d : lstDrones) {
                if (d.hasId(intDroneID)) {
                    break;
                }
            }

            if (oCtrl.removeDrone(intDroneID)) {
                LOGGER.log(Level.INFO,"Operation was Successfull!");
            } else LOGGER.log(Level.WARNING, "There was a problem removing the Scooter");
        }catch (Exception e) {
            LOGGER.log(Level.WARNING, "There was a problem removing the Scooter");
        }
    }
}*/
