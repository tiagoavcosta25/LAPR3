package lapr.project.model.service;

import lapr.project.data.ScooterDB;
import lapr.project.model.Pharmacy;
import lapr.project.model.Scooter;
import lapr.project.model.VehicleModel;

import java.util.List;

public class ScooterService {

    private ScooterDB moScooterDB;

    public ScooterService() {
        this.moScooterDB = new ScooterDB();
    }

    public Scooter getScooter(int intId){
        return moScooterDB.getScooter(intId);
    }

    public boolean updateScooterFromDB(int intId, double dblBatteryPerc, String strCharginStatus, double dblPotency,
                                       double dblWeight, int intBatteryCapacity, double dblBatteryVoltage,
                                       double dblMaxPayload, int intPharmacyId){
        return moScooterDB.updateScooterFromDB(intId, dblBatteryPerc, strCharginStatus, dblPotency, dblWeight,
                intBatteryCapacity, dblBatteryVoltage, dblMaxPayload, intPharmacyId);
    }

    public List<Scooter> getScootersList(int intPharmacyId) { return moScooterDB.getScootersList(intPharmacyId);}

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
