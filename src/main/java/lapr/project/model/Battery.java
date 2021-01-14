package lapr.project.model;

import java.util.Objects;

public class Battery {

    private int m_intId;
    private float m_fltBatteryPerc;
    private int m_intBatteryCapacity;
    private float m_fltBatteryVoltage;

    private static float DEFAULT_BATTERY_PERC = -1;
    private static int DEFAULT_BATTERY_CAPACITY = -1;
    private static float DEFAULT_BATTERY_Voltage = -1;

    public Battery() {
        this.m_fltBatteryPerc= DEFAULT_BATTERY_PERC;
        this.m_intBatteryCapacity = DEFAULT_BATTERY_CAPACITY;
        this.m_fltBatteryVoltage = DEFAULT_BATTERY_Voltage;
    }

    public Battery(float fltBatteryPerc, int intBatteryCapacity, float intBatteryVoltage) {
        this.m_fltBatteryPerc= fltBatteryPerc;
        this.m_intBatteryCapacity = intBatteryCapacity;
        this.m_fltBatteryVoltage = intBatteryVoltage;
    }

    public Battery(int intId, float fltBatteryPerc, int intBatteryCapacity, float intBatteryVoltage) {
        this.m_fltBatteryPerc= fltBatteryPerc;
        this.m_intBatteryCapacity = intBatteryCapacity;
        this.m_fltBatteryVoltage = intBatteryVoltage;
        this.m_intId = intId;
    }

    public int getId() {
        return m_intId;
    }

    public void setId(int intId) {
        this.m_intId = intId;
    }

    public float getBatteryPerc() {
        return m_fltBatteryPerc;
    }

    public void setBatteryPerc(float fltBatteryPerc) {
        this.m_fltBatteryPerc = fltBatteryPerc;
    }

    public int getBatteryCapacity() {
        return m_intBatteryCapacity;
    }

    public void setBatteryCapacity(int intBatteryCapacity) {
        this.m_intBatteryCapacity = intBatteryCapacity;
    }

    public float getBatteryVoltage() {
        return m_fltBatteryVoltage;
    }

    public void setBatteryVoltage(float fltBatteryVoltage) {
        this.m_fltBatteryVoltage = fltBatteryVoltage;
    }

    @Override
    public String toString() {
        return "Battery{" +
                "Id=" + m_intId +
                ", BatteryPerc=" + m_fltBatteryPerc +
                ", BatteryCapacity=" + m_intBatteryCapacity +
                ", BatteryVoltage=" + m_fltBatteryVoltage +
                '}';
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
}
