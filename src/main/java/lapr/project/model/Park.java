package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Park {

    private int mintId;
    private int mintMaxSlotsNumber;
    private Double mfltTotalOutputCurrent;
    private VehicleType menumVehicleType;
    private List<ChargingSlot> mLstChargingSlots;
    private List<NonChargingSlot> mLstNonChargingSlots;

    private static int DEFAULTID = -1;
    private static int DEFAULTMAXSLOTS = -1;
    private static Double DEFAULTTOTALOUTPUTCURRENT = -1d;
    private static VehicleType DEFAULTVEHICLETYPE = VehicleType.NOTDEFINED;
    private static List<ChargingSlot> DEFAULTCHARGINGSLOTS = new ArrayList<>();
    private static List<NonChargingSlot> DEFAULTPARKINGSLOTS = new ArrayList<>();

    public Park(int intId, int intMaxSlotsNumber, Double fltTotalOutputCurrent, VehicleType enumVehicleType) {
        this.mintId = intId;
        this.mintMaxSlotsNumber = intMaxSlotsNumber;
        this.mfltTotalOutputCurrent = fltTotalOutputCurrent;
        this.menumVehicleType = enumVehicleType;
        this.mLstChargingSlots = DEFAULTCHARGINGSLOTS;
        this.mLstNonChargingSlots = DEFAULTPARKINGSLOTS;
    }

    public Park(int intMaxSlotsNumber, Double fltTotalOutputCurrent, VehicleType enumVehicleType) {
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

    public void setId(int mintId) {
        this.mintId = mintId;
    }

    public int getMaxSlotsNumber() {
        return mintMaxSlotsNumber;
    }

    public void setMaxSlotsNumber(int mintMaxSlotsNumber) {
        this.mintMaxSlotsNumber = mintMaxSlotsNumber;
    }

    public VehicleType getVehicleType() {
        return menumVehicleType;
    }

    public void setVehicleType(VehicleType menumVehicleType) {
        this.menumVehicleType = menumVehicleType;
    }

    public List<ChargingSlot> getChargingSlots() {
        return new ArrayList<>(mLstChargingSlots);
    }

    public void setChargingSlots(List<ChargingSlot> mlstChargingSlots) {
        this.mLstChargingSlots = new ArrayList<>(mlstChargingSlots);
    }

    public List<NonChargingSlot> getParkingSlots() {
        return new ArrayList<>(mLstNonChargingSlots);
    }

    public void setParkingSlots(List<NonChargingSlot> mlstNonChargingSlots) {
        this.mLstNonChargingSlots = new ArrayList<>(mlstNonChargingSlots);
    }

    public Double getTotalOutputCurrent() {
        return mfltTotalOutputCurrent;
    }

    public void setTotalOutputCurrent(Double mfltTotalOutputCurrent) {
        this.mfltTotalOutputCurrent = mfltTotalOutputCurrent;
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
