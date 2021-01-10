package lapr.project.model;

import java.util.Objects;

public class Scooter {
    private int m_intId;
    private float m_fltBatteryPerc;
    private String m_strCharginStatus;
    private float m_fltPotency;
    private float m_fltWeight;
    private int m_intBatteryCapacity;
    private float m_fltMaxPayload;
    private Pharmacy m_oPharmacy;

    private static int DEFAULT_ID = -1;
    private static float DEFAULT_BATTERY_PERC = -1;
    private static String DEFAULT_CHARGING_STATUS = "Not Charging";
    private static float DEFAULT_POTENCY = -1;
    private static float DEFAULT_WEIGHT = -1;
    private static int DEFAULT_BATTERY_CAPACITY = -1;
    private static float DEFAULT_MAX_PAYLOAD = -1;
    private static Pharmacy DEFAULT_PHARMACY = new Pharmacy();

    public Scooter() {
        this.m_intId = DEFAULT_ID;
        this.m_fltBatteryPerc= DEFAULT_BATTERY_PERC;
        this.m_strCharginStatus = DEFAULT_CHARGING_STATUS;
        this.m_fltPotency = DEFAULT_POTENCY;
        this.m_fltWeight = DEFAULT_WEIGHT;
        this.m_intBatteryCapacity = DEFAULT_BATTERY_CAPACITY;
        this.m_fltMaxPayload = DEFAULT_MAX_PAYLOAD;
        this.m_oPharmacy = DEFAULT_PHARMACY;
    }

    public Scooter(int intId, float fltBatteryPerc, String strCharginStatus, float fltPotency, float fltWeight,
                   int intBatteryCapacity, float fltMaxPayload, Pharmacy oPharmacy) {
        this.m_intId = intId;
        this.m_fltBatteryPerc= fltBatteryPerc;
        this.m_strCharginStatus = strCharginStatus;
        this.m_fltPotency = fltPotency;
        this.m_fltWeight = fltWeight;
        this.m_intBatteryCapacity = intBatteryCapacity;
        this.m_fltMaxPayload = fltMaxPayload;
        this.m_oPharmacy = oPharmacy;
    }

    public Scooter(float fltBatteryPerc, String strCharginStatus, float fltPotency, float fltWeight,
                   int intBatteryCapacity, float fltMaxPayload, Pharmacy oPharmacy) {
        this.m_fltBatteryPerc= fltBatteryPerc;
        this.m_strCharginStatus = strCharginStatus;
        this.m_fltPotency = fltPotency;
        this.m_fltWeight = fltWeight;
        this.m_intBatteryCapacity = intBatteryCapacity;
        this.m_fltMaxPayload = fltMaxPayload;
        this.m_oPharmacy = oPharmacy;
    }

    public Scooter(int intId, float fltBatteryPerc, String strCharginStatus, float fltPotency, float fltWeight,
                   int intBatteryCapacity, float fltMaxPayload) {
        this.m_intId = intId;
        this.m_fltBatteryPerc= fltBatteryPerc;
        this.m_strCharginStatus = strCharginStatus;
        this.m_fltPotency = fltPotency;
        this.m_fltWeight = fltWeight;
        this.m_intBatteryCapacity = intBatteryCapacity;
        this.m_fltMaxPayload = fltMaxPayload;
    }

    public int getId() {
        return m_intId;
    }

    public void setId(int intId) {
        this.m_intId = intId;
    }

    public boolean hasId(Integer intId) {return this.getId() == intId;}

    public float getBatteryPerc() {
        return m_fltBatteryPerc;
    }

    public void setBatteryPerc(float fltBatteryPerc) {
        this.m_fltBatteryPerc = fltBatteryPerc;
    }

    public String getCharginStatus() {
        return m_strCharginStatus;
    }

    public void setCharginStatus(String strCharginStatus) {
        this.m_strCharginStatus = strCharginStatus;
    }

    public float getPotency() {
        return m_fltPotency;
    }

    public void setPotency(float fltPotency) {
        this.m_fltPotency = fltPotency;
    }

    public float getWeight() {
        return m_fltWeight;
    }

    public void setWeight(float fltWeight) {
        this.m_fltWeight = fltWeight;
    }

    public int getBatteryCapacity() {
        return m_intBatteryCapacity;
    }

    public void setBatteryCapacity(int intBatteryCapacity) {
        this.m_intBatteryCapacity = intBatteryCapacity;
    }

    public float getMaxPayload() {
        return m_fltMaxPayload;
    }

    public void setMaxPayload(float fltMaxPayload) {
        this.m_fltMaxPayload = fltMaxPayload;
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
                ", Battery Perc = " + m_fltBatteryPerc +
                ", Chargin Status = " + m_strCharginStatus +
                ", Potency = " + m_fltPotency +
                ", Weight = " + m_fltWeight +
                ", Battery Capacity = " + m_intBatteryCapacity +
                ", Max Payload = " + m_fltMaxPayload +
                ", Pharmacy = " + m_oPharmacy +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scooter scooter = (Scooter) o;
        return m_intId == scooter.m_intId &&
                Float.compare(scooter.m_fltBatteryPerc, m_fltBatteryPerc) == 0 &&
                Float.compare(scooter.m_fltPotency, m_fltPotency) == 0 &&
                Float.compare(scooter.m_fltWeight, m_fltWeight) == 0 &&
                m_intBatteryCapacity == scooter.m_intBatteryCapacity &&
                Float.compare(scooter.m_fltMaxPayload, m_fltMaxPayload) == 0 &&
                m_strCharginStatus.equals(scooter.m_strCharginStatus) &&
                m_oPharmacy.equals(scooter.m_oPharmacy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_intId);
    }
}
