package lapr.project.model;

import java.sql.Date;
import java.util.Calendar;
import java.util.Objects;

public class PharmacyTransfer {
    private Integer mintId;
    private Date mdtTransferDate;
    private Order moOrder;
    private Product moProduct;
    private Integer mintQuantity;
    private Pharmacy moNearbyPharmacy;

    private static Integer mDEFAULTID = -1;
    private static Date mCURRENTDATE = new Date(Calendar.getInstance().getTimeInMillis());
    private static Order mDEFAULTORDER = new Order();
    private static Product mDEFAULTADDRESS = new Product();
    private static Integer mDEFAULTQUANTITY = -1;
    private static Pharmacy mDEFAULTPHARMACY = new Pharmacy();

    public PharmacyTransfer(Integer intId, Date dtDate, Order oOrder, Product oProduct, Integer intQuantity, Pharmacy oNearbyPharmacy) {
        this.mintId = intId;
        this.mdtTransferDate = (Date) dtDate.clone();
        this.moOrder = oOrder;
        this.moProduct = oProduct;
        this.mintQuantity = intQuantity;
        this.moNearbyPharmacy = oNearbyPharmacy;
    }

    public PharmacyTransfer(Date dtDate, Order oOrder, Product oProduct, Integer intQuantity, Pharmacy oNearbyPharmacy) {
        this.mintId = mDEFAULTID;
        this.mdtTransferDate = (Date) dtDate.clone();
        this.moOrder = oOrder;
        this.moProduct = oProduct;
        this.mintQuantity = intQuantity;
        this.moNearbyPharmacy = oNearbyPharmacy;
    }

    public PharmacyTransfer(Order oOrder, Product oProduct, Integer intQuantity, Pharmacy oNearbyPharmacy) {
        this.mintId = mDEFAULTID;
        this.mdtTransferDate = mCURRENTDATE;
        this.moOrder = oOrder;
        this.moProduct = oProduct;
        this.mintQuantity = intQuantity;
        this.moNearbyPharmacy = oNearbyPharmacy;
    }

    public PharmacyTransfer() {
        this.mintId = mDEFAULTID;
        this.mdtTransferDate = mCURRENTDATE;
        this.moOrder = mDEFAULTORDER;
        this.moProduct = mDEFAULTADDRESS;
        this.mintQuantity = mDEFAULTQUANTITY;
        this.moNearbyPharmacy = mDEFAULTPHARMACY;
    }


    public Integer getId() {
        return mintId;
    }

    public void setId(Integer intId) {
        this.mintId = intId;
    }

    public Date getTransferDate() {
        return (Date)mdtTransferDate.clone();
    }

    public void setTransferDate(Date m_dtTransferDate) {
        this.mdtTransferDate = (Date)m_dtTransferDate.clone();
    }

    public Order getOrder() {
        return moOrder;
    }

    public void setOrder(Order oOrder) {
        this.moOrder = oOrder;
    }

    public Product getProduct() {
        return moProduct;
    }

    public void setProduct(Product oProduct) {
        this.moProduct = oProduct;
    }

    public Integer getQuantity() {
        return mintQuantity;
    }

    public void setQuantity(Integer intQuantity) {
        this.mintQuantity = intQuantity;
    }

    public Pharmacy getNearbyPharmacy() {
        return moNearbyPharmacy;
    }

    public void setNearbyPharmacy(Pharmacy oNearbyPharmacy) {
        this.moNearbyPharmacy = oNearbyPharmacy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PharmacyTransfer that = (PharmacyTransfer) o;
        return mintId.equals(that.mintId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mintId);
    }


    @Override
    public String toString() {
        return "PharmacyTransfer{" +
                "m_intId=" + mintId +
                ", m_dtTransferDate=" + mdtTransferDate +
                ", m_oOrder=" + moOrder +
                ", m_oProduct=" + moProduct +
                ", m_intQuantity=" + mintQuantity +
                ", m_oNearbyPharmacy=" + moNearbyPharmacy +
                '}';
    }
}
