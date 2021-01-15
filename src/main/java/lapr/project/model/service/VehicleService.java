package lapr.project.model.service;

import lapr.project.data.VehicleDB;
import lapr.project.model.Vehicle;


public class VehicleService {

    private VehicleDB m_oVehicleDB;

    public VehicleService() {
        this.m_oVehicleDB = new VehicleDB();
    }

    public Vehicle getSuitableVehicle(Double distanceScooter, Double distanceDrone, String currentUserEmail) {
        return m_oVehicleDB.getSuitableVehicle(distanceScooter,distanceDrone, currentUserEmail);
    }
}
