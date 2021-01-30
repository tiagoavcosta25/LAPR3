package lapr.project.model;

import java.util.Objects;

/**
 * Battery.
 *
 * Group: Team Lisa [G-021]
 * ______________________________________________________
 * @author António Barbosa <1190404@isep.ipp.pt>
 * @author Ernesto Rodrigues <1190560@isep.ipp.pt>
 * @author Jessica Alves <1190682@isep.ipp.pt>
 * @author Pedro Santos <1190967@isep.ipp.pt>
 * @author Rodrigo Costa <1191014@isep.ipp.pt>
 * @author Tiago Costa <1191460@isep.ipp.pt>
 */

public class VehicleModel {
    /**
     * Vehicle Model ID.
     */
    private int mintId;

    /**
     * Vehicle Model Designation.
     */
    private String mstrDesignation;

    /**
     * Vehicle Model Potency.
     */
    private double mdblPotency;

    /**
     * Vehicle Model Weight.
     */
    private double mdblWeight;

    /**
     * Vehicle Model Maximum Payload.
     */
    private double mdblMaxPayload;

    /**
     * Vehicle Model Battery.
     */
    private Battery moBattery;

    /**
     * Vehicle Type class instance.
     */
    private VehicleType menumVehicleType;

    /**
     * Vehicle Model Default ID.
     */

    private static int mDEFAULTID = -1;

    /**
     * Vehicle Model Default Designation.
     */
    private static String mDEFAULTDESIGANTION = "No designation";

    /**
     * Vehicle Model Default Potency.
     */
    private static double mDEFAULTPOTENCY = -1;

    /**
     * Vehicle Model Default Weight.
     */
    private static double mDEFAULTWEIGHT = -1;

    /**
     * Vehicle Model Default Maximum Payload.
     */
    private static double mDEFAULTMAXPAYLOAD = -1;

    /**
     * Vehicle Model Default Baattery.
     */
    private static Battery mDEFAULTBATTERY = new Battery();

    /**
     * Default Vehicle Type.
     */
    private static VehicleType mDEFAULTVEHICLETYPE = VehicleType.NOTDEFINED;

    /**
     * An empty constructor of Battery.
     */
    public VehicleModel() {
        this.mintId = mDEFAULTID;
        this.mstrDesignation = mDEFAULTDESIGANTION;
        this.mdblPotency = mDEFAULTPOTENCY;
        this.mdblWeight = mDEFAULTWEIGHT;
        this.mdblMaxPayload = mDEFAULTMAXPAYLOAD;
        this.moBattery = mDEFAULTBATTERY;
        this.menumVehicleType = mDEFAULTVEHICLETYPE;
    }

    /**
     * Builds an instance of VehicleModel receiving Vehicle Model's ID, Designation, Potency, Weight,
     * Maximum Payload, Battery and Vehicle Type.
     *
     * @param intId Vehicle Model's ID.
     * @param strDesignation Vehicle Model's Designation.
     * @param dblPotency Vehicle Model's Potency.
     * @param dblWeight Vehicle Model's Weight.
     * @param dblMaxPayload Vehicle Model's Maximum Payload.
     * @param oBattery Vehicle Model's Battery.
     * @param enumVehicleType Vehicle Model's Type.
     */
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

    /**
     * Builds an instance of VehicleModel receiving Vehicle Model's Designation, Potency, Weight,
     * Maximum Payload, Battery and Vehicle Type.
     *
     * @param strDesignation Vehicle Model's Designation.
     * @param dblPotency Vehicle Model's Potency.
     * @param dblWeight Vehicle Model's Weight.
     * @param dblMaxPayload Vehicle Model's Maximum Payload.
     * @param oBattery Vehicle Model's Battery.
     * @param enumVehicleType Vehicle Model's Type.
     */
    public VehicleModel(String strDesignation, double dblPotency, double dblWeight, double dblMaxPayload,
                        Battery oBattery, VehicleType enumVehicleType) {
        this.mstrDesignation = strDesignation;
        this.mdblPotency = dblPotency;
        this.mdblWeight = dblWeight;
        this.mdblMaxPayload = dblMaxPayload;
        this.moBattery = oBattery;
        this.menumVehicleType = enumVehicleType;
    }

    /**
     * Returns Vehicle Model ID.
     * @return Vehicle Model ID.
     */
    public int getId() {
        return mintId;
    }

    /**
     * Modifies Vehicle Model ID.
     * @param intId Vehicle Model ID.
     */
    public void setId(int intId) {
        this.mintId = intId;
    }

    /**
     * Returns Vehicle Model Designation.
     * @return Vehicle Model Designation.
     */
    public String getDesignation() {
        return mstrDesignation;
    }

    /**
     * Modifies Vehicle Model Designation.
     * @param strDesignation Vehicle Model Designation.
     */
    public void setDesignation(String strDesignation) {
        this.mstrDesignation = strDesignation;
    }

    /**
     * Returns Vehicle Model Potency.
     * @return Vehicle Model Potency.
     */
    public double getPotency() {
        return mdblPotency;
    }

    /**
     * Modifies Vehicle Model Potency.
     * @param dblPotency Vehicle Model Potency.
     */
    public void setPotency(double dblPotency) {
        this.mdblPotency = dblPotency;
    }

    /**
     * Returns Vehicle Model Weight.
     * @return Vehicle Model Weight.
     */
    public double getWeight() {
        return mdblWeight;
    }

    /**
     * Modifies Vehicle Model Weight.
     * @param dblWeight Vehicle Model Weight.
     */
    public void setWeight(double dblWeight) {
        this.mdblWeight = dblWeight;
    }

    /**
     * Returns Vehicle Model Maximum Payload.
     * @return Vehicle Model Maximum Payload.
     */
    public double getMaxPayload() {
        return mdblMaxPayload;
    }

    /**
     * Modifies Vehicle Model Maximum Payload.
     * @param dblMaxPayload Vehicle Model Maximum Payload.
     */
    public void setMaxPayload(double dblMaxPayload) {
        this.mdblMaxPayload = dblMaxPayload;
    }

    /**
     * Returns Vehicle Model Battery.
     * @return Vehicle Model Battery.
     */
    public Battery getBattery() {
        return moBattery;
    }

    /**
     * Modifies Vehicle Model Battery.
     * @param oBattery Vehicle Model Battery.
     */
    public void setBattery(Battery oBattery) {
        this.moBattery = oBattery;
    }

    /**
     * Returns Vehicle Model Type.
     * @return Vehicle Model Type.
     */
    public VehicleType getVehicleType() {
        return menumVehicleType;
    }

    /**
     * Modifies Vehicle Model Type.
     * @param enumVehicleType Vehicle Model Type.
     */
    public void setVehicleType(VehicleType enumVehicleType) {
        this.menumVehicleType = enumVehicleType;
    }

    /**
     * Returns a logical value referring to the equality (or lack thereof) of the Vehicle Model passed
     * by parameter with the instance of the Vehicle Model class.
     *
     * @param o Object.
     * @return a logical value referring to the equality (or lack thereof).
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleModel that = (VehicleModel) o;
        return Objects.equals(mstrDesignation, that.mstrDesignation);
    }

    /**
     * Returns a hash code value for the object.
     * @return a hash code value for the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(mstrDesignation);
    }

    /**
     * Returns the textual description of the Vehicle Model in the format: ID, Designation, Potency, Weight,
     * Maximum Payload, Battery and Vehicle Type.
     * @return textual description of the Vehicle Model.
     */
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

    public String toStringRoute() {
        return "name->" + mstrDesignation + ", potency->" + mdblPotency + " watt's, weight->" +
                mdblWeight + " kilos, max payload weight->" + mdblMaxPayload + " kilos" +
                moBattery.toStringRoute();
    }
}
