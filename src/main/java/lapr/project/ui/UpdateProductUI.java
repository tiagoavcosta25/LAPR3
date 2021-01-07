package lapr.project.ui;

import lapr.project.model.registration.ProductRegistration;

public class UpdateProductUI {
    public static void main(String[] args) {
        ProductRegistration pr = new ProductRegistration("jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_G21", "g21rumoAo20");
        pr.updateProductFromDB(2,"Name Update","Description Update",69.0f,69.0f);
    }
}
