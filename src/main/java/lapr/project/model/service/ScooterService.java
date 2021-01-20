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

    public boolean updateScooterFromDB(int intId, float fltBatteryPerc, String strCharginStatus, float fltPotency,
                                       float fltWeight, int intBatteryCapacity, float fltBatteryVoltage,
                                       float fltMaxPayload, int intPharmacyId){
        return moScooterDB.updateScooterFromDB(intId, fltBatteryPerc, strCharginStatus, fltPotency, fltWeight,
                intBatteryCapacity, fltBatteryVoltage, fltMaxPayload, intPharmacyId);
    }

    public List<Scooter> getScootersList(int intPharmacyId) { return moScooterDB.getScootersList(intPharmacyId);}

    public boolean removeScooterFromDB(int intId) { return moScooterDB.removeScooterFromDB(intId);}

    public Scooter newScooter(VehicleModel oVehicleModel, Pharmacy oPharmacy) {
        return new Scooter(oVehicleModel, oPharmacy);
    }

    public boolean registerScooter(Scooter oScooter) {
        return moScooterDB.registerScooter(oScooter);
    }

    public boolean parkScooter(int idScooter, int idParkingSlot) {
        return false;
    }

}
