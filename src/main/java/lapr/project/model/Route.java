package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Route.
 * <p>
 * Group: Team Lisa [G-021]
 * ______________________________________________________
 *
 * @author António Barbosa <1190404@isep.ipp.pt>
 * @author Ernesto Rodrigues <1190560@isep.ipp.pt>
 * @author Jessica Alves <1190682@isep.ipp.pt>
 * @author Pedro Santos <1190967@isep.ipp.pt>
 * @author Rodrigo Costa <1191014@isep.ipp.pt>
 * @author Tiago Costa <1191460@isep.ipp.pt>
 */
public class Route {

    /**
     * Vehicle Type.
     */
    private VehicleType vehicleType;

    /**
     * Vehicle Model.
     */
    private VehicleModel vehicleModel;

    /**
     * List Of Addresses.
     */
    private List<Address> addressList;

    /**
     * List Of Paths.
     */
    private List<Path> pathList;

    /**
     * List Of Energy.
     */
    private List<Double> energyList;

    /**
     * List of Time.
     */
    private List<Double> timeList;

    /**
     * List of Charge Stops.
     */
    private List<Address> chargeStops;

    /**
     * Empty Constructor.
     */
    public Route() {
        this.vehicleType = VehicleType.NOTDEFINED;
        this.vehicleModel = null;
        this.pathList = new ArrayList<>();
        this.addressList = new ArrayList<>();
        this.energyList = new ArrayList<>();
        this.timeList = new ArrayList<>();
        this.chargeStops = new ArrayList<>();
    }

    /**
     * Route Constructor.
     * @param vehicleType Vehicle Type.
     * @param addressList List Of Addresses.
     */
    public Route(VehicleType vehicleType, List<Address> addressList) {
        this.vehicleType = vehicleType;
        this.vehicleModel = null;
        this.addressList = new ArrayList<>(addressList);
        this.pathList = new ArrayList<>();
        this.energyList = new ArrayList<>();
        this.timeList = new ArrayList<>();
        this.chargeStops = new ArrayList<>();
    }

    /**
     * Getter for the Vehicle Model.
     * @return Vehicle Model.
     */
    public VehicleModel getVehicleModel() {
        return vehicleModel;
    }

    /**
     * Setter for the Vehicle Model.
     * @param vehicleModel Vehicle Model.
     */
    public void setVehicleModel(VehicleModel vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    /**
     * Getter for the Vehicle Type.
     * @return Vehicle Type.
     */
    public VehicleType getVehicleType() {
        return vehicleType;
    }

    /**
     * Setter for the Vehicle Type.
     * @param vehicleType Vehicle Type.
     */
    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    /**
     * Getter for the Address List.
     * @return Address List.
     */
    public List<Address> getAddressList() {
        return new ArrayList<>(addressList);
    }

    /**
     * Getter for the Address List.
     * @param addressList Address List.
     */
    public void setAddressList(List<Address> addressList) {
        this.addressList = new ArrayList<>(addressList);
    }

    /**
     * Getter for the Path List.
     * @return Path List.
     */
    public List<Path> getPathList() {
        return new ArrayList<>(pathList);
    }

    /**
     * Setter for the Path List.
     * @param pathList Path List.
     */
    public void setPathList(List<Path> pathList) {
        this.pathList = new ArrayList<>(pathList);
    }

    /**
     * Getter for the Energy List.
     * @return Energy List.
     */
    public List<Double> getEnergyList() {
        return new ArrayList<>(energyList);
    }

    /**
     * Setter for the Energy List.
     * @param energyList Energy List.
     */
    public void setEnergyList(List<Double> energyList) {
        this.energyList = new ArrayList<>(energyList);
    }

    /**
     * Getter for the Time List.
     * @return Time List.
     */
    public List<Double> getTimeList() {
        return new ArrayList<>(timeList);
    }

    /**
     * Setter for the Time List.
     * @param timeList Time List.
     */
    public void setTimeList(List<Double> timeList) {
        this.timeList = new ArrayList<>(timeList);
    }

    /**
     * Getter for the Charge Stops List.
     * @return Charge Stops List.
     */
    public List<Address> getChargeStops() {
        return new ArrayList<>(chargeStops);
    }

    /**
     * Setter for the Charge Stops List.
     * @param chargeStops Charge Stops List.
     */
    public void setChargeStops(List<Address> chargeStops) {
        this.chargeStops = new ArrayList<>(chargeStops);
    }

    /**
     * Method that calculates the total time.
     * @return Total Time.
     */
    public double getTotalTime() {
        if(this.addressList.isEmpty())
            return Double.MAX_VALUE;
        double time = 0;
        for(double t : timeList)
            time += t;
        return time;
    }

    /**
     * Method that calculates the energy.
     * @return Energy.
     */
    public double getTotalEnergy() {
        if(this.addressList.isEmpty())
            return Double.MAX_VALUE;
        double energy = 0;
        for(double e : energyList)
            energy += e;
        return energy;
    }

    /**
     * Method that calculates the total distance.
     * @return Total Distance.
     */
    public double getTotalDistance() {
        if(this.addressList.isEmpty())
            return Double.MAX_VALUE;
        double distance = 0;
        for(int i = 0; i < addressList.size() - 1; i++)
            distance += addressList.get(i).distanceTo(addressList.get(i + 1));
        return distance;
    }

    /**
     * Equals Override.
     * @param o object.
     * @return true if the objects are equal, false they are not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return vehicleType == route.vehicleType &&
                Objects.equals(vehicleModel, route.vehicleModel) &&
                Objects.equals(addressList, route.addressList) &&
                Objects.equals(pathList, route.pathList) &&
                Objects.equals(energyList, route.energyList) &&
                Objects.equals(timeList, route.timeList) &&
                Objects.equals(chargeStops, route.chargeStops);
    }

    /**
     * Hash Code Override.
     * @return Hash Code Value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(vehicleType, vehicleModel, addressList, pathList, energyList, timeList, chargeStops);
    }

    /**
     * toString Override.
     * @return Route Information.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        if(this.vehicleModel == null)
            return "";
        str.append("\nBest vehicle model for delivery: ").append(this.vehicleModel.toStringRoute()).append("\n");
        str.append("Best route for delivery: ").append(this.addressList).append("\n");
        str.append(String.format("Route is %.2f meters.", this.getTotalDistance())).append("\n");
        str.append(String.format("Delivery route takes %.2f minutes.", this.getTotalTime())).append("\n");
        str.append(String.format("Delivery route costs %.5f kWh.", this.getTotalEnergy())).append("\n");

        if(chargeStops.isEmpty())
            str.append("No need to recharge during delivery.").append("\n\n");
        else {
            str.append("Delivery recharge spots: ").append(chargeStops).append("\n\n");
        }

        str.append("Route characteristics: ").append("\n");
        for(int i = 0; i < pathList.size(); i++) {
            str.append("\n\t").append(String.format("Path %s:", this.pathList.get(i).getName())).append("\n").
            append(this.pathList.get(i).toStringRoute()).append("\n").
                    append("\t\t").append(String.format("Path is %.2f meters.",
                    this.addressList.get(i).distanceTo(this.addressList.get(i + 1)))).append("\n").
                    append("\t\t").append(String.format("Path takes %.2f minutes.", this.timeList.get(i))).append("\n").
                    append("\t\t").append(String.format("Path costs %.5f kWh.", this.energyList.get(i))).append("\n");
            if(this.chargeStops.contains(addressList.get(i)))
                str.append("\t\t").append("Vehicle can recharge before completing path!").append("\n");
        }
        return str.toString();
    }
}
