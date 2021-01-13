package lapr.project.controller;

import lapr.project.model.Product;
import lapr.project.model.service.ProductService;

public class ProductInformationController {

    private ProductService pServ;

    public ProductInformationController() {
        this.pServ = new ProductService();
    }

    public Product getProduct(int intId) {
        return pServ.getProduct(intId);
    }
}
