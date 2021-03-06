package lapr.project.ui.console;

import lapr.project.controller.AddPharmacyProductController;
import lapr.project.model.Product;
import lapr.project.ui.Menu;
import lapr.project.ui.UI;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddPharmacyProductUI implements UI {
    private static final Logger LOGGER = Logger.getLogger(LoginUI.class.getName());

    public void run(){
        AddPharmacyProductController oCtrl = new AddPharmacyProductController();
        Scanner sc = new Scanner(System.in);

        do{
            try{
                System.out.print("Add Stock to Pharmacy:\n\nPharmacy's Email: ");
                String strEmail = sc.nextLine();
                Menu.clear();

                List<Product> lstProducts = oCtrl.getProducts();

                for(Product p : lstProducts){
                    System.out.printf("[%d] %s (%.2f€)\n", p.getId(), p.getName(), p.getUnitaryPrice());
                }

                System.out.print("\nChoose the Product's Id: ");
                int intProductId = Integer.parseInt(sc.nextLine());
                System.out.println();

                Menu.clear();
                if(intProductId == 0){
                    throw new Exception();
                }

                Product oProduct = new Product();
                Integer intQuantity = 0;
                for(Product p : lstProducts){
                    if (p.hasId(intProductId)){
                        oProduct = p;
                        break;
                    }
                }

                System.out.print("\nChoose the Product's Added Stock: ");
                intQuantity = Integer.parseInt(sc.nextLine());
                System.out.println();
                Menu.clear();

                if(oProduct.getName().equalsIgnoreCase("") || intQuantity <= 0){
                    throw new Exception();
                }

                if(oCtrl.addPharmacyProduct(strEmail, oProduct, intQuantity)) {
                    if(oCtrl.registerPharmacyProduct()) {
                        LOGGER.log(Level.INFO, "Stock added with success.");
                        break;
                    } else LOGGER.log(Level.WARNING, "Error Adding Stock to the Pharmacy.");
                } else LOGGER.log(Level.WARNING, "Error Adding Stock to the Pharmacy.");
            } catch (Exception e){
                LOGGER.log(Level.WARNING, "Error Adding Stock to the Pharmacy.");
            }
        } while(true);
    }
}
