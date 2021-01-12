package lapr.project.controller;

import javafx.util.Pair;
import lapr.project.data.DeliveryDB;
import lapr.project.model.Address;
import lapr.project.model.UserSession;
import java.util.LinkedList;
import java.util.List;


public class CalculateMostEfficientPathController {

    private DeliveryDB oDeliveryDB;

    private UserSession m_oUserSession;

    public CalculateMostEfficientPathController(String jdbcUrl, String username, String password) {
        this.oDeliveryDB = new DeliveryDB(jdbcUrl, username, password);
    }

    public double getShortestPath() {
        this.m_oUserSession = ApplicationPOT.getInstance().getCurrentSession();
        String email = this.m_oUserSession.getCurrentUserEmail();
        List<Address> list = this.oDeliveryDB.getAddressesByDeliveryRunId(email);
        Address a = list.get(list.size() - 1);
        list.remove(list.size() - 1);
        Pair<LinkedList<Address>, Double> result = this.oDeliveryDB.calculateMostEfficientPath(a, a, list);
        if (result == null) {
            return -1;
        }
        return result.getValue();
    }
}
