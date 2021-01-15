package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Park {

    private int m_intId;
    private int m_intMaxSlotsNumber;
    private Pharmacy m_oPharmacy;
    private List<ChargingSlot> m_lstChargingSlots;
    private List<NonChargingSlot> m_lstNonChargingSlots;

    private static int DEFAULT_ID = -1;
    private static int DEFAULT_MAX_SLOTS = -1;
    private static Pharmacy DEFAULT_PHARMACY = new Pharmacy();
    private static List<ChargingSlot> DEFAULT_CHARGING_SLOTS = new ArrayList<>();
    private static List<NonChargingSlot> DEFAULT_PARKING_SLOTS = new ArrayList<>();

    public Park(int intId, int intMaxSlotsNumber, Pharmacy oPharmacy) {
        this.m_intId = intId;
        this.m_intMaxSlotsNumber = intMaxSlotsNumber;
        this.setPharmacy(oPharmacy);
        this.m_lstChargingSlots = DEFAULT_CHARGING_SLOTS;
        this.m_lstNonChargingSlots = DEFAULT_PARKING_SLOTS;
    }

    public Park() {
        this.m_intId = DEFAULT_ID;
        this.m_intMaxSlotsNumber = DEFAULT_MAX_SLOTS;
        this.setPharmacy(DEFAULT_PHARMACY);
        this.m_lstChargingSlots = DEFAULT_CHARGING_SLOTS;
        this.m_lstNonChargingSlots = DEFAULT_PARKING_SLOTS;
    }

    public Park(int intMaxSlotsNumber) {
        this.setMaxSlotsNumber(intMaxSlotsNumber);
    }

    public int getId() {
        return m_intId;
    }

    public void setId(int m_intId) {
        this.m_intId = m_intId;
    }

    public int getMaxSlotsNumber() {
        return m_intMaxSlotsNumber;
    }

    public void setMaxSlotsNumber(int m_intMaxSlotsNumber) {
        this.m_intMaxSlotsNumber = m_intMaxSlotsNumber;
    }

    public List<ChargingSlot> getChargingSlots() {
        return new ArrayList<>(m_lstChargingSlots);
    }

    public void setChargingSlots(List<ChargingSlot> m_lstChargingSlots) {
        this.m_lstChargingSlots = new ArrayList<>(m_lstChargingSlots);
    }

    public List<NonChargingSlot> getParkingSlots() {
        return new ArrayList<>(m_lstNonChargingSlots);
    }

    public void setParkingSlots(List<NonChargingSlot> m_lstNonChargingSlots) {
        this.m_lstNonChargingSlots = new ArrayList<>(m_lstNonChargingSlots);
    }

    public Pharmacy getPharmacy() {
        return m_oPharmacy;
    }

    public void setPharmacy(Pharmacy m_oPharmacy) {
        this.m_oPharmacy = m_oPharmacy;
    }

    public boolean addParkingSlot(NonChargingSlot oNonChargingSlot) {
        if ((this.m_lstNonChargingSlots.size() + this.m_lstChargingSlots.size() + 1) <= this.m_intMaxSlotsNumber){
            this.m_lstNonChargingSlots.add(oNonChargingSlot);
            return true;
        }
        return false;
    }

    public boolean addChargingSlot(ChargingSlot oChargingSlot) {
        if ((this.m_lstNonChargingSlots.size() + this.m_lstChargingSlots.size() + 1) <= this.m_intMaxSlotsNumber){
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
        return m_intId == park.m_intId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_intId);
    }

    @Override
    public String toString() {
        return "Park{" +
                "m_intId=" + m_intId +
                ", m_intMaxSlotsNumber=" + m_intMaxSlotsNumber +
                '}';
    }
}
