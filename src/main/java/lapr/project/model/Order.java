package lapr.project.model;

import oracle.sql.DATE;

import java.util.Objects;

public class Order {
    private int m_intId;
    private float m_fltAmount;
    private float m_fltTotalWeight;
    private float m_fltAdditionalFee;
    private DATE m_dtOrderDate;
    private String m_strDescription;
    private String m_strStatus;

    private static int DEFAULT_ID = -1;
    private static float DEFAULT_AMOUNT = 0;
    private static float DEFAULT_TOTAL_WEIGHT = 0;
    private static float DEFAULT_ADDITIONAL_FEE = 0;
    private static DATE DEFAULT_DATE = new DATE();
    private static String DEFAULT_DESCRIPTION = "";
    private static String DEFAULT_STATUS = "";

    public Order(int intId, float fltAmount, float fltTotalWeight, float fltAdditionalFee, DATE dtOrderDate,
                 String strDescription, String strStatus) {
        this.m_intId = intId;
        this.m_fltAmount = fltAmount;
        this.m_fltTotalWeight = fltTotalWeight;
        this.m_fltAdditionalFee = fltAdditionalFee;
        this.m_dtOrderDate = dtOrderDate;
        this.m_strDescription = strDescription;
        this.m_strStatus = strStatus;
    }

    public Order(float fltAmount, float fltTotalWeight, float fltAdditionalFee, DATE dtOrderDate,
                 String strDescription, String strStatus) {
        this.m_intId = DEFAULT_ID;
        this.m_fltAmount = fltAmount;
        this.m_fltTotalWeight = fltTotalWeight;
        this.m_fltAdditionalFee = fltAdditionalFee;
        this.m_dtOrderDate = dtOrderDate;
        this.m_strDescription = strDescription;
        this.m_strStatus = strStatus;
    }

    public Order() {
        this.m_intId = DEFAULT_ID;
        this.m_fltAmount = DEFAULT_AMOUNT;
        this.m_fltTotalWeight = DEFAULT_TOTAL_WEIGHT;
        this.m_fltAdditionalFee = DEFAULT_ADDITIONAL_FEE;
        this.m_dtOrderDate = DEFAULT_DATE;
        this.m_strDescription = DEFAULT_DESCRIPTION;
        this.m_strStatus = DEFAULT_STATUS;
    }

    public int getId() {
        return m_intId;
    }

    public void setId(int intId) {
        this.m_intId = intId;
    }

    public float getAmount() {
        return m_fltAmount;
    }

    public void setAmount(float fltAmount) {
        this.m_fltAmount = fltAmount;
    }

    public float getTotalWeight() {
        return m_fltTotalWeight;
    }

    public void setTotalWeight(float fltTotalWeight) {
        this.m_fltTotalWeight = fltTotalWeight;
    }

    public float getAdditionalFee() {
        return m_fltAdditionalFee;
    }

    public void setAdditionalFee(float fltAdditionalFee) {
        this.m_fltAdditionalFee = fltAdditionalFee;
    }

    public DATE getOrderDate() {
        return m_dtOrderDate;
    }

    public void setOrderDate(DATE dtOrderDate) {
        this.m_dtOrderDate = dtOrderDate;
    }

    public String getDescription() {
        return m_strDescription;
    }

    public void setDescription(String strDescription) {
        this.m_strDescription = strDescription;
    }

    public String getStatus() {
        return m_strStatus;
    }

    public void setStatus(String strStatus) {
        this.m_strStatus = strStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "m_intId=" + m_intId +
                ", m_fltAmount=" + m_fltAmount +
                ", m_fltTotalWeight=" + m_fltTotalWeight +
                ", m_fltAdditionalFee=" + m_fltAdditionalFee +
                ", m_dtOrderDate=" + m_dtOrderDate +
                ", m_strDescription='" + m_strDescription + '\'' +
                ", m_strStatus='" + m_strStatus + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return m_intId == order.m_intId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_intId);
    }
}
