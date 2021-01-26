package lapr.project.model;

import java.util.Objects;

public class Battery {

    private int mintId;
    private double mdblEfficiency;
    private int mintBatteryCapacity;
    private double mdlbBatteryVoltage;

    private static int mDEFAULTID = -1;
    private static double mDEFAULTEFFICICIENY = -1;
    private static int mDEFAULTBATTERY_CAPACITY = -1;
    private static double mDEFAULTBATTERY_VOLTAGE = -1;

    public Battery() {
        this.mintId = mDEFAULTID;
        this.mintBatteryCapacity = mDEFAULTBATTERY_CAPACITY;
        this.mdlbBatteryVoltage = mDEFAULTBATTERY_VOLTAGE;
        this.mdblEfficiency = mDEFAULTEFFICICIENY;
    }

    public Battery(int intBatteryCapacity, double dblBatteryVoltage,  double dblEfficiency) {
        this.mintId = mDEFAULTID;
        this.mintBatteryCapacity = intBatteryCapacity;
        this.mdlbBatteryVoltage = dblBatteryVoltage;
        this.mdblEfficiency = dblEfficiency;
    }

    public Battery(int intId, int intBatteryCapacity, double dblBatteryVoltage,  double dblEfficiency) {
        this.mintId = intId;
        this.mintBatteryCapacity = intBatteryCapacity;
        this.mdlbBatteryVoltage = dblBatteryVoltage;
        this.mdblEfficiency = dblEfficiency;
    }

    public int getId() {
        return mintId;
    }

    public void setId(int intId) {
        this.mintId = intId;
    }

    public double getEfficiency() {
        return mdblEfficiency;
    }

    public void setEfficiency(double dblEfficiency) {
        this.mdblEfficiency = dblEfficiency;
    }

    public int getBatteryCapacity() {
        return mintBatteryCapacity;
    }

    public void setBatteryCapacity(int intBatteryCapacity) {
        this.mintBatteryCapacity = intBatteryCapacity;
    }

    public double getBatteryVoltage() {
        return mdlbBatteryVoltage;
    }

    public void setBatteryVoltage(double dlbBatteryVoltage) {
        this.mdlbBatteryVoltage = dlbBatteryVoltage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Battery battery = (Battery) o;
        return mintId == battery.mintId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mintId);
    }

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
