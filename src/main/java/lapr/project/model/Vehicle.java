package lapr.project.model;

import java.util.Objects;

public abstract class Vehicle {
    private int mintId;
    private double mdblBatteryPerc;
    private VehicleModel moModel;
    private Pharmacy moPharmacy;

    private static int mDEFAULTID = -1;
    private static double mDEFAULTBATTERYPERC = -1;
    private static VehicleModel mDEFAULTVEHICLEMODEL = new VehicleModel();
    private static Pharmacy mDEFAULTPHARMACY = new Pharmacy();

    private static double mSTARTINGBATTERYPERC = 100;

    public Vehicle() {
        this.mintId = mDEFAULTID;
        this.mdblBatteryPerc = mDEFAULTBATTERYPERC;
        this.moModel = mDEFAULTVEHICLEMODEL;
        this.moPharmacy = mDEFAULTPHARMACY;
    }

    public Vehicle(VehicleModel oModel, Pharmacy oPharmacy) {
        this.mintId = mDEFAULTID;
        this.mdblBatteryPerc = mSTARTINGBATTERYPERC;
        this.moModel = oModel;
        this.moPharmacy = oPharmacy;
    }

    public Vehicle(int intId, VehicleModel oModel, Pharmacy oPharmacy) {
        this.mintId = intId;
        this.mdblBatteryPerc = mSTARTINGBATTERYPERC;
        this.moModel = oModel;
        this.moPharmacy = oPharmacy;
    }

    public Vehicle(int intId, double dblBatteryPerc, VehicleModel oModel, Pharmacy oPharmacy) {
        this.mintId = intId;
        this.mdblBatteryPerc = dblBatteryPerc;
        this.moModel = oModel;
        this.moPharmacy = oPharmacy;
    }

    public int getId() {
        return mintId;
    }

    public void setId(int intId) {
        this.mintId = intId;
    }

    public boolean hasId(Integer intId) {return this.getId() == intId;}

    public Pharmacy getPharmacy() {
        return moPharmacy;
    }

    public void setPharmacy(Pharmacy oPharmacy) {
        this.moPharmacy = oPharmacy;
    }

    public VehicleModel getModel() {
        return moModel;
    }

    public void setModel(VehicleModel oModel) {
        this.moModel = oModel;
    }

    public double getBatteryPerc() {
        return mdblBatteryPerc;
    }

    public void setBatteryPerc(double dblBatteryPerc) {
        this.mdblBatteryPerc = dblBatteryPerc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return mintId == vehicle.mintId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mintId);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "Id=" + mintId +
                ", BatteryPerc=" + mdblBatteryPerc +
                ", Model=" + moModel +
                ", Pharmacy=" + moPharmacy +
                '}';
    }

}


