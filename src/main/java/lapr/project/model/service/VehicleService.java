package lapr.project.model.service;

import lapr.project.data.VehicleDB;
import lapr.project.model.*;

import java.util.ArrayList;
import java.util.List;


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

    public VehicleModel newVehicleModel(String strDesignation, double dblPotency, double dblWeight, double dblMaxPayload, int intBatteryCapacity,
                                        double dblBatteryVoltage, double dblEfficiency, VehicleType oVehicleType) {
            return new VehicleModel(strDesignation, dblPotency, dblWeight, dblMaxPayload, new Battery(intBatteryCapacity,
                    dblBatteryVoltage, dblEfficiency), oVehicleType);
    }

    public boolean generateQRCode(Vehicle oVehicle) {
        return true;
    }

    public VehicleModel getVehicleModel(String strDesignation) {
        return this.moVehicleDB.getVehicleModel(strDesignation);
    }

    public int registerVehicleModel(VehicleModel moVehicleModel) {
        return this.moVehicleDB.registerVehicleModel(moVehicleModel);
    }

}
