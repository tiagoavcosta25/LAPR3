package lapr.project.controller;

import javafx.util.Pair;
import lapr.project.model.Address;
import lapr.project.model.DeliveryRun;
import lapr.project.model.service.DroneService;

import java.util.LinkedList;

public class MoveDroneController {

    private final DroneService moDroneService;

    public MoveDroneController() {
        this.moDroneService = new DroneService();
    }
    public boolean moveDrone(Pair<LinkedList<Address>, Double> pathInfo, DeliveryRun oDeliveryRun) {
        if (this.moDroneService.checkEnergy(pathInfo.getValue(), oDeliveryRun)) {
           return this.moDroneService.startDelivery(oDeliveryRun);
        }
        return false;
    }
}
