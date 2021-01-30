package lapr.project.controller;

import javafx.util.Pair;
import lapr.project.model.*;
import lapr.project.model.service.DeliveryRunService;
import lapr.project.model.service.GraphService;
import lapr.project.model.service.PharmacyService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Register Delivery Run Controller.
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
public class RegisterDeliveryRunController {

    /**
     * Logger which is used to generate warnings or information, with
     * a custom message
     */
    private static final Logger LOGGER = Logger.getLogger(RegisterDeliveryRunController.class.getName());

    /**
     * DeliveryRun Service
     */
    private DeliveryRunService moDeliveryRunService;

    /**
     * Pharmacy Service
     */
    private PharmacyService moPharmacyService;

    /**
     * Graph Service
     */
    private GraphService moGraphService;

    /**
     * Empty constructor of RegisterDeliveryRunController, which instantiates a new
     * DeliveryRunService, PharmacyService and GraphService
     */
    public RegisterDeliveryRunController() {
        moDeliveryRunService = new DeliveryRunService();
        moPharmacyService = new PharmacyService();
        moGraphService = ApplicationPOT.getInstance().getWorldMap();
    }

    /**
     * Returns the DeliveryRun Service
     *
     * @return DeliveryRun Service
     */
    public DeliveryRunService getDeliveryRunService() {
        return moDeliveryRunService;
    }

    /**
     * Sets the DeliveryRun Service to the one given by parameter
     *
     * @param oDeliveryRunService new Delivery Run Service
     */
    public void setDeliveryRunService(DeliveryRunService oDeliveryRunService) {
        this.moDeliveryRunService = oDeliveryRunService;
    }

    /**
     * Returns the Pharmacy Service
     *
     * @return Pharmacy Service
     */
    public PharmacyService getPharmacyService() {
        return moPharmacyService;
    }

    /**
     * Sets the Pharmacy Service to the one given by pararmeter
     *
     * @param oPharmacyService new Pharmacy Service
     */
    public void setPharmacyService(PharmacyService oPharmacyService) {
        this.moPharmacyService = oPharmacyService;
    }

    /**
     * Returns the Graph Service
     *
     * @return Graph Service
     */
    public GraphService getGraphService() {
        return moGraphService;
    }

    /**
     * Sets the Graph Service to the one given by parameter
     *
     * @param oGraphService new Graph Service
     */
    public void setGraphService(GraphService oGraphService) {
        this.moGraphService = oGraphService;
    }

    /**
     * Returns the List of Orders related to a certain pharmacy,
     * by the pharmacy's email
     *
     * @param strPharmacyEmail Pharmacy's email
     * @return List of Orders
     */
    public List<Order> getOrdersList(String strPharmacyEmail) {
        return moPharmacyService.getOrdersByPharmacyEmail(strPharmacyEmail);
    }

    /**
     * Registers a new Delivery Run basing on the List of Orders
     * given by parameter
     *
     * @param lstOrder List of Orders
     * @return True if the Delivery Run was registered, false if otherwise
     */
    public boolean registerDeliveryRun(List<Order> lstOrder, boolean energyCheck, boolean backTrackCheck) {
        if (ApplicationPOT.getInstance().getCurrentSession().getRole().equals(UserSession.Role.ADMIN)) {
            List<Route> lstPair = moGraphService.calculateBestVehicleAndBestPath(lstOrder,energyCheck,backTrackCheck);
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
