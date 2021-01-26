package lapr.project.model;

import java.util.Objects;

/**
 * Address.
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
public class Address {

    /**
     * Address latitude
     */
    private Double mdblLatitude;

    /**
     * Address longitude
     */
    private Double mdblLongitude;

    /**
     * Address altitude
     */
    private Double mdblAltitude;

    /**
     * Address street name
     */
    private String mstrStreetName;

    /**
     * Address door number
     */
    private String mstrDoorNumber;

    /**
     * Address postal code
     */
    private String mstrPostalCode;

    /**
     * Address locality
     */
    private String mstrLocality;

    /**
     * Address country
     */
    private String mstrCountry;

    /**
     * Empty constructor of Address, which sets all the atributes to
     * default values
     */
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

    /**
     * Constructor of Address which sets all the atributes to the ones given
     * by parameter
     *
     * @param latitude  Address latitude
     * @param longitude Address longitude
     * @param altitude  Address altitude
     * @param streetName    Address street name
     * @param doorNumber    Address door number
     * @param postalCode    Address postal code
     * @param locality      Address locality
     * @param country   Address country
     */
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

    /**
     * Returns the Address latitude
     *
     * @return  Address latitude
     */
    public Double getLatitude() {
        return mdblLatitude;
    }

    /**
     * Returns the Address longitude
     *
     * @return  Address longitude
     */
    public Double getLongitude() {
        return mdblLongitude;
    }

    /**
     * Returns the Address altitude
     *
     * @return  Address altitude
     */
    public Double getAltitude() {
        return mdblAltitude;
    }

    /**
     * Sets the Address altitude to the one given by parameter
     *
     * @param dblAltitude   new Address altitude
     */
    public void setAltitude(Double dblAltitude) {
        this.mdblAltitude = dblAltitude;
    }

    /**
     * Returns the Address street name
     *
     * @return  Address street name
     */
    public String getStreetName() {
        return mstrStreetName;
    }

    /**
     * Returns the Address door number
     *
     * @return  Address door number
     */
    public String getDoorNumber() {
        return mstrDoorNumber;
    }

    /**
     * Returns the Address postal code
     *
     * @return  Address postal code
     */
    public String getPostalCode() {
        return mstrPostalCode;
    }

    /**
     * Returns the Address locality
     *
     * @return  Address locality
     */
    public String getLocality() {
        return mstrLocality;
    }

    /**
     * Returns the Address country
     *
     * @return  Address country
     */
    public String getCountry() {
        return mstrCountry;
    }

    /**
     * Sets the Address latitude to the one given by parameter
     *
     * @param mdblLatitude new Address latitude
     */
    public void setLatitude(Double mdblLatitude) {
        this.mdblLatitude = mdblLatitude;
    }

    /**
     * Sets the Address longitude to the one given by parameter
     *
     * @param mdblLongitude new Address longitude
     */
    public void setLongitude(Double mdblLongitude) {
        this.mdblLongitude = mdblLongitude;
    }

    /**
     * Sets the Address street name to the one given by parameter
     *
     * @param mstrStreetName new Address street name
     */
    public void setStreetName(String mstrStreetName) {
        this.mstrStreetName = mstrStreetName;
    }

    /**
     * Sets the Address door number to the one given by parameter
     *
     * @param mstrDoorNumber    new Address door number
     */
    public void setDoorNumber(String mstrDoorNumber) {
        this.mstrDoorNumber = mstrDoorNumber;
    }

    /**
     * Sets the Address postal code to the one given by parameter
     *
     * @param mstrPostalCode    new Address postal code
     */
    public void setPostalCode(String mstrPostalCode) {
        this.mstrPostalCode = mstrPostalCode;
    }

    /**
     * Sets the Address locality to the one given by parameter
     *
     * @param mstrLocality  Address locality
     */
    public void setLocality(String mstrLocality) {
        this.mstrLocality = mstrLocality;
    }

    /**
     * Sets the Address country to the one given by parameter
     *
     * @param mstrCountry   Address country
     */
    public void setCountry(String mstrCountry) {
        this.mstrCountry = mstrCountry;
    }

    /**
     * Calculates the distance between two Addresses, in meters
     *
     * @param oAddress  Other Address to be compared to
     * @return  Distance between both Addresses
     */
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

    /**
     * Equals method that compares two Addresses
     *
     * @param o Other object
     * @return  True if the latitudes and longitudes are equal,
     *          false if otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(mdblLatitude, address.mdblLatitude) &&
                Objects.equals(mdblLongitude, address.mdblLongitude);
    }

    /**
     * HashCode
     *
     * @return  Integer hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(mdblLatitude, mdblLongitude);
    }

    /**
     * ToString method that formats the information in order to
     * print it correctly
     *
     * @return  formatted String
     */
    @Override
    public String toString() {
        return  mstrStreetName;
    }
}
