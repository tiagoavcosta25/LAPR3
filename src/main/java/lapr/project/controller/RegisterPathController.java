package lapr.project.controller;

import lapr.project.model.service.DeliveryRunService;

import java.util.logging.Logger;

public class RegisterPathController {

    private static final Logger LOGGER = Logger.getLogger(RegisterPathController.class.getName());

    private DeliveryRunService moServ;

    public RegisterPathController() {
        this.moServ = new DeliveryRunService();
    }

    public boolean registerPath(double dblLatitudeA, double dblLongitudeA, double dblLatitudeB, double dblLongitudeB,
                                String strName, double dblWindSpeed, double dblWindAngle,
                                double dblKineticFrictionCoefficient) {
        return moServ.registerPath(dblLatitudeA, dblLongitudeA, dblLatitudeB, dblLongitudeB,
                strName, dblWindSpeed, dblWindAngle, dblKineticFrictionCoefficient);
    }
}
