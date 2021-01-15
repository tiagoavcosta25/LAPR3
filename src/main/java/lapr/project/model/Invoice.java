package lapr.project.model;

import java.sql.Date;
import java.util.Calendar;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Invoice {
    private int m_intId;
    private Date m_dtInvoiceDate;
    private float m_fltTotalPrice;
    private Order m_oOrder;
    private Map<CreditCard, Float> m_mapPayments;

    private static int DEFAULT_ID = -1;
    private static Date CURRENT_DATE = new Date(Calendar.getInstance().getTimeInMillis());
    private static float DEFAULT_TOTAL_PRICE = -1;
    private static Order DEFAULT_ORDER = new Order();
    private static Map<CreditCard, Float> DEFAULT_PAYMENTS = new TreeMap<>();

    public Invoice(int intId, Date dtDate, float fltTotalAmount, Order oOrder) {
        this.m_intId = intId;
        this.m_dtInvoiceDate = (Date) dtDate.clone();
        this.m_fltTotalPrice = fltTotalAmount;
        this.m_oOrder = oOrder;
        this.m_mapPayments = DEFAULT_PAYMENTS;
    }

    public Invoice(int intId, Order oOrder, Map<CreditCard, Float> mapPayments) {
        this.m_intId = intId;
        this.m_dtInvoiceDate = (Date) oOrder.getOrderDate().clone();
        this.m_fltTotalPrice = oOrder.getAmount();
        this.m_oOrder = oOrder;
        this.m_mapPayments = mapPayments;
    }

    public Invoice(Order oOrder, Map<CreditCard, Float> mapPayments) {
        this.m_intId = DEFAULT_ID;
        this.m_dtInvoiceDate = (Date) oOrder.getOrderDate().clone();
        this.m_fltTotalPrice = oOrder.getAmount();
        this.m_oOrder = oOrder;
        this.m_mapPayments = mapPayments;
    }

    public Invoice() {
        this.m_intId = DEFAULT_ID;
        this.m_dtInvoiceDate = DEFAULT_ORDER.getOrderDate();
        this.m_fltTotalPrice = DEFAULT_ORDER.getAmount();
        this.m_oOrder = DEFAULT_ORDER;
        this.m_mapPayments = DEFAULT_PAYMENTS;
    }

    public int getId() {
        return m_intId;
    }

    public void setId(int m_intId) {
        this.m_intId = m_intId;
    }

    public Date getInvoiceDate() {
        return (Date) m_dtInvoiceDate.clone();
    }

    public void setInvoiceDate(Date m_dtInvoiceDate) {
        this.m_dtInvoiceDate = (Date) m_dtInvoiceDate.clone();
    }

    public float getTotalPrice() {
        return m_fltTotalPrice;
    }

    public void setTotalPrice(float m_fltTotalPrice) {
        this.m_fltTotalPrice = m_fltTotalPrice;
    }

    public Order getOrder() {
        return m_oOrder;
    }

    public void setOrder(Order m_oOrder) {
        this.m_oOrder = m_oOrder;
    }

    public Map<CreditCard, Float> getPayments() {
        return m_mapPayments;
    }

    public void setPayments(Map<CreditCard, Float> mapPayments) {
        this.m_mapPayments = mapPayments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return m_intId == invoice.m_intId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_intId);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "m_intId=" + m_intId +
                ", m_dtInvoiceDate=" + m_dtInvoiceDate +
                ", m_fltTotalPrice=" + m_fltTotalPrice +
                ", m_oOrder=" + m_oOrder +
                ", m_mapPayments=" + m_mapPayments +
                '}';
    }
}
