package lapr.project.controller;

import lapr.project.model.VehicleType;
import lapr.project.model.service.DeliveryRunService;

import java.util.logging.Logger;

public class RegisterPathController {

    private DeliveryRunService moServ;

    public RegisterPathController() {
        this.moServ = new DeliveryRunService();
    }

    public boolean registerPath(double dblLatitudeA, double dblLongitudeA, double dblLatitudeB, double dblLongitudeB,
                                String strName, double dblWindSpeed, double dblWindAngle,
                                double dblKineticFrictionCoefficient, VehicleType oVehicleType) {
        return moServ.registerPath(dblLatitudeA, dblLongitudeA, dblLatitudeB, dblLongitudeB,
                strName, dblWindSpeed, dblWindAngle, dblKineticFrictionCoefficient, oVehicleType);
    }
}
