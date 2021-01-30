package lapr.project.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Route {

    private VehicleType vehicleType;
    private List<Address> addressList;
    private List<Path> pathList;
    private List<Double> energyList;
    private List<Double> timeList;
    private List<Address> chargeStops;

    public Route() {
        this.vehicleType = VehicleType.NOTDEFINED;
        this.pathList = new ArrayList<>();
        this.addressList = new ArrayList<>();
        this.energyList = new ArrayList<>();
        this.timeList = new ArrayList<>();
        this.chargeStops = new ArrayList<>();
    }

    public Route(VehicleType vehicleType, List<Address> addressList) {
        this.vehicleType = vehicleType;
        this.addressList = addressList;
        this.pathList = new ArrayList<>();
        this.energyList = new ArrayList<>();
        this.timeList = new ArrayList<>();
        this.chargeStops = new ArrayList<>();
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
            return 0;
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
                Objects.equals(addressList, route.addressList) &&
                Objects.equals(pathList, route.pathList) &&
                Objects.equals(energyList, route.energyList) &&
                Objects.equals(timeList, route.timeList) &&
                Objects.equals(chargeStops, route.chargeStops);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleType, addressList, pathList, energyList, timeList, chargeStops);
    }

    @Override
    public String toString() {
        return "\nRoute{" +
                "\nvehicleType=" + vehicleType +
                ", \naddressList=" + addressList +
                ", \npathList=" + pathList +
                ", \nenergyList=" + energyList +
                ", \ntimeList=" + timeList +
                ", \nchargeStops=" + chargeStops +
                ", \nfullTime=" + getTotalTime() +
                ", \nfullEnergy=" + getTotalEnergy() +
                ", \nfullDistance=" + getTotalDistance() +
                '}';
    }
}
