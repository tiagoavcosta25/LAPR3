package lapr.project.model;

import java.util.Objects;

public class Address {

    private Double mdblLatitude;
    private Double mdblLongitude;
    private Double mdblAltitude;
    private String mstrStreetName;
    private String mstrDoorNumber;
    private String mstrPostalCode;
    private String mstrLocality;
    private String mstrCountry;

    public Address() {
        this.mdblLatitude = -22d;
        this.mdblLongitude = -22d;
        this.mdblAltitude = -Double.MAX_VALUE;
        this.mstrStreetName = "No Street Name";
        this.mstrDoorNumber = "No Door Number";
        this.mstrPostalCode = "No Postal Code";
        this.mstrLocality = "No Locality";
        this.mstrCountry = "No Country";
    }

    public Address(Double latitude, Double longitude, Double altitude, String streetName, String doorNumber,
                   String postalCode, String locality, String country) {
        this.mdblLatitude = latitude;
        this.mdblLongitude = longitude;
        this.mdblAltitude = altitude;
        this.mstrStreetName = streetName;
        this.mstrDoorNumber = doorNumber;
        this.mstrPostalCode = postalCode;
        this.mstrLocality = locality;
        this.mstrCountry = country;
    }


    public Double getLatitude() {
        return mdblLatitude;
    }

    public Double getLongitude() {
        return mdblLongitude;
    }

    public Double getAltitude() {
        return mdblAltitude;
    }

    public void setAltitude(Double dblAltitude) {
        this.mdblAltitude = dblAltitude;
    }

    public String getStreetName() {
        return mstrStreetName;
    }

    public String getDoorNumber() {
        return mstrDoorNumber;
    }

    public String getPostalCode() {
        return mstrPostalCode;
    }

    public String getLocality() {
        return mstrLocality;
    }

    public String getCountry() {
        return mstrCountry;
    }

    public void setLatitude(Double mdblLatitude) {
        this.mdblLatitude = mdblLatitude;
    }

    public void setLongitude(Double mdblLongitude) {
        this.mdblLongitude = mdblLongitude;
    }

    public void setStreetName(String mstrStreetName) {
        this.mstrStreetName = mstrStreetName;
    }

    public void setDoorNumber(String mstrDoorNumber) {
        this.mstrDoorNumber = mstrDoorNumber;
    }

    public void setPostalCode(String mstrPostalCode) {
        this.mstrPostalCode = mstrPostalCode;
    }

    public void setLocality(String mstrLocality) {
        this.mstrLocality = mstrLocality;
    }

    public void setCountry(String mstrCountry) {
        this.mstrCountry = mstrCountry;
    }

    public double distanceTo(Address oAddress) {
        final double EARTH_RADIUS = 6371e3;
        double latitude1 = this.getLatitude() * Math.PI / 180;
        double latitude2 = oAddress.getLatitude() * Math.PI / 180;
        double differenceOfLatitude = (this.mdblLatitude - oAddress.getLatitude()) * Math.PI / 180;
        double differenceOfLongitude = (this.mdblLongitude - oAddress.getLongitude()) * Math.PI / 180;
        double differenceOfAltitude = (this.mdblAltitude - oAddress.getAltitude());

        double a = Math.sin(differenceOfLatitude / 2) * Math.sin(differenceOfLatitude / 2) +
                Math.cos(latitude1) * Math.cos(latitude2) * Math.sin(differenceOfLongitude / 2) *
                        Math.sin(differenceOfLongitude / 2);
        double aux = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return Math.sqrt((Math.pow(differenceOfAltitude, 2) + Math.pow((EARTH_RADIUS * aux), 2)));
        //in meters
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(mdblLatitude, address.mdblLatitude) &&
                Objects.equals(mdblLongitude, address.mdblLongitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mdblLatitude, mdblLongitude);
    }

    @Override
    public String toString() {
        return  mstrStreetName;
    }
}
