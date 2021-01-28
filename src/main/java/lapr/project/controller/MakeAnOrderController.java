package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.service.ClientService;
import lapr.project.model.service.OrderService;
import lapr.project.model.service.PharmacyService;
import lapr.project.model.service.ProductService;

import java.util.*;

/**
 * Make An Order Controller.
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
public class MakeAnOrderController {
    /**
     * Order class instance.
     */
    private Order moOrder;

    /**
     * Order Management class.
     */
    private OrderService moOrderService;

    /**
     * Client Management class.
     */
    private ClientService moClientService;

    /**
     * Order's Client.
     */
    private Client moClient;

    /**
     * Product Management class.
     */
    private ProductService moProductService;

    /**
     * Pharmacy Management class.
     */
    private PharmacyService moPharmacyService;

    /**
     * Order's Pharmacy.
     */
    private Pharmacy moPharmacy;

    /**
     * Order's Product Map.
     */
    private Map<Product, Integer> mMapProducts;

    /**
     * Payments.
     */
    private Map<CreditCard, Double> mMapPayments;

    /**
     * Controller to Generate Invoice.
     */
    private GenerateInvoiceController moGenerateInvoiceController;

    /**
     * Controller to Notify And Remove A Product With No Stock.
     */
    private NotifyAndRemoveController moNotifyAndRemoveController;

    /**
     * Current Value Payed.
     */
    private double mdblCurrentPayment;

    /**
     * Expected Total Payment.
     */
    private double mdblExpectedPayment;

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
        this.moNotifyAndRemoveController = new NotifyAndRemoveController();
        this.mdblCurrentPayment = 0f;
        this.mdblExpectedPayment = 0f;
    }

    /**
     * The method creates a new order.
     * @param strDescription Order's Description.
     * @param blIsHomeDelivery If its a Home Delivery or Store Pickup.
     * @return The Order.
     */
    public Order newOrder(String strDescription, Boolean blIsHomeDelivery) {
        try {
            this.moOrder = moOrderService.newOrder(strDescription, blIsHomeDelivery, moClient, moPharmacy, this.mMapProducts);
            this.mdblExpectedPayment = this.moOrder.getAmount() + this.moOrder.getAdditionalFee();
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
            this.moNotifyAndRemoveController.notifyAndRemove(this.moOrder);
            return this.generateInvoice();
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * The method returns the list of pharmacies.
     * @return The list of pharmacies.
     */
    public List<Pharmacy> getPharmacies() {
        this.moClient = this.moClientService.getClientByEmail(ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail());
        return this.moPharmacyService.getPharmacies();
    }

    /**
     * The method returns the list of available products for a pharmacy.
     * @param oPharmacy Pharamcy In Question.
     * @return The List of Products.
     */
    public List<Product> getAvailableProducts(Pharmacy oPharmacy) {
        try {
            this.moPharmacy = oPharmacy;
            return this.moProductService.getAvailableProducts(this.moPharmacy.getId());
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }

    /**
     * Method that finds out the closest pharmacy to the client and gives him the list of products of that pharamcy.
     * @return The List of Products.
     */
    public List<Product> getAvailableProducts() {
        try {
            this.moClient = this.moClientService.getClientByEmail(ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail());
            this.moPharmacy = this.moPharmacyService.getClosestPharmacyToClient(this.moClient);
            return this.moProductService.getAvailableProducts(this.moPharmacy.getId());
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }

    /**
     * The method returns the list of the user's credit cards.
     * @return The list of the user's credit cards.
     */
    public List<CreditCard> getCreditCardsByClient() {
        try {
            return this.moClientService.getCreditCardsByClient(this.moClient.getEmail());
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }

    /**
     * The method adds a product to the map and its quantity.
     * @param oProduct Product.
     * @param intQuantity Quantity.
     * @return true if everything works out, false if it doesn't.
     */
    public boolean addProductToOrder(Product oProduct, Integer intQuantity) {
        try{
            this.mMapProducts.put(oProduct, intQuantity);
            mdblExpectedPayment += oProduct.getUnitaryPrice() * (float) intQuantity;
            return true;
        } catch(Exception e){
            return false;
        }
    }

    /**
     * The method adds a payment method to the map and its value.
     * @param oCreditCard CreditCard For The Payment.
     * @param dblValue Value.
     * @return true if everything works out, false if it doesn't.
     */
    public int addPayment(CreditCard oCreditCard, Double dblValue) {
        try{

            if((this.mdblCurrentPayment + dblValue) <= this.mdblExpectedPayment){
                this.mMapPayments.put(oCreditCard, dblValue);
                this.mdblCurrentPayment += dblValue;
                if ((this.mdblCurrentPayment) == this.mdblExpectedPayment){
                    return 1; // if its fully payed
                }
                return 0; // if there is still money owed
            } else{
                throw new Exception();
            }
        } catch(Exception e){
            return -1;
        }
    }

    /**
     * The method generates the invoice and sends it by email to the client.
     * @return true if everything works out, false if it doesn't.
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
     * @param oOrder Order.
     */
    public void setOrder(Order oOrder) {
        this.moOrder = oOrder;
    }

    /**
     * The method sets the client.
     * @param oClient Client.
     */
    public void setClient(Client oClient) {
        this.moClient = oClient;
    }

    /**
     * The method sets the pharmacy.
     * @param strPharmacyEmail Pharmacy's Email.
     * @return
     */
    public boolean setPharmacy(String strPharmacyEmail){
        try {
            this.moPharmacy = this.moPharmacyService.getPharmacy(strPharmacyEmail);
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    /**
     * The method gets the Order.
     * @return Order.
     */
    public Order getOrder() {
        return moOrder;
    }

    /**
     * The method gets the order management class.
     * @return The order management class.
     */
    public OrderService getOrderService() {
        return moOrderService;
    }

    /**
     * The method sets the order management class.
     * @param oOrderService The order management class.
     */
    public void setOrderService(OrderService oOrderService) {
        this.moOrderService = oOrderService;
    }

    /**
     * The method gets the client management class.
     * @return The client management class.
     */
    public ClientService getClientService() {
        return moClientService;
    }

    /**
     * The method sets the client management class.
     * @param oClientService The client management class.
     */
    public void setClientService(ClientService oClientService) {
        this.moClientService = oClientService;
    }

    /**
     * The method gets the product management class.
     * @return The product management class.
     */
    public ProductService getProductService() {
        return moProductService;
    }

    /**
     * The method sets the product management class.
     * @param oProductService The product management class.
     */
    public void setProductService(ProductService oProductService) {
        this.moProductService = oProductService;
    }

    /**
     * The method gets the pharmacy management class.
     * @return The pharmacy management class.
     */
    public PharmacyService getPharmacyService() {
        return moPharmacyService;
    }

    /**
     * The method sets the pharmacy management class.
     * @param oPharmacyService The pharmacy management class.
     */
    public void setPharmacyService(PharmacyService oPharmacyService) {
        this.moPharmacyService = oPharmacyService;
    }

    /**
     * The method gets the map with the products.
     * @return The map with the products.
     */
    public Map<Product, Integer> getMapProducts() {
        return mMapProducts;
    }

    /**
     * The method sets the map with the products.
     * @param mapProducts The map with the products.
     */
    public void setMapProducts(Map<Product, Integer> mapProducts) {
        this.mMapProducts = mapProducts;
    }

    /**
     * The method gets the map with the payments.
     * @return The map with the payments.
     */
    public Map<CreditCard, Double> getMapPayments() {
        return mMapPayments;
    }

    /**
     * The method sets the map with the payments.
     * @param mapPayments The map with the payments.
     */
    public void setMapPayments(Map<CreditCard, Double> mapPayments) {
        this.mMapPayments = mapPayments;
    }

    /**
     * The method gets the Generate Invoice Controller.
     * @return The Generate Invoice Controller.
     */
    public GenerateInvoiceController getGenerateInvoiceController() {
        return moGenerateInvoiceController;
    }

    /**
     * The method sets the Generate Invoice Controller.
     * @param oGenerateInvoiceController The Generate Invoice Controller.
     */
    public void setGenerateInvoiceController(GenerateInvoiceController oGenerateInvoiceController) {
        this.moGenerateInvoiceController = oGenerateInvoiceController;
    }

    /**
     * The method gets the Notify And Remove Controller.
     * @return The Notify And Remove Controller.
     */
    public NotifyAndRemoveController getNotifyAndRemoveController() {
        return moNotifyAndRemoveController;
    }

    /**
     * The method sets the Notify And Remove Controller.
     * @param oNotifyAndRemoveController The Notify And Remove Controller.
     */
    public void setNotifyAndRemoveController(NotifyAndRemoveController oNotifyAndRemoveController) {
        this.moNotifyAndRemoveController = oNotifyAndRemoveController;
    }

    /**
     * The method gets the Current Payment.
     * @return The Current Payment.
     */
    public double getCurrentPayment() {
        return mdblCurrentPayment;
    }

    /**
     * The method sets the Current Payment.
     * @param dblCurrentPayment The Current Payment.
     */
    public void setCurrentPayment(double dblCurrentPayment) {
        this.mdblCurrentPayment = dblCurrentPayment;
    }

    /**
     * The method gets the Expected Payment.
     * @return The Expected Payment.
     */
    public double getExpectedPayment() {
        return mdblExpectedPayment;
    }

    /**
     * The method sets the Expected Payment.
     * @param dblExpectedPayment The Expected Payment.
     */
    public void setExpectedPayment(double dblExpectedPayment) {
        this.mdblExpectedPayment = dblExpectedPayment;
    }
}
