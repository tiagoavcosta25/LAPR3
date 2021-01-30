package lapr.project.ui.console;

import lapr.project.controller.ScooterInformationController;
import lapr.project.model.Battery;
import lapr.project.model.Pharmacy;
import lapr.project.model.Scooter;
import lapr.project.model.VehicleModel;
import lapr.project.ui.Menu;
import lapr.project.ui.UI;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScooterInformationUI implements UI {

    private static final Logger LOGGER = Logger.getLogger(ScooterInformationUI.class.getName());

    public void run() {
        try {
            Scanner sc = new Scanner(System.in);
            ScooterInformationController oCtrl = new ScooterInformationController();
            List<Pharmacy> lstPharmacies = oCtrl.showPharmacies();

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

            List<Scooter> lstScooters = oCtrl.showScootersList(oPharmacy.getEmail());
            for (Scooter s : lstScooters) {
                System.out.printf("[%d] %s (Battery: %.2f%%)\n", s.getId(), s.getModel().getDesignation(), s.getBatteryPerc());
            }

            System.out.print("\nChoose the Scooter's ID: ");
            Integer intScooterID = Integer.parseInt(sc.nextLine());
            System.out.println();
            Menu.clear();

            Scooter oScooter = new Scooter();

            for (Scooter s : lstScooters) {
                if (s.hasId(intScooterID)) {
                    oScooter = s;
                    break;
                }
            }

            if (oScooter.getId() == -1) {
                throw new Exception();
            }

            VehicleModel oVM = oScooter.getModel();
            Battery oBat = oVM.getBattery();

            System.out.println("Scooter Information:\n");
            System.out.printf("-ID: %s\n-Battery Percentage: %.2f%%\n-Model ID: %d\n-Model Designation: %s\nPotency: %.2f W\n" +
                    "-Weight: %.2f g\n-Max Payload: %.2f\n-Battery ID: %d\n-Battery Efficiency: %.2f%%\n-Baterry Capacity: %d\n" +
                    "Battery Voltage: %.2f V", oScooter.getId(), oScooter.getBatteryPerc(), oVM.getId(), oVM.getDesignation(),
                    oVM.getPotency(), oVM.getWeight(), oVM.getMaxPayload(), oBat.getId(), oBat.getEfficiency(), oBat.getBatteryCapacity(),
                    oBat.getBatteryVoltage());

            sc.nextLine();
            Menu.clear();

            LOGGER.log(Level.INFO,"Operation was Successfull!");
        }catch (Exception e) {
            LOGGER.log(Level.WARNING, "There was a problem showing the Scooter information");
        }
    }
}
