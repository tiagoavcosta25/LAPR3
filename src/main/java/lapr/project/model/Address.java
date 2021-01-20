package lapr.project.model;

import java.util.Objects;

public class Address {

    private Integer m_intId;
    private Double m_dblLatitude;
    private Double m_dblLongitude;
    private Double m_dblAltitude;
    private String m_strStreetName;
    private String m_strDoorNumber;
    private String m_strPostalCode;
    private String m_strLocality;
    private String m_strCountry;

    public Address() {
        this.m_intId = -1;
        this.m_dblLatitude = -22d;
        this.m_dblLongitude = -22d;
        this.m_dblAltitude = -Double.MAX_VALUE;
        this.m_strStreetName = "No Street Name";
        this.m_strDoorNumber = "No Door Number";
        this.m_strPostalCode = "No Postal Code";
        this.m_strLocality = "No Locality";
        this.m_strCountry = "No Country";
    }

    //TODO: REMOVER - ESTÁ DESATUALIZADO
    public Address(Double latitude, Double longitude, String streetName, String doorNumber,
                   String postalCode, String locality, String country) {
        this.m_dblLatitude = latitude;
        this.m_dblLongitude = longitude;
        this.m_strStreetName = streetName;
        this.m_strDoorNumber = doorNumber;
        this.m_strPostalCode = postalCode;
        this.m_strLocality = locality;
        this.m_strCountry = country;
    }

    //TODO: REMOVER - ESTÁ DESATUALIZADO
    public Address(Integer id, Double latitude, Double longitude, String streetName, String doorNumber,
                   String postalCode, String locality, String country) {
        this.m_intId = id;
        this.m_dblLatitude = latitude;
        this.m_dblLongitude = longitude;
        this.m_strStreetName = streetName;
        this.m_strDoorNumber = doorNumber;
        this.m_strPostalCode = postalCode;
        this.m_strLocality = locality;
        this.m_strCountry = country;
    }

    public Address(Double latitude, Double longitude, Double altitude, String streetName, String doorNumber,
                   String postalCode, String locality, String country) {
        this.m_dblLatitude = latitude;
        this.m_dblLongitude = longitude;
        this.m_dblAltitude = altitude;
        this.m_strStreetName = streetName;
        this.m_strDoorNumber = doorNumber;
        this.m_strPostalCode = postalCode;
        this.m_strLocality = locality;
        this.m_strCountry = country;
    }

    public Address(Integer id, Double latitude, Double longitude, Double altitude, String streetName, String doorNumber,
                   String postalCode, String locality, String country) {
        this.m_intId = id;
        this.m_dblLatitude = latitude;
        this.m_dblLongitude = longitude;
        this.m_dblAltitude = altitude;
        this.m_strStreetName = streetName;
        this.m_strDoorNumber = doorNumber;
        this.m_strPostalCode = postalCode;
        this.m_strLocality = locality;
        this.m_strCountry = country;
    }



    public Integer getId() {
        return m_intId;
    }

    public Double getLatitude() {
        return m_dblLatitude;
    }

    public Double getLongitude() {
        return m_dblLongitude;
    }

    public Double getAltitude() {
        return m_dblAltitude;
    }

    public void setAltitude(Double dblAltitude) {
        this.m_dblAltitude = dblAltitude;
    }

    public String getStreetName() {
        return m_strStreetName;
    }

    public String getDoorNumber() {
        return m_strDoorNumber;
    }

    public String getPostalCode() {
        return m_strPostalCode;
    }

    public String getLocality() {
        return m_strLocality;
    }

    public String getCountry() {
        return m_strCountry;
    }

    public void setId(Integer m_id) {
        this.m_intId = m_id;
    }

    public void setLatitude(Double m_latitude) {
        this.m_dblLatitude = m_latitude;
    }

    public void setLongitude(Double m_longitude) {
        this.m_dblLongitude = m_longitude;
    }

    public void setStreetName(String m_streetName) {
        this.m_strStreetName = m_streetName;
    }

    public void setDoorNumber(String m_doorNumber) {
        this.m_strDoorNumber = m_doorNumber;
    }

    public void setPostalCode(String m_postalCode) {
        this.m_strPostalCode = m_postalCode;
    }

    public void setLocality(String m_locality) {
        this.m_strLocality = m_locality;
    }

    public void setCountry(String m_country) {
        this.m_strCountry = m_country;
    }

    public double distanceTo(Address oAddress) {
        final double EARTH_RADIUS = 6371e3;
        double latitude1 = this.getLatitude() * Math.PI / 180;
        double latitude2 = oAddress.getLatitude() * Math.PI / 180;
        double differenceOfLatitude = (this.m_dblLatitude - oAddress.getLatitude()) * Math.PI / 180;
        double differenceOfLongitude = (this.m_dblLongitude - oAddress.getLongitude()) * Math.PI / 180;
        double differenceOfAltitude = (this.m_dblAltitude - oAddress.getAltitude());

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
        return Objects.equals(m_intId, address.m_intId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_intId);
    }

    @Override
    public String toString() {
        return "Address{" +
                "m_id=" + m_intId +
                ", m_latitude=" + m_dblLatitude +
                ", m_longitude=" + m_dblLongitude +
                ", m_streetName='" + m_strStreetName + '\'' +
                ", m_doorNumber='" + m_strDoorNumber + '\'' +
                ", m_postalCode='" + m_strPostalCode + '\'' +
                ", m_locality='" + m_strLocality + '\'' +
                ", m_country='" + m_strCountry + '\'' +
                '}';
    }
}
