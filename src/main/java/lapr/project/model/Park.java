package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Park {

    private int m_intId;
    private int m_intMaxSlotsNumber;
    private Pharmacy m_oPharmacy;
    private List<ChargingSlot> m_lstChargingSlots;
    private List<ParkingSlot> m_lstParkingSlots;

    private static int DEFAULT_ID = -1;
    private static int DEFAULT_MAX_SLOTS = -1;
    private static Pharmacy DEFAULT_PHARMACY = new Pharmacy();
    private static List<ChargingSlot> DEFAULT_CHARGING_SLOTS = new ArrayList<>();
    private static List<ParkingSlot> DEFAULT_PARKING_SLOTS = new ArrayList<>();

    public Park(int intId, int intMaxSlotsNumber, Pharmacy oPharmacy) {
        this.m_intId = intId;
        this.m_intMaxSlotsNumber = intMaxSlotsNumber;
        this.setM_oPharmacy(oPharmacy);
        this.m_lstChargingSlots = DEFAULT_CHARGING_SLOTS;
        this.m_lstParkingSlots = DEFAULT_PARKING_SLOTS;
    }

    public Park() {
        this.m_intId = DEFAULT_ID;
        this.m_intMaxSlotsNumber = DEFAULT_MAX_SLOTS;
        this.setM_oPharmacy(DEFAULT_PHARMACY);
        this.m_lstChargingSlots = DEFAULT_CHARGING_SLOTS;
        this.m_lstParkingSlots = DEFAULT_PARKING_SLOTS;
    }

    public Park(int intMaxSlotsNumber) {
        this.setM_intMaxSlotsNumber(intMaxSlotsNumber);
    }

    public int getM_intId() {
        return m_intId;
    }

    public void setM_intId(int m_intId) {
        this.m_intId = m_intId;
    }

    public int getM_intMaxSlotsNumber() {
        return m_intMaxSlotsNumber;
    }

    public void setM_intMaxSlotsNumber(int m_intMaxSlotsNumber) {
        this.m_intMaxSlotsNumber = m_intMaxSlotsNumber;
    }

    public List<ChargingSlot> getM_lstChargingSlots() {
        return m_lstChargingSlots;
    }

    public void setM_lstChargingSlots(List<ChargingSlot> m_lstChargingSlots) {
        this.m_lstChargingSlots = m_lstChargingSlots;
    }

    public List<ParkingSlot> getM_lstParkingSlots() {
        return m_lstParkingSlots;
    }

    public void setM_lstParkingSlots(List<ParkingSlot> m_lstParkingSlots) {
        this.m_lstParkingSlots = m_lstParkingSlots;
    }

    public boolean addParkingSlot(ParkingSlot oParkingSlot) {
        if ((this.m_lstParkingSlots.size() + this.m_lstChargingSlots.size() + 1) <= this.m_intMaxSlotsNumber){
            this.m_lstParkingSlots.add(oParkingSlot);
            return true;
        }
        return false;
    }

    public boolean addChargingSlot(ChargingSlot oChargingSlot) {
        if ((this.m_lstParkingSlots.size() + this.m_lstChargingSlots.size() + 1) <= this.m_intMaxSlotsNumber){
            this.m_lstChargingSlots.add(oChargingSlot);
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Park park = (Park) o;
        return m_intId == park.m_intId &&
                m_intMaxSlotsNumber == park.m_intMaxSlotsNumber &&
                m_lstChargingSlots.equals(park.m_lstChargingSlots) &&
                m_lstParkingSlots.equals(park.m_lstParkingSlots);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_intId, m_intMaxSlotsNumber, m_lstChargingSlots, m_lstParkingSlots);
    }

    @Override
    public String toString() {
        return "Park{" +
                "m_intId=" + m_intId +
                ", m_intMaxSlotsNumber=" + m_intMaxSlotsNumber +
                ", m_lstChargingSlots=" + m_lstChargingSlots +
                ", m_lstParkingSlots=" + m_lstParkingSlots +
                '}';
    }

    public Pharmacy getM_oPharmacy() {
        return m_oPharmacy;
    }

    public void setM_oPharmacy(Pharmacy m_oPharmacy) {
        this.m_oPharmacy = m_oPharmacy;
    }
}
