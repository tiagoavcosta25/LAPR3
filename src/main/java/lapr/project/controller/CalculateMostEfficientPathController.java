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


}
