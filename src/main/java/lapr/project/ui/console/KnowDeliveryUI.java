package lapr.project.ui.console;

import lapr.project.controller.ApplicationPOT;
import lapr.project.controller.KnowDeliveryController;
import lapr.project.model.Order;
import lapr.project.ui.UI;

import java.util.logging.Level;
import java.util.logging.Logger;

public class KnowDeliveryUI implements UI {

    public static final Logger LOGGER = Logger.getLogger(KnowDeliveryUI.class.getName());

    public void run() {
        KnowDeliveryController oCtrl = new KnowDeliveryController();
        try {
            System.out.print("Know delivery:\n\n");
            Order oOrder = oCtrl.getOrderByCour();
            System.out.printf("For the Courier with the email %s, the order assigned is:%n%s%n", ApplicationPOT.getInstance().getCurrentSession(), oOrder.toString());
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Error Finding the delivery.");
        }
    }
}