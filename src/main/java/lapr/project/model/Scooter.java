package lapr.project.model;

public class Scooter extends Vehicle {

    public Scooter() {
        super();
    }

    public Scooter(VehicleModel oVehicleModel, Pharmacy oPharmacy) {
        super(oVehicleModel, oPharmacy);
    }

    public Scooter(int intId, VehicleModel oVehicleModel, Pharmacy oPharmacy) {
        super(intId, oVehicleModel, oPharmacy);
    }

    public Scooter(int intId, double dblBatteryPerc, VehicleModel oVehicleModel, Pharmacy oPharmacy) {
        super(intId, dblBatteryPerc, oVehicleModel, oPharmacy);
    }
}
