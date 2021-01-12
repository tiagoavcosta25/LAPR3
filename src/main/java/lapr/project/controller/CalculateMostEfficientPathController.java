/*package lapr.project.controller;

import lapr.project.data.DeliveryDB;
import lapr.project.data.OrderDB;
import lapr.project.model.UserSession;


public class CalculateMostEfficientPathController {


    private DeliveryDB oDeliveryDB;


    private OrderDB oOrderDB;

    public CalculateMostEfficientPathController(String jdbcUrl, String username, String password) {
        this.oDeliveryDB = new DeliveryDB(jdbcUrl, username, password);
        this.oOrderDB = new OrderDB(jdbcUrl, username, password);
    }

    //TO IMPLEMENT
    public double getShortestPath() {
        this.m_oUserSession = this.m_oApplicationPOT.getCurrentSession();
        String email = this.m_oUserSession.getCurrentUserEmail();
        List<Address> list = this.oDeliveryRegistration.getAddressesByDeliveryRunId(email);
        Address a = new Address();
        Address b = new Address();
        Pair<LinkedList<Address>, Double> result = this.oDeliveryRegistration.calculateMostEfficientPath(a, b, list);
        if (result == null) {
            return -1;
        }
        return result.getValue();
    }


}*/
