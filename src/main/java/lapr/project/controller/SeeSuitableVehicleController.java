package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.service.VehicleService;

import java.util.ArrayList;

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
    private VehicleService moVehicleService;

    /**
     * An empty constructor of RegisterCourierController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public SeeSuitableVehicleController() {
        moVehicleService = new VehicleService();
    }

    public Vehicle getSuitableVehicle(Double distanceScooter, Double distanceDrone) {
        return moVehicleService.getSuitableVehicle(distanceScooter, distanceDrone, ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail());
    }

    public ArrayList<VehicleModel> getPharmacyModel(String strPharmacyEmail){
        return moVehicleService.getPharamcyModel(strPharmacyEmail);
    }

}

