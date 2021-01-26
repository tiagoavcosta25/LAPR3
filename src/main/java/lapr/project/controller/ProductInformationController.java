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

    public Product getProduct(String strName) {
        try {
            if(ApplicationPOT.getInstance().getCurrentSession().getRole().equals(UserSession.Role.ADMIN))
                return moServ.getProduct(strName);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "User not logged in!");
        }
        return null;
    }

    public ProductService getMoServ() {
        return moServ;
    }

    public void setMoServ(ProductService moServ) {
        this.moServ = moServ;
    }
}
