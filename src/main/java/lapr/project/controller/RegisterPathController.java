package lapr.project.controller;

import lapr.project.model.VehicleType;
import lapr.project.model.service.DeliveryRunService;

/**
 * Register Path Controller.
 *
 * Group: Team Lisa [G-021]
 * ______________________________________________________
 * @author António Barbosa <1190404@isep.ipp.pt>
 * @author Ernesto Rodrigues <1190560@isep.ipp.pt>
 * @author Jessica Alves <1190682@isep.ipp.pt>
 * @author Pedro Santos <1190967@isep.ipp.pt>
 * @author Rodrigo Costa <1191014@isep.ipp.pt>
 * @author Tiago Costa <1191460@isep.ipp.pt>
 */

public class RegisterPathController {

    /**
     * Delivery Run Management class.
     */
    private DeliveryRunService moServ;

    /**
     * An empty constructor of RegisterPathController.
     */
    public RegisterPathController() {
        this.moServ = new DeliveryRunService();
    }

    /**
     * Returns the Delivery Run Service.
     * @return the Delivery Run Service.
     */
    public DeliveryRunService getDeliveryRunService() {
        return moServ;
    }

    /**
     * Modifies the Delivery Run Service.
     * @param oDeliveryRunService the Delivery Run Service.
     */
    public void setDeliveryRunService(DeliveryRunService oDeliveryRunService) {
        this.moServ = oDeliveryRunService;
    }

    /**
     * Registers a Path.
     * @param dblLatitudeA First Address Latitude.
     * @param dblLongitudeA First Address Longitude.
     * @param dblLatitudeB Second Address Latitude.
     * @param dblLongitudeB Second Address Longitude.
     * @param strName Path's Name.
     * @param dblWindSpeed Path's Wind Speed.
     * @param dblWindAngle Path's Wind Angle.
     * @param dblKineticFrictionCoefficient Path's Kinetic Friction Coefficient.
     * @param oVehicleType Path's Vehicle Type.
     * @return true if the Park is registered. False if otherwise.
     */
    public boolean registerPath(double dblLatitudeA, double dblLongitudeA, double dblLatitudeB, double dblLongitudeB,
                                String strName, double dblWindSpeed, double dblWindAngle,
                                double dblKineticFrictionCoefficient, VehicleType oVehicleType) {
        return moServ.registerPath(dblLatitudeA, dblLongitudeA, dblLatitudeB, dblLongitudeB,
                strName, dblWindSpeed, dblWindAngle, dblKineticFrictionCoefficient, oVehicleType);
    }
}
