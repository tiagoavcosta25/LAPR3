package lapr.project.model.service;

import lapr.project.data.DroneDB;
import lapr.project.model.*;
import java.util.List;

public class DroneService {

    private DroneDB moDroneDB;

    public DroneService() {
        moDroneDB = new DroneDB();
    }

    public DroneDB getDroneDB() {
        return moDroneDB;
    }

    public void setDroneDB(DroneDB oDroneDB) {
        this.moDroneDB = oDroneDB;
    }

    public boolean updateDroneFromDB(int intId, double dblBatteryPerc, String strDesignation, double dblPotency, double dblWeight, double dblMaxPayload,
                                     int intBatteryCapacity, double dblBatteryVoltage, double dblEfficiency){
        return moDroneDB.updateDroneFromDB(intId, dblBatteryPerc, strDesignation, dblPotency, dblWeight, dblMaxPayload,
                intBatteryCapacity, dblBatteryVoltage, dblEfficiency);
    }

    public Drone newDrone(VehicleModel oVehicleModel, Pharmacy oPharmacy) {
        return new Drone(oVehicleModel, oPharmacy);
    }

    public int registerDrone(Drone oDrone) {
        return moDroneDB.registerDrone(oDrone);
    }

    public List<Drone> getDronesList(String strPharmacyEmail) {
        return moDroneDB.getDronesList(strPharmacyEmail);
    }

    public boolean removeDroneFromDB(int intId) {
        return moDroneDB.removeDroneFromDB(intId);
    }

}
