package lapr.project.model;

import java.util.Objects;

public class Product {
    private int m_intId;
    private String m_strName;
    private String m_strDescription;
    private float m_fltUnitaryPrice;
    private float m_fltUnitaryWeight;

    private static int DEFAULT_ID = -1;
    private static String DEFAULT_NAME = "";
    private static String DEFAULT_DESCRIPTION = "";
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return m_intId == product.m_intId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_intId);
    }
}
