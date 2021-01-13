package lapr.project.controller;

import lapr.project.model.service.ProductService;

public class RemoveProductController {

    private ProductService pServ;

    public RemoveProductController() {
        this.pServ = new ProductService();
    }

    public boolean removeProductFromDB(int intId) {
        return pServ.removeProduct(intId);
    }
}
