package lapr.project.ui;

import lapr.project.model.Product;
import lapr.project.data.registration.ProductRegistration;

public class ProductInformationUI {
    public static void main(String[] args) {
        ProductRegistration pr = new ProductRegistration("jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_G21", "g21rumoAo20");
        Product p = pr.getProductFromBD(1);
        System.out.println(p.getId());
    }
}
