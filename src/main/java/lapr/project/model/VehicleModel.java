package lapr.project.model;

import java.util.Objects;

public class VehicleModel {

    private int m_intId;
    private String m_strDesignation;
    private double m_dblPotency;
    private double m_dblWeight;
    private double m_dblMaxPayload;
    private Battery m_oBattery;
    private VehicleType m_enumVehicleType;

    private static int DEFAULT_ID = -1;
    private static String DEFAULT_DESIGANTION = "No designation";
    private static double DEFAULT_POTENCY = -1;
    private static double DEFAULT_WEIGHT = -1;
    private static double DEFAULT_MAX_PAYLOAD = -1;
    private static Battery DEFAULT_BATTERY = new Battery();
    private static VehicleType DEFAULT_VEHICLE_TYPE = VehicleType.NOTDEFINED;

    public VehicleModel() {
        this.m_intId = DEFAULT_ID;
        this.m_strDesignation = DEFAULT_DESIGANTION;
        this.m_dblPotency = DEFAULT_POTENCY;
        this.m_dblWeight = DEFAULT_WEIGHT;
        this.m_dblMaxPayload = DEFAULT_MAX_PAYLOAD;
        this.m_oBattery = DEFAULT_BATTERY;
        this.m_enumVehicleType = DEFAULT_VEHICLE_TYPE;
    }

    public VehicleModel(int m_intId, String m_strDesignation, double m_dblPotency, double m_dblWeight,
                        double m_dblMaxPayload, Battery m_oBattery, VehicleType m_enumVehicleType) {
        this.m_intId = m_intId;
        this.m_strDesignation = m_strDesignation;
        this.m_dblPotency = m_dblPotency;
        this.m_dblWeight = m_dblWeight;
        this.m_dblMaxPayload = m_dblMaxPayload;
        this.m_oBattery = m_oBattery;
        this.m_enumVehicleType = m_enumVehicleType;
    }

    public VehicleModel(String m_strDesignation, double m_dblPotency, double m_dblWeight, double m_dblMaxPayload,
                        Battery m_oBattery, VehicleType m_enumVehicleType) {
        this.m_strDesignation = m_strDesignation;
        this.m_dblPotency = m_dblPotency;
        this.m_dblWeight = m_dblWeight;
        this.m_dblMaxPayload = m_dblMaxPayload;
        this.m_oBattery = m_oBattery;
        this.m_enumVehicleType = m_enumVehicleType;
    }

    public int getId() {
        return m_intId;
    }

    public void setId(int m_intId) {
        this.m_intId = m_intId;
    }

    public String getDesignation() {
        return m_strDesignation;
    }

    public void setDesignation(String m_strDesignation) {
        this.m_strDesignation = m_strDesignation;
    }

    public double getPotency() {
        return m_dblPotency;
    }

    public void setPotency(double m_dblPotency) {
        this.m_dblPotency = m_dblPotency;
    }

    public double getWeight() {
        return m_dblWeight;
    }

    public void setWeight(double m_dblWeight) {
        this.m_dblWeight = m_dblWeight;
    }

    public double getMaxPayload() {
        return m_dblMaxPayload;
    }

    public void setMaxPayload(double m_dblMaxPayload) {
        this.m_dblMaxPayload = m_dblMaxPayload;
    }

    public Battery getBattery() {
        return m_oBattery;
    }

    public void setBattery(Battery m_oBattery) {
        this.m_oBattery = m_oBattery;
    }

    public VehicleType getVehicleType() {
        return m_enumVehicleType;
    }

    public void setVehicleType(VehicleType m_enumVehicleType) {
        this.m_enumVehicleType = m_enumVehicleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleModel that = (VehicleModel) o;
        return Objects.equals(m_strDesignation, that.m_strDesignation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_strDesignation);
    }

    @Override
    public String toString() {
        return "VehicleModel{" +
                "m_intId=" + m_intId +
                ", m_strDesignation='" + m_strDesignation + '\'' +
                ", m_dblPotency=" + m_dblPotency +
                ", m_dblWeight=" + m_dblWeight +
                ", m_dblMaxPayload=" + m_dblMaxPayload +
                ", m_oBattery=" + m_oBattery +
                ", m_enumVehicleType=" + m_enumVehicleType +
                '}';
    }
}
