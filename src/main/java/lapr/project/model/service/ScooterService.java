package lapr.project.model.service;

import lapr.project.data.ScooterDB;
import lapr.project.model.Pharmacy;
import lapr.project.model.Scooter;
import lapr.project.model.VehicleModel;

import java.util.List;

public class ScooterService {

    private ScooterDB moScooterDB;

    public ScooterDB getScooterDB() {
        return moScooterDB;
    }

    public void setScooterDB(ScooterDB oScooterDB) {
        this.moScooterDB = oScooterDB;
    }

    public ScooterService() {
        this.moScooterDB = new ScooterDB();
    }

    public Scooter getScooter(int intId){
        return moScooterDB.getScooter(intId);
    }

    public boolean updateScooterFromDB(int intId, double dblBatteryPerc, String strDesignation, double dblPotency, double dblWeight, double dblMaxPayload,
                                       int intBatteryCapacity, double dblBatteryVoltage, double dblEfficiency){
        return moScooterDB.updateScooterFromDB(intId, dblBatteryPerc, strDesignation, dblPotency, dblWeight, dblMaxPayload,
                intBatteryCapacity, dblBatteryVoltage, dblEfficiency);
    }

    public List<Scooter> getScootersList(String strPharmacyEmail) { return moScooterDB.getScootersList(strPharmacyEmail);}

    public boolean removeScooterFromDB(int intId) { return moScooterDB.removeScooterFromDB(intId);}

    public Scooter newScooter(VehicleModel oVehicleModel, Pharmacy oPharmacy) {
        return new Scooter(oVehicleModel, oPharmacy);
    }

    public int registerScooter(Scooter oScooter) {
        return moScooterDB.registerScooter(oScooter);
    }

    public boolean parkScooter(int idScooter, int idParkingSlot) {
        return false;
    }

}
