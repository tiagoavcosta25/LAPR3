package lapr.project.model.service;

import lapr.project.data.OrderDB;
import lapr.project.model.*;

import java.util.Map;

/**
 * Order Service.
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
public class OrderService {

    /**
     * Order Database Class.
     */
    private OrderDB moOrderDB;

    /**
     * Getter For the Order Database Class.
     * @return Order Database Class.
     */
    public OrderDB getOrderDB() {
        return moOrderDB;
    }

    /**
     * Setter For the Order Database Class.
     * @param oOrderDB Order Database Class.
     */
    public void setOrderDB(OrderDB oOrderDB) {
        this.moOrderDB = oOrderDB;
    }

    /**
     * Empty Constructor.
     */
    public OrderService() {
        this.moOrderDB = new OrderDB();
    }

    /**
     * Method that gets an Order from the database based on the ID.
     * @param strId ID.
     * @return Order.
     */
    public Order getOrder(int strId) {
        return this.moOrderDB.getOrder(strId);
    }

    /**
     * Method that removes an Order from the database based on the ID.
     * @param intId ID.
     * @return true if it removes, false if it does not.
     */
    public boolean removeOrder(int intId) {
        return this.moOrderDB.removeOrder(intId);
    }

    /**
     * Method that registers an Order in the database.
     * @param oOrder
     * @return true if it registers, false if it does not.
     */
    public int registerOrder(Order oOrder) {
        return this.moOrderDB.registerOrder(oOrder);
    }

    /**
     * Method that creates an instance of an Order.
     * @param strDescription Description.
     * @param blIsHomeDelivery Home Delivery Boolean.
     * @param oClient Client.
     * @param oPharmacy Pharmacy.
     * @param mapProducts Map of Products.
     * @return Order.
     */
    public Order newOrder(String strDescription, boolean blIsHomeDelivery, Client oClient, Pharmacy oPharmacy, Map<Product, Integer> mapProducts) {
        return new Order(strDescription, blIsHomeDelivery, oClient, oPharmacy, mapProducts);
    }

    /**
     * Gets the lastest order from a client.
     * @param oClient Client.
     * @return Order.
     */
    public Order getLatestOrder(Client oClient) {
        return this.moOrderDB.getLatestOrder(oClient);
    }

    /**
     * Gets the lastest order from a courier.
     * @param strEmail courier email.
     * @return Order.
     */
    public Order getOrderByCourier(String strEmail) {
        return this.moOrderDB.getOrderByCourier(strEmail);
    }

    /**
     * Notifies a Client and Removes the Out of Stock Products.
     * @param oOrder Order.
     * @return Map of products.
     */
    public Map<Product,Integer> notifyAndRemove(Order oOrder) {
        return this.moOrderDB.notifyAndRemove(oOrder);
    }

}
