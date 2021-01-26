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

    private static int mDEFAULTID = -1;
    private static Order mDEFAULTORDER = new Order();
    private static Map<CreditCard, Double> mDEFAULTPAYMENTS = new TreeMap<>();

    public Invoice(int intId, Date dtDate, Double dblTotalAmount, Order oOrder) {
        this.mintId = intId;
        this.mdtInvoiceDate = (Date) dtDate.clone();
        this.mdblTotalPrice = dblTotalAmount;
        this.moOrder = oOrder;
        this.mMapPayments = mDEFAULTPAYMENTS;
    }

    public Invoice(int intId, Order oOrder, Map<CreditCard, Double> mapPayments) {
        this.mintId = intId;
        this.mdtInvoiceDate = (Date) oOrder.getOrderDate().clone();
        this.mdblTotalPrice = oOrder.getAmount();
        this.moOrder = oOrder;
        this.mMapPayments = mapPayments;
    }

    public Invoice(Order oOrder, Map<CreditCard, Double> mapPayments) {
        this.mintId = mDEFAULTID;
        this.mdtInvoiceDate = (Date) oOrder.getOrderDate().clone();
        this.mdblTotalPrice = oOrder.getAmount();
        this.moOrder = oOrder;
        this.mMapPayments = mapPayments;
    }

    public Invoice() {
        this.mintId = mDEFAULTID;
        this.mdtInvoiceDate = mDEFAULTORDER.getOrderDate();
        this.mdblTotalPrice = mDEFAULTORDER.getAmount();
        this.moOrder = mDEFAULTORDER;
        this.mMapPayments = mDEFAULTPAYMENTS;
    }

    public int getId() {
        return mintId;
    }

    public void setId(int intId) {
        this.mintId = intId;
    }

    public Date getInvoiceDate() {
        return (Date) mdtInvoiceDate.clone();
    }

    public void setInvoiceDate(Date dtInvoiceDate) {
        this.mdtInvoiceDate = (Date) dtInvoiceDate.clone();
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

    public void setOrder(Order oOrder) {
        this.moOrder = oOrder;
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
