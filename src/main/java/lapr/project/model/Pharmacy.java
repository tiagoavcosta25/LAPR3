package lapr.project.model;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Pharmacy {

    private int m_intId;
    private String m_strName;
    private String m_strEmail;
    private Address m_oAddress;
    private Map<Product, Integer> m_mapStock;

    private static int DEFAULT_ID = -1;
    private static String DEFAULT_NAME = "No name.";
    private static String DEFAULT_EMAIL = "No email.";
    private static Address DEFAULT_ADDRESS = new Address();
    private static Map<Product, Integer> DEFAULT_STOCK = new TreeMap<>();

    public Pharmacy(int intId, String strName, String strEmail, Address oAddress, Map<Product, Integer> mapStock) {
        this.m_intId = intId;
        this.m_strName= strName;
        this.m_strEmail= strEmail;
        this.m_oAddress= oAddress;
        this.m_mapStock = mapStock;
    }

    public Pharmacy(String strName, String strEmail, Address oAddress, Map<Product, Integer> mapStock) {
        this.m_intId = DEFAULT_ID;
        this.m_strName= strName;
        this.m_strEmail= strEmail;
        this.m_oAddress= oAddress;
        this.m_mapStock = mapStock;
    }

    public Pharmacy(int intId, String strName, String strEmail, Address oAddress) {
        this.m_intId = intId;
        this.m_strName= strName;
        this.m_strEmail= strEmail;
        this.m_oAddress= oAddress;
        this.m_mapStock = DEFAULT_STOCK;
    }

    public Pharmacy(String strName, String strEmail, Address oAddress) {
        this.m_intId = DEFAULT_ID;
        this.m_strName= strName;
        this.m_strEmail= strEmail;
        this.m_oAddress= oAddress;
        this.m_mapStock = DEFAULT_STOCK;
    }

    public Pharmacy() {
        this.m_intId = DEFAULT_ID;
        this.m_strName= DEFAULT_NAME;
        this.m_strEmail= DEFAULT_EMAIL;
        this.m_oAddress = DEFAULT_ADDRESS;
        this.m_mapStock = DEFAULT_STOCK;
    }

    public int getId() {
        return m_intId;
    }

    public void setId(int intId) {
        this.m_intId = intId;
    }

    public boolean hasId(Integer intId) {return this.getId() == intId;}

    public String getName() {
        return m_strName;
    }

    public void setName(String strName) {
        this.m_strName = strName;
    }

    public String getEmail() {
        return m_strEmail;
    }

    public void setEmail(String strEmail) {
        this.m_strEmail = strEmail;
    }

    public Address getAddress() {
        return m_oAddress;
    }

    public void setAddress(Address oAddress) {
        this.m_oAddress = oAddress;
    }

    public Map<Product, Integer> getStock() {
        return m_mapStock;
    }

    public void setStock(Map<Product, Integer> mapStock) {
        this.m_mapStock = mapStock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pharmacy pharmacy = (Pharmacy) o;
        return m_intId == pharmacy.m_intId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_intId);
    }


    @Override
    public String toString() {
        return "Pharmacy{" +
                "m_intId=" + m_intId +
                ", m_strName='" + m_strName + '\'' +
                ", m_strEmail='" + m_strEmail + '\'' +
                ", m_oAddress=" + m_oAddress +
                ", m_mapStock=" + m_mapStock +
                '}';
    }
}
