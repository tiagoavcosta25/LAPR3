package lapr.project.ui.console;

import lapr.project.controller.ScooterInformationController;
import lapr.project.model.Pharmacy;
import lapr.project.model.Scooter;
import lapr.project.ui.FileReader;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScooterInformationUI {

    private static final Logger LOGGER = Logger.getLogger(FileReader.class.getName());

    public void run() {
        try {
            Scanner sc = new Scanner(System.in);
            ScooterInformationController oCtrl = new ScooterInformationController();
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

            Scooter oScooter = oCtrl.getScooterInformation(intScooterID);

            System.out.println("Scooter Information:");
            System.out.print(oScooter.toString());

            LOGGER.log(Level.INFO,"Operation was Successfull!");
        }catch (Exception e) {
            LOGGER.log(Level.WARNING, "There was a problem showing the Scooter information");
        }
    }
}
