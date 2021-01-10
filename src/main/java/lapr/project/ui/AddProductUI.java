package lapr.project.ui;

import lapr.project.model.Product;
import lapr.project.data.registration.ProductRegistration;

public class AddProductUI {
    public static void main(String[] args) {
        ProductRegistration pr = new ProductRegistration("jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_G21", "g21rumoAo20");
        Product p = new Product("Produto 1","Descrição 1",3.0f,4.0f);
        pr.addProductToDB(p);
    }
}
