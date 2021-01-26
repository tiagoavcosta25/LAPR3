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

    private static int mDEFAULTID = -1;
    private static double mDEFAULTAMOUNT = -1d;
    private static double mDEFAULTTOTALWEIGHT = -1d;
    private static double mDEFAULTADDITIONALFEE = -1d;
    private static double mHOMEDELIVERYFEE = 5d;
    private static double mSTOREPICKUPFEE = 0d;
    private static int mMINOFCREDITSFORFREEDELIVERY = 10;
    private static Date mCURRENTDATE = new Date(Calendar.getInstance().getTimeInMillis());
    private static String mDEFAULTDESCRIPTION = "No Description.";
    private static String mDEFAULTSTATUS = "ordered";
    private static boolean mDEFAULTISHOMEDELIVERY = false;
    private static Client mDEFAULTCLIENT = new Client();
    private static Pharmacy mDEFAULTPHARMACY = new Pharmacy();
    private static Map<Product, Integer> mDEFAULTPRODUCTMAP = new TreeMap<>();

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

    public Order(int intId, String strDescription, boolean blIsHomeDelivery, Client oClient,
                 Pharmacy oPharmacy, Map<Product, Integer> mapProducts) {
        this.mintId = intId;
        this.mdtOrderDate = mCURRENTDATE;
        this.mstrDescription = strDescription;
        this.mstrStatus = mDEFAULTSTATUS;
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
        this(mDEFAULTID, strDescription, blIsHomeDelivery, oClient, oPharmacy, mapProducts);
    }

    public Order() {
        this.mintId = mDEFAULTID;
        this.mdblAmount = mDEFAULTAMOUNT;
        this.mdblTotalWeight = mDEFAULTTOTALWEIGHT;
        this.mdblAdditionalFee = mDEFAULTADDITIONALFEE;
        this.mdtOrderDate = mCURRENTDATE;
        this.mstrDescription = mDEFAULTDESCRIPTION;
        this.mstrStatus = mDEFAULTSTATUS;
        this.mblIsHomeDelivery = mDEFAULTISHOMEDELIVERY;
        this.moClient = mDEFAULTCLIENT;
        this.moPharmacy = mDEFAULTPHARMACY;
        this.mMapProducts = mDEFAULTPRODUCTMAP;
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

    public void setClient(Client moClient) {
        this.moClient = moClient;
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
        if(!this.mblIsHomeDelivery || intCredits >= mMINOFCREDITSFORFREEDELIVERY){
            this.mdblAdditionalFee = mSTOREPICKUPFEE;
        } else{
            this.mdblAdditionalFee = mHOMEDELIVERYFEE;
        }
    }

    private void calculateAmount() {
        double dblAmount = 0d;
        for(Map.Entry<Product, Integer> e : this.mMapProducts.entrySet()){
            dblAmount += e.getKey().getUnitaryPrice() * (double) e.getValue();
        }
        this.mdblAmount = dblAmount;
    }

    private void calculateTotalWeight() {
        double dblTotalWeight = 0f;
        for(Map.Entry<Product, Integer> e : this.mMapProducts.entrySet()){
            dblTotalWeight += e.getKey().getUnitaryWeight() * (double) e.getValue();
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
