package lapr.project.ui;

import lapr.project.controller.*;


/**
 * @author Nuno Bettencourt <nmb@isep.ipp.pt> on 24/05/16.
 */
class Main {

    private RegisterDeliveryRunController m_oRegisterDeliveryRunController;
    /**
     * Private constructor to hide implicit public one.
     */
    private Main() {
        m_oRegisterDeliveryRunController = new RegisterDeliveryRunController();
    }

    /**
     * Application main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hello world\n");

        ApplicationPOT.getInstance().getWorldMap().createGraph();
        System.out.println(ApplicationPOT.getInstance().getWorldMap().getGraph().toString());


        System.out.println("\nGoodbye world");
    }
}