package lapr.project.model;

import java.util.Objects;

public class Address {

    private Integer m_id;
    private Double m_latitude;
    private Double m_longitude;
    private String m_streetName;
    private String m_doorNumber;
    private String m_postalCode;
    private String m_locality;
    private String m_country;

    public Address() {
        this.m_id = -1;
        this.m_latitude = -22d;
        this.m_longitude = -22d;
        this.m_streetName = "No Street Name";
        this.m_doorNumber = "No Door Number";
        this.m_postalCode = "No Postal Code";
        this.m_locality = "No Locality";
        this.m_country = "No Country";
    }

    public Address(Double latitude, Double longitude, String streetName, String doorNumber,
            String postalCode, String locality, String country) {
        this.m_latitude = latitude;
        this.m_longitude = longitude;
        this.m_streetName = streetName;
        this.m_doorNumber = doorNumber;
        this.m_postalCode = postalCode;
        this.m_locality = locality;
        this.m_country = country;
    }


    public Address(Integer id, Double latitude, Double longitude, String streetName, String doorNumber,
                   String postalCode, String locality, String country) {
        this.m_id = id;
        this.m_latitude = latitude;
        this.m_longitude = longitude;
        this.m_streetName = streetName;
        this.m_doorNumber = doorNumber;
        this.m_postalCode = postalCode;
        this.m_locality = locality;
        this.m_country = country;
    }

    public Integer getM_id() {
        return m_id;
    }

    public Double getM_latitude() {
        return m_latitude;
    }

    public Double getM_longitude() {
        return m_longitude;
    }

    public String getM_streetName() {
        return m_streetName;
    }

    public String getM_doorNumber() {
        return m_doorNumber;
    }

    public String getM_postalCode() {
        return m_postalCode;
    }

    public String getM_locality() {
        return m_locality;
    }

    public String getM_country() {
        return m_country;
    }

    public void setM_id(Integer m_id) {
        this.m_id = m_id;
    }

    public void setM_latitude(Double m_latitude) {
        this.m_latitude = m_latitude;
    }

    public void setM_longitude(Double m_longitude) {
        this.m_longitude = m_longitude;
    }

    public void setM_streetName(String m_streetName) {
        this.m_streetName = m_streetName;
    }

    public void setM_doorNumber(String m_doorNumber) {
        this.m_doorNumber = m_doorNumber;
    }

    public void setM_postalCode(String m_postalCode) {
        this.m_postalCode = m_postalCode;
    }

    public void setM_locality(String m_locality) {
        this.m_locality = m_locality;
    }

    public void setM_country(String m_country) {
        this.m_country = m_country;
    }

    public double distanceTo(Address oAddress) {
        final double EARTH_RADIUS = 6371e3;
        double latitude1 = this.getM_latitude() * Math.PI / 180;
        double latitude2 = oAddress.getM_latitude() * Math.PI / 180;
        double differenceOfLatitude = (this.m_latitude - oAddress.getM_latitude()) * Math.PI / 180;
        double differenceOfLongitude = (this.m_longitude - oAddress.getM_longitude()) * Math.PI / 180;

        double a = Math.sin(differenceOfLatitude / 2) * Math.sin(differenceOfLatitude / 2) +
                Math.cos(latitude1) * Math.cos(latitude2) * Math.sin(differenceOfLongitude / 2) *
                        Math.sin(differenceOfLongitude / 2);
        double aux = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * aux;
        //in meters
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(m_id, address.m_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_id);
    }

    @Override
    public String toString() {
        return "Address{" +
                "m_id=" + m_id +
                ", m_latitude=" + m_latitude +
                ", m_longitude=" + m_longitude +
                ", m_streetName='" + m_streetName + '\'' +
                ", m_doorNumber='" + m_doorNumber + '\'' +
                ", m_postalCode='" + m_postalCode + '\'' +
                ", m_locality='" + m_locality + '\'' +
                ", m_country='" + m_country + '\'' +
                '}';
    }
}
