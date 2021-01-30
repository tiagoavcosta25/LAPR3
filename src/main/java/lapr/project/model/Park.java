package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Park.
 *
 * Group: Team Lisa [G-021]
 * ______________________________________________________
 * @author António Barbosa <1190404@isep.ipp.pt>
 * @author Ernesto Rodrigues <1190560@isep.ipp.pt>
 * @author Jessica Alves <1190682@isep.ipp.pt>
 * @author Pedro Santos <1190967@isep.ipp.pt>
 * @author Rodrigo Costa <1191014@isep.ipp.pt>
 * @author Tiago Costa <1191460@isep.ipp.pt>
 */

public class Park {

    /**
     * Park's ID.
     */
    private int mintId;

    /**
     * Park's Max Slots Number.
     */
    private int mintMaxSlotsNumber;

    /**
     * Park's Total Current Output.
     */
    private Double mfltTotalOutputCurrent;

    /**
     * Park's Vehicle Type.
     */
    private VehicleType menumVehicleType;

    /**
     * List of Park's Charging Slots.
     */
    private List<ChargingSlot> mLstChargingSlots;

    /**
     * List of Park's Non Charging Slots.
     */
    private List<NonChargingSlot> mLstNonChargingSlots;

    /**
     * Park's Default ID.
     */
    private static int mDEFAULTID = -1;

    /**
     * Park's Default Max Slots.
     */
    private static int mDEFAULTMAXSLOTS = -1;

    /**
     * Park's Default Total Current Output.
     */
    private static Double mDEFAULTTOTALOUTPUTCURRENT = -1d;

    /**
     * Park's Dfault Vehicle Type.
     */
    private static VehicleType mDEFAULTVEHICLETYPE = VehicleType.NOTDEFINED;

    /**
     * Default List of Park's Charging Slots.
     */
    private static List<ChargingSlot> mDEFAULTCHARGINGSLOTS = new ArrayList<>();

    /**
     * Default List of Park's Non Charging Slots.
     */
    private static List<NonChargingSlot> mDEFAULTPARKINGSLOTS = new ArrayList<>();

    /**
     * Builds an instance of Park receiving Park ID, Max Slots, Total Output Current and Vehicle Type.
     * @param intId Park's ID.
     * @param intMaxSlotsNumber Park'Max Slots.
     * @param fltTotalOutputCurrent Park's Total Output Current.
     * @param enumVehicleType Park's Vehicle Type.
     */
    public Park(int intId, int intMaxSlotsNumber, Double fltTotalOutputCurrent, VehicleType enumVehicleType) {
        this.mintId = intId;
        this.mintMaxSlotsNumber = intMaxSlotsNumber;
        this.mfltTotalOutputCurrent = fltTotalOutputCurrent;
        this.menumVehicleType = enumVehicleType;
        this.mLstChargingSlots = mDEFAULTCHARGINGSLOTS;
        this.mLstNonChargingSlots = mDEFAULTPARKINGSLOTS;
    }

    /**
     * Builds an instance of Park receiving Park Max Slots, Total Output Current and Vehicle Type.
     * @param intMaxSlotsNumber
     * @param intMaxSlotsNumber Park'Max Slots.
     * @param fltTotalOutputCurrent Park's Total Output Current.
     * @param enumVehicleType Park's Vehicle Type.
     */
    public Park(int intMaxSlotsNumber, Double fltTotalOutputCurrent, VehicleType enumVehicleType) {
        this.mintMaxSlotsNumber = intMaxSlotsNumber;
        this.mfltTotalOutputCurrent = fltTotalOutputCurrent;
        this.menumVehicleType = enumVehicleType;
        this.mLstChargingSlots = mDEFAULTCHARGINGSLOTS;
        this.mLstNonChargingSlots = mDEFAULTPARKINGSLOTS;
    }

    /**
     * An empty constructor of Park.
     */
    public Park() {
        this.mintId = mDEFAULTID;
        this.mintMaxSlotsNumber = mDEFAULTMAXSLOTS;
        this.mfltTotalOutputCurrent = mDEFAULTTOTALOUTPUTCURRENT;
        this.menumVehicleType = mDEFAULTVEHICLETYPE;
        this.mLstChargingSlots = mDEFAULTCHARGINGSLOTS;
        this.mLstNonChargingSlots = mDEFAULTPARKINGSLOTS;
    }

    /**
     * Returns Park's ID.
     * @return Park's ID.
     */
    public int getId() {
        return mintId;
    }

    /**
     * Modifies Park's ID.
     * @param mintId Park's ID.
     */
    public void setId(int mintId) {
        this.mintId = mintId;
    }

    /**
     * Returns Park's Max Slots.
     * @return Park's Max Slots.
     */
    public int getMaxSlotsNumber() {
        return mintMaxSlotsNumber;
    }

    /**
     * Modifies Park's Max Slots.
     * @param mintMaxSlotsNumber Park's Max Slots.
     */
    public void setMaxSlotsNumber(int mintMaxSlotsNumber) {
        this.mintMaxSlotsNumber = mintMaxSlotsNumber;
    }

    /**
     * Returns Park's Vehicle Type.
     * @return Park's Vehicle Type.
     */
    public VehicleType getVehicleType() {
        return menumVehicleType;
    }

    /**
     * Modifies Park's Vehicle Type.
     * @param menumVehicleType Park's Vehicle Type.
     */
    public void setVehicleType(VehicleType menumVehicleType) {
        this.menumVehicleType = menumVehicleType;
    }

    /**
     * Rerurns List of Park's Charging Slots.
     * @return List of Park's Charging Slots.
     */
    public List<ChargingSlot> getChargingSlots() {
        return new ArrayList<>(mLstChargingSlots);
    }

    /**
     * Modifies List of Park's Charging Slots.
     * @param mlstChargingSlots List of Park's Charging Slots.
     */
    public void setChargingSlots(List<ChargingSlot> mlstChargingSlots) {
        this.mLstChargingSlots = new ArrayList<>(mlstChargingSlots);
    }

    /**
     * Returns List of Park's Non Charging Slots.
     * @return List of Park's Non Charging Slots.
     */
    public List<NonChargingSlot> getParkingSlots() {
        return new ArrayList<>(mLstNonChargingSlots);
    }

    /**
     * Modifeis List of Park's Non Charging Slots.
     * @param mlstNonChargingSlots List of Park's Non Charging Slots.
     */
    public void setParkingSlots(List<NonChargingSlot> mlstNonChargingSlots) {
        this.mLstNonChargingSlots = new ArrayList<>(mlstNonChargingSlots);
    }

    /**
     * Reurns Park's Total Output Current.
     * @return Park's Total Output Current.
     */
    public Double getTotalOutputCurrent() {
        return mfltTotalOutputCurrent;
    }

    /**
     * Modifies Park's Total Output Current.
     * @param mfltTotalOutputCurrent Park's Total Output Current.
     */
    public void setTotalOutputCurrent(Double mfltTotalOutputCurrent) {
        this.mfltTotalOutputCurrent = mfltTotalOutputCurrent;
    }

    /**
     * Returns a logical value referring to the equality (or lack thereof) of the Park passed
     * by parameter with the instance of the Park class.
     * @param o Object.
     * @return a logical value referring to the equality (or lack thereof).
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Park park = (Park) o;
        return mintId == park.mintId;
    }

    /**
     * Returns a hash code value for the object.
     * @return a hash code value for the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(mintId);
    }

    /**
     * Returns the textual description of the Park in the format: ID and Max Slots Number.
     *
     * @return textual description of the Park.
     */
    @Override
    public String toString() {
        return "Park{" +
                "m_intId=" + mintId +
                ", m_intMaxSlotsNumber=" + mintMaxSlotsNumber +
                '}';
    }
}
