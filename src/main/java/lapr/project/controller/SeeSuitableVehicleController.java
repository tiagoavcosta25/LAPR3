package lapr.project.controller;

import lapr.project.model.Drone;
import lapr.project.model.Scooter;
import lapr.project.data.DeliveryDB;
import lapr.project.data.ScooterDB;
import lapr.project.model.Vehicle;
import lapr.project.model.service.ScooterService;
import lapr.project.model.service.VehicleService;

/**
 * Register Courier Controller.
 * <p>
 * Group: Team Lisa [G-037]
 * ______________________________________________________
 *
 * @author Ernesto Rodrigues <1190560@isep.ipp.pt>
 * @author Jessica Alves <1190682@isep.ipp.pt>
 * @author Pedro Santos <1190967@isep.ipp.pt>
 * @author Rodrigo Costa <1191014@isep.ipp.pt>
 */
public class SeeSuitableVehicleController {
    /**
     * Courier Management class
     */
    private VehicleService oVehicleService;

    /**
     * An empty constructor of RegisterCourierController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public SeeSuitableVehicleController() {
        oVehicleService = new VehicleService();
    }

    public Vehicle getSuitableVehicle(Double distanceScooter, Double distanceDrone) {
        return oVehicleService.getSuitableVehicle(distanceScooter, distanceDrone, ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail());
    }

}

