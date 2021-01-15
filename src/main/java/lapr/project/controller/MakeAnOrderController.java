package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.service.ClientService;
import lapr.project.model.service.OrderService;
import lapr.project.model.service.PharmacyService;
import lapr.project.model.service.ProductService;

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
     * Client Management class
     */
    private ClientService m_oClientService;

    /**
     * Order's Client
     */
    private Client m_oClient;

    /**
     * Pharmacy Management class
     */
    private ProductService m_oProductService;

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
    private Map<Product, Integer> m_mapProducts;

    /**
     * Payments
     */
    private Map<CreditCard, Float> m_mapPayments;

    /**
     * Payments
     */
    private GenerateInvoiceController m_oGenerateInvoiceController;

    /**
     * Payments
     */
    private float m_fltCurrentPayment;

    /**
     * An empty constructor of MakeAnOrderController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public MakeAnOrderController() {
        this.m_oPharmacyService = new PharmacyService();
        this.m_oProductService = new ProductService();
        this.m_oOrderService = new OrderService();
        this.m_oClientService = new ClientService();
        this.m_mapProducts = new TreeMap<>();
        this.m_mapPayments = new TreeMap<>();
        this.m_oGenerateInvoiceController = new GenerateInvoiceController();
        this.m_fltCurrentPayment = 0f;
    }

    /**
     * The method creates a new order for a delivery to the client's address or a .
     */
    public Order newOrder(String strDescription, Boolean blIsHomeDelivery) {
        try {
            this.m_oOrder = m_oOrderService.newOrder(strDescription, blIsHomeDelivery, m_oClient, m_oPharmacy, this.m_mapProducts);
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

    /**
     * The method returns the list of available products for a pharmacy.
     */
    public List<Pharmacy> getPharmacies() {
        this.m_oClient = this.m_oClientService.getClientByEmail(ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail());
        return this.m_oPharmacyService.getPharmacies();
    }

    /**
     * The method returns the list of pharmacies.
     */
    public List<Product> getAvailableProducts(Pharmacy oPharmacy) {
        try {
            this.m_oPharmacy = oPharmacy;
            return this.m_oProductService.getAvailableProducts(oPharmacy.getId());
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * The method returns the list of the user's credit cards.
     */
    public List<CreditCard> getCreditCardsByClient(String strEmail) {
        try {
            return this.m_oClientService.getCreditCardsByClient(strEmail);
        } catch (Exception ex) {
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
     * The method adds a payment method to the map and its value.
     */
    public boolean addPayment(CreditCard oCreditCard, Float fltValue) {
        try{
            if((this.m_fltCurrentPayment + fltValue) <= this.m_oOrder.getAmount()){
                m_mapPayments.put(oCreditCard, fltValue);
                return true;
            } else{
                return false;
            }
        } catch(Exception e){
            return false;
        }
    }

    /**
     * The method generates the invoice and sends it by email to the client.
     */
    public boolean generateInvoice() {
        try{
            return m_oGenerateInvoiceController.generateInvoice(this.m_oOrder, this.m_mapPayments);
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
