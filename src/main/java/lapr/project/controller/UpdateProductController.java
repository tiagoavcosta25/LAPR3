package lapr.project.controller;

import lapr.project.data.ProductDB;
import lapr.project.model.Product;
import lapr.project.utils.ValidationProduct;

public class UpdateProductController {

    private ProductDB pr;

    public UpdateProductController(String jdbcUrl, String username, String password) {
        this.pr = new ProductDB(jdbcUrl, username, password);
    }

    private Product validateInput(int intId, String strName, String strDescription, float fltUnitaryPrice, float fltUnitaryWeight) {
        return ValidationProduct.validateInputWithId(intId, strName, strDescription, fltUnitaryPrice, fltUnitaryWeight);
    }

    public boolean updateProduct(int intId, String strName, String strDescription, float fltUnitaryPrice, float fltUnitaryWeight) {
        Product validatedProduct = validateInput(intId, strName, strDescription, fltUnitaryPrice, fltUnitaryWeight);
        if(validatedProduct == null) return false;
        return pr.updateProductFromDB(intId, strName, strDescription, fltUnitaryPrice, fltUnitaryWeight);
    }
}
