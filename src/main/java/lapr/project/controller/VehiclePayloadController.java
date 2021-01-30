package lapr.project.controller;

import lapr.project.model.service.VehicleService;

/**
 * Vehicle Payload Controller.
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

public class VehiclePayloadController {
    /**
     * Vehicle Managment Instance.
     */
    private VehicleService moVehicleServ;

    /**
     * An empty constructor of ParkScooterController.
     */
    public VehiclePayloadController() {
        this.moVehicleServ = new VehicleService();
    }

    /**
     * Returns the Vehicle Payload.
     * @param vehicleId Vehicle ID.
     * @return the Vehicle Payload.
     */
    public double getVehiclePayload(int vehicleId) {
        return this.moVehicleServ.getVehiclePayload(vehicleId);
    }

    /**
     * Returns Vehicle Service Instance.
     * @returnVehicle Service Instance.
     */
    public VehicleService getMoVehicleServ() {
        return moVehicleServ;
    }

    /**
     * Modifies Vehicle Service Instance..
     * @param moVehicleServ Vehicle Service Instance..
     */
    public void setMoVehicleServ(VehicleService moVehicleServ) {
        this.moVehicleServ = moVehicleServ;
    }
}
