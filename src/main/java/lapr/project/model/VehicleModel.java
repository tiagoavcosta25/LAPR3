package lapr.project.model;

import java.util.Objects;

public class VehicleModel {

    private int mintId;
    private String mstrDesignation;
    private double mdblPotency;
    private double mdblWeight;
    private double mdblMaxPayload;
    private Battery moBattery;
    private VehicleType menumVehicleType;

    private static int DEFAULTID = -1;
    private static String DEFAULTDESIGANTION = "No designation";
    private static double DEFAULTPOTENCY = -1;
    private static double DEFAULTWEIGHT = -1;
    private static double DEFAULTMAXPAYLOAD = -1;
    private static Battery DEFAULTBATTERY = new Battery();
    private static VehicleType DEFAULTVEHICLETYPE = VehicleType.NOTDEFINED;

    public VehicleModel() {
        this.mintId = DEFAULTID;
        this.mstrDesignation = DEFAULTDESIGANTION;
        this.mdblPotency = DEFAULTPOTENCY;
        this.mdblWeight = DEFAULTWEIGHT;
        this.mdblMaxPayload = DEFAULTMAXPAYLOAD;
        this.moBattery = DEFAULTBATTERY;
        this.menumVehicleType = DEFAULTVEHICLETYPE;
    }

    public VehicleModel(int intId, String strDesignation, double dblPotency, double dblWeight,
                        double dblMaxPayload, Battery oBattery, VehicleType enumVehicleType) {
        this.mintId = intId;
        this.mstrDesignation = strDesignation;
        this.mdblPotency = dblPotency;
        this.mdblWeight = dblWeight;
        this.mdblMaxPayload = dblMaxPayload;
        this.moBattery = oBattery;
        this.menumVehicleType = enumVehicleType;
    }

    public VehicleModel(String strDesignation, double dblPotency, double dblWeight, double dblMaxPayload,
                        Battery oBattery, VehicleType enumVehicleType) {
        this.mstrDesignation = strDesignation;
        this.mdblPotency = dblPotency;
        this.mdblWeight = dblWeight;
        this.mdblMaxPayload = dblMaxPayload;
        this.moBattery = oBattery;
        this.menumVehicleType = enumVehicleType;
    }

    public int getId() {
        return mintId;
    }

    public void setId(int intId) {
        this.mintId = intId;
    }

    public String getDesignation() {
        return mstrDesignation;
    }

    public void setDesignation(String strDesignation) {
        this.mstrDesignation = strDesignation;
    }

    public double getPotency() {
        return mdblPotency;
    }

    public void setPotency(double dblPotency) {
        this.mdblPotency = dblPotency;
    }

    public double getWeight() {
        return mdblWeight;
    }

    public void setWeight(double dblWeight) {
        this.mdblWeight = dblWeight;
    }

    public double getMaxPayload() {
        return mdblMaxPayload;
    }

    public void setMaxPayload(double dblMaxPayload) {
        this.mdblMaxPayload = dblMaxPayload;
    }

    public Battery getBattery() {
        return moBattery;
    }

    public void setBattery(Battery oBattery) {
        this.moBattery = oBattery;
    }

    public VehicleType getVehicleType() {
        return menumVehicleType;
    }

    public void setVehicleType(VehicleType enumVehicleType) {
        this.menumVehicleType = enumVehicleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleModel that = (VehicleModel) o;
        return Objects.equals(mstrDesignation, that.mstrDesignation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mstrDesignation);
    }

    @Override
    public String toString() {
        return "VehicleModel{" +
                "m_intId=" + mintId +
                ", m_strDesignation='" + mstrDesignation + '\'' +
                ", m_dblPotency=" + mdblPotency +
                ", m_dblWeight=" + mdblWeight +
                ", m_dblMaxPayload=" + mdblMaxPayload +
                ", m_oBattery=" + moBattery +
                ", m_enumVehicleType=" + menumVehicleType +
                '}';
    }
}
