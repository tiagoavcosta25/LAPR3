package lapr.project.model;

import java.sql.Date;
import java.util.Calendar;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Order {
    private int mintId;
    private float mfltAmount;
    private float mfltTotalWeight;
    private float mfltAdditionalFee;
    private Date mdtOrderDate;
    private String mstrDescription;
    private String mstrStatus;
    private boolean mblIsHomeDelivery;
    private Client moClient;
    private Pharmacy moPharmacy;
    private Map<Product, Integer> mMapProducts;

    private static int DEFAULTID = -1;
    private static float DEFAULTAMOUNT = -1;
    private static float DEFAULTTOTALWEIGHT = -1;
    private static float DEFAULTADDITIONALFEE = -1;
    private static float HOMEDELIVERYFEE = 5f;
    private static float STOREPICKUPFEE = 0f;
    private static int MINOFCREDITSFORFREEDELIVERY = 10;
    private static Date CURRENTDATE = new Date(Calendar.getInstance().getTimeInMillis());
    private static String DEFAULTDESCRIPTION = "No Description.";
    private static String DEFAULTSTATUS = "ordered";
    private static boolean DEFAULTISHOMEDELIVERY = false;
    private static Client DEFAULTCLIENT = new Client();
    private static Pharmacy DEFAULTPHARMACY = new Pharmacy();
    private static Map<Product, Integer> DEFAULTPRODUCTMAP = new TreeMap<>();

    public Order(int intId, float fltAmount, float fltTotalWeight, float fltAdditionalFee, Date dtOrderDate,
                 String strDescription, String strStatus, boolean blIsHomeDelivery, Client oClient, Pharmacy oPharmacy, Map<Product, Integer> mapProducts) {
        this.mintId = intId;
        this.mfltAmount = fltAmount;
        this.mfltTotalWeight = fltTotalWeight;
        this.mfltAdditionalFee = fltAdditionalFee;
        this.mdtOrderDate = (Date) dtOrderDate.clone();
        this.mstrDescription = strDescription;
        this.mstrStatus = strStatus;
        this.mblIsHomeDelivery = blIsHomeDelivery;
        this.moClient = oClient;
        this.moPharmacy = oPharmacy;
        this.mMapProducts = mapProducts;
    }

    public Order(float fltAmount, float fltTotalWeight, float fltAdditionalFee, Date dtOrderDate, String strDescription,
                 String strStatus, boolean blIsHomeDelivery, Client oClient, Pharmacy oPharmacy, Map<Product, Integer> mapProducts) {
        this.mintId = DEFAULTID;
        this.mfltAmount = fltAmount;
        this.mfltTotalWeight = fltTotalWeight;
        this.mfltAdditionalFee = fltAdditionalFee;
        this.mdtOrderDate = (Date) dtOrderDate.clone();
        this.mstrDescription = strDescription;
        this.mstrStatus = strStatus;
        this.mblIsHomeDelivery = blIsHomeDelivery;
        this.moClient = oClient;
        this.moPharmacy = oPharmacy;
        this.mMapProducts = mapProducts;
    }

    public Order(int intId, String strDescription, boolean blIsHomeDelivery, Client oClient,
                 Pharmacy oPharmacy, Map<Product, Integer> mapProducts) {
        this.mintId = intId;
        this.mdtOrderDate = CURRENTDATE;
        this.mstrDescription = strDescription;
        this.mstrStatus = DEFAULTSTATUS;
        this.mblIsHomeDelivery = blIsHomeDelivery;
        this.moClient = oClient;
        this.moPharmacy = oPharmacy;
        this.mMapProducts = mapProducts;

        calculateAdditonalFee(this.moClient.getCredits());
        calculateAmount();
        calculateTotalWeight();
        this.moClient.addCredits((int)(this.mfltAmount + this.mfltAdditionalFee) / 5);
    }

    public Order(String strDescription, boolean blIsHomeDelivery, Client oClient,
                 Pharmacy oPharmacy, Map<Product, Integer> mapProducts) {
        this.mintId = DEFAULTID;
        this.mfltAdditionalFee = HOMEDELIVERYFEE;
        this.mdtOrderDate = CURRENTDATE;
        this.mstrDescription = strDescription;
        this.mstrStatus = DEFAULTSTATUS;
        this.mblIsHomeDelivery = blIsHomeDelivery;
        this.moClient = oClient;
        this.moPharmacy = oPharmacy;
        this.mMapProducts = mapProducts;

        calculateAdditonalFee(this.moClient.getCredits());
        calculateAmount();
        calculateTotalWeight();
        this.moClient.addCredits((int)(this.mfltAmount + this.mfltAdditionalFee) / 5);
    }

    public Order() {
        this.mintId = DEFAULTID;
        this.mfltAmount = DEFAULTAMOUNT;
        this.mfltTotalWeight = DEFAULTTOTALWEIGHT;
        this.mfltAdditionalFee = DEFAULTADDITIONALFEE;
        this.mdtOrderDate = CURRENTDATE;
        this.mstrDescription = DEFAULTDESCRIPTION;
        this.mstrStatus = DEFAULTSTATUS;
        this.mblIsHomeDelivery = DEFAULTISHOMEDELIVERY;
        this.moClient = DEFAULTCLIENT;
        this.moPharmacy = DEFAULTPHARMACY;
        this.mMapProducts = DEFAULTPRODUCTMAP;
    }

    public int getId() {
        return mintId;
    }

    public void setId(int intId) {
        this.mintId = intId;
    }

    public float getAmount() {
        return mfltAmount;
    }

    public void setAmount(float fltAmount) {
        this.mfltAmount = fltAmount;
    }

    public float getTotalWeight() {
        return mfltTotalWeight;
    }

    public void setTotalWeight(float fltTotalWeight) {
        this.mfltTotalWeight = fltTotalWeight;
    }

    public float getAdditionalFee() {
        return mfltAdditionalFee;
    }

    public void setAdditionalFee(float fltAdditionalFee) {
        this.mfltAdditionalFee = fltAdditionalFee;
    }

    public Date getOrderDate() {
        return (Date) mdtOrderDate.clone();
    }

    public void setOrderDate(Date dtOrderDate) {
        this.mdtOrderDate = (Date) dtOrderDate.clone();
    }

    public String getDescription() {
        return mstrDescription;
    }

    public void setDescription(String strDescription) {
        this.mstrDescription = strDescription;
    }

    public String getStatus() {
        return mstrStatus;
    }

    public void setStatus(String strStatus) {
        this.mstrStatus = strStatus;
    }

    public boolean isHomeDelivery() {
        return mblIsHomeDelivery;
    }

    public void setHomeDelivery(boolean blIsHomeDelivery) {
        this.mblIsHomeDelivery = blIsHomeDelivery;
    }

    public Client getClient() {
        return moClient;
    }

    public void setClient(Client m_oClient) {
        this.moClient = m_oClient;
    }

    public Pharmacy getPharmacy() {
        return moPharmacy;
    }

    public void setPharmacy(Pharmacy oPharmacy) {
        this.moPharmacy = oPharmacy;
    }

    public Map<Product, Integer> getProducts() {
        return mMapProducts;
    }

    public void setProducts(Map<Product, Integer> mapProducts) {
        this.mMapProducts = mapProducts;
        calculateAmount();
        calculateTotalWeight();
    }

    private void calculateAdditonalFee(int intCredits) {
        if(!this.mblIsHomeDelivery || intCredits >= MINOFCREDITSFORFREEDELIVERY){
            this.mfltAdditionalFee = STOREPICKUPFEE;
        } else{
            this.mfltAdditionalFee = HOMEDELIVERYFEE;
        }
    }

    private void calculateAmount() {
        float fltAmount = 0f;
        for(Product p : this.mMapProducts.keySet()){
            fltAmount += p.getUnitaryPrice() * (float) this.mMapProducts.get(p);
        }
        this.mfltAmount = fltAmount;
    }

    private void calculateTotalWeight() {
        float fltTotalWeight = 0f;
        for(Product p : this.mMapProducts.keySet()){
            fltTotalWeight += p.getUnitaryWeight() * (float) this.mMapProducts.get(p);
        }
        this.mfltTotalWeight = fltTotalWeight;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return mintId == order.mintId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mintId);
    }


    @Override
    public String toString() {
        return "Order{" +
                "m_intId=" + mintId +
                ", m_fltAmount=" + mfltAmount +
                ", m_fltTotalWeight=" + mfltTotalWeight +
                ", m_fltAdditionalFee=" + mfltAdditionalFee +
                ", m_dtOrderDate=" + mdtOrderDate +
                ", m_strDescription='" + mstrDescription + '\'' +
                ", m_strStatus='" + mstrStatus + '\'' +
                ", m_blIsHomeDelivery=" + mblIsHomeDelivery +
                ", m_oClient=" + moClient +
                ", m_oPharmacy=" + moPharmacy +
                ", m_mapProducts=" + mMapProducts +
                '}';
    }
}
