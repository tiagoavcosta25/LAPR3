package lapr.project.model;

import java.sql.Date;
import java.util.Map;
import java.util.TreeMap;

public class Invoice {
    private int m_intId;
    private Date m_dtInvoiceDate;
    private Float m_fltTotalPrice;
    private Client m_oClient;
    private Address m_oAddress;
    private Map<Product, Integer> m_mapProducts;

    private static int DEFAULT_ID = -1;
    private static float DEFAULT_AMOUNT = -1;
    private static float DEFAULT_TOTAL_WEIGHT = -1;
    private static float DEFAULT_ADDITIONAL_FEE = -1;
    private static Date DEFAULT_DATE = null;
    private static String DEFAULT_DESCRIPTION = "No Description.";
    private static String DEFAULT_STATUS = "No Status.";
    private static Client DEFAULT_CLIENT = new Client();
    private static Address DEFAULT_ADDRESS = new Address();
    private static Map<Product, Integer> DEFAULT_PRODUCT_MAP = new TreeMap<>();

    public Invoice(int intId, float fltAmount, float fltTotalWeight, float fltAdditionalFee, Date dtOrderDate,
                 String strDescription, String strStatus, Client oClient, Address oAddress, Map<Product, Integer> mapProducts) {
        this.m_intId = intId;
        this.m_fltAmount = fltAmount;
        this.m_fltTotalWeight = fltTotalWeight;
        this.m_fltAdditionalFee = fltAdditionalFee;
        this.m_dtOrderDate = dtOrderDate;
        this.m_strDescription = strDescription;
        this.m_strStatus = strStatus;
        this.m_oClient = oClient;
        this.m_oAddress = oAddress;
        this.m_mapProducts = mapProducts;
    }

    public Invoice(float fltAmount, float fltTotalWeight, float fltAdditionalFee, Date dtOrderDate,
                 String strDescription, String strStatus, Client oClient, Address oAddress, Map<Product, Integer> mapProducts) {
        this.m_intId = DEFAULT_ID;
        this.m_fltAmount = fltAmount;
        this.m_fltTotalWeight = fltTotalWeight;
        this.m_fltAdditionalFee = fltAdditionalFee;
        this.m_dtOrderDate = dtOrderDate;
        this.m_strDescription = strDescription;
        this.m_strStatus = strStatus;
        this.m_oClient = oClient;
        this.m_oAddress = oAddress;
        this.m_mapProducts = mapProducts;
    }

    public Invoice() {
        this.m_intId = DEFAULT_ID;
        this.m_fltAmount = DEFAULT_AMOUNT;
        this.m_fltTotalWeight = DEFAULT_TOTAL_WEIGHT;
        this.m_fltAdditionalFee = DEFAULT_ADDITIONAL_FEE;
        this.m_dtOrderDate = DEFAULT_DATE;
        this.m_strDescription = DEFAULT_DESCRIPTION;
        this.m_strStatus = DEFAULT_STATUS;
        this.m_oClient = DEFAULT_CLIENT;
        this.m_oAddress = DEFAULT_ADDRESS;
        this.m_mapProducts = DEFAULT_PRODUCT_MAP;
    }
}
