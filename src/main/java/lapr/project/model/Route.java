package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Route {

    private VehicleType vehicleType;
    private VehicleModel vehicleModel;
    private List<Address> addressList;
    private List<Path> pathList;
    private List<Double> energyList;
    private List<Double> timeList;
    private List<Address> chargeStops;

    public Route() {
        this.vehicleType = VehicleType.NOTDEFINED;
        this.vehicleModel = null;
        this.pathList = new ArrayList<>();
        this.addressList = new ArrayList<>();
        this.energyList = new ArrayList<>();
        this.timeList = new ArrayList<>();
        this.chargeStops = new ArrayList<>();
    }

    public Route(VehicleType vehicleType, List<Address> addressList) {
        this.vehicleType = vehicleType;
        this.vehicleModel = null;
        this.addressList = addressList;
        this.pathList = new ArrayList<>();
        this.energyList = new ArrayList<>();
        this.timeList = new ArrayList<>();
        this.chargeStops = new ArrayList<>();
    }

    public VehicleModel getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(VehicleModel vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public List<Path> getPathList() {
        return pathList;
    }

    public void setPathList(List<Path> pathList) {
        this.pathList = pathList;
    }

    public List<Double> getEnergyList() {
        return energyList;
    }

    public void setEnergyList(List<Double> energyList) {
        this.energyList = energyList;
    }

    public List<Double> getTimeList() {
        return timeList;
    }

    public void setTimeList(List<Double> timeList) {
        this.timeList = timeList;
    }

    public List<Address> getChargeStops() {
        return chargeStops;
    }

    public void setChargeStops(List<Address> chargeStops) {
        this.chargeStops = chargeStops;
    }

    public double getTotalTime() {
        if(this.addressList.isEmpty())
            return Double.MAX_VALUE;
        double time = 0;
        for(double t : timeList)
            time += t;
        return time;
    }

    public double getTotalEnergy() {
        if(this.addressList.isEmpty())
            return Double.MAX_VALUE;
        double energy = 0;
        for(double e : energyList)
            energy += e;
        return energy;
    }

    public double getTotalDistance() {
        if(this.addressList.isEmpty())
            return Double.MAX_VALUE;
        double distance = 0;
        for(int i = 0; i < addressList.size() - 1; i++)
            distance += addressList.get(i).distanceTo(addressList.get(i + 1));
        return distance;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(vehicleType, vehicleModel, addressList, pathList, energyList, timeList, chargeStops);
    }

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
