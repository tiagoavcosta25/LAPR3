package lapr.project.controller;

import lapr.project.model.UserSession;
import lapr.project.model.service.ProductService;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RemoveProductController {

    private static final Logger LOGGER = Logger.getLogger(RemoveProductController.class.getName());

    private ProductService moServ;

    public RemoveProductController() {
        this.moServ = new ProductService();
    }

    public boolean removeProductFromDB(String strName) {
        try {
            if(ApplicationPOT.getInstance().getCurrentSession().getRole().equals(UserSession.Role.ADMIN))
                return moServ.removeProduct(strName);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "User not logged in!");
        }
        return false;
    }

    public ProductService getMoServ() {
        return moServ;
    }

    public void setMoServ(ProductService moServ) {
        this.moServ = moServ;
    }
}
