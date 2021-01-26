package lapr.project.model.service;

import com.google.zxing.WriterException;
import lapr.project.data.VehicleDB;
import lapr.project.model.*;
import lapr.project.utils.QRCode;
import java.io.IOException;
import java.util.List;


public class VehicleService {

    private VehicleDB moVehicleDB;

    public VehicleDB getVehicleDB() {
        return moVehicleDB;
    }

    public void setVehicleDB(VehicleDB oVehicleDB) {
        this.moVehicleDB = oVehicleDB;
    }

    public VehicleService() {
        this.moVehicleDB = new VehicleDB();
    }

    public Vehicle getSuitableVehicle(Double distanceScooter, Double distanceDrone, String currentUserEmail) {
        return moVehicleDB.getSuitableVehicle(distanceScooter,distanceDrone, currentUserEmail);
    }

    public List<VehicleModel> getPharamcyModel(String strPharmacyEmail) {
        return moVehicleDB.getPharmacyModel(strPharmacyEmail);
    }

    public VehicleModel newVehicleModel(String strDesignation, double dblPotency, double dblWeight, double dblMaxPayload, int intBatteryCapacity,
                                        double dblBatteryVoltage, double dblEfficiency, VehicleType oVehicleType) {
            return new VehicleModel(strDesignation, dblPotency, dblWeight, dblMaxPayload, new Battery(intBatteryCapacity,
                    dblBatteryVoltage, dblEfficiency), oVehicleType);
    }

    public boolean generateQRCode(Vehicle oVehicle) throws IOException, WriterException {
        return QRCode.generateQRCode(oVehicle.getModel().getVehicleType().getDesignation() + "#" + oVehicle.getId()
                        + "_" + oVehicle.getPharmacy().getEmail(), oVehicle.getModel().getVehicleType().getDesignation() + "_" + oVehicle.getId());
    }

    public VehicleModel getVehicleModel(String strDesignation) {
        return this.moVehicleDB.getVehicleModel(strDesignation);
    }

    public int registerVehicleModel(VehicleModel moVehicleModel) {
        return this.moVehicleDB.registerVehicleModel(moVehicleModel);
    }
    public double getVehiclePayload(int vehicleId){
        return this.moVehicleDB.getVehiclePayload(vehicleId);
    }

}
