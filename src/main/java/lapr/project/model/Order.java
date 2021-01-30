package lapr.project.model;

import java.sql.Date;
import java.util.Calendar;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * Order.
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
public class Order {
    /**
     * Id.
     */
    private int mintId;

    /**
     * Amount to Pay.
     */
    private double mdblAmount;

    /**
     * Total Weight of the Order.
     */
    private double mdblTotalWeight;

    /**
     * Additional Fees (Including Home Delivery Fee)
     */
    private double mdblAdditionalFee;

    /**
     * Date.
     */
    private Date mdtOrderDate;

    /**
     * Descritpion.
     */
    private String mstrDescription;

    /**
     * Order Status.
     */
    private String mstrStatus;

    /**
     * If Its a Home Delivery or a Store Pickup.
     */
    private boolean mblIsHomeDelivery;

    /**
     * Client.
     */
    private Client moClient;

    /**
     * Pharmacy.
     */
    private Pharmacy moPharmacy;

    /**
     * Map Product.
     */
    private Map<Product, Integer> mMapProducts;

    /**
     * Default Id.
     */
    private static int mDEFAULTID = -1;

    /**
     * Default Amount.
     */
    private static double mDEFAULTAMOUNT = -1d;

    /**
     * Default Total Weight.
     */
    private static double mDEFAULTTOTALWEIGHT = -1d;

    /**
     * Default Additonal Fee.
     */
    private static double mDEFAULTADDITIONALFEE = -1d;

    /**
     * Default Home Delivery Fee.
     */
    private static double mHOMEDELIVERYFEE = 5d;

    /**
     * Default Store Pick Up Fee.
     */
    private static double mSTOREPICKUPFEE = 0d;

    /**
     * Default Min Credits For a Free Delivery.
     */
    private static int mMINOFCREDITSFORFREEDELIVERY = 10;

    /**
     * Current Date.
     */
    private static Date mCURRENTDATE = new Date(Calendar.getInstance().getTimeInMillis());

    /**
     * Default Descrpition.
     */
    private static String mDEFAULTDESCRIPTION = "No Description.";

    /**
     * Default Status.
     */
    private static String mDEFAULTSTATUS = "ordered";

    /**
     * Default Home Delivery.
     */
    private static boolean mDEFAULTISHOMEDELIVERY = false;

    /**
     * Default Client.
     */
    private static Client mDEFAULTCLIENT = new Client();

    /**
     * Default Pharmacy.
     */
    private static Pharmacy mDEFAULTPHARMACY = new Pharmacy();

    /**
     * Default Product Map.
     */
    private static Map<Product, Integer> mDEFAULTPRODUCTMAP = new TreeMap<>();


    /**
     * Order Construtor.
     * @param intId id.
     * @param dblAmount amount.
     * @param dblTotalWeight total weight.
     * @param dblAdditionalFee additional fee.
     * @param dtOrderDate date.
     * @param strDescription description.
     * @param strStatus status.
     * @param blIsHomeDelivery home delivery boolean.
     * @param oClient client.
     * @param oPharmacy pharmacy.
     * @param mapProducts map of order's products.
     */
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

    /**
     * Order Construtor.
     * @param intId id.
     * @param strDescription descprition.
     * @param blIsHomeDelivery home delivery boolean.
     * @param oClient client.
     * @param oPharmacy pharmacy.
     * @param mapProducts map of order's products.
     */
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

    /**
     * Order Construtor.
     * @param strDescription description.
     * @param blIsHomeDelivery home delivery boolean.
     * @param oClient client.
     * @param oPharmacy pharmacy.
     * @param mapProducts map of order's products.
     */
    public Order(String strDescription, boolean blIsHomeDelivery, Client oClient,
                 Pharmacy oPharmacy, Map<Product, Integer> mapProducts) {
        this(mDEFAULTID, strDescription, blIsHomeDelivery, oClient, oPharmacy, mapProducts);
    }

    /**
     * Empty Order Construtor.
     */
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

    /**
     * Getter for the id.
     * @return id.
     */
    public int getId() {
        return mintId;
    }

    /**
     * Setter for the id.
     * @param intId id.
     */
    public void setId(int intId) {
        this.mintId = intId;
    }

    /**
     * Getter for the amount.
     * @return amount.
     */
    public double getAmount() {
        return mdblAmount;
    }

    /**
     * Setter for the amount
     * @param dblAmount amount.
     */
    public void setAmount(double dblAmount) {
        this.mdblAmount = dblAmount;
    }

    /**
     * Getter for the total weight.
     * @return total weight.
     */
    public double getTotalWeight() {
        return mdblTotalWeight;
    }

    /**
     * Setter for the total weight.
     * @param dblTotalWeight total weight.
     */
    public void setTotalWeight(double dblTotalWeight) {
        this.mdblTotalWeight = dblTotalWeight;
    }

    /**
     * Getter for the additiona fee.
     * @return additiona fee.
     */
    public double getAdditionalFee() {
        return mdblAdditionalFee;
    }

    /**
     * Setter for the additiona fee.
     * @param dblAdditionalFee additiona fee.
     */
    public void setAdditionalFee(double dblAdditionalFee) {
        this.mdblAdditionalFee = dblAdditionalFee;
    }

    /**
     * Getter for the date.
     * @return date.
     */
    public Date getOrderDate() {
        return (Date) mdtOrderDate.clone();
    }

    /**
     * Setter for the date.
     * @param dtOrderDate date.
     */
    public void setOrderDate(Date dtOrderDate) {
        this.mdtOrderDate = (Date) dtOrderDate.clone();
    }

    /**
     * Getter for the description.
     * @return description.
     */
    public String getDescription() {
        return mstrDescription;
    }

    /**
     * Setter for the description.
     * @param strDescription description.
     */
    public void setDescription(String strDescription) {
        this.mstrDescription = strDescription;
    }

    /**
     * Getter for the status.
     * @return status.
     */
    public String getStatus() {
        return mstrStatus;
    }

    /**
     * Setter for the status.
     * @param strStatus status.
     */
    public void setStatus(String strStatus) {
        this.mstrStatus = strStatus;
    }

    /**
     * Getter for the home delivery boolean.
     * @return home delivery boolean.
     */
    public boolean isHomeDelivery() {
        return mblIsHomeDelivery;
    }

    /**
     * Setter for the home delivery boolean.
     * @param blIsHomeDelivery home delivery boolean.
     */
    public void setHomeDelivery(boolean blIsHomeDelivery) {
        this.mblIsHomeDelivery = blIsHomeDelivery;
    }

    /**
     * Getter for the client.
     * @return client.
     */
    public Client getClient() {
        return moClient;
    }

    /**
     * Setter for the client.
     * @param moClient client.
     */
    public void setClient(Client moClient) {
        this.moClient = moClient;
    }

    /**
     * Getter for the pharmacy.
     * @return pharmacy.
     */
    public Pharmacy getPharmacy() {
        return moPharmacy;
    }

    /**
     * Setter for the pharmacy.
     * @param oPharmacy pharmacy.
     */
    public void setPharmacy(Pharmacy oPharmacy) {
        this.moPharmacy = oPharmacy;
    }

    /**
     * Getter for the products.
     * @return products' list.
     */
    public Map<Product, Integer> getProducts() {
        return mMapProducts;
    }

    /**
     * Setter for the products.
     * @param mapProducts products' list.
     */
    public void setProducts(Map<Product, Integer> mapProducts) {
        this.mMapProducts = mapProducts;
        calculateAmount();
        calculateTotalWeight();
    }

    /**
     * Method that calculates the additional fee based on delivery type and client's credits.
     * @param intCredits Client's Credits.
     */
    private void calculateAdditonalFee(int intCredits) {
        if(!this.mblIsHomeDelivery || intCredits >= mMINOFCREDITSFORFREEDELIVERY){
            this.mdblAdditionalFee = mSTOREPICKUPFEE;
        } else{
            this.mdblAdditionalFee = mHOMEDELIVERYFEE;
        }
    }

    /**
     * Method that calculates the total amount to pay.
     */
    private void calculateAmount() {
        double dblAmount = 0d;
        for(Map.Entry<Product, Integer> e : this.mMapProducts.entrySet()){
            dblAmount += e.getKey().getUnitaryPrice() * (double) e.getValue();
        }
        this.mdblAmount = dblAmount;
    }

    /**
     * Method that calculates the total weight to carry.
     */
    private void calculateTotalWeight() {
        double dblTotalWeight = 0f;
        for(Map.Entry<Product, Integer> e : this.mMapProducts.entrySet()){
            dblTotalWeight += e.getKey().getUnitaryWeight() * (double) e.getValue();
        }
        this.mdblTotalWeight = dblTotalWeight;
    }

    /**
     * Equals Override.
     * @param o object.
     * @return true if they're the same, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return mintId == order.mintId;
    }

    /**
     * Hash Code.
     * @return hash code value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(mintId);
    }

    /**
     * Order to String Override.
     * @return
     */
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
