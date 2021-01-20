package lapr.project.model;

import java.util.Objects;

public class Product implements Comparable {
    private int mintId;
    private String mstrName;
    private String mstrDescription;
    private float mfltUnitaryPrice;
    private float mfltUnitaryWeight;

    private static int DEFAULTID = -1;
    private static String DEFAULTNAME = "No name.";
    private static String DEFAULTDESCRIPTION = "No description.";
    private static float DEFAULTUNITARYPRICE = 0;
    private static float DEFAULTUNITARYWEIGHT = 0;

    public Product(int intId, String strName, String strDescription, float fltUnitaryPrice, float fltUnitaryWeight) {
        this.mintId = intId;
        this.mstrName = strName;
        this.mstrDescription = strDescription;
        this.mfltUnitaryPrice = fltUnitaryPrice;
        this.mfltUnitaryWeight = fltUnitaryWeight;
    }

    public Product(String strName, String strDescription, float fltUnitaryPrice, float fltUnitaryWeight) {
        this.mstrName = strName;
        this.mstrDescription = strDescription;
        this.mfltUnitaryPrice = fltUnitaryPrice;
        this.mfltUnitaryWeight = fltUnitaryWeight;
    }

    public Product() {
        this.mintId = DEFAULTID;
        this.mstrName = DEFAULTNAME;
        this.mstrDescription = DEFAULTDESCRIPTION;
        this.mfltUnitaryPrice = DEFAULTUNITARYPRICE;
        this.mfltUnitaryWeight = DEFAULTUNITARYWEIGHT;
    }

    public int getId() {
        return mintId;
    }

    public boolean hasId(Integer intId) {return this.getId() == intId;}

    public String getName() {
        return mstrName;
    }

    public String getDescription() {
        return mstrDescription;
    }

    public float getUnitaryPrice() {
        return mfltUnitaryPrice;
    }

    public float getUnitaryWeight() {
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

    public void setUnitaryPrice(float fltUnitaryPrice) {
        this.mfltUnitaryPrice = fltUnitaryPrice;
    }

    public void setUnitaryWeight(float fltUnitaryWeight) {
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
        return Float.compare(product.mfltUnitaryPrice, mfltUnitaryPrice) == 0 &&
                Float.compare(product.mfltUnitaryWeight, mfltUnitaryWeight) == 0 &&
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
