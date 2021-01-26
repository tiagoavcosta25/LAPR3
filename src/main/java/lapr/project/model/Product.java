package lapr.project.model;

import java.util.Objects;

public class Product implements Comparable {
    private int mintId;
    private String mstrName;
    private String mstrDescription;
    private Double mfltUnitaryPrice;
    private Double mfltUnitaryWeight;

    private static int mDEFAULTID = -1;
    private static String mDEFAULTNAME = "No name.";
    private static String mDEFAULTDESCRIPTION = "No description.";
    private static Double mDEFAULTUNITARYPRICE = 0d;
    private static Double mDEFAULTUNITARYWEIGHT = 0d;

    public Product(int intId, String strName, String strDescription, Double fltUnitaryPrice, Double fltUnitaryWeight) {
        this.mintId = intId;
        this.mstrName = strName;
        this.mstrDescription = strDescription;
        this.mfltUnitaryPrice = fltUnitaryPrice;
        this.mfltUnitaryWeight = fltUnitaryWeight;
    }

    public Product(String strName, String strDescription, Double fltUnitaryPrice, Double fltUnitaryWeight) {
        this.mstrName = strName;
        this.mstrDescription = strDescription;
        this.mfltUnitaryPrice = fltUnitaryPrice;
        this.mfltUnitaryWeight = fltUnitaryWeight;
    }

    public Product() {
        this.mintId = mDEFAULTID;
        this.mstrName = mDEFAULTNAME;
        this.mstrDescription = mDEFAULTDESCRIPTION;
        this.mfltUnitaryPrice = mDEFAULTUNITARYPRICE;
        this.mfltUnitaryWeight = mDEFAULTUNITARYWEIGHT;
    }

    public int getId() {
        return mintId;
    }

    public boolean hasId(Integer intId) {return this.getId() == intId;}

    public String getName() {
        return mstrName;
    }

    public boolean hasName(String strName) {return this.mstrName.equalsIgnoreCase(strName);}

    public String getDescription() {
        return mstrDescription;
    }

    public Double getUnitaryPrice() {
        return mfltUnitaryPrice;
    }

    public Double getUnitaryWeight() {
        return mfltUnitaryWeight;
    }

    public void setId(int intId) {
        this.mintId = intId;
    }

    public void setName(String strName) {
        this.mstrName = strName;
    }

    public void setDescription(String strDescription) {
        this.mstrDescription = strDescription;
    }

    public void setUnitaryPrice(Double fltUnitaryPrice) {
        this.mfltUnitaryPrice = fltUnitaryPrice;
    }

    public void setUnitaryWeight(Double fltUnitaryWeight) {
        this.mfltUnitaryWeight = fltUnitaryWeight;
    }

    @Override
    public int compareTo(Object o) {
        Product p = (Product) o;
        return this.getId() - p.getId();
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(mstrName, mstrDescription, mfltUnitaryPrice, mfltUnitaryWeight);
    }

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
