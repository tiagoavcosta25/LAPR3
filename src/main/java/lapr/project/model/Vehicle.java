package lapr.project.model;

import java.util.Objects;

public abstract class Vehicle {
    private int m_intId;
    private int m_intPharmacyId;
    private float m_fltPotency;
    private float m_fltWeight;
    private float m_fltMaxPayload;
    private String m_strCharginStatus;
    private Battery m_oBattery;
    private Pharmacy m_oPharmacy;

    private static int DEFAULT_ID = -1;
    private static int DEFAULT_PHARMACY_ID = -1;
    private static String DEFAULT_CHARGING_STATUS = "Not Charging";
    private static float DEFAULT_POTENCY = -1;
    private static float DEFAULT_WEIGHT = -1;
    private static float DEFAULT_MAX_PAYLOAD = -1;
    private static Battery DEFAULT_BATTERY = new Battery();
    private static Pharmacy DEFAULT_PHARMACY = new Pharmacy();

    public Vehicle() {
        this.m_intId = DEFAULT_ID;
        this.m_intPharmacyId = DEFAULT_PHARMACY_ID;
        this.m_fltPotency = DEFAULT_POTENCY;
        this.m_fltWeight = DEFAULT_WEIGHT;
        this.m_fltMaxPayload = DEFAULT_MAX_PAYLOAD;
        this.m_strCharginStatus = DEFAULT_CHARGING_STATUS;
        this.m_oBattery = DEFAULT_BATTERY;
        this.m_oPharmacy = DEFAULT_PHARMACY;
    }

    public Vehicle(float fltPotency, float fltWeight, float fltMaxPayload, String strCharginStatus,
                   Battery oBatery, Pharmacy oPharmacy) {
        this.m_fltPotency = fltPotency;
        this.m_fltWeight = fltWeight;
        this.m_fltMaxPayload = fltMaxPayload;
        this.m_strCharginStatus = strCharginStatus;
        this.m_oBattery = oBatery;
        this.m_oPharmacy = oPharmacy;
    }

    public Vehicle(int intId, float fltPotency, float fltWeight,
                   float fltMaxPayload, String strCharginStatus, Battery oBaterry, Pharmacy oPharmacy) {
        this.m_intId = intId;
        this.m_fltPotency = fltPotency;
        this.m_fltWeight = fltWeight;
        this.m_fltMaxPayload = fltMaxPayload;
        this.m_strCharginStatus = strCharginStatus;
        this.m_oBattery = oBaterry;
        this.m_oPharmacy = oPharmacy;
    }

    public int getId() {
        return m_intId;
    }

    public void setId(int intId) {
        this.m_intId = intId;
    }

    public boolean hasId(Integer intId) {return this.getId() == intId;}

    public int getPharmacyId() {
        return m_intPharmacyId;
    }

    public void setPharmacyId(int intPharmacyId) {
        this.m_intPharmacyId = intPharmacyId;
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

    public float getMaxPayload() {
        return m_fltMaxPayload;
    }

    public void setMaxPayload(float fltMaxPayload) {
        this.m_fltMaxPayload = fltMaxPayload;
    }

    public String getCharginStatus() {
        return m_strCharginStatus;
    }

    public void setCharginStatus(String strCharginStatus) {
        this.m_strCharginStatus = strCharginStatus;
    }

    public Battery getBattery() {
        return m_oBattery;
    }

    public void setBattery(Battery oBattery) {
        this.m_oBattery = oBattery;
    }

    public Pharmacy getPharmacy() {
        return m_oPharmacy;
    }

    public void setPharmacy(Pharmacy oPharmacy) {
        this.m_oPharmacy = oPharmacy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return m_intId == vehicle.m_intId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_intId);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "Id=" + m_intId +
                ", PharmacyId=" + m_intPharmacyId +
                ", Potency=" + m_fltPotency +
                ", Weight=" + m_fltWeight +
                ", MaxPayload=" + m_fltMaxPayload +
                ", CharginStatus='" + m_strCharginStatus + '\'' +
                ", Battery=" + m_oBattery +
                ", Pharmacy=" + m_oPharmacy +
                '}';
    }
}


