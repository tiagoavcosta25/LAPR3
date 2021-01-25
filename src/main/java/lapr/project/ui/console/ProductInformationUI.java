package lapr.project.ui.console;

import lapr.project.controller.ProductInformationController;
import lapr.project.model.Product;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductInformationUI {

    private static final Logger LOGGER = Logger.getLogger(ProductInformationUI.class.getName());

    public void run() {
        ProductInformationController oCtrl = new ProductInformationController();
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Product Information:\n\nName: ");
            String strName = sc.nextLine();
            Product oProduct = oCtrl.getProduct(strName);
            System.out.printf("Product \"%s\" information: %s%n",strName,oProduct.toString());
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "The input may not be in the correct format");
        }
    }
}
