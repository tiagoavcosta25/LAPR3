package lapr.project.model;

import java.util.Objects;

/**
 * Vehicle.
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

public abstract class ParkingSlot {

    /**
     * Parking Slot ID.
     */
    private int mintId;

    /**
     * Parking Slot Vehicle.
     */
    private Vehicle moVehicle;

    /**
     * An empty constructor of ParkingSlot.
     */
    public ParkingSlot() {
        this.mintId = -1;
        this.moVehicle = null;
    }

    /**
     * Builds an instance of ParkingSlot receiving Parking Slot's ID.
     * @param intId Parking Slot's ID.
     */
    public ParkingSlot(int intId) {
        this.mintId = intId;
        this.moVehicle = null;
    }

    /**
     * Builds an instance of ParkingSlot receiving Parking Slot's Vehicle.
     * @param oVehicle Parking Slot's Vehicle.
     */
    public ParkingSlot(Vehicle oVehicle) {
        this.mintId = -1;
        this.moVehicle = oVehicle;
    }

    /**
     * Builds an instance of ParkingSlot receiving Parking Slot's ID and Vehicle.
     * @param intId Parking Slot's ID.
     * @param oVehicle Parking Slot's Vehicle
     */
    public ParkingSlot(int intId, Vehicle oVehicle) {
        this.mintId = intId;
        this.moVehicle = oVehicle;
    }

    /**
     * Returns Parking Slot ID.
     * @return Parking Slot ID.
     */
    public int getId() {
        return mintId;
    }

    /**
     * Modifies Parking Slot ID.
     * @param intId Parking Slot ID.
     */
    public void setId(int intId) {
        this.mintId = intId;
    }

    /**
     * Returns Parking Slot Vehicle.
     * @return Parking Slot Vehicle.
     */
    public Vehicle getVehicle() {
        return moVehicle;
    }

    /**
     * Modifies Parking Slot Vehicle.
     * @param oVehicle Parking Slot Vehicle.
     */
    public void setVehicle(Vehicle oVehicle) {
        this.moVehicle = oVehicle;
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
     * Returns the textual description of the ParkingSlot in the format: ID and Vehicle.
     * @return
     */
    @Override
    public String toString() {
        return "ParkingSlot{" +
                "m_intId=" + mintId +
                ", m_oVehicle=" + moVehicle +
                '}';
    }
}
