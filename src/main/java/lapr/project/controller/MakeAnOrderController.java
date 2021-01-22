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
    private Order moOrder;

    /**
     * Order Management class
     */
    private OrderService moOrderService;

    /**
     * Client Management class
     */
    private ClientService moClientService;

    /**
     * Order's Client
     */
    private Client moClient;

    /**
     * Pharmacy Management class
     */
    private ProductService moProductService;

    /**
     * Pharmacy Management class
     */
    private PharmacyService moPharmacyService;

    /**
     * Order's Pharmacy
     */
    private Pharmacy moPharmacy;

    /**
     * Order's Product Map
     */
    private Map<Product, Integer> mMapProducts;

    /**
     * Payments
     */
    private Map<CreditCard, Double> mMapPayments;

    /**
     * Payments
     */
    private GenerateInvoiceController moGenerateInvoiceController;

    /**
     * Payments
     */
    private float mfltCurrentPayment;

    /**
     * Payments
     */
    private float mfltExpectedPayment;

    /**
     * An empty constructor of MakeAnOrderController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public MakeAnOrderController() {
        this.moPharmacyService = new PharmacyService();
        this.moProductService = new ProductService();
        this.moOrderService = new OrderService();
        this.moClientService = new ClientService();
        this.mMapProducts = new TreeMap<>();
        this.mMapPayments = new TreeMap<>();
        this.moGenerateInvoiceController = new GenerateInvoiceController();
        this.mfltCurrentPayment = 0f;
        this.mfltExpectedPayment = 0f;
    }

    /**
     * The method creates a new order for a delivery to the client's address or a .
     */
    public Order newOrder(String strDescription, Boolean blIsHomeDelivery) {
        try {
            this.moOrder = moOrderService.newOrder(strDescription, blIsHomeDelivery, moClient, moPharmacy, this.mMapProducts);
            return this.moOrder;
        } catch (Exception ex) {
            this.moOrder = null;
            return null;
        }
    }

    /**
     * The method registers an order to the database.
     */
    public boolean registerOrder() {
        try{
            int intId = this.moOrderService.registerOrder(moOrder);
            this.moOrder.setId(intId);
            return this.generateInvoice();
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * The method returns the list of pharmacies.
     */
    public List<Pharmacy> getPharmacies() {
        this.moClient = this.moClientService.getClientByEmail(ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail());
        return this.moPharmacyService.getPharmacies();
    }

    /**
     * The method returns the list of available products for a pharmacy.
     */
    public List<Product> getAvailableProducts(Pharmacy oPharmacy) {
        try {
            this.moPharmacy = oPharmacy;
            return this.moProductService.getAvailableProducts(this.moPharmacy.getId());
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * The method returns the list of available products for a pharmacy.
     */
    public List<Product> getAvailableProducts() {
        try {
            this.moPharmacy = this.moPharmacyService.getClosestPharmacyToClient(this.moClient);
            return this.moProductService.getAvailableProducts(this.moPharmacy.getId());
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * The method returns the list of the user's credit cards.
     */
    public List<CreditCard> getCreditCardsByClient() {
        try {
            return this.moClientService.getCreditCardsByClient(this.moClient.getEmail());
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * The method adds a product to the map and its quantity.
     */
    public boolean addProductToOrder(Product oProduct, Integer intQuantity) {
        try{
            this.mMapProducts.put(oProduct, intQuantity);
            mfltExpectedPayment += oProduct.getUnitaryPrice() * (float) intQuantity;
            return true;
        } catch(Exception e){
            return false;
        }
    }

    /**
     * The method adds a payment method to the map and its value.
     */
    public boolean addPayment(CreditCard oCreditCard, Double dblValue) {
        try{
            if((this.mfltCurrentPayment + dblValue) <= this.mfltExpectedPayment){
                this.mMapPayments.put(oCreditCard, dblValue);
                this.mfltCurrentPayment += dblValue;
                return true;
            } else{
                throw new Exception();
            }
        } catch(Exception e){
            return false;
        }
    }

    /**
     * The method adds a payment method to the map and its value.
     */
    /*public boolean payWithCredits(int intCredits) {
        try{
            if(intCredits > this.m_oClient.getCredits()){
                throw new Exception();
            }
            this.m_fltCurrentPayment -= (double) this.m_oClient.getCredits() / 5f;
            this.m_oClientService.deductCredits(this.m_oClient, this.m_oClient.getCredits());
            return true;
        } catch(Exception e){
            return false;
        }
    }*/

    /**
     * The method generates the invoice and sends it by email to the client.
     */
    public boolean generateInvoice() {
        try{
            return moGenerateInvoiceController.generateInvoice(this.moOrder, this.mMapPayments);
        } catch(Exception e){
            return false;
        }
    }

    /**
     * The method sets the order.
     */
    public void setOrder(Order oOrder) {
        this.moOrder = oOrder;
    }

    /**
     * The method sets the client.
     */
    public void setClient(Client oClient) {
        this.moClient = oClient;
    }

    public boolean setPharmacy(String strPharmacyEmail){
        try {
            this.moPharmacy = this.moPharmacyService.getPharmacy(strPharmacyEmail);
            return true;
        } catch (Exception ex){
            return false;
        }
    }

}
