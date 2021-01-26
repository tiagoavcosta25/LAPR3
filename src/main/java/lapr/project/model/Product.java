package lapr.project.model;

import java.util.Objects;

/**
 * Product.
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
public class Product implements Comparable {
    /**
     * Product ID
     */
    private int mintId;

    /**
     * Product name
     */
    private String mstrName;

    /**
     * Product description
     */
    private String mstrDescription;

    /**
     * Product unitary price
     */
    private Double mfltUnitaryPrice;

    /**
     * Product unitary weight
     */
    private Double mfltUnitaryWeight;

    /**
     * Product Default ID
     */
    private static int mDEFAULTID = -1;

    /**
     * Product Default name
     */
    private static String mDEFAULTNAME = "No name.";

    /**
     * Product Default description
     */
    private static String mDEFAULTDESCRIPTION = "No description.";

    /**
     * Product Default unitary price
     */
    private static Double mDEFAULTUNITARYPRICE = 0d;

    /**
     * Product Default unitary weight
     */
    private static Double mDEFAULTUNITARYWEIGHT = 0d;

    /**
     * Constructor of Product, which sets all the atributes to the ones
     * given by parameter
     *
     * @param intId            Product ID
     * @param strName          Product name
     * @param strDescription   Product description
     * @param fltUnitaryPrice  Product unitary price
     * @param fltUnitaryWeight Product unitary weight
     */
    public Product(int intId, String strName, String strDescription, Double fltUnitaryPrice, Double fltUnitaryWeight) {
        this.mintId = intId;
        this.mstrName = strName;
        this.mstrDescription = strDescription;
        this.mfltUnitaryPrice = fltUnitaryPrice;
        this.mfltUnitaryWeight = fltUnitaryWeight;
    }

    /**
     * Constructor of Product, which sets all the atributes to the ones
     * given by parameter
     *
     * @param strName          Product name
     * @param strDescription   Product description
     * @param fltUnitaryPrice  Product unitary price
     * @param fltUnitaryWeight Product unitary weight
     */
    public Product(String strName, String strDescription, Double fltUnitaryPrice, Double fltUnitaryWeight) {
        this.mstrName = strName;
        this.mstrDescription = strDescription;
        this.mfltUnitaryPrice = fltUnitaryPrice;
        this.mfltUnitaryWeight = fltUnitaryWeight;
    }

    /**
     * Empty Constructor of Product, which sets all atributes to
     * default values
     */
    public Product() {
        this.mintId = mDEFAULTID;
        this.mstrName = mDEFAULTNAME;
        this.mstrDescription = mDEFAULTDESCRIPTION;
        this.mfltUnitaryPrice = mDEFAULTUNITARYPRICE;
        this.mfltUnitaryWeight = mDEFAULTUNITARYWEIGHT;
    }

    /**
     * Returns the Product ID
     *
     * @return Product ID
     */
    public int getId() {
        return mintId;
    }

    /**
     * Checks if a Product has a certain ID
     *
     * @param intId ID to be checked
     * @return True if the Product has the ID given by parameter,
     *          False if otherwise
     */
    public boolean hasId(Integer intId) {
        return this.getId() == intId;
    }

    /**
     * Returns the Product name
     *
     * @return  Product name
     */
    public String getName() {
        return mstrName;
    }

    /**
     * Checks if a Product has a certain name
     *
     * @param strName  Name to be checked
     * @return  True if the Product has the name given by parameter,
     *          False if otherwise
     */
    public boolean hasName(String strName) {
        return this.mstrName.equalsIgnoreCase(strName);
    }

    /**
     * Returns the Product description
     *
     * @return  Product description
     */
    public String getDescription() {
        return mstrDescription;
    }

    /**
     * Returns the Product unitary price
     *
     * @return  Product unitary price
     */
    public Double getUnitaryPrice() {
        return mfltUnitaryPrice;
    }

    /**
     * Returns the Product unitary weight
     *
     * @return  Product unitary weight
     */
    public Double getUnitaryWeight() {
        return mfltUnitaryWeight;
    }

    /**
     * Sets the Product ID to the one given by parameter
     *
     * @param intId new Product ID
     */
    public void setId(int intId) {
        this.mintId = intId;
    }

    /**
     * Sets the Product name to the one given by parameter
     *
     * @param strName   new Product name
     */
    public void setName(String strName) {
        this.mstrName = strName;
    }

    /**
     * Sets the Product description to the one given by parameter
     *
     * @param strDescription    new Product description
     */
    public void setDescription(String strDescription) {
        this.mstrDescription = strDescription;
    }

    /**
     * Sets the Product unitary price to the one given by parameter
     *
     * @param fltUnitaryPrice   new Product unitary price
     */
    public void setUnitaryPrice(Double fltUnitaryPrice) {
        this.mfltUnitaryPrice = fltUnitaryPrice;
    }

    /**
     * Sets the Product unitary weight the one given by parameter
     *
     * @param fltUnitaryWeight  Product unitary weight
     */
    public void setUnitaryWeight(Double fltUnitaryWeight) {
        this.mfltUnitaryWeight = fltUnitaryWeight;
    }

    /**
     * Compares two Product objects
     *
     * @param o Other Object
     * @return  0 if the objects are the same, 1 if the first is greater
     *          than the second or -1 if otherwise
     */
    @Override
    public int compareTo(Object o) {
        Product p = (Product) o;
        return this.getId() - p.getId();
    }

    /**
     * Equals method, which compares two Products
     *
     * @param o Other Object
     * @return True if both Products are equal, false if otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.mfltUnitaryPrice, mfltUnitaryPrice) == 0 &&
                Double.compare(product.mfltUnitaryWeight, mfltUnitaryWeight) == 0 &&
                mstrName.equals(product.mstrName) &&
                mstrDescription.equals(product.mstrDescription);
    }

    /**
     * HashCode
     *
     * @return Integer hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(mstrName, mstrDescription, mfltUnitaryPrice, mfltUnitaryWeight);
    }

    /**
     * ToString method, which formats a string to
     * print a formatted message
     *
     * @return formatted String
     */
    @Override
    public String toString() {
        return "Product{" +
                "m_intId=" + mintId +
                ", m_strName='" + mstrName + '\'' +
                ", m_strDescription='" + mstrDescription + '\'' +
                ", m_fltUnitaryPrice=" + mfltUnitaryPrice +
                ", m_fltUnitaryWeight=" + mfltUnitaryWeight +
                '}';
    }
}
