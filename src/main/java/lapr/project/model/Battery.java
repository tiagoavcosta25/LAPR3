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

public class Battery {

    /**
     * Battery ID.
     */
    private int mintId;

    /**
     * Battery Efficiency.
     */
    private double mdblEfficiency;

    /**
     * Battery Capacity.
     */
    private int mintBatteryCapacity;

    /**
     * Battery Voltage.
     */
    private double mdlbBatteryVoltage;

    /**
     * Default Battery ID.
     */
    private static final int DEFAULTID = -1;

    /**
     * Default Battery Efficiency.
     */
    private static final double DEFAULTEFFICICIENY = -1;

    /**
     * Default Battery Capacity.
     */
    private static final int DEFAULTBATTERYCAPACITY = -1;

    /**
     * Default Battery Voltage.
     */
    private static final double DEFAULTBATTERYVOLTAGE = -1;

    /**
     * An empty constructor of Battery.
     */
    public Battery() {
        this.mintId = DEFAULTID;
        this.mintBatteryCapacity = DEFAULTBATTERYCAPACITY;
        this.mdlbBatteryVoltage = DEFAULTBATTERYVOLTAGE;
        this.mdblEfficiency = DEFAULTEFFICICIENY;
    }

    /**
     * Builds an instance of Battery receiving Battery's Capacity, Voltage and Efficiency.
     * @param intBatteryCapacity Battery's Capacity.
     * @param dblBatteryVoltage Battery's Voltage.
     * @param dblEfficiency Battery's Efficiency.
     */
    public Battery(int intBatteryCapacity, double dblBatteryVoltage,  double dblEfficiency) {
        this.mintId = DEFAULTID;
        this.mintBatteryCapacity = intBatteryCapacity;
        this.mdlbBatteryVoltage = dblBatteryVoltage;
        this.mdblEfficiency = dblEfficiency;
    }

    /**
     * Builds an instance of Battery receiving Battery's ID, Capacity, Voltage and Efficiency.
     * @param intId Battery's id.
     * @param intBatteryCapacity Battery's Capacity.
     * @param dblBatteryVoltage Battery's Voltage.
     * @param dblEfficiency Battery's Efficiency.
     */
    public Battery(int intId, int intBatteryCapacity, double dblBatteryVoltage,  double dblEfficiency) {
        this.mintId = intId;
        this.mintBatteryCapacity = intBatteryCapacity;
        this.mdlbBatteryVoltage = dblBatteryVoltage;
        this.mdblEfficiency = dblEfficiency;
    }

    /**
     * Returns Battery ID.
     * @return Battery ID.
     */
    public int getId() {
        return mintId;
    }

    /**
     * Modifies Battery ID.
     * @param intId Battery ID.
     */
    public void setId(int intId) {
        this.mintId = intId;
    }

    /**
     * Returns Battery Efficiency.
     * @return Battery Efficiency.
     */
    public double getEfficiency() {
        return mdblEfficiency;
    }

    /**
     * Modifies  Battery Efficiency.
     * @param dblEfficiency Battery Efficiency.
     */
    public void setEfficiency(double dblEfficiency) {
        this.mdblEfficiency = dblEfficiency;
    }

    /**
     * Returns Battery Capacity.
     * @return Battery Capacity.
     */
    public int getBatteryCapacity() {
        return mintBatteryCapacity;
    }

    /**
     * Modifies Battery Capacity.
     * @param intBatteryCapacity Battery Capacity.
     */
    public void setBatteryCapacity(int intBatteryCapacity) {
        this.mintBatteryCapacity = intBatteryCapacity;
    }

    /**
     * Returns Battery Voltage.
     * @return Battery Voltage.
     */
    public double getBatteryVoltage() {
        return mdlbBatteryVoltage;
    }

    /**
     * Modifies Battery Voltage.
     * @param dlbBatteryVoltage Battery Voltage.
     */
    public void setBatteryVoltage(double dlbBatteryVoltage) {
        this.mdlbBatteryVoltage = dlbBatteryVoltage;
    }

    /**
     * Returns a logical value referring to the equality (or lack thereof) of the Battery passed
     * by parameter with the instance of the Battery class.
     * @param o Object.
     * @return a logical value referring to the equality (or lack thereof).
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Battery battery = (Battery) o;
        return mintId == battery.mintId;
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
     * Returns the textual description of the Battery in the format: ID, Efficiency, Battery Capacity
     * and Battery Voltage.
     * @return textual description of the Battery.
     */
    @Override
    public String toString() {
        return "Battery{" +
                "Id=" + mintId +
                ", Efficiency=" + mdblEfficiency +
                ", Battery Capacity=" + mintBatteryCapacity +
                ", Battery Voltage=" + mdlbBatteryVoltage +
                '}';
    }
}
