package lapr.project.model;

import java.util.*;

/**
 * Pharmacy.
 *
 * Group: Team Lisa [G-021]
 * ______________________________________________________
 * @author António Barbosa <1190404@isep.ipp.pt>
 * @author Ernesto Rodrigues <1190560@isep.ipp.pt>
 * @author Jessica Alves <1190682@isep.ipp.pt>
 * @author Pedro Santos <1190967@isep.ipp.pt>
 * @author Rodrigo Costa <1191014@isep.ipp.pt>
 * @author Tiago Costa <1191460@isep.ipp.pt>
 */
public class Pharmacy {

    /**
     * Id.
     */
    private int mintId;

    /**
     * Pharamcy's Name.
     */
    private String mstrName;

    /**
     * Pharamcy's Email.
     */
    private String mstrEmail;

    /**
     * Pharamcy's Address.
     */
    private Address moAddress;

    /**
     * Pharmacy's Park List.
     */
    private List<Park> mlstParks;

    /**
     * Pharamcy's Stock.
     */
    private Map<Product, Integer> mMapStock;

    /**
     * Default Id.
     */
    private static int mDEFAULTID = -1;

    /**
     * Default Name.
     */
    private static String mDEFAULTNAME = "No name.";

    /**
     * Default Email.
     */
    private static String mDEFAULTEMAIL = "No email.";

    /**
     * Default Address.
     */
    private static Address mDEFAULTADDRESS = new Address();

    /**
     * Default Parks List.
     */
    private static List<Park> mDEFAULTPARKS = new ArrayList<>();

    /**
     * Default Stock.
     */
    private static Map<Product, Integer> mDEFAULTSTOCK = new TreeMap<>();

    /**
     * Pharamcy Constructor.
     * @param intId id.
     * @param strName name.
     * @param strEmail email.
     * @param oAddress address.
     * @param mapStock stock.
     */
    public Pharmacy(int intId, String strName, String strEmail, Address oAddress, Map<Product, Integer> mapStock) {
        this.mintId = intId;
        this.mstrName = strName;
        this.mstrEmail = strEmail;
        this.moAddress = oAddress;
        this.mlstParks = mDEFAULTPARKS;
        this.mMapStock = mapStock;
    }

    /**
     * Pharamcy Constructor.
     * @param strName name.
     * @param strEmail email.
     * @param oAddress address.
     * @param mapStock stock.
     */
    public Pharmacy(String strName, String strEmail, Address oAddress, Map<Product, Integer> mapStock) {
        this.mintId = mDEFAULTID;
        this.mstrName = strName;
        this.mstrEmail = strEmail;
        this.moAddress = oAddress;
        this.mlstParks = mDEFAULTPARKS;
        this.mMapStock = mapStock;
    }

    /**
     * Pharamcy Constructor.
     * @param intId id.
     * @param strName name.
     * @param strEmail email.
     * @param oAddress address.
     */
    public Pharmacy(int intId, String strName, String strEmail, Address oAddress) {
        this.mintId = intId;
        this.mstrName = strName;
        this.mstrEmail = strEmail;
        this.moAddress = oAddress;
        this.mlstParks = mDEFAULTPARKS;
        this.mMapStock = mDEFAULTSTOCK;
    }

    /**
     * Pharamcy Constructor.
     * @param strName name.
     * @param strEmail email.
     * @param oAddress address.
     */
    public Pharmacy(String strName, String strEmail, Address oAddress) {
        this.mintId = mDEFAULTID;
        this.mstrName = strName;
        this.mstrEmail = strEmail;
        this.moAddress = oAddress;
        this.mlstParks = mDEFAULTPARKS;
        this.mMapStock = mDEFAULTSTOCK;
    }

    /**
     * Empty Pharamcy Constructor.
     */
    public Pharmacy() {
        this.mintId = mDEFAULTID;
        this.mstrName = mDEFAULTNAME;
        this.mstrEmail = mDEFAULTEMAIL;
        this.moAddress = mDEFAULTADDRESS;
        this.mlstParks = mDEFAULTPARKS;
        this.mMapStock = mDEFAULTSTOCK;
    }

    /**
     * Getter for the id.
     * @return id.
     */
    public int getId() {
        return mintId;
    }

    /**
     * Setter for the id.
     * @param intId id.
     */
    public void setId(int intId) {
        this.mintId = intId;
    }

    /**
     * Method that checks if a pharmacy has an id.
     * @param intId id.
     * @return true or false, if it has or not.
     */
    public boolean hasId(Integer intId) {return this.getId() == intId;}

    /**
     * Getter for the name.
     * @return name.
     */
    public String getName() {
        return mstrName;
    }

    /**
     * Setter for the name.
     * @param strName name.
     */
    public void setName(String strName) {
        this.mstrName = strName;
    }

    /**
     * Getter for the email.
     * @return email.
     */
    public String getEmail() {
        return mstrEmail;
    }

    /**
     * Setter for the email.
     * @param strEmail email.
     */
    public void setEmail(String strEmail) {
        this.mstrEmail = strEmail;
    }

    /**
     * Method that checks if a pharmacy has an email.
     * @param strEmail email.
     * @return true or false, if it has or not.
     */
    public boolean hasEmail(String strEmail) {return this.mstrEmail.equalsIgnoreCase(strEmail);}

    /**
     * Getter for the address.
     * @return address.
     */
    public Address getAddress() {
        return moAddress;
    }

    /**
     * Setter for the address.
     * @param oAddress address.
     */
    public void setAddress(Address oAddress) {
        this.moAddress = oAddress;
    }

    /**
     * Getter for the parks.
     * @return parks.
     */
    public List<Park> getParks() {
        return new ArrayList<>(mlstParks);
    }

    /**
     * Setter for the parks.
     * @param lstParks
     */
    public void setParks(List<Park> lstParks) {
        this.mlstParks = new ArrayList<>(lstParks);
    }

    /**
     * Getter for the stock.
     * @return stock.
     */
    public Map<Product, Integer> getStock() {
        return mMapStock;
    }

    /**
     * Setter for the stock.
     * @param mapStock
     */
    public void setStock(Map<Product, Integer> mapStock) {
        this.mMapStock = mapStock;
    }

    /**
     * Equals Override.
     * @param o object.
     * @return true or false, if its the same or not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pharmacy pharmacy = (Pharmacy) o;
        return mintId == pharmacy.mintId;
    }

    /**
     * Hash Code Override.
     * @return hash code value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(mintId);
    }

    /**
     * ToString override.
     * @return information.
     */
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
