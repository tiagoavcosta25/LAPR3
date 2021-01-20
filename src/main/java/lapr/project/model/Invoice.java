package lapr.project.model;

import java.sql.Date;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Invoice {
    private int mintId;
    private Date mdtInvoiceDate;
    private Double mdblTotalPrice;
    private Order moOrder;
    private Map<CreditCard, Double> mMapPayments;

    private static int DEFAULTID = -1;
    private static Order DEFAULTORDER = new Order();
    private static Map<CreditCard, Double> DEFAULTPAYMENTS = new TreeMap<>();

    public Invoice(int intId, Date dtDate, Double dblTotalAmount, Order oOrder) {
        this.mintId = intId;
        this.mdtInvoiceDate = (Date) dtDate.clone();
        this.mdblTotalPrice = dblTotalAmount;
        this.moOrder = oOrder;
        this.mMapPayments = DEFAULTPAYMENTS;
    }

    public Invoice(int intId, Order oOrder, Map<CreditCard, Double> mapPayments) {
        this.mintId = intId;
        this.mdtInvoiceDate = (Date) oOrder.getOrderDate().clone();
        this.mdblTotalPrice = oOrder.getAmount();
        this.moOrder = oOrder;
        this.mMapPayments = mapPayments;
    }

    public Invoice(Order oOrder, Map<CreditCard, Double> mapPayments) {
        this.mintId = DEFAULTID;
        this.mdtInvoiceDate = (Date) oOrder.getOrderDate().clone();
        this.mdblTotalPrice = oOrder.getAmount();
        this.moOrder = oOrder;
        this.mMapPayments = mapPayments;
    }

    public Invoice() {
        this.mintId = DEFAULTID;
        this.mdtInvoiceDate = DEFAULTORDER.getOrderDate();
        this.mdblTotalPrice = DEFAULTORDER.getAmount();
        this.moOrder = DEFAULTORDER;
        this.mMapPayments = DEFAULTPAYMENTS;
    }

    public int getId() {
        return mintId;
    }

    public void setId(int m_intId) {
        this.mintId = m_intId;
    }

    public Date getInvoiceDate() {
        return (Date) mdtInvoiceDate.clone();
    }

    public void setInvoiceDate(Date m_dtInvoiceDate) {
        this.mdtInvoiceDate = (Date) m_dtInvoiceDate.clone();
    }

    public Double getTotalPrice() {
        return mdblTotalPrice;
    }

    public void setTotalPrice(Double dblTotalPrice) {
        this.mdblTotalPrice = dblTotalPrice;
    }

    public Order getOrder() {
        return moOrder;
    }

    public void setOrder(Order m_oOrder) {
        this.moOrder = m_oOrder;
    }

    public Map<CreditCard, Double> getPayments() {
        return mMapPayments;
    }

    public void setPayments(Map<CreditCard, Double> mapPayments) {
        this.mMapPayments = mapPayments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return mintId == invoice.mintId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mintId);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "m_intId=" + mintId +
                ", m_dtInvoiceDate=" + mdtInvoiceDate +
                ", m_fltTotalPrice=" + mdblTotalPrice +
                ", m_oOrder=" + moOrder +
                ", m_mapPayments=" + mMapPayments +
                '}';
    }
}
