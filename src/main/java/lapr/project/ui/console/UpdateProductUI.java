package lapr.project.ui.console;

import lapr.project.controller.UpdateProductController;
import lapr.project.ui.UI;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateProductUI implements UI {
    private static final Logger LOGGER = Logger.getLogger(UpdateProductUI.class.getName());

    public void run() {
        UpdateProductController oCtrl = new UpdateProductController();
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.print("Update a Product:\n\nCurrent Name: ");
                String strProductName = sc.nextLine();
                System.out.print("Nome: ");
                String strName = sc.nextLine();
                System.out.print("Description: ");
                String strDescription = sc.nextLine();
                System.out.print("Unitary Price: ");
                Double fltUnitaryPrice = Double.parseDouble(sc.nextLine());
                System.out.print("Unitary Weight: ");
                Double fltUnitaryWeight = Double.parseDouble(sc.nextLine());

                if (oCtrl.updateProduct(strProductName, strName, strDescription, fltUnitaryPrice, fltUnitaryWeight)) {
                    LOGGER.log(Level.INFO, "Product Update with success.");
                    break;
                }
                LOGGER.log(Level.WARNING, "Error updating a Product.");
            } catch (Exception e) {
                LOGGER.log(Level.WARNING, "Error updating a Product.");
            }
        } while (true);
    }

}
