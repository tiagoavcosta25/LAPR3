package lapr.project.controller;

import lapr.project.model.service.ScooterService;

public class ParkScooterController {

    private ScooterService sServ;

    public ParkScooterController() {
        this.sServ = new ScooterService();
    }

    public boolean parkScooter(int idScooter, int idParkingSlot) {
        return false;
    }
}
