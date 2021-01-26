package lapr.project.controller;

import lapr.project.model.UserSession;
import lapr.project.model.service.ProductService;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateProductController {

    private static final Logger LOGGER = Logger.getLogger(UpdateProductController.class.getName());

    private ProductService moServ;

    public UpdateProductController() {
        this.moServ = new ProductService();
    }

    public boolean updateProduct(String strProductName, String strName, String strDescription, Double fltUnitaryPrice, Double fltUnitaryWeight) {
        try {
            if(ApplicationPOT.getInstance().getCurrentSession().getRole().equals(UserSession.Role.ADMIN))
                return moServ.updateProduct(strProductName, strName, strDescription, fltUnitaryPrice, fltUnitaryWeight);
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
