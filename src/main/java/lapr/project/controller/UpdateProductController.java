package lapr.project.controller;

import lapr.project.model.Platform;
import lapr.project.data.registration.ProductRegistration;
import lapr.project.model.Product;
import lapr.project.utils.ValidationProduct;

public class UpdateProductController {

    private final ApplicationPOT app;
    private final Platform plat;
    private ProductRegistration pr;

    public UpdateProductController() {
        this.app = ApplicationPOT.getInstance();
        this.plat = app.getPlatform();
        setProductRegistration(plat.getProductReg());
    }

    private Product validateInput(int intId, String strName, String strDescription, float fltUnitaryPrice, float fltUnitaryWeight) {
        return ValidationProduct.validateInputWithId(intId, strName, strDescription, fltUnitaryPrice, fltUnitaryWeight);
    }

    public boolean updateProduct(int intId, String strName, String strDescription, float fltUnitaryPrice, float fltUnitaryWeight) {
        Product validatedProduct = validateInput(intId, strName, strDescription, fltUnitaryPrice, fltUnitaryWeight);
        if(validatedProduct == null) return false;
        return pr.updateProductFromDB(intId, strName, strDescription, fltUnitaryPrice, fltUnitaryWeight);
    }

    public void setProductRegistration(ProductRegistration pr) {
        this.pr = pr;
    }
}
