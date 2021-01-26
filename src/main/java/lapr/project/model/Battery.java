package lapr.project.model;

import java.util.Objects;

public class Battery {

    private int mintId;
    private double mdblEfficiency;
    private int mintBatteryCapacity;
    private double mdlbBatteryVoltage;

    private static final int DEFAULTID = -1;
    private static final double DEFAULTEFFICICIENY = -1;
    private static final int DEFAULTBATTERYCAPACITY = -1;
    private static final double DEFAULTBATTERYVOLTAGE = -1;

    public Battery() {
        this.mintId = DEFAULTID;
        this.mintBatteryCapacity = DEFAULTBATTERYCAPACITY;
        this.mdlbBatteryVoltage = DEFAULTBATTERYVOLTAGE;
        this.mdblEfficiency = DEFAULTEFFICICIENY;
    }

    public Battery(int intBatteryCapacity, double dblBatteryVoltage,  double dblEfficiency) {
        this.mintId = DEFAULTID;
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
