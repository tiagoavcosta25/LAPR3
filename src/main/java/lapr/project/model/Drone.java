package lapr.project.model;

public class Drone extends Vehicle {

    public Drone() {
        super();
    }

    public Drone(float fltPotency, float fltWeight, float fltMaxPayload,
                   String strCharginStatus, float fltBatteryPerc, int intBatteryCapacity,
                   float fltBatteryVoltage, Pharmacy oPharmacy) {
        super(fltPotency, fltWeight, fltMaxPayload, strCharginStatus,
                new Battery(fltBatteryPerc, intBatteryCapacity, fltBatteryVoltage), oPharmacy);
    }

    public Drone(int intId, float fltPotency, float fltWeight, float fltMaxPayload,
                   String strCharginStatus, float fltBatteryPerc, int intBatteryCapacity,
                   float fltBatteryVoltage, Pharmacy oPharmacy) {
        super(intId, fltPotency, fltWeight, fltMaxPayload, strCharginStatus,
                new Battery(fltBatteryPerc, intBatteryCapacity, fltBatteryVoltage), oPharmacy);
    }

    public Drone(int intId, float fltPotency, float fltWeight, float fltMaxPayload, String strCharginStatus,
                 int intBatteryId, float fltBatteryPerc, int intBatteryCapacity, float fltBatteryVoltage,
                 Pharmacy oPharmacy) {
        super(intId, fltPotency, fltWeight, fltMaxPayload, strCharginStatus,
                new Battery(intBatteryId, fltBatteryPerc, intBatteryCapacity, fltBatteryVoltage), oPharmacy);
    }
}
