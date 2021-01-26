package lapr.project.ui;

import lapr.project.controller.ApplicationPOT;
import lapr.project.controller.ParkScooterController;
import lapr.project.controller.RegisterDeliveryRunController;
import lapr.project.model.Order;
import lapr.project.model.UserSession;
import lapr.project.ui.FileReader;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        //FileReader.readFiles();
        //ApplicationPOT.getInstance().getWorldMap().createGraph();
        //ApplicationPOT.getInstance().setCurrentSession(new UserSession("asdas", UserSession.Role.ADMIN));
        //RegisterDeliveryRunController ctrl = new RegisterDeliveryRunController();
        //List<Order> allOrders = ctrl.getOrdersList("info@trindade.com");
        // escolhes as orders aqui e metes numa lista
        //ctrl.registerDeliveryRun(allOrders);

        ParkScooterController ctrl1 = new ParkScooterController();
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("asdads@", UserSession.Role.COURIER));
        ctrl1.parkScooter(12);

    }
}
