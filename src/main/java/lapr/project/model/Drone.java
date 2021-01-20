package lapr.project.model;

public class Drone extends Vehicle {

    public Drone() {
        super();
    }

    public Drone(VehicleModel oVehicleModel, Pharmacy oPharmacy) {
        super(oVehicleModel, oPharmacy);
    }

    public Drone(int intId, VehicleModel oVehicleModel, Pharmacy oPharmacy) {
        super(intId, oVehicleModel, oPharmacy);
    }

    public Drone(int intId, double dblBatteryPerc, VehicleModel oVehicleModel, Pharmacy oPharmacy) {
        super(intId, dblBatteryPerc, oVehicleModel, oPharmacy);
    }
}
