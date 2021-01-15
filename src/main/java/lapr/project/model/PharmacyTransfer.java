package lapr.project.model;

import java.sql.Date;
import java.util.Calendar;
import java.util.Objects;

public class PharmacyTransfer {
    private Integer m_intId;
    private Date m_dtTransferDate;
    private Order m_oOrder;
    private Product m_oProduct;
    private Integer m_intQuantity;
    private Pharmacy m_oNearbyPharmacy;

    private static Integer DEFAULT_ID = -1;
    private static Date CURRENT_DATE = new Date(Calendar.getInstance().getTimeInMillis());
    private static Order DEFAULT_ORDER = new Order();
    private static Product DEFAULT_ADDRESS = new Product();
    private static Integer DEFAULT_QUANTITY = -1;
    private static Pharmacy DEFAULT_PHARMACY = new Pharmacy();

    public PharmacyTransfer(Integer intId, Date dtDate, Order oOrder, Product oProduct, Integer intQuantity, Pharmacy oNearbyPharmacy) {
        this.m_intId = intId;
        this.m_dtTransferDate = (Date) dtDate.clone();
        this.m_oOrder = oOrder;
        this.m_oProduct = oProduct;
        this.m_intQuantity = intQuantity;
        this.m_oNearbyPharmacy = oNearbyPharmacy;
    }

    public PharmacyTransfer(Date dtDate, Order oOrder, Product oProduct, Integer intQuantity, Pharmacy oNearbyPharmacy) {
        this.m_intId = DEFAULT_ID;
        this.m_dtTransferDate = (Date) dtDate.clone();
        this.m_oOrder = oOrder;
        this.m_oProduct = oProduct;
        this.m_intQuantity = intQuantity;
        this.m_oNearbyPharmacy = oNearbyPharmacy;
    }

    public PharmacyTransfer(Order oOrder, Product oProduct, Integer intQuantity, Pharmacy oNearbyPharmacy) {
        this.m_intId = DEFAULT_ID;
        this.m_dtTransferDate = CURRENT_DATE;
        this.m_oOrder = oOrder;
        this.m_oProduct = oProduct;
        this.m_intQuantity = intQuantity;
        this.m_oNearbyPharmacy = oNearbyPharmacy;
    }

    public PharmacyTransfer() {
        this.m_intId = DEFAULT_ID;
        this.m_dtTransferDate = CURRENT_DATE;
        this.m_oOrder = DEFAULT_ORDER;
        this.m_oProduct = DEFAULT_ADDRESS;
        this.m_intQuantity = DEFAULT_QUANTITY;
        this.m_oNearbyPharmacy = DEFAULT_PHARMACY;
    }


    public Integer getId() {
        return m_intId;
    }

    public void setId(Integer intId) {
        this.m_intId = intId;
    }

    public Date getTransferDate() {
        return m_dtTransferDate;
    }

    public void setTransferDate(Date m_dtTransferDate) {
        this.m_dtTransferDate = m_dtTransferDate;
    }

    public Order getOrder() {
        return m_oOrder;
    }

    public void setOrder(Order oOrder) {
        this.m_oOrder = oOrder;
    }

    public Product getProduct() {
        return m_oProduct;
    }

    public void setProduct(Product oProduct) {
        this.m_oProduct = oProduct;
    }

    public Integer getQuantity() {
        return m_intQuantity;
    }

    public void setQuantity(Integer intQuantity) {
        this.m_intQuantity = intQuantity;
    }

    public Pharmacy getNearbyPharmacy() {
        return m_oNearbyPharmacy;
    }

    public void setNearbyPharmacy(Pharmacy NearbyPharmacy) {
        this.m_oNearbyPharmacy = NearbyPharmacy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PharmacyTransfer that = (PharmacyTransfer) o;
        return m_intId.equals(that.m_intId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_intId);
    }


    @Override
    public String toString() {
        return "PharmacyTransfer{" +
                "m_intId=" + m_intId +
                ", m_dtTransferDate=" + m_dtTransferDate +
                ", m_oOrder=" + m_oOrder +
                ", m_oProduct=" + m_oProduct +
                ", m_intQuantity=" + m_intQuantity +
                ", m_oNearbyPharmacy=" + m_oNearbyPharmacy +
                '}';
    }
}
