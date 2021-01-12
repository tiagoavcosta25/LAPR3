package lapr.project.controller;

import lapr.project.data.ProductDB;
import lapr.project.utils.ValidationProduct;

public class RemoveProductController {

    private ProductDB pr;

    public RemoveProductController(String jdbcUrl, String username, String password) {
        this.pr = new ProductDB(jdbcUrl, username, password);
    }

    public boolean verifyProductId(int intId) {
        return ValidationProduct.validateId(intId);
    }

    public boolean removeProductFromDB(int intId) {
        if(!verifyProductId(intId)) return false;
        return pr.removeProductFromDB(intId);
    }
}
