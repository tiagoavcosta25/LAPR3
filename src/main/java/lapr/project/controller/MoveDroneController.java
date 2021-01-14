package lapr.project.controller;

import javafx.util.Pair;
import lapr.project.model.Address;
import lapr.project.model.Drone;
import lapr.project.model.service.DroneService;

import java.util.LinkedList;

public class MoveDroneController {

    private DroneService oDroneService;

    public MoveDroneController() {
        this.oDroneService = new DroneService();
    }
    public boolean moveDrone(Pair<LinkedList<Address>, Double> pathInfo, Drone drone) {
        if (this.oDroneService.checkEnergy(pathInfo.getValue(), drone)) {
           return this.oDroneService.startDelivery();
        }
        return false;
    }
}
