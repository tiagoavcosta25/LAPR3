package lapr.project.model;

import java.sql.Date;
import java.util.Objects;

public class Invoice {
    private int m_intId;
    private Date m_dtInvoiceDate;
    private float m_fltTotalPrice;
    private Order m_oOrder;

    private static int DEFAULT_ID = -1;
    private static Date DEFAULT_DATE = null;
    private static float DEFAULT_TOTAL_PRICE = -1;
    private static Order DEFAULT_ORDER = new Order();

    public Invoice(int intId, Date dtInvoiceDate, float fltTotalPrice, Order oOrder) {
        this.m_intId = intId;
        this.m_dtInvoiceDate = dtInvoiceDate;
        this.m_fltTotalPrice = fltTotalPrice;
        this.m_oOrder = oOrder;
    }

    public Invoice(Date dtInvoiceDate, float fltTotalPrice, Order oOrder) {
        this.m_intId = DEFAULT_ID;
        this.m_dtInvoiceDate = dtInvoiceDate;
        this.m_fltTotalPrice = fltTotalPrice;
        this.m_oOrder = oOrder;
    }

    public Invoice() {
        this.m_intId = DEFAULT_ID;
        this.m_dtInvoiceDate = DEFAULT_DATE;
        this.m_fltTotalPrice = DEFAULT_TOTAL_PRICE;
        this.m_oOrder = DEFAULT_ORDER;
    }

    public int getM_intId() {
        return m_intId;
    }

    public void setM_intId(int m_intId) {
        this.m_intId = m_intId;
    }

    public Date getM_dtInvoiceDate() {
        return m_dtInvoiceDate;
    }

    public void setM_dtInvoiceDate(Date m_dtInvoiceDate) {
        this.m_dtInvoiceDate = m_dtInvoiceDate;
    }

    public float getM_fltTotalPrice() {
        return m_fltTotalPrice;
    }

    public void setM_fltTotalPrice(float m_fltTotalPrice) {
        this.m_fltTotalPrice = m_fltTotalPrice;
    }

    public Order getM_oOrder() {
        return m_oOrder;
    }

    public void setM_oOrder(Order m_oOrder) {
        this.m_oOrder = m_oOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return m_intId == invoice.m_intId &&
                Float.compare(invoice.m_fltTotalPrice, m_fltTotalPrice) == 0 &&
                m_dtInvoiceDate.equals(invoice.m_dtInvoiceDate) &&
                m_oOrder.equals(invoice.m_oOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_intId, m_dtInvoiceDate, m_fltTotalPrice, m_oOrder);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "m_intId=" + m_intId +
                ", m_dtInvoiceDate=" + m_dtInvoiceDate +
                ", m_fltTotalPrice=" + m_fltTotalPrice +
                ", m_oOrder=" + m_oOrder +
                '}';
    }
}