package lapr.project.controller;

import lapr.project.model.UserSession;
import lapr.project.model.service.ProductService;

public class UpdateProductController {

    private ProductService pServ;

    public UpdateProductController() {
        this.pServ = new ProductService();
    }

    public boolean updateProduct(int intId, String strName, String strDescription, float fltUnitaryPrice, float fltUnitaryWeight) {
        if(ApplicationPOT.getInstance().getCurrentSession().getRole().equals(UserSession.Role.ADMIN))
            return pServ.updateProduct(intId, strName, strDescription, fltUnitaryPrice, fltUnitaryWeight);
        else
            return false;
    }
}
