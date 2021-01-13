package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.data.ClientDB;
import lapr.project.data.ProductDB;
import lapr.project.model.service.OrderService;
import lapr.project.model.service.PharmacyService;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MakeAnOrderController {
    /**
     * Order class instance
     */
    private Order m_oOrder;

    /**
     * Order Management class
     */
    private OrderService m_oOrderService;

    /**
     * Order Management class
     */
    private ClientDB m_oClientDB;

    /**
     * Order's Client
     */
    private Client m_oClient;

    /**
     * Pharmacy Management class
     */
    private ProductDB m_oProductDB;

    /**
     * Pharmacy Management class
     */
    private PharmacyService m_oPharmacyService;

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
        this.m_oPharmacyService = new PharmacyService();
        this.m_oProductDB = new ProductDB();
        this.m_oOrderService = new OrderService();
        this.m_oClientDB = new ClientDB();
    }

    /**
     * The method creates a new order for a delivery to an address.
     */
    public Order newOrder(String strDescription, Double latitude, Double longitude, String streetName,
                         String doorNumber, String postalCode, String locality, String country) {
        try {
            this.m_oClient = m_oClientDB.getClientByEmail(ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail());
            this.m_oOrder = m_oOrderService.newOrder(strDescription, m_oClient, latitude, longitude,
                    streetName, doorNumber, postalCode, locality, country, m_oPharmacy, this.m_mapProducts);
            return this.m_oOrder;
        } catch (Exception ex) {
            this.m_oOrder = null;
            return null;
        }
    }

    /**
     * The method creates a new order for a delivery to the client's address or a .
     */
    public Order newOrder(String strDescription, Boolean blIsHomeDelivery) {
        try {
            this.m_oClient = m_oClientDB.getClientByEmail(ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail());
            if(blIsHomeDelivery){
                Address oAddress = m_oClient.getAddress();
                this.m_oOrder = m_oOrderService.newOrder(strDescription, m_oClient, oAddress.getLatitude(),
                        oAddress.getLongitude(), oAddress.getStreetName(), oAddress.getDoorNumber(), oAddress.getPostalCode(),
                        oAddress.getLocality(), oAddress.getCountry(), m_oPharmacy, this.m_mapProducts);
            } else{
                this.m_oOrder = m_oOrderService.newOrder(strDescription, m_oClient, m_oPharmacy, this.m_mapProducts);
            }
            return this.m_oOrder;
        } catch (Exception ex) {
            this.m_oOrder = null;
            return null;
        }
    }

    /**
     * The method registers an order to the database.
     */
    public boolean registerOrder() {
        return this.m_oOrderService.registerOrder(m_oOrder);
    }

    public boolean registerOrder2(Order order) {
        return this.m_oOrderDB.registerOrder(order);
    }

    /**
     * The method returns the list of available products for a pharmacy.
     */
    public List<Pharmacy> getPharmacies() {
        return this.m_oPharmacyService.getPharmacies();
    }

    /**
     * The method returns the list of pharmacies.
     */
    public List<Product> getAvailableProducts(Pharmacy oPharmacy) {
        try {
            this.m_oPharmacy = oPharmacy;
            return this.m_oProductDB.getAvailableProducts(oPharmacy.getId());
        } catch (Exception ex) {
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
