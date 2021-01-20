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

    public boolean removeProductFromDB(int intId) {
        try {
            if(ApplicationPOT.getInstance().getCurrentSession().getRole().equals(UserSession.Role.ADMIN))
                return moServ.removeProduct(intId);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "User not logged in!");
        }
        return false;
    }
}
