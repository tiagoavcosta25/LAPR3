package lapr.project.ui;

import javafx.util.Pair;
import lapr.project.controller.ApplicationPOT;
import lapr.project.controller.RegisterDeliveryRunController;
import lapr.project.model.*;
import lapr.project.model.service.GraphService;

import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.*;

public class Test {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        //FileReader.readFiles();
        ApplicationPOT.getInstance().getWorldMap().createGraph();
        RegisterDeliveryRunController ctrl = new RegisterDeliveryRunController();
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("A", UserSession.Role.ADMIN));
        List<Order> lstOrder = ctrl.getOrdersList("info@sabandeira.com");
        System.out.println(ctrl.registerDeliveryRun(lstOrder));
    }
}
