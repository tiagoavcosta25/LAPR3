package lapr.project.controller;

import javafx.util.Pair;
import lapr.project.model.Address;
import lapr.project.model.UserSession;
import lapr.project.model.service.DeliveryRunService;

import java.util.LinkedList;
import java.util.List;


public class CalculateMostEfficientPathController {

    private DeliveryRunService oDeliveryRunService;
    private UserSession m_oUserSession;

    public CalculateMostEfficientPathController() {
        this.oDeliveryRunService = new DeliveryRunService();
    }

    public double getShortestPath() {
        this.m_oUserSession = ApplicationPOT.getInstance().getCurrentSession();
        String email = this.m_oUserSession.getCurrentUserEmail();
        List<Address> list = this.oDeliveryRunService.getAddressesByDeliveryRunId(email);
        if (list != null) {
            Address a = list.get(list.size() - 1);
            list.remove(list.size() - 1);
            Pair<LinkedList<Address>, Double> result = this.oDeliveryRunService.calculateMostEfficientPath(a, a, list);
            if (result == null) {
                return -1;
            }
            return result.getValue();
        }
        return -1;
    }


}
