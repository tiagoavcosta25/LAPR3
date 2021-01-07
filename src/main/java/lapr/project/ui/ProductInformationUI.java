package lapr.project.ui;

import lapr.project.model.registration.ProductRegistration;

public class ProductInformationUI {
    public static void main(String[] args) {
        ProductRegistration pr = new ProductRegistration("jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_G21", "g21rumoAo20");
        pr.getProductFromBD(2);
    }
}
