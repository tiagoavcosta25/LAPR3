package lapr.project.model;

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
        this.m_latitude = -1d;
        this.m_longitude = -1d;
        this.m_streetName = "No Address";
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

<<<<<<< HEAD
    public Double getM_latitude() {
=======
    public Address(Integer id, float latitude, float longitude, String streetName, String doorNumber,
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

    public float getM_latitude() {
>>>>>>> f2c44af05c8cadfb7f5af271c7c146fe936b0c9e
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
