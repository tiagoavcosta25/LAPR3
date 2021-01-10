package lapr.project.model;

import java.sql.Date;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Order {
    private int m_intId;
    private float m_fltAmount;
    private float m_fltTotalWeight;
    private float m_fltAdditionalFee;
    private Date m_dtOrderDate;
    private String m_strDescription;
    private String m_strStatus;
    private Client m_oClient;
    private Address m_oAddress;
    private Pharmacy m_oPharmacy;
    private Map<Product, Integer> m_mapProducts;

    private static int DEFAULT_ID = -1;
    private static float DEFAULT_AMOUNT = -1;
    private static float DEFAULT_TOTAL_WEIGHT = -1;
    private static float DEFAULT_ADDITIONAL_FEE = -1;
    private static Date DEFAULT_DATE = null;
    private static String DEFAULT_DESCRIPTION = "No Description.";
    private static String DEFAULT_STATUS = "Ordered";
    private static Client DEFAULT_CLIENT = new Client();
    private static Address DEFAULT_ADDRESS = new Address();
    private static Pharmacy DEFAULT_PHARMACY = new Pharmacy();
    private static Map<Product, Integer> DEFAULT_PRODUCT_MAP = new TreeMap<>();

    public Order(int intId, float fltAmount, float fltTotalWeight, float fltAdditionalFee, Date dtOrderDate,
                 String strDescription, String strStatus, Client oClient, Address oAddress, Pharmacy oPharmacy, Map<Product, Integer> mapProducts) {
        this.m_intId = intId;
        this.m_fltAmount = fltAmount;
        this.m_fltTotalWeight = fltTotalWeight;
        this.m_fltAdditionalFee = fltAdditionalFee;
        this.m_dtOrderDate = dtOrderDate;
        this.m_strDescription = strDescription;
        this.m_strStatus = strStatus;
        this.m_oClient = oClient;
        this.m_oAddress = oAddress;
        this.m_oPharmacy = oPharmacy;
        this.m_mapProducts = mapProducts;

        this.m_oClient.addCredits((int)(fltAmount + fltAdditionalFee) * 100);
    }

    public Order(float fltAmount, float fltTotalWeight, float fltAdditionalFee, Date dtOrderDate, String strDescription,
                 String strStatus, Client oClient, Address oAddress, Pharmacy oPharmacy, Map<Product, Integer> mapProducts) {
        this.m_intId = DEFAULT_ID;
        this.m_fltAmount = fltAmount;
        this.m_fltTotalWeight = fltTotalWeight;
        this.m_fltAdditionalFee = fltAdditionalFee;
        this.m_dtOrderDate = dtOrderDate;
        this.m_strDescription = strDescription;
        this.m_strStatus = strStatus;
        this.m_oClient = oClient;
        this.m_oAddress = oAddress;
        this.m_oPharmacy = oPharmacy;
        this.m_mapProducts = mapProducts;
    }

    public Order() {
        this.m_intId = DEFAULT_ID;
        this.m_fltAmount = DEFAULT_AMOUNT;
        this.m_fltTotalWeight = DEFAULT_TOTAL_WEIGHT;
        this.m_fltAdditionalFee = DEFAULT_ADDITIONAL_FEE;
        this.m_dtOrderDate = DEFAULT_DATE;
        this.m_strDescription = DEFAULT_DESCRIPTION;
        this.m_strStatus = DEFAULT_STATUS;
        this.m_oClient = DEFAULT_CLIENT;
        this.m_oPharmacy = DEFAULT_PHARMACY;
        this.m_oAddress = DEFAULT_ADDRESS;
        this.m_mapProducts = DEFAULT_PRODUCT_MAP;
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

    public Date getOrderDate() {
        return m_dtOrderDate;
    }

    public void setOrderDate(Date dtOrderDate) {
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

    public Client getClient() {
        return m_oClient;
    }

    public void setClient(Client m_oClient) {
        this.m_oClient = m_oClient;
    }

    public Address getAddress() {
        return m_oAddress;
    }

    public void setAddress(Address m_oAddress) {
        this.m_oAddress = m_oAddress;
    }

    public Pharmacy getPharmacy() {
        return m_oPharmacy;
    }

    public void setPharmacy(Pharmacy oPharmacy) {
        this.m_oPharmacy = oPharmacy;
    }

    public Map<Product, Integer> getProducts() {
        return m_mapProducts;
    }

    public void setProducts(Map<Product, Integer> mapProducts) {
        this.m_mapProducts = mapProducts;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return m_intId == order.m_intId &&
                Float.compare(order.m_fltAmount, m_fltAmount) == 0 &&
                Float.compare(order.m_fltTotalWeight, m_fltTotalWeight) == 0 &&
                Float.compare(order.m_fltAdditionalFee, m_fltAdditionalFee) == 0 &&
                Objects.equals(m_dtOrderDate, order.m_dtOrderDate) &&
                m_strDescription.equals(order.m_strDescription) &&
                m_strStatus.equals(order.m_strStatus) &&
                m_oClient.equals(order.m_oClient) &&
                m_oAddress.equals(order.m_oAddress) &&
                m_oPharmacy.equals(order.m_oPharmacy) &&
                m_mapProducts.equals(order.m_mapProducts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_intId, m_fltAmount, m_fltTotalWeight, m_fltAdditionalFee, m_dtOrderDate, m_strDescription, m_strStatus, m_oClient, m_oAddress, m_oPharmacy, m_mapProducts);
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
                ", m_oClient=" + m_oClient +
                ", m_oAddress=" + m_oAddress +
                ", m_oPharmacy=" + m_oPharmacy +
                ", m_mapProducts=" + m_mapProducts +
                '}';
    }
}
