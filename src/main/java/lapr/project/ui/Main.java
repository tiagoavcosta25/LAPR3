package lapr.project.ui;

import lapr.project.controller.ApplicationPOT;
import lapr.project.controller.MakeAnOrderController;
import lapr.project.data.OrderDB;
import lapr.project.model.Order;

/**
 * @author Nuno Bettencourt <nmb@isep.ipp.pt> on 24/05/16.
 */
class Main {


    /**
     * Private constructor to hide implicit public one.
     */
    private Main() {

    }

    /**
     * Application main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hello");

        MakeAnOrderController o = ApplicationPOT.getInstance().getMakeAnOrderController();

        Order order = o.newOrder("", true);

        System.out.println();

    }
}