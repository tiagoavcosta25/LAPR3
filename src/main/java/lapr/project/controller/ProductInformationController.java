package lapr.project.controller;

import lapr.project.model.Platform;
import lapr.project.model.Product;
import lapr.project.data.registration.ProductRegistration;

import static java.lang.Integer.parseInt;

public class ProductInformationController {

    private final ApplicationPOT app;
    private final Platform plat;
    private ProductRegistration pr;

    public ProductInformationController() {
        this.app = ApplicationPOT.getInstance();
        this.plat = app.getPlatform();
        this.pr = plat.getProductReg();
    }

    public boolean verifyProductId(String productId) {
        return productId != null && !productId.equals("") && productId.matches("^[0-9]*$") && parseInt(productId) > 0;
    }

    public Product getProductFromDB(int productId) {
        return pr.getProductFromBD(productId);
    }
}
