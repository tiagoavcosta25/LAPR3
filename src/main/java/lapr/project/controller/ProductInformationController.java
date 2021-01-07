package lapr.project.controller;

import lapr.project.model.Platform;
import lapr.project.model.Product;
import lapr.project.model.registration.ProductRegistration;

public class ProductInformationController {

    private final ApplicationPOT app;
    private final Platform plat;
    private final ProductRegistration pr;

    public ProductInformationController() {
        this.app = ApplicationPOT.getInstance();
        this.plat = app.getPlatform();
        this.pr = plat.getProductReg();
    }

    public Product getProduct(int productId) {
        return pr.getProductFromBD(productId);
    }
}
