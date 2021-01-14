package lapr.project.controller;

import lapr.project.model.Product;
import lapr.project.model.UserSession;
import lapr.project.model.service.ProductService;

public class ProductInformationController {

    private ProductService pServ;

    public ProductInformationController() {
        this.pServ = new ProductService();
    }

    public Product getProduct(int intId) {
        if(ApplicationPOT.getInstance().getCurrentSession().getRole().equals(UserSession.Role.ADMIN))
            return pServ.getProduct(intId);
        return null;
    }
}
