package lapr.project.controller;

import lapr.project.model.UserSession;
import lapr.project.model.service.ProductService;

public class RegisterProductController {

    private ProductService pServ;

    public RegisterProductController() {
        this.pServ = new ProductService();
    }

    public boolean registerProduct(String strName, String strDescription, float fltUnitaryPrice, float fltUnitaryWeight) {
        if(ApplicationPOT.getInstance().getCurrentSession().getRole().equals(UserSession.Role.ADMIN))
            return pServ.registerProduct(strName, strDescription, fltUnitaryPrice, fltUnitaryWeight);
        else
            return false;
    }
}
