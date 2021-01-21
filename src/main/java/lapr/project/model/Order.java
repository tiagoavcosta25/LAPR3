package lapr.project.model;

import java.sql.Date;
import java.util.Calendar;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Order {
    private int mintId;
    private double mdblAmount;
    private double mdblTotalWeight;
    private double mdblAdditionalFee;
    private Date mdtOrderDate;
    private String mstrDescription;
    private String mstrStatus;
    private boolean mblIsHomeDelivery;
    private Client moClient;
    private Pharmacy moPharmacy;
    private Map<Product, Integer> mMapProducts;

    private static int DEFAULTID = -1;
    private static double DEFAULTAMOUNT = -1d;
    private static double DEFAULTTOTALWEIGHT = -1d;
    private static double DEFAULTADDITIONALFEE = -1d;
    private static double HOMEDELIVERYFEE = 5d;
    private static double STOREPICKUPFEE = 0d;
    private static int MINOFCREDITSFORFREEDELIVERY = 10;
    private static Date CURRENTDATE = new Date(Calendar.getInstance().getTimeInMillis());
    private static String DEFAULTDESCRIPTION = "No Description.";
    private static String DEFAULTSTATUS = "ordered";
    private static boolean DEFAULTISHOMEDELIVERY = false;
    private static Client DEFAULTCLIENT = new Client();
    private static Pharmacy DEFAULTPHARMACY = new Pharmacy();
    private static Map<Product, Integer> DEFAULTPRODUCTMAP = new TreeMap<>();

    public Order(int intId, double dblAmount, double dblTotalWeight, double dblAdditionalFee, Date dtOrderDate,
                 String strDescription, String strStatus, boolean blIsHomeDelivery, Client oClient, Pharmacy oPharmacy, Map<Product, Integer> mapProducts) {
        this.mintId = intId;
        this.mdblAmount = dblAmount;
        this.mdblTotalWeight = dblTotalWeight;
        this.mdblAdditionalFee = dblAdditionalFee;
        this.mdtOrderDate = (Date) dtOrderDate.clone();
        this.mstrDescription = strDescription;
        this.mstrStatus = strStatus;
        this.mblIsHomeDelivery = blIsHomeDelivery;
        this.moClient = oClient;
        this.moPharmacy = oPharmacy;
        this.mMapProducts = mapProducts;
    }

    public Order(double dblAmount, double dblTotalWeight, double dblAdditionalFee, Date dtOrderDate, String strDescription,
                 String strStatus, boolean blIsHomeDelivery, Client oClient, Pharmacy oPharmacy, Map<Product, Integer> mapProducts) {
        this.mintId = DEFAULTID;
        this.mdblAmount = dblAmount;
        this.mdblTotalWeight = dblTotalWeight;
        this.mdblAdditionalFee = dblAdditionalFee;
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
        this.moClient.addCredits((int)(this.mdblAmount + this.mdblAdditionalFee) / 5);
    }

    public Order(String strDescription, boolean blIsHomeDelivery, Client oClient,
                 Pharmacy oPharmacy, Map<Product, Integer> mapProducts) {
        this.mintId = DEFAULTID;
        this.mdblAdditionalFee = HOMEDELIVERYFEE;
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
        this.moClient.addCredits((int)(this.mdblAmount + this.mdblAdditionalFee) / 5);
    }

    public Order() {
        this.mintId = DEFAULTID;
        this.mdblAmount = DEFAULTAMOUNT;
        this.mdblTotalWeight = DEFAULTTOTALWEIGHT;
        this.mdblAdditionalFee = DEFAULTADDITIONALFEE;
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

    public double getAmount() {
        return mdblAmount;
    }

    public void setAmount(double dblAmount) {
        this.mdblAmount = dblAmount;
    }

    public double getTotalWeight() {
        return mdblTotalWeight;
    }

    public void setTotalWeight(double dblTotalWeight) {
        this.mdblTotalWeight = dblTotalWeight;
    }

    public double getAdditionalFee() {
        return mdblAdditionalFee;
    }

    public void setAdditionalFee(double dblAdditionalFee) {
        this.mdblAdditionalFee = dblAdditionalFee;
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
            this.mdblAdditionalFee = STOREPICKUPFEE;
        } else{
            this.mdblAdditionalFee = HOMEDELIVERYFEE;
        }
    }

    private void calculateAmount() {
        double dblAmount = 0d;
        for(Product p : this.mMapProducts.keySet()){
            dblAmount += p.getUnitaryPrice() * (double) this.mMapProducts.get(p);
        }
        this.mdblAmount = dblAmount;
    }

    private void calculateTotalWeight() {
        double dblTotalWeight = 0f;
        for(Product p : this.mMapProducts.keySet()){
            dblTotalWeight += p.getUnitaryWeight() * (double) this.mMapProducts.get(p);
        }
        this.mdblTotalWeight = dblTotalWeight;
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
                ", m_dblAmount=" + mdblAmount +
                ", m_dblTotalWeight=" + mdblTotalWeight +
                ", m_dblAdditionalFee=" + mdblAdditionalFee +
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
