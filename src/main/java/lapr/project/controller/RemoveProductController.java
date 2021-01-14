package lapr.project.controller;

import lapr.project.model.UserSession;
import lapr.project.model.service.ProductService;

public class RemoveProductController {

    private ProductService pServ;

    public RemoveProductController() {
        this.pServ = new ProductService();
    }

    public boolean removeProductFromDB(int intId) {
        if(ApplicationPOT.getInstance().getCurrentSession().getRole().equals(UserSession.Role.ADMIN))
            return pServ.removeProduct(intId);
        return false;
    }
}
