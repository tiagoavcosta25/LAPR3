package lapr.project.model.service;

import lapr.project.data.OrderDB;
import lapr.project.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderService {

    private OrderDB m_oOrderDB;

    public OrderService() {
        this.m_oOrderDB = new OrderDB();
    }

    public Order getOrder(int strId) {
        return this.m_oOrderDB.getOrder(strId);
    }

    public boolean removeOrder(int intId) {
        return this.m_oOrderDB.removeOrder(intId);
    }

    public boolean registerOrder(Order oOrder) {
        return this.m_oOrderDB .registerOrder(oOrder);
    }

    public Order newOrder(String strDescription, boolean blIsHomeDelivery, Client oClient, Pharmacy oPharmacy, Map<Product, Integer> mapProducts) {
        return new Order(strDescription, blIsHomeDelivery, oClient, oPharmacy, mapProducts);
    }

    public Order getLatestOrder(Client oClient) {
        return this.m_oOrderDB .getLatestOrder(oClient);
    }

    public Order getOrderByCourier(String strEmail) {
        return this.m_oOrderDB .getOrderByCourier(strEmail);
    }


    public boolean notifyAndRemove(Order oOrder) {
        return this.m_oOrderDB .notifyAndRemove(oOrder);
    }

}
