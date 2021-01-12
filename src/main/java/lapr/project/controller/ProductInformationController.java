package lapr.project.controller;

import lapr.project.model.Product;
import lapr.project.data.ProductDB;
import lapr.project.utils.ValidationProduct;

public class ProductInformationController {

    private ProductDB pr;

    public ProductInformationController(String jdbcUrl, String username, String password) {
        this.pr = new ProductDB(jdbcUrl, username, password);
    }

    public boolean verifyProductId(int intId) {
        return ValidationProduct.validateId(intId);
    }

    public Product getProductFromDB(int intId) {
        if(!verifyProductId(intId)) return null;
        return pr.getProductFromBD(intId);
    }
}
