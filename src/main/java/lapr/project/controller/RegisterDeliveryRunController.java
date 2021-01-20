package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.service.DeliveryRunService;
import lapr.project.model.service.OrderService;
import lapr.project.model.service.PharmacyService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterDeliveryRunController {

    private static final Logger LOGGER = Logger.getLogger(RegisterDeliveryRunController.class.getName());
    private DeliveryRunService moDeliveryRunService;
    private PharmacyService moPharmacyService;

    public RegisterDeliveryRunController() {
        moDeliveryRunService = new DeliveryRunService();
        moPharmacyService = new PharmacyService();
    }

    public boolean registerDeliveryRun(List<Order> lstOrder){
        if (ApplicationPOT.getInstance().getCurrentSession().getRole().equals(UserSession.Role.ADMIN)) {
            Courier courier = moPharmacyService.getSuitableCourier();
            DeliveryRun deliveryRun = moDeliveryRunService.newDeliveryRun(courier,lstOrder);
            return moDeliveryRunService.addNewDeliveryRun(deliveryRun);
        }else{
            LOGGER.log(Level.WARNING,"User NOT Authorized!");
            return false;
        }
    }
}
