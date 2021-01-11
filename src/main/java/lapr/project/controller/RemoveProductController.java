package lapr.project.controller;

import lapr.project.model.Platform;
import lapr.project.data.registration.ProductRegistration;
import lapr.project.utils.ValidationProduct;

public class RemoveProductController {

    private final ApplicationPOT app;
    private final Platform plat;
    private ProductRegistration pr;

    public RemoveProductController() {
        this.app = ApplicationPOT.getInstance();
        this.plat = app.getPlatform();
        this.pr = plat.getProductReg();
    }

    public boolean verifyProductId(int intId) {
        return ValidationProduct.validateId(intId);
    }

    public boolean removeProductFromDB(int intId) {
        if(!verifyProductId(intId)) return false;
        return pr.removeProductFromDB(intId);
    }
}
