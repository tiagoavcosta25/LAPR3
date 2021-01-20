package lapr.project.controller;

import lapr.project.model.Product;
import lapr.project.model.UserSession;
import lapr.project.model.service.ProductService;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductInformationController {

    private static final Logger LOGGER = Logger.getLogger(ProductInformationController.class.getName());

    private ProductService moServ;

    public ProductInformationController() {
        this.moServ = new ProductService();
    }

    public Product getProduct(int intId) {
        try {
            if(ApplicationPOT.getInstance().getCurrentSession().getRole().equals(UserSession.Role.ADMIN))
                return moServ.getProduct(intId);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "User not logged in!");
        }
        return null;
    }
}
