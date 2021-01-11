package lapr.project.controller;

import lapr.project.model.Platform;
import lapr.project.model.Product;
import lapr.project.data.registration.ProductRegistration;
import lapr.project.utils.ValidationProduct;

public class ProductInformationController {

    private final ApplicationPOT app;
    private final Platform plat;
    private ProductRegistration pr;

    public ProductInformationController() {
        this.app = ApplicationPOT.getInstance();
        this.plat = app.getPlatform();
        this.pr = plat.getProductReg();
    }

    private boolean verifyProductId(int intId) {
        return ValidationProduct.validateId(intId);
    }

    public Product getProductFromDB(int intId) {
        if(!verifyProductId(intId)) return null;
        return pr.getProductFromBD(intId);
    }
}
