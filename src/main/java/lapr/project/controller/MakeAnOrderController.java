package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.data.registration.ClientRegistration;
import lapr.project.data.registration.OrderRegistration;
import lapr.project.data.registration.PharmacyRegistration;
import lapr.project.data.registration.ProductRegistration;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MakeAnOrderController {
    /**
     * Platform class instance
     */
    private Platform m_oPlatform;
    /**
     * Order class instance
     */
    private Order m_oOrder;

    /**
     * Order Management class
     */
    private OrderRegistration m_oOrderRegistration;

    /**
     * Order Management class
     */
    private ClientRegistration m_oClientRegistration;

    /**
     * Order's Client
     */
    private Client m_oClient;

    /**
     * Pharmacy Management class
     */
    private ProductRegistration m_oProductRegistration;
    /**
     * Order's Pharmacy
     */
    private Product m_oProduct;

    /**
     * Pharmacy Management class
     */
    private PharmacyRegistration m_oPharmacyRegistration;

    /**
     * Order's Pharmacy
     */
    private Pharmacy m_oPharmacy;

    /**
     * Order's Product Map
     */
    private Map<Product, Integer> m_mapProducts = new TreeMap<>();


    /**
     * An empty constructor of MakeAnOrderController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public MakeAnOrderController() {
        this.m_oPlatform = ApplicationPOT.getInstance().getPlatform();
        this.m_oPharmacyRegistration = m_oPlatform.getPharmacyReg();
        this.m_oProductRegistration = m_oPlatform.getProductReg();
        this.m_oOrderRegistration = m_oPlatform.getOrderReg();
        this.m_oClientRegistration = m_oPlatform.getClientReg();
    }

    /**
     * The method creates a new order for a delivery to an address.
     */
    public Order newOrder(String strDescription, Double latitude, Double longitude, String streetName,
                         String doorNumber, String postalCode, String locality, String country) {
        try {
            this.m_oClient = m_oClientRegistration.getClientByEmail(ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail());
            this.m_oOrder = m_oOrderRegistration.newOrder(strDescription, m_oClient, latitude, longitude,
                    streetName, doorNumber, postalCode, locality, country, m_oPharmacy, this.m_mapProducts);
            return this.m_oOrder;
        } catch (RuntimeException ex) {
            this.m_oOrder = null;
            return null;
        }
    }

    /**
     * The method creates a new order for a delivery to the client's address or a .
     */
    public Order newOrder(String strDescription, Boolean blIsHomeDelivery) {
        try {
            this.m_oClient = m_oClientRegistration.getClientByEmail(ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail());
            if(blIsHomeDelivery){
                Address oAddress = m_oClient.getAddress();
                this.m_oOrder = m_oOrderRegistration.newOrder(strDescription, m_oClient, oAddress.getLatitude(),
                        oAddress.getLongitude(), oAddress.getStreetName(), oAddress.getDoorNumber(), oAddress.getPostalCode(),
                        oAddress.getLocality(), oAddress.getCountry(), m_oPharmacy, this.m_mapProducts);
            } else{
                this.m_oOrder = m_oOrderRegistration.newOrder(strDescription, m_oClient, m_oPharmacy, this.m_mapProducts);
            }
            return this.m_oOrder;
        } catch (RuntimeException ex) {
            this.m_oOrder = null;
            return null;
        }
    }

    /**
     * The method registers an order to the database.
     */
    public boolean registerOrder() {
        return this.m_oOrderRegistration.registerOrder(m_oOrder);
    }

    /**
     * The method returns the list of available products for a pharmacy.
     */
    public List<Pharmacy> getPharmacies() {
        return this.m_oPharmacyRegistration.getPharmacies();
    }

    /**
     * The method returns the list of pharmacies.
     */
    public List<Product> getAvailableProducts(Pharmacy oPharmacy) {
        try {
            this.m_oPharmacy = oPharmacy;
            return this.m_oProductRegistration.getAvailableProducts(oPharmacy.getId());
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * The method adds a product to the map and its quantity.
     */
    public boolean addProductToOrder(Product oProduct, Integer intQuantity) {
        try{
            m_mapProducts.put(oProduct, intQuantity);
            return true;
        } catch(Exception e){
            return false;
        }
    }

    /**
     * The method sets the order.
     */
    public void setOrder(Order oOrder) {
        this.m_oOrder = oOrder;
    }

}
