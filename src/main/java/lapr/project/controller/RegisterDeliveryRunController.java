package lapr.project.controller;

import javafx.util.Pair;
import lapr.project.model.*;
import lapr.project.model.service.DeliveryRunService;
import lapr.project.model.service.GraphService;
import lapr.project.model.service.OrderService;
import lapr.project.model.service.PharmacyService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterDeliveryRunController {

    private static final Logger LOGGER = Logger.getLogger(RegisterDeliveryRunController.class.getName());
    private DeliveryRunService moDeliveryRunService;
    private PharmacyService moPharmacyService;
    private GraphService moGraphService;

    public RegisterDeliveryRunController() {
        moDeliveryRunService = new DeliveryRunService();
        moPharmacyService = new PharmacyService();
        moGraphService = ApplicationPOT.getInstance().getWorldMap();
    }

    public List<Order> getOrdersList(String strPharmacyEmail) {
        return moPharmacyService.getOrdersByPharmacyEmail(strPharmacyEmail);
    }

    public boolean registerDeliveryRun(List<Order> lstOrder) {
        if (ApplicationPOT.getInstance().getCurrentSession().getRole().equals(UserSession.Role.ADMIN)) {
            List<Pair<Pair<VehicleModel, Double>, List<Address>>> lstPair = moGraphService.calculateBestVehicleAndBestPath(lstOrder);
            VehicleModel oModel = moDeliveryRunService.getMostEfficientVehicleModel(lstPair);

            if (oModel == null) {
                LOGGER.log(Level.WARNING, "The Delivery Run cannot be carried out!");
                return false;
            }

            Courier courier = null;
            Vehicle oVehicle;
            if (oModel.getVehicleType().equals(VehicleType.SCOOTER)) {
                courier = moPharmacyService.getSuitableCourier();
                oVehicle = moDeliveryRunService.getMostChargedScooter(oModel);
            } else {
                oVehicle = moDeliveryRunService.getMostChargedDrone(oModel);
            }
            DeliveryRun deliveryRun = moDeliveryRunService.newDeliveryRun(courier, lstOrder, oVehicle);
            return moDeliveryRunService.addNewDeliveryRun(deliveryRun);
        } else {
            LOGGER.log(Level.WARNING, "User NOT Authorized!");
            return false;
        }
    }
}
