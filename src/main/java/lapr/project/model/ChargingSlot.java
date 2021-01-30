package lapr.project.model;

import java.util.Objects;

/**
 * Charging Slot.
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

public class ChargingSlot extends ParkingSlot implements Comparable{

    /**
     * An empty constructor of ChargingSlot.
     */
    public ChargingSlot() {
    }

    /**
     * Builds an instance of ChargingSlot receiving Chargin Slot ID.
     * @param mintId Chargin Slot ID.
     */
    public ChargingSlot(int mintId) {
        super(mintId);
    }

    /**
     * Builds an instance of ChargingSlot receiving a Vehicle.
     * @param moVehicle Vehicle.
     */
    public ChargingSlot(Vehicle moVehicle) {
        super(moVehicle);
    }

    /**
     * Builds an instance of ChargingSlot receiving Chargin Slot ID and Vehicle.
     * @param mintId Chargin Slot ID.
     * @param moVehicle Vehicle.
     */
    public ChargingSlot(int mintId, Vehicle moVehicle) {
        super(mintId, moVehicle);
    }

    /**
     * Compare Override.
     * @param o Object.
     * @return a negative integer, zero, or a positive integer as the first
     * argument is less than, equal to, or greater than the second.
     */
    @Override
    public int compareTo(Object o) {
        ChargingSlot p = (ChargingSlot) o;
        return this.getId() - p.getId();
    }

    /**
     * Returns a logical value referring to the equality (or lack thereof) of the Charging Slot passed
     * by parameter with the instance of the CharginSlot class.
     *
     * @param o Object.
     * @return a logical value referring to the equality (or lack thereof).
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChargingSlot that = (ChargingSlot) o;
        return super.getId() == that.getId();
    }

    /**
     * Returns a hash code value for the object.
     * @return a hash code value for the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.getId());
    }
}
