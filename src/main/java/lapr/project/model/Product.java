package lapr.project.model;

import java.util.Objects;

public class Product implements Comparable {
    private int m_intId;
    private String m_strName;
    private String m_strDescription;
    private float m_fltUnitaryPrice;
    private float m_fltUnitaryWeight;

    private static int DEFAULT_ID = -1;
    private static String DEFAULT_NAME = "No name.";
    private static String DEFAULT_DESCRIPTION = "No description.";
    private static float DEFAULT_UNITARY_PRICE = 0;
    private static float DEFAULT_UNITARY_WEIGHT = 0;

    public Product(int m_intId, String m_strName, String m_strDescription, float m_fltUnitaryPrice, float m_fltUnitaryWeight) {
        this.m_intId = m_intId;
        this.m_strName = m_strName;
        this.m_strDescription = m_strDescription;
        this.m_fltUnitaryPrice = m_fltUnitaryPrice;
        this.m_fltUnitaryWeight = m_fltUnitaryWeight;
    }

    public Product(String m_strName, String m_strDescription, float m_fltUnitaryPrice, float m_fltUnitaryWeight) {
        this.m_strName = m_strName;
        this.m_strDescription = m_strDescription;
        this.m_fltUnitaryPrice = m_fltUnitaryPrice;
        this.m_fltUnitaryWeight = m_fltUnitaryWeight;
    }

    public Product() {
        this.m_intId = DEFAULT_ID;
        this.m_strName = DEFAULT_NAME;
        this.m_strDescription = DEFAULT_DESCRIPTION;
        this.m_fltUnitaryPrice = DEFAULT_UNITARY_PRICE;
        this.m_fltUnitaryWeight = DEFAULT_UNITARY_WEIGHT;
    }

    public int getId() {
        return m_intId;
    }

    public boolean hasId(Integer intId) {return this.getId() == intId;}

    public String getName() {
        return m_strName;
    }

    public String getDescription() {
        return m_strDescription;
    }

    public float getUnitaryPrice() {
        return m_fltUnitaryPrice;
    }

    public float getUnitaryWeight() {
        return m_fltUnitaryWeight;
    }

    public void setId(int m_intId) {
        this.m_intId = m_intId;
    }

    public void setName(String m_strName) {
        this.m_strName = m_strName;
    }

    public void setDescription(String m_strDescription) {
        this.m_strDescription = m_strDescription;
    }

    public void setUnitaryPrice(float m_fltUnitaryPrice) {
        this.m_fltUnitaryPrice = m_fltUnitaryPrice;
    }

    public void setUnitaryWeight(float m_fltUnitaryWeight) {
        this.m_fltUnitaryWeight = m_fltUnitaryWeight;
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
        return m_intId == product.m_intId &&
                Float.compare(product.m_fltUnitaryPrice, m_fltUnitaryPrice) == 0 &&
                Float.compare(product.m_fltUnitaryWeight, m_fltUnitaryWeight) == 0 &&
                m_strName.equals(product.m_strName) &&
                m_strDescription.equals(product.m_strDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_intId, m_strName, m_strDescription, m_fltUnitaryPrice, m_fltUnitaryWeight);
    }

    @Override
    public String toString() {
        return "Product{" +
                "m_intId=" + m_intId +
                ", m_strName='" + m_strName + '\'' +
                ", m_strDescription='" + m_strDescription + '\'' +
                ", m_fltUnitaryPrice=" + m_fltUnitaryPrice +
                ", m_fltUnitaryWeight=" + m_fltUnitaryWeight +
                '}';
    }
}
