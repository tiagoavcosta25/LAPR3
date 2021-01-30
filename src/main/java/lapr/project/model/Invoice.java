package lapr.project.model;

import java.sql.Date;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * Invoice.
 *
 * Group: Team Lisa [G-021]
 * ______________________________________________________
 * @author António Barbosa <1190404@isep.ipp.pt>
 * @author Ernesto Rodrigues <1190560@isep.ipp.pt>
 * @author Jessica Alves <1190682@isep.ipp.pt>
 * @author Pedro Santos <1190967@isep.ipp.pt>
 * @author Rodrigo Costa <1191014@isep.ipp.pt>
 * @author Tiago Costa <1191460@isep.ipp.pt>
 */
public class Invoice {
    /**
     * ID.
     */
    private int mintId;

    /**
     * Invoice Date.
     */
    private Date mdtInvoiceDate;

    /**
     * Total Price.
     */
    private Double mdblTotalPrice;

    /**
     * Order.
     */
    private Order moOrder;

    /**
     * Payment Map.
     */
    private Map<CreditCard, Double> mMapPayments;

    /**
     * Default Value for the ID.
     */
    private static int mDEFAULTID = -1;

    /**
     * Default Value for the Order.
     */
    private static Order mDEFAULTORDER = new Order();

    /**
     * Default Value for the Payment Map.
     */
    private static Map<CreditCard, Double> mDEFAULTPAYMENTS = new TreeMap<>();

    /**
     * Invoice Constructor.
     * @param intId ID.
     * @param dtDate Invoice Date.
     * @param dblTotalAmount Total Amont.
     * @param oOrder Order.
     */
    public Invoice(int intId, Date dtDate, Double dblTotalAmount, Order oOrder) {
        this.mintId = intId;
        this.mdtInvoiceDate = (Date) dtDate.clone();
        this.mdblTotalPrice = dblTotalAmount;
        this.moOrder = oOrder;
        this.mMapPayments = mDEFAULTPAYMENTS;
    }

    /**
     * Invoice Constructor.
     * @param intId ID.
     * @param oOrder Order.
     * @param mapPayments Payment Map.
     */
    public Invoice(int intId, Order oOrder, Map<CreditCard, Double> mapPayments) {
        this.mintId = intId;
        this.mdtInvoiceDate = (Date) oOrder.getOrderDate().clone();
        this.mdblTotalPrice = oOrder.getAmount();
        this.moOrder = oOrder;
        this.mMapPayments = mapPayments;
    }

    /**
     * Invoice Constructor.
     * @param oOrder Order.
     * @param mapPayments Payment Map.
     */
    public Invoice(Order oOrder, Map<CreditCard, Double> mapPayments) {
        this.mintId = mDEFAULTID;
        this.mdtInvoiceDate = (Date) oOrder.getOrderDate().clone();
        this.mdblTotalPrice = oOrder.getAmount();
        this.moOrder = oOrder;
        this.mMapPayments = mapPayments;
    }

    /**
     * Empty Invoice Constructor.
     */
    public Invoice() {
        this.mintId = mDEFAULTID;
        this.mdtInvoiceDate = mDEFAULTORDER.getOrderDate();
        this.mdblTotalPrice = mDEFAULTORDER.getAmount();
        this.moOrder = mDEFAULTORDER;
        this.mMapPayments = mDEFAULTPAYMENTS;
    }

    /**
     * Getter for the ID.
     * @return ID.
     */
    public int getId() {
        return mintId;
    }

    /**
     * Setter for the ID.
     * @param intId ID:
     */
    public void setId(int intId) {
        this.mintId = intId;
    }

    /**
     * Getter for the Invoice Date.
     * @return Getter for the Invoice Date.
     */
    public Date getInvoiceDate() {
        return (Date) mdtInvoiceDate.clone();
    }

    /**
     * Setter for the Invoice Date.
     * @param dtInvoiceDate Invoice Date.
     */
    public void setInvoiceDate(Date dtInvoiceDate) {
        this.mdtInvoiceDate = (Date) dtInvoiceDate.clone();
    }

    /**
     * Getter for the Total Price.
     * @return Total Price.
     */
    public Double getTotalPrice() {
        return mdblTotalPrice;
    }

    /**
     * Setter for the Total Price.
     * @param dblTotalPrice Total Price.
     */
    public void setTotalPrice(Double dblTotalPrice) {
        this.mdblTotalPrice = dblTotalPrice;
    }

    /**
     * Getter for the Order.
     * @return Order.
     */
    public Order getOrder() {
        return moOrder;
    }

    /**
     * Setter for the Order.
     * @param oOrder Order.
     */
    public void setOrder(Order oOrder) {
        this.moOrder = oOrder;
    }

    /**
     * Getter for the Map of Payments.
     * @return Map of Payments.
     */
    public Map<CreditCard, Double> getPayments() {
        return mMapPayments;
    }

    /**
     * Setter for the Map of Payments.
     * @param mapPayments Map of Payments.
     */
    public void setPayments(Map<CreditCard, Double> mapPayments) {
        this.mMapPayments = mapPayments;
    }

    /**
     * Equals override.
     * @param o object.
     * @return true if the objects are equal, false they are not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return mintId == invoice.mintId;
    }

    /**
     * Hash Code Override.
     * @return Hash Code Value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(mintId);
    }

    /**
     * toString Override.
     * @return Invoice Information.
     */
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
