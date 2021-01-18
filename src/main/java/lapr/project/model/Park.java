package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Park {

    private int m_intId;
    private int m_intMaxSlotsNumber;
    private float m_fltTotalOutputCurrent;
    private VehicleType m_enumVehicleType;
    private List<ChargingSlot> m_lstChargingSlots;
    private List<NonChargingSlot> m_lstNonChargingSlots;

    private static int DEFAULT_ID = -1;
    private static int DEFAULT_MAX_SLOTS = -1;
    private static float DEFAULT_TOTAL_OUTPUT_CURRENT = -1f;
    private static VehicleType DEFAULT_VEHICLE_TYPE = VehicleType.NOTDEFINED;
    private static List<ChargingSlot> DEFAULT_CHARGING_SLOTS = new ArrayList<>();
    private static List<NonChargingSlot> DEFAULT_PARKING_SLOTS = new ArrayList<>();

    public Park(int intId, int intMaxSlotsNumber, float fltTotalOutputCurrent, VehicleType enumVehicleType) {
        this.m_intId = intId;
        this.m_intMaxSlotsNumber = intMaxSlotsNumber;
        this.m_fltTotalOutputCurrent = fltTotalOutputCurrent;
        this.m_enumVehicleType = enumVehicleType;
        this.m_lstChargingSlots = DEFAULT_CHARGING_SLOTS;
        this.m_lstNonChargingSlots = DEFAULT_PARKING_SLOTS;
    }

    public Park(int intMaxSlotsNumber, float fltTotalOutputCurrent, VehicleType enumVehicleType) {
        this.m_intMaxSlotsNumber = intMaxSlotsNumber;
        this.m_fltTotalOutputCurrent = fltTotalOutputCurrent;
        this.m_enumVehicleType = enumVehicleType;
        this.m_lstChargingSlots = DEFAULT_CHARGING_SLOTS;
        this.m_lstNonChargingSlots = DEFAULT_PARKING_SLOTS;
    }

    public Park() {
        this.m_intId = DEFAULT_ID;
        this.m_intMaxSlotsNumber = DEFAULT_MAX_SLOTS;
        this.m_fltTotalOutputCurrent = DEFAULT_TOTAL_OUTPUT_CURRENT;
        this.m_enumVehicleType = DEFAULT_VEHICLE_TYPE;
        this.m_lstChargingSlots = DEFAULT_CHARGING_SLOTS;
        this.m_lstNonChargingSlots = DEFAULT_PARKING_SLOTS;
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

    public VehicleType getVehicleType() {
        return m_enumVehicleType;
    }

    public void setVehicleType(VehicleType m_enumVehicleType) {
        this.m_enumVehicleType = m_enumVehicleType;
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

    public float getTotalOutputCurrent() {
        return m_fltTotalOutputCurrent;
    }

    public void getTotalOutputCurrent(float m_fltTotalOutputCurrent) {
        this.m_fltTotalOutputCurrent = m_fltTotalOutputCurrent;
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
