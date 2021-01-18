package lapr.project.controller;

import lapr.project.model.UserSession;
import lapr.project.model.service.ProductService;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterProductController {

    private static final Logger LOGGER = Logger.getLogger(RegisterProductController.class.getName());

    private ProductService pServ;

    public RegisterProductController() {
        this.pServ = new ProductService();
    }

    public boolean registerProduct(String strName, String strDescription, float fltUnitaryPrice, float fltUnitaryWeight) {
        try {
            if(ApplicationPOT.getInstance().getCurrentSession().getRole().equals(UserSession.Role.ADMIN))
                return pServ.registerProduct(strName, strDescription, fltUnitaryPrice, fltUnitaryWeight);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "User not logged in!");
        }
        return false;
    }
}
