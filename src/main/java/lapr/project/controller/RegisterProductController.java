package lapr.project.controller;

import lapr.project.model.Product;
import lapr.project.data.ProductDB;
import lapr.project.utils.ValidationProduct;

public class RegisterProductController {

    private ProductDB pr;

    public RegisterProductController(String jdbcUrl, String username, String password) {
        this.pr = new ProductDB(jdbcUrl, username, password);
    }

    public RegisterProductController() {
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
