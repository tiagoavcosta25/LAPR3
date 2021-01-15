package lapr.project.model;

import java.sql.Date;
import java.util.Calendar;
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
    private boolean m_blIsHomeDelivery;
    private Client m_oClient;
    private Pharmacy m_oPharmacy;
    private Map<Product, Integer> m_mapProducts;

    private static int DEFAULT_ID = -1;
    private static float DEFAULT_AMOUNT = -1;
    private static float DEFAULT_TOTAL_WEIGHT = -1;
    private static float DEFAULT_ADDITIONAL_FEE = -1;
    private static float HOME_DELIVERY_FEE = 5;
    private static float STORE_PICKUP_FEE = 0;
    private static Date CURRENT_DATE = new Date(Calendar.getInstance().getTimeInMillis());
    private static String DEFAULT_DESCRIPTION = "No Description.";
    private static String DEFAULT_STATUS = "ordered";
    private static boolean DEFAULT_IS_HOME_DELIVERY = false;
    private static Client DEFAULT_CLIENT = new Client();
    private static Pharmacy DEFAULT_PHARMACY = new Pharmacy();
    private static Map<Product, Integer> DEFAULT_PRODUCT_MAP = new TreeMap<>();

    public Order(int intId, float fltAmount, float fltTotalWeight, float fltAdditionalFee, Date dtOrderDate,
                 String strDescription, String strStatus, boolean blIsHomeDelivery, Client oClient, Pharmacy oPharmacy, Map<Product, Integer> mapProducts) {
        this.m_intId = intId;
        this.m_fltAmount = fltAmount;
        this.m_fltTotalWeight = fltTotalWeight;
        this.m_fltAdditionalFee = fltAdditionalFee;
        this.m_dtOrderDate = (Date) dtOrderDate.clone();
        this.m_strDescription = strDescription;
        this.m_strStatus = strStatus;
        this.m_blIsHomeDelivery = blIsHomeDelivery;
        this.m_oClient = oClient;
        this.m_oPharmacy = oPharmacy;
        this.m_mapProducts = mapProducts;
    }

    public Order(float fltAmount, float fltTotalWeight, float fltAdditionalFee, Date dtOrderDate, String strDescription,
                 String strStatus, boolean blIsHomeDelivery, Client oClient, Pharmacy oPharmacy, Map<Product, Integer> mapProducts) {
        this.m_intId = DEFAULT_ID;
        this.m_fltAmount = fltAmount;
        this.m_fltTotalWeight = fltTotalWeight;
        this.m_fltAdditionalFee = fltAdditionalFee;
        this.m_dtOrderDate = (Date) dtOrderDate.clone();
        this.m_strDescription = strDescription;
        this.m_strStatus = strStatus;
        this.m_blIsHomeDelivery = blIsHomeDelivery;
        this.m_oClient = oClient;
        this.m_oPharmacy = oPharmacy;
        this.m_mapProducts = mapProducts;
    }

    public Order(int intId, String strDescription, boolean blIsHomeDelivery, Client oClient,
                 Pharmacy oPharmacy, Map<Product, Integer> mapProducts) {
        this.m_intId = intId;
        this.m_dtOrderDate = CURRENT_DATE;
        this.m_strDescription = strDescription;
        this.m_strStatus = DEFAULT_STATUS;
        this.m_blIsHomeDelivery = blIsHomeDelivery;
        this.m_oClient = oClient;
        this.m_oPharmacy = oPharmacy;
        this.m_mapProducts = mapProducts;

        calculateAdditonalFee();
        calculateAmount();
        calculateTotalWeight();
        this.m_oClient.addCredits((int)(this.m_fltAmount + this.m_fltAdditionalFee) / 5);
    }

    public Order(String strDescription, boolean blIsHomeDelivery, Client oClient,
                 Pharmacy oPharmacy, Map<Product, Integer> mapProducts) {
        this.m_intId = DEFAULT_ID;
        this.m_fltAdditionalFee = HOME_DELIVERY_FEE;
        this.m_dtOrderDate = CURRENT_DATE;
        this.m_strDescription = strDescription;
        this.m_strStatus = DEFAULT_STATUS;
        this.m_blIsHomeDelivery = blIsHomeDelivery;
        this.m_oClient = oClient;
        this.m_oPharmacy = oPharmacy;
        this.m_mapProducts = mapProducts;

        calculateAdditonalFee();
        calculateAmount();
        calculateTotalWeight();
        this.m_oClient.addCredits((int)(this.m_fltAmount + this.m_fltAdditionalFee) / 5);
    }

    public Order() {
        this.m_intId = DEFAULT_ID;
        this.m_fltAmount = DEFAULT_AMOUNT;
        this.m_fltTotalWeight = DEFAULT_TOTAL_WEIGHT;
        this.m_fltAdditionalFee = DEFAULT_ADDITIONAL_FEE;
        this.m_dtOrderDate = CURRENT_DATE;
        this.m_strDescription = DEFAULT_DESCRIPTION;
        this.m_strStatus = DEFAULT_STATUS;
        this.m_blIsHomeDelivery = DEFAULT_IS_HOME_DELIVERY;
        this.m_oClient = DEFAULT_CLIENT;
        this.m_oPharmacy = DEFAULT_PHARMACY;
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
        return (Date) m_dtOrderDate.clone();
    }

    public void setOrderDate(Date dtOrderDate) {
        this.m_dtOrderDate = (Date) dtOrderDate.clone();
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

    public boolean isHomeDelivery() {
        return m_blIsHomeDelivery;
    }

    public void setHomeDelivery(boolean blIsHomeDelivery) {
        this.m_blIsHomeDelivery = blIsHomeDelivery;
    }

    public Client getClient() {
        return m_oClient;
    }

    public void setClient(Client m_oClient) {
        this.m_oClient = m_oClient;
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
        calculateAmount();
        calculateTotalWeight();
    }

    public void calculateAdditonalFee() {
        if(this.m_blIsHomeDelivery){
            this.m_fltAdditionalFee = HOME_DELIVERY_FEE;
        } else{
            this.m_fltAdditionalFee = STORE_PICKUP_FEE;
        }
    }

    public void calculateAmount() {
        float fltAmount = 0f;
        for(Product p : this.m_mapProducts.keySet()){
            fltAmount += p.getUnitaryPrice() * (float) this.m_mapProducts.get(p);
        }
        this.m_fltAmount = fltAmount;
    }

    public void calculateTotalWeight() {
        float fltTotalWeight = 0f;
        for(Product p : this.m_mapProducts.keySet()){
            fltTotalWeight += p.getUnitaryWeight() * (float) this.m_mapProducts.get(p);
        }
        this.m_fltTotalWeight = fltTotalWeight;
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
                ", m_blIsHomeDelivery=" + m_blIsHomeDelivery +
                ", m_oClient=" + m_oClient +
                ", m_oPharmacy=" + m_oPharmacy +
                ", m_mapProducts=" + m_mapProducts +
                '}';
    }
}
