package lapr.project.controller;

import lapr.project.data.DeliveryDB;
import lapr.project.data.OrderDB;
import lapr.project.model.UserSession;

/**
 * Calculate Most Efficient Path Controller.
 * <p>
 * Group: Team Lisa [G-021]
 * ______________________________________________________
 *
 * @author Ernesto Rodrigues <1190560@isep.ipp.pt>
 * @author Jessica Alves <1190682@isep.ipp.pt>
 * @author Pedro Santos <1190967@isep.ipp.pt>
 * @author Rodrigo Costa <1191014@isep.ipp.pt>
 * @author António Barbosa <1190404@isep.ipp.pt>
 */
public class CalculateMostEfficientPathController {

    /**
     * Delivery Registration class
     */
    private DeliveryDB oDeliveryDB;

    /**
     * Order Registration class
     */
    private OrderDB oOrderDB;

    public CalculateMostEfficientPathController(String jdbcUrl, String username, String password) {
        this.oDeliveryDB = new DeliveryDB(jdbcUrl, username, password);
        this.oOrderDB = new OrderDB(jdbcUrl, username, password);
    }

    public CalculateMostEfficientPathController() {
    }

    //TO IMPLEMENT
    /*public double getShortestPath() {
        this.m_oUserSession = this.m_oApplicationPOT.getCurrentSession();
        String email = this.m_oUserSession.getCurrentUserEmail();
        List<Address> list = this.oDeliveryRegistration.getAddressesByDeliveryRunId(email);
        Address a = new Address();
        Address b = new Address();
        Pair<LinkedList<Address>, Double> result = this.oDeliveryRegistration.calculateMostEfficientPath(a, b, list);
        if (result == null) {
            return -1;
        }
        return result.getValue();
    }*/


}
