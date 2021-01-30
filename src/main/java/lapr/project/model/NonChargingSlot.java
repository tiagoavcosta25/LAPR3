package lapr.project.model;

/**
 * Non Charging Slot.
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

public class NonChargingSlot extends ParkingSlot implements Comparable{

    /**
     * An empty constructor of NonChargingSlot.
     */
    public NonChargingSlot() {
    }

    /**
     * Builds an instance of NonChargingSlot receiving Non Chargin Slot ID.
     * @param intId Non Chargin Slot ID.
     */
    public NonChargingSlot(int intId) {
        super(intId);
    }

    /**
     * Builds an instance of NonChargingSlot receiving a Vehicle.
     * @param oVehicle Vehicle.
     */
    public NonChargingSlot(Vehicle oVehicle) {
        super(oVehicle);
    }

    /**
     * Builds an instance of NonChargingSlot receiving Non Chargin Slot ID and Vehicle.
     * @param intId Non Chargin Slot ID.
     * @param oVehicle Vehicle.
     */
    public NonChargingSlot(int intId, Vehicle oVehicle) {
        super(intId, oVehicle);
    }

    /**
     * Compare Override.
     * @param o Object.
     * @return a negative integer, zero, or a positive integer as the first
     * argument is less than, equal to, or greater than the second.
     */
    @Override
    public int compareTo(Object o) {
        NonChargingSlot p = (NonChargingSlot) o;
        return this.getId() - p.getId();
    }

    /**
     * Returns a logical value referring to the equality (or lack thereof) of the Charging Slot passed
     * by parameter with the instance of the Vehicle class.
     *
     * @param o Object.
     * @return a logical value referring to the equality (or lack thereof).
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NonChargingSlot that = (NonChargingSlot) o;
        return super.getId() == that.getId();
    }
}
