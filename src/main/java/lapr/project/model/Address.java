package lapr.project.model;

public class Address {

    private Integer m_id;
    private float m_latitude;
    private float m_longitude;
    private String m_streetName;
    private String m_doorNumber;
    private String m_postalCode;
    private String m_locality;
    private String m_country;

    public Address() {
        this.m_id = -1;
        this.m_latitude = -1;
        this.m_longitude = -1;
        this.m_streetName = "No Address";
        this.m_doorNumber = "No Door Number";
        this.m_postalCode = "No Postal Code";
        this.m_locality = "No Locality";
        this.m_country = "No Country";
    }

    public Address(float latitude, float longitude, String streetName, String doorNumber,
            String postalCode, String locality, String country) {
        this.m_latitude = latitude;
        this.m_longitude = longitude;
        this.m_streetName = streetName;
        this.m_doorNumber = doorNumber;
        this.m_postalCode = postalCode;
        this.m_locality = locality;
        this.m_country = country;
    }

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
        return m_latitude;
    }

    public float getM_longitude() {
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
}
