package lapr.project.model;

import java.util.Objects;

public class Battery {

    private int m_intId;
    private double m_dblEfficiency;
    private int m_intBatteryCapacity;
    private double m_dlbBatteryVoltage;

    private static int DEFAULT_ID = -1;
    private static double DEFAULT_EFFICICIENY = -1;
    private static int DEFAULT_BATTERY_CAPACITY = -1;
    private static double DEFAULT_BATTERY_VOLTAGE = -1;

    public Battery() {
        this.m_intId = DEFAULT_ID;
        this.m_intBatteryCapacity = DEFAULT_BATTERY_CAPACITY;
        this.m_dlbBatteryVoltage = DEFAULT_BATTERY_VOLTAGE;
        this.m_dblEfficiency= DEFAULT_EFFICICIENY;
    }

    public Battery(int intBatteryCapacity, double dblBatteryVoltage,  double dblEfficiency) {
        this.m_intId = DEFAULT_ID;
        this.m_intBatteryCapacity = intBatteryCapacity;
        this.m_dlbBatteryVoltage = dblBatteryVoltage;
        this.m_dblEfficiency = dblEfficiency;
    }

    public Battery(int intId, int intBatteryCapacity, double dblBatteryVoltage,  double dblEfficiency) {
        this.m_intId = intId;
        this.m_intBatteryCapacity = intBatteryCapacity;
        this.m_dlbBatteryVoltage = dblBatteryVoltage;
        this.m_dblEfficiency = dblEfficiency;
    }

    public int getId() {
        return m_intId;
    }

    public void setId(int intId) {
        this.m_intId = intId;
    }

    public double getEfficiency() {
        return m_dblEfficiency;
    }

    public void setEfficiency(double dblEfficiency) {
        this.m_dblEfficiency = dblEfficiency;
    }

    public int getBatteryCapacity() {
        return m_intBatteryCapacity;
    }

    public void setBatteryCapacity(int intBatteryCapacity) {
        this.m_intBatteryCapacity = intBatteryCapacity;
    }

    public double getBatteryVoltage() {
        return m_dlbBatteryVoltage;
    }

    public void setBatteryVoltage(double dlbBatteryVoltage) {
        this.m_dlbBatteryVoltage = dlbBatteryVoltage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Battery battery = (Battery) o;
        return m_intId == battery.m_intId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_intId);
    }

    @Override
    public String toString() {
        return "Battery{" +
                "Id=" + m_intId +
                ", Efficiency=" + m_dblEfficiency +
                ", Battery Capacity=" + m_intBatteryCapacity +
                ", Battery Voltage=" + m_dlbBatteryVoltage +
                '}';
    }
}
