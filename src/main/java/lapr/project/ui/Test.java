package lapr.project.ui;

import javafx.util.Pair;
import lapr.project.controller.ApplicationPOT;
import lapr.project.controller.RegisterDeliveryRunController;
import lapr.project.model.*;

import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Test {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        //FileReader.readFiles();
        ApplicationPOT.getInstance().getWorldMap().createGraph();
        RegisterDeliveryRunController ctrl = new RegisterDeliveryRunController();
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("A", UserSession.Role.ADMIN));
        List<Order> lstOrder = ctrl.getOrdersList("info@sabandeira.com");
        for(Order o : lstOrder)
            System.out.println(o.getClient().getAddress());
        List<Pair<Pair<VehicleModel, Double>, List<Address>>> lst = ApplicationPOT.getInstance().getWorldMap().calculateBestVehicleAndBestPath(lstOrder);
        //System.out.println(ctrl.registerDeliveryRun(lstOrder));
    }
}
