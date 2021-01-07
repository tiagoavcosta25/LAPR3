package lapr.project.model;

import java.util.Objects;

public class Pharmacy {

    private int m_intId;
    private String m_strName;
    private int m_intParkingSlots;
    private int m_intChargingSlots;

    private static int DEFAULT_ID = -1;
    private static String DEFAULT_NAME = "";
    private static int DEFAULT_pARKING_SLOTS = 0;
    private static int DEFAULT_CHARGING_SLOTS = 0;

    public Pharmacy() {
        this.m_intId = DEFAULT_ID;
        this.m_strName= DEFAULT_NAME;
        this.m_intParkingSlots = DEFAULT_pARKING_SLOTS;
        this.m_intChargingSlots = DEFAULT_CHARGING_SLOTS;
    }

    public Pharmacy(int intId, String strName, int intParkingSlots, int intChargingSlots) {
        this.m_intId = intId;
        this.m_strName= strName;
        this.m_intParkingSlots = intParkingSlots;
        this.m_intChargingSlots = intChargingSlots;
    }

    public Pharmacy(String strName, int intParkingSlots, int intChargingSlots) {
        this.m_strName= strName;
        this.m_intParkingSlots = intParkingSlots;
        this.m_intChargingSlots = intChargingSlots;
    }

    public int getId() {
        return m_intId;
    }

    public void setId(int intId) {
        this.m_intId = intId;
    }

    public String getName() {
        return m_strName;
    }

    public void setName(String strName) {
        this.m_strName = strName;
    }

    public int getParkingSlots() {
        return m_intParkingSlots;
    }

    public void setParkingSlots(int intParkingSlots) {
        this.m_intParkingSlots = intParkingSlots;
    }

    public int getChargingSlots() {
        return m_intChargingSlots;
    }

    public void setChargingSlots(int intChargingSlots) {
        this.m_intChargingSlots = intChargingSlots;
    }

    @Override
    public String toString() {
        return "Pharmacy{" +
                "Id = " + m_intId +
                ", Name = '" + m_strName + '\'' +
                ", ParkingSlots = " + m_intParkingSlots +
                ", ChargingSlots = " + m_intChargingSlots +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pharmacy pharmacy = (Pharmacy) o;
        return m_intId == pharmacy.m_intId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_intId);
    }

}
