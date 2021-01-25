package lapr.project.ui.console;

import lapr.project.controller.ApplicationPOT;
import lapr.project.controller.KnowDeliveryController;
import lapr.project.model.Order;
import lapr.project.model.UserSession;
import lapr.project.ui.UI;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KnowDeliveryUI implements UI {

    public static final Logger LOGGER = Logger.getLogger(KnowDeliveryUI.class.getName());

    public void run() {
        KnowDeliveryController oCtrl = new KnowDeliveryController();
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.print("Know delivery:\n\nCourier's Email: ");
                String strEmail = sc.nextLine();
                ApplicationPOT.getInstance().setCurrentSession(new UserSession(strEmail));
                Order oOrder = oCtrl.getOrderByCour();
                System.out.printf("For the Courier with the email %s, the order assigned is:\n%s%n", strEmail, oOrder.toString());
            } catch (Exception e) {
                LOGGER.log(Level.WARNING, "Error Finding the delivery.");
            }
        } while (true);
    }
}
