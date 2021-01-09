package lapr.project.model;

import java.util.Objects;

public class Scooter {
    private int m_intId;
    private int m_intBatteryPerc;
    private String m_strCharginStatus;
    private int m_intPotency;
    private int m_intWeight;
    private int m_intBatteryCapacity;
    private Pharmacy m_oPharmacy;

    private static int DEFAULT_ID = -1;
    private static int DEFAULT_BATTERY_PERC = 0;
    private static String DEFAULT_CHARGING_STATUS = "Not Charging";
    private static int DEFAULT_POTENCY = 0;
    private static int DEFAULT_WEIGHT = 0;
    private static int DEFAULT_BATTERY_CAPACITY = 0;
    private static Pharmacy DEFAULT_PHARMACY = new Pharmacy();

    public Scooter() {
        this.m_intId = DEFAULT_ID;
        this.m_intBatteryPerc= DEFAULT_BATTERY_PERC;
        this.m_strCharginStatus = DEFAULT_CHARGING_STATUS;
        this.m_intPotency = DEFAULT_POTENCY;
        this.m_intWeight = DEFAULT_WEIGHT;
        this.m_intBatteryCapacity = DEFAULT_BATTERY_CAPACITY;
        this.m_oPharmacy = DEFAULT_PHARMACY;
    }

    public Scooter(int intId, int intBatteryPerc, String strCharginStatus, int intPotency, int intWeight,
                   int intBatteryCapacity, Pharmacy oPharmacy) {
        this.m_intId = intId;
        this.m_intBatteryPerc= intBatteryPerc;
        this.m_strCharginStatus = strCharginStatus;
        this.m_intPotency = intPotency;
        this.m_intWeight = intWeight;
        this.m_intBatteryCapacity = intBatteryCapacity;
        this.m_oPharmacy = oPharmacy;
    }

    public Scooter(int intBatteryPerc, String strCharginStatus, int intPotency, int intWeight,
                   int intBatteryCapacity, Pharmacy oPharmacy) {
        this.m_intBatteryPerc= intBatteryPerc;
        this.m_strCharginStatus = strCharginStatus;
        this.m_intPotency = intPotency;
        this.m_intWeight = intWeight;
        this.m_intBatteryCapacity = intBatteryCapacity;
        this.m_oPharmacy = oPharmacy;
    }

    public Scooter(int intId, int intBatteryPerc, String strCharginStatus, int intPotency, int intWeight,
                   int intBatteryCapacity) {
        this.m_intId = intId;
        this.m_intBatteryPerc= intBatteryPerc;
        this.m_strCharginStatus = strCharginStatus;
        this.m_intPotency = intPotency;
        this.m_intWeight = intWeight;
        this.m_intBatteryCapacity = intBatteryCapacity;
    }

    public int getId() {
        return m_intId;
    }

    public void setId(int intId) {
        this.m_intId = intId;
    }

    public int getBatteryPerc() {
        return m_intBatteryPerc;
    }

    public void setBatteryPerc(int intBatteryPerc) {
        this.m_intBatteryPerc = intBatteryPerc;
    }

    public String getCharginStatus() {
        return m_strCharginStatus;
    }

    public void setCharginStatus(String strCharginStatus) {
        this.m_strCharginStatus = strCharginStatus;
    }

    public int getPotency() {
        return m_intPotency;
    }

    public void setPotency(int intPotency) {
        this.m_intPotency = intPotency;
    }

    public int getWeight() {
        return m_intWeight;
    }

    public void setWeight(int intWeight) {
        this.m_intWeight = intWeight;
    }

    public int getBatteryCapacity() {
        return m_intBatteryCapacity;
    }

    public void setBatteryCapacity(int intBatteryCapacity) {
        this.m_intBatteryCapacity = intBatteryCapacity;
    }

    public Pharmacy getPharmacy() {
        return m_oPharmacy;
    }

    public void setPharmacy(Pharmacy oPharmacy) {
        this.m_oPharmacy = oPharmacy;
    }

    @Override
    public String toString() {
        return "Scooter{" +
                "Id = " + m_intId +
                ", Battery Perc = " + m_intBatteryPerc +
                ", Chargin Status = " + m_strCharginStatus +
                ", Potency = " + m_intPotency +
                ", Weight = " + m_intWeight +
                ", Battery Capacity = " + m_intBatteryCapacity +
                ", Pharmacy = " + m_oPharmacy +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scooter scooter = (Scooter) o;
        return m_intId == scooter.m_intId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_intId);
    }
}
