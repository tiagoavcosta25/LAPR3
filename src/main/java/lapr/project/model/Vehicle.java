package lapr.project.model;

import java.util.Objects;

/**
 * Vehicle.
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

public abstract class Vehicle {

    /**
     * Vehicle ID.
     */
    private int mintId;

    /**
     * Vehicle Battery Percentage.
     */
    private double mdblBatteryPerc;

    /**
     * Vehicle Model class instance.
     */
    private VehicleModel moModel;

    /**
     * Pharmacy class instance.
     */
    private Pharmacy moPharmacy;

    /**
     * Vehicle Default ID.
     */
    private static int mDEFAULTID = -1;

    /**
     * Vehicle Default Battery Percentage.
     */
    private static double mDEFAULTBATTERYPERC = -1;

    /**
     * Default Vehicle Model.
     */
    private static VehicleModel mDEFAULTVEHICLEMODEL = new VehicleModel();

    /**
     * Default Pharmacy.
     */
    private static Pharmacy mDEFAULTPHARMACY = new Pharmacy();

    /**
     * Vehicle Default Battery Percentage.
     */
    private static double mSTARTINGBATTERYPERC = 100;

    /**
     * An empty constructor of Vehicle.
     */
    public Vehicle() {
        this.mintId = mDEFAULTID;
        this.mdblBatteryPerc = mDEFAULTBATTERYPERC;
        this.moModel = mDEFAULTVEHICLEMODEL;
        this.moPharmacy = mDEFAULTPHARMACY;
    }

    /**
     * Builds an instance of Vehicle receiving Vehicle's Model and Pharmacy.
     * @param oModel Vehicle's Model.
     * @param oPharmacy Vehicle's Pharmacy.
     */
    public Vehicle(VehicleModel oModel, Pharmacy oPharmacy) {
        this.mintId = mDEFAULTID;
        this.mdblBatteryPerc = mSTARTINGBATTERYPERC;
        this.moModel = oModel;
        this.moPharmacy = oPharmacy;
    }

    /**
     * Builds an instance of Vehicle receiving Vehicle's ID, Model and Pharmacy.
     * @param intId Vehicle's ID.
     * @param oModel Vehicle's Model.
     * @param oPharmacy Vehicle's Pharmacy.
     */
    public Vehicle(int intId, VehicleModel oModel, Pharmacy oPharmacy) {
        this.mintId = intId;
        this.mdblBatteryPerc = mSTARTINGBATTERYPERC;
        this.moModel = oModel;
        this.moPharmacy = oPharmacy;
    }

    /**
     * Builds an instance of Vehicle receiving Vehicle's ID, Battery Percentage, Model and Pharmacy.
     * @param intId Vehicle's ID.
     * @param dblBatteryPerc Vehicle's Battery Percentage.
     * @param oModel Vehicle's Model.
     * @param oPharmacy Vehicle's Pharmacy.
     */
    public Vehicle(int intId, double dblBatteryPerc, VehicleModel oModel, Pharmacy oPharmacy) {
        this.mintId = intId;
        this.mdblBatteryPerc = dblBatteryPerc;
        this.moModel = oModel;
        this.moPharmacy = oPharmacy;
    }

    /**
     * Returns Vehicle ID.
     * @return Vehicle ID.
     */
    public int getId() {
        return mintId;
    }

    /**
     * Modifies Vehicle ID.
     * @param intId
     */
    public void setId(int intId) {
        this.mintId = intId;
    }

    /**
     * This method is responsible for comparing a String ID with the Vehicle's ID.
     * If both the strings are the same, the Vehicle has the same ID as the introduced and it will return
     * true.
     *
     * @param intId the other Vehicle ID which we want to compare to.
     * @return true if both Strings are the same, false if otherwise
     */
    public boolean hasId(Integer intId) {return this.getId() == intId;}

    /**
     * Returns Vehicle Pharmacy.
     * @return Vehicle Pharmacy.
     */
    public Pharmacy getPharmacy() {
        return moPharmacy;
    }

    /**
     * Modifies Vehicle Pharmacy.
     * @param oPharmacy Vehicle Pharmacy.
     */
    public void setPharmacy(Pharmacy oPharmacy) {
        this.moPharmacy = oPharmacy;
    }

    /**
     * Returns Vehicle Model.
     * @return Vehicle Model.
     */
    public VehicleModel getModel() {
        return moModel;
    }

    /**
     * Modifies Vehicle Model.
     * @param oModel Vehicle Model.
     */
    public void setModel(VehicleModel oModel) {
        this.moModel = oModel;
    }

    /**
     * Returns Vehicle Battery Percentage.
     * @return Vehicle Battery Percentage.
     */
    public double getBatteryPerc() {
        return mdblBatteryPerc;
    }

    /**
     * Modifies Vehicle Battery Percentage.
     * @param dblBatteryPerc Vehicle Battery Percentage.
     */
    public void setBatteryPerc(double dblBatteryPerc) {
        this.mdblBatteryPerc = dblBatteryPerc;
    }

    /**
     * Returns a logical value referring to the equality (or lack thereof) of the Vehicle passed
     * by parameter with the instance of the Vehicle class.
     *
     * @param o Object.
     * @return a logical value referring to the equality (or lack thereof).
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return mintId == vehicle.mintId;
    }

    /**
     * Returns a hash code value for the object.
     * @return a hash code value for the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(mintId);
    }

    /**
     * Returns the textual description of the Vehicle in the format: ID, Battery Percentage, Vehicle Model
     * and Pharmacy.
     *
     * @return textual description of the Vehicle.
     */
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


