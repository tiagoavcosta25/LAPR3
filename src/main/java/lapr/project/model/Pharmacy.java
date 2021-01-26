package lapr.project.model;

import java.util.*;

public class Pharmacy {

    private int mintId;
    private String mstrName;
    private String mstrEmail;
    private Address moAddress;
    private List<Park> mlstParks;
    private Map<Product, Integer> mMapStock;

    private static int mDEFAULTID = -1;
    private static String mDEFAULTNAME = "No name.";
    private static String mDEFAULTEMAIL = "No email.";
    private static Address mDEFAULTADDRESS = new Address();
    private static List<Park> mDEFAULTPARKS = new ArrayList<>();
    private static Map<Product, Integer> mDEFAULTSTOCK = new TreeMap<>();

    public Pharmacy(int intId, String strName, String strEmail, Address oAddress, Map<Product, Integer> mapStock) {
        this.mintId = intId;
        this.mstrName = strName;
        this.mstrEmail = strEmail;
        this.moAddress = oAddress;
        this.mlstParks = mDEFAULTPARKS;
        this.mMapStock = mapStock;
    }

    public Pharmacy(String strName, String strEmail, Address oAddress, Map<Product, Integer> mapStock) {
        this.mintId = mDEFAULTID;
        this.mstrName = strName;
        this.mstrEmail = strEmail;
        this.moAddress = oAddress;
        this.mlstParks = mDEFAULTPARKS;
        this.mMapStock = mapStock;
    }

    public Pharmacy(int intId, String strName, String strEmail, Address oAddress) {
        this.mintId = intId;
        this.mstrName = strName;
        this.mstrEmail = strEmail;
        this.moAddress = oAddress;
        this.mlstParks = mDEFAULTPARKS;
        this.mMapStock = mDEFAULTSTOCK;
    }

    public Pharmacy(String strName, String strEmail, Address oAddress) {
        this.mintId = mDEFAULTID;
        this.mstrName = strName;
        this.mstrEmail = strEmail;
        this.moAddress = oAddress;
        this.mlstParks = mDEFAULTPARKS;
        this.mMapStock = mDEFAULTSTOCK;
    }

    public Pharmacy() {
        this.mintId = mDEFAULTID;
        this.mstrName = mDEFAULTNAME;
        this.mstrEmail = mDEFAULTEMAIL;
        this.moAddress = mDEFAULTADDRESS;
        this.mlstParks = mDEFAULTPARKS;
        this.mMapStock = mDEFAULTSTOCK;
    }

    public int getId() {
        return mintId;
    }

    public void setId(int intId) {
        this.mintId = intId;
    }

    public boolean hasId(Integer intId) {return this.getId() == intId;}

    public String getName() {
        return mstrName;
    }

    public void setName(String strName) {
        this.mstrName = strName;
    }

    public String getEmail() {
        return mstrEmail;
    }

    public void setEmail(String strEmail) {
        this.mstrEmail = strEmail;
    }

    public boolean hasEmail(String strEmail) {return this.mstrEmail.equalsIgnoreCase(strEmail);}

    public Address getAddress() {
        return moAddress;
    }

    public void setAddress(Address oAddress) {
        this.moAddress = oAddress;
    }

    public List<Park> getParks() {
        return mlstParks;
    }

    public void setParks(List<Park> lstParks) {
        this.mlstParks = lstParks;
    }

    public Map<Product, Integer> getStock() {
        return mMapStock;
    }

    public void setStock(Map<Product, Integer> mapStock) {
        this.mMapStock = mapStock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pharmacy pharmacy = (Pharmacy) o;
        return mintId == pharmacy.mintId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mintId);
    }


    @Override
    public String toString() {
        return "Pharmacy{" +
                "m_intId=" + mintId +
                ", m_strName='" + mstrName + '\'' +
                ", m_strEmail='" + mstrEmail + '\'' +
                ", m_oAddress=" + moAddress +
                ", m_lstParks=" + mlstParks +
                ", m_mapStock=" + mMapStock +
                '}';
    }
}
