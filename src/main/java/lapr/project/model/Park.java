package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Park {

    private int mintId;
    private int mintMaxSlotsNumber;
    private float mfltTotalOutputCurrent;
    private VehicleType menumVehicleType;
    private List<ChargingSlot> mLstChargingSlots;
    private List<NonChargingSlot> mLstNonChargingSlots;

    private static int DEFAULTID = -1;
    private static int DEFAULTMAXSLOTS = -1;
    private static float DEFAULTTOTALOUTPUTCURRENT = -1f;
    private static VehicleType DEFAULTVEHICLETYPE = VehicleType.NOTDEFINED;
    private static List<ChargingSlot> DEFAULTCHARGINGSLOTS = new ArrayList<>();
    private static List<NonChargingSlot> DEFAULTPARKINGSLOTS = new ArrayList<>();

    public Park(int intId, int intMaxSlotsNumber, float fltTotalOutputCurrent, VehicleType enumVehicleType) {
        this.mintId = intId;
        this.mintMaxSlotsNumber = intMaxSlotsNumber;
        this.mfltTotalOutputCurrent = fltTotalOutputCurrent;
        this.menumVehicleType = enumVehicleType;
        this.mLstChargingSlots = DEFAULTCHARGINGSLOTS;
        this.mLstNonChargingSlots = DEFAULTPARKINGSLOTS;
    }

    public Park(int intMaxSlotsNumber, float fltTotalOutputCurrent, VehicleType enumVehicleType) {
        this.mintMaxSlotsNumber = intMaxSlotsNumber;
        this.mfltTotalOutputCurrent = fltTotalOutputCurrent;
        this.menumVehicleType = enumVehicleType;
        this.mLstChargingSlots = DEFAULTCHARGINGSLOTS;
        this.mLstNonChargingSlots = DEFAULTPARKINGSLOTS;
    }

    public Park() {
        this.mintId = DEFAULTID;
        this.mintMaxSlotsNumber = DEFAULTMAXSLOTS;
        this.mfltTotalOutputCurrent = DEFAULTTOTALOUTPUTCURRENT;
        this.menumVehicleType = DEFAULTVEHICLETYPE;
        this.mLstChargingSlots = DEFAULTCHARGINGSLOTS;
        this.mLstNonChargingSlots = DEFAULTPARKINGSLOTS;
    }

    public int getId() {
        return mintId;
    }

    public void setId(int m_intId) {
        this.mintId = m_intId;
    }

    public int getMaxSlotsNumber() {
        return mintMaxSlotsNumber;
    }

    public void setMaxSlotsNumber(int m_intMaxSlotsNumber) {
        this.mintMaxSlotsNumber = m_intMaxSlotsNumber;
    }

    public VehicleType getVehicleType() {
        return menumVehicleType;
    }

    public void setVehicleType(VehicleType m_enumVehicleType) {
        this.menumVehicleType = m_enumVehicleType;
    }

    public List<ChargingSlot> getChargingSlots() {
        return new ArrayList<>(mLstChargingSlots);
    }

    public void setChargingSlots(List<ChargingSlot> m_lstChargingSlots) {
        this.mLstChargingSlots = new ArrayList<>(m_lstChargingSlots);
    }

    public List<NonChargingSlot> getParkingSlots() {
        return new ArrayList<>(mLstNonChargingSlots);
    }

    public void setParkingSlots(List<NonChargingSlot> m_lstNonChargingSlots) {
        this.mLstNonChargingSlots = new ArrayList<>(m_lstNonChargingSlots);
    }

    public float getTotalOutputCurrent() {
        return mfltTotalOutputCurrent;
    }

    public void getTotalOutputCurrent(float m_fltTotalOutputCurrent) {
        this.mfltTotalOutputCurrent = m_fltTotalOutputCurrent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Park park = (Park) o;
        return mintId == park.mintId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mintId);
    }

    @Override
    public String toString() {
        return "Park{" +
                "m_intId=" + mintId +
                ", m_intMaxSlotsNumber=" + mintMaxSlotsNumber +
                '}';
    }
}
