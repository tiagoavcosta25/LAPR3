package lapr.project.model;

import java.util.Objects;

public class Pharmacy {

    private int m_intId;
    private String m_strName;
    private PharmacyManager m_oPharmacyManager;
    private Address m_oAddress;


    private static int DEFAULT_ID = -1;
    private static String DEFAULT_NAME = "";
    private static PharmacyManager DEFAULT_PHARMACY_MANAGER = new PharmacyManager();
    private static Address DEFAULT_ADDRESS = new Address();

    public Pharmacy() {
        this.m_intId = DEFAULT_ID;
        this.m_strName= DEFAULT_NAME;
        this.m_oPharmacyManager = DEFAULT_PHARMACY_MANAGER;
        this.m_oAddress = DEFAULT_ADDRESS;
    }

    public Pharmacy(int intId, String strName, PharmacyManager oPharmacyManager, Address oAddress) {
        this.m_intId = intId;
        this.m_strName= strName;
        this.m_oPharmacyManager = oPharmacyManager;
        this.m_oAddress= oAddress;
    }

    public Pharmacy(String strName, PharmacyManager oPharmacyManager, Address oAddress) {
        this.m_intId = DEFAULT_ID;
        this.m_strName= strName;
        this.m_oPharmacyManager = oPharmacyManager;
        this.m_oAddress= oAddress;
    }

    public int getId() {
        return m_intId;
    }

    public void setId(int intId) {
        this.m_intId = intId;
    }

    public String getName() {
        return m_strName;
    }

    public void setName(String strName) {
        this.m_strName = strName;
    }

    public PharmacyManager getPharmacyManager() {
        return m_oPharmacyManager;
    }

    public void setPharmacyManager(PharmacyManager oPharmacyManager) {
        this.m_oPharmacyManager = oPharmacyManager;
    }

    public Address getAddress() {
        return m_oAddress;
    }

    public void setAddress(Address oAddress) {
        this.m_oAddress = oAddress;
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
                ", m_oAddress=" + m_oAddress +
                '}';
    }
}
