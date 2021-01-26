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

    private static int mDEFAULTID = -1;
    private static int mDEFAULTMAXSLOTS = -1;
    private static Double mDEFAULTTOTALOUTPUTCURRENT = -1d;
    private static VehicleType mDEFAULTVEHICLETYPE = VehicleType.NOTDEFINED;
    private static List<ChargingSlot> mDEFAULTCHARGINGSLOTS = new ArrayList<>();
    private static List<NonChargingSlot> mDEFAULTPARKINGSLOTS = new ArrayList<>();

    public Park(int intId, int intMaxSlotsNumber, Double fltTotalOutputCurrent, VehicleType enumVehicleType) {
        this.mintId = intId;
        this.mintMaxSlotsNumber = intMaxSlotsNumber;
        this.mfltTotalOutputCurrent = fltTotalOutputCurrent;
        this.menumVehicleType = enumVehicleType;
        this.mLstChargingSlots = mDEFAULTCHARGINGSLOTS;
        this.mLstNonChargingSlots = mDEFAULTPARKINGSLOTS;
    }

    public Park(int intMaxSlotsNumber, Double fltTotalOutputCurrent, VehicleType enumVehicleType) {
        this.mintMaxSlotsNumber = intMaxSlotsNumber;
        this.mfltTotalOutputCurrent = fltTotalOutputCurrent;
        this.menumVehicleType = enumVehicleType;
        this.mLstChargingSlots = mDEFAULTCHARGINGSLOTS;
        this.mLstNonChargingSlots = mDEFAULTPARKINGSLOTS;
    }

    public Park() {
        this.mintId = mDEFAULTID;
        this.mintMaxSlotsNumber = mDEFAULTMAXSLOTS;
        this.mfltTotalOutputCurrent = mDEFAULTTOTALOUTPUTCURRENT;
        this.menumVehicleType = mDEFAULTVEHICLETYPE;
        this.mLstChargingSlots = mDEFAULTCHARGINGSLOTS;
        this.mLstNonChargingSlots = mDEFAULTPARKINGSLOTS;
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
