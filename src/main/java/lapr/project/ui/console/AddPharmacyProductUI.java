package lapr.project.ui.console;

import lapr.project.controller.AddPharmacyProductController;
import lapr.project.controller.RegisterProductController;
import lapr.project.model.Product;

import java.util.List;

public class AddPharmacyProductUI {
    public void run(){
        try{
            AddPharmacyProductController oCtrl = new AddPharmacyProductController();

            List<Product> lstProds = oCtrl.getProducts();

            Product oProduct = lstProds.get(1);

            oCtrl.addPharmacyProduct("info@pharmacy1.com", oProduct, 10);

            oCtrl.registerPharmacyProduct();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
