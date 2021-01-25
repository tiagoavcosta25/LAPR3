package lapr.project.ui.console;

import lapr.project.controller.RemoveScooterController;
import lapr.project.model.Pharmacy;
import lapr.project.model.Scooter;
import lapr.project.utils.FileReader;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RemoveScooterUI {

    private static final Logger LOGGER = Logger.getLogger(FileReader.class.getName());

    public void run() throws Exception {
        try {
            Scanner sc = new Scanner(System.in);
            RemoveScooterController oCtrl = new RemoveScooterController();
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

            List<Scooter> lstScooters = oCtrl.showScootersList(oPharmacy.getEmail());
            for (Scooter s : lstScooters) {
                System.out.println(s.toString());
            }

            System.out.println("\nChoose the Scooter's ID: ");
            Integer intScooterID = Integer.parseInt(sc.nextLine());
            System.out.println();

            for (Scooter s : lstScooters) {
                if (s.hasId(intScooterID)) {
                    break;
                }
            }

            if (oCtrl.removeScooter(intScooterID)) {
                LOGGER.log(Level.INFO,"Operation was Successfull!");
            } else LOGGER.log(Level.WARNING, "There was a problem removing the Scooter");
        }catch (Exception e) {
            LOGGER.log(Level.WARNING, "There was a problem removing the Scooter");
        }
    }
}
