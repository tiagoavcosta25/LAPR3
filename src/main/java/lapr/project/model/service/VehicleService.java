package lapr.project.model.service;

import lapr.project.data.VehicleDB;
import lapr.project.model.Vehicle;
import lapr.project.model.VehicleModel;

import java.util.ArrayList;


public class VehicleService {

    private VehicleDB moVehicleDB;

    public VehicleService() {
        this.moVehicleDB = new VehicleDB();
    }

    public Vehicle getSuitableVehicle(Double distanceScooter, Double distanceDrone, String currentUserEmail) {
        return moVehicleDB.getSuitableVehicle(distanceScooter,distanceDrone, currentUserEmail);
    }

    public ArrayList<VehicleModel> getPharamcyModel(String strPharmacyEmail) {
        return moVehicleDB.getPharmacyModel(strPharmacyEmail);
    }
}
