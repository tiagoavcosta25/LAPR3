package lapr.project.controller;

import lapr.project.model.Platform;
import lapr.project.model.Product;
import lapr.project.data.registration.ProductRegistration;
import lapr.project.utils.ValidationProduct;

public class RegisterProductController {

    private final ApplicationPOT app;
    private final Platform plat;
    private ProductRegistration pr;

    public RegisterProductController() {
        this.app = ApplicationPOT.getInstance();
        this.plat = app.getPlatform();
        this.pr = plat.getProductReg();
    }

    private Product validateInput(String strName, String strDescription, float fltUnitaryPrice, float fltUnitaryWeight) {
        return ValidationProduct.validateInput(strName, strDescription, fltUnitaryPrice, fltUnitaryWeight);
    }

    public boolean registerProductToDB(String strName, String strDescription, float fltUnitaryPrice, float fltUnitaryWeight) {
        Product validatedProduct = validateInput(strName, strDescription, fltUnitaryPrice, fltUnitaryWeight);
        if(validatedProduct == null) return false;
        return pr.addProductToDB(validatedProduct);
    }
}
