package lapr.project.model.service;

import lapr.project.data.OrderDB;
import lapr.project.model.*;

import java.util.Map;

public class OrderService {

    private OrderDB moOrderDB;

    public OrderDB getOrderDB() {
        return moOrderDB;
    }

    public void setOrderDB(OrderDB oOrderDB) {
        this.moOrderDB = oOrderDB;
    }

    public OrderService() {
        this.moOrderDB = new OrderDB();
    }

    public Order getOrder(int strId) {
        return this.moOrderDB.getOrder(strId);
    }

    public boolean removeOrder(int intId) {
        return this.moOrderDB.removeOrder(intId);
    }

    public int registerOrder(Order oOrder) {
        return this.moOrderDB.registerOrder(oOrder);
    }

    public Order newOrder(String strDescription, boolean blIsHomeDelivery, Client oClient, Pharmacy oPharmacy, Map<Product, Integer> mapProducts) {
        return new Order(strDescription, blIsHomeDelivery, oClient, oPharmacy, mapProducts);
    }

    public Order getLatestOrder(Client oClient) {
        return this.moOrderDB.getLatestOrder(oClient);
    }

    public Order getOrderByCourier(String strEmail) {
        return this.moOrderDB.getOrderByCourier(strEmail);
    }


    public Map<Product,Integer> notifyAndRemove(Order oOrder) {
        return this.moOrderDB.notifyAndRemove(oOrder);
    }

}
