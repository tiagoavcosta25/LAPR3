package lapr.project.model;

import java.sql.Date;
import java.util.Calendar;
import java.util.Objects;

/**
 * Pharmacy Transfer.
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
public class PharmacyTransfer {
    /**
     * id.
     */
    private Integer mintId;

    /**
     * Date.
     */
    private Date mdtTransferDate;

    /**
     * Order.
     */
    private Order moOrder;

    /**
     * Product.
     */
    private Product moProduct;

    /**
     * Quantity.
     */
    private Integer mintQuantity;

    /**
     * Nearby Pharamcy With Stock.
     */
    private Pharmacy moNearbyPharmacy;

    /**
     * Default id.
     */
    private static Integer mDEFAULTID = -1;

    /**
     * Current Date.
     */
    private static Date mCURRENTDATE = new Date(Calendar.getInstance().getTimeInMillis());

    /**
     * Default Order.
     */
    private static Order mDEFAULTORDER = new Order();

    /**
     * Default Address.
     */
    private static Product mDEFAULTADDRESS = new Product();

    /**
     * Default Quantity.
     */
    private static Integer mDEFAULTQUANTITY = -1;

    /**
     * Default Pharmacy.
     */
    private static Pharmacy mDEFAULTPHARMACY = new Pharmacy();

    /**
     * Pharmacy Transfer Constructor.
     * @param intId id.
     * @param dtDate date.
     * @param oOrder order.
     * @param oProduct product.
     * @param intQuantity quantity.
     * @param oNearbyPharmacy nearby pharmacy.
     */
    public PharmacyTransfer(Integer intId, Date dtDate, Order oOrder, Product oProduct, Integer intQuantity, Pharmacy oNearbyPharmacy) {
        this.mintId = intId;
        this.mdtTransferDate = (Date) dtDate.clone();
        this.moOrder = oOrder;
        this.moProduct = oProduct;
        this.mintQuantity = intQuantity;
        this.moNearbyPharmacy = oNearbyPharmacy;
    }

    /**
     * Pharmacy Transfer Constructor.
     * @param dtDate date.
     * @param oOrder order.
     * @param oProduct product.
     * @param intQuantity quantity.
     * @param oNearbyPharmacy nearby pharmacy.
     */
    public PharmacyTransfer(Date dtDate, Order oOrder, Product oProduct, Integer intQuantity, Pharmacy oNearbyPharmacy) {
        this.mintId = mDEFAULTID;
        this.mdtTransferDate = (Date) dtDate.clone();
        this.moOrder = oOrder;
        this.moProduct = oProduct;
        this.mintQuantity = intQuantity;
        this.moNearbyPharmacy = oNearbyPharmacy;
    }

    /**
     * Pharmacy Transfer Constructor.
     * @param oOrder order.
     * @param oProduct product.
     * @param intQuantity quantity.
     * @param oNearbyPharmacy nearby pharamcy.
     */
    public PharmacyTransfer(Order oOrder, Product oProduct, Integer intQuantity, Pharmacy oNearbyPharmacy) {
        this.mintId = mDEFAULTID;
        this.mdtTransferDate = mCURRENTDATE;
        this.moOrder = oOrder;
        this.moProduct = oProduct;
        this.mintQuantity = intQuantity;
        this.moNearbyPharmacy = oNearbyPharmacy;
    }

    /**
     * Empty Pharmacy Transfer Constructor.
     */
    public PharmacyTransfer() {
        this.mintId = mDEFAULTID;
        this.mdtTransferDate = mCURRENTDATE;
        this.moOrder = mDEFAULTORDER;
        this.moProduct = mDEFAULTADDRESS;
        this.mintQuantity = mDEFAULTQUANTITY;
        this.moNearbyPharmacy = mDEFAULTPHARMACY;
    }

    /**
     * Getter for the id.
     * @return id.
     */
    public Integer getId() {
        return mintId;
    }

    /**
     * Setter for the id.
     * @param intId id.
     */
    public void setId(Integer intId) {
        this.mintId = intId;
    }

    /**
     * Getter for the date.
     * @return date.
     */
    public Date getTransferDate() {
        return (Date)mdtTransferDate.clone();
    }

    /**
     * Setter for the date.
     * @param mdtTransferDate date.
     */
    public void setTransferDate(Date mdtTransferDate) {
        this.mdtTransferDate = (Date)mdtTransferDate.clone();
    }

    /**
     * Getter for the order.
     * @return order.
     */
    public Order getOrder() {
        return moOrder;
    }

    /**
     * Setter for the order.
     * @param oOrder order.
     */
    public void setOrder(Order oOrder) {
        this.moOrder = oOrder;
    }

    /**
     * Getter for the product.
     * @return product.
     */
    public Product getProduct() {
        return moProduct;
    }

    /**
     * Setter for the product.
     * @param oProduct product.
     */
    public void setProduct(Product oProduct) {
        this.moProduct = oProduct;
    }

    /**
     * Getter for the quantity.
     * @return quantity.
     */
    public Integer getQuantity() {
        return mintQuantity;
    }

    /**
     * Setter for the quantity.
     * @param intQuantity quantity.
     */
    public void setQuantity(Integer intQuantity) {
        this.mintQuantity = intQuantity;
    }

    /**
     * Getter for the nearby pharmacy.
     * @return nearby pharmacy.
     */
    public Pharmacy getNearbyPharmacy() {
        return moNearbyPharmacy;
    }

    /**
     * Setter for the nearby pharmacy.
     * @param oNearbyPharmacy nearby pharmacy.
     */
    public void setNearbyPharmacy(Pharmacy oNearbyPharmacy) {
        this.moNearbyPharmacy = oNearbyPharmacy;
    }

    /**
     * Equals Override.
     * @param o object.
     * @return true or false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PharmacyTransfer that = (PharmacyTransfer) o;
        return mintId.equals(that.mintId);
    }

    /**
     * Hash Code Override.
     * @return hash code value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(mintId);
    }

    /**
     * To String Override.
     * @return information.
     */
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
