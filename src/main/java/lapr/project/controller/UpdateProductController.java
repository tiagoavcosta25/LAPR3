package lapr.project.controller;

import lapr.project.model.UserSession;
import lapr.project.model.service.ProductService;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateProductController {

    private static final Logger LOGGER = Logger.getLogger(UpdateProductController.class.getName());

    private ProductService pServ;

    public UpdateProductController() {
        this.pServ = new ProductService();
    }

    public boolean updateProduct(int intId, String strName, String strDescription, float fltUnitaryPrice, float fltUnitaryWeight) {
        try {
            if(ApplicationPOT.getInstance().getCurrentSession().getRole().equals(UserSession.Role.ADMIN))
                return pServ.updateProduct(intId, strName, strDescription, fltUnitaryPrice, fltUnitaryWeight);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "User not logged in!");
        }
        return false;
    }
}
