package lapr.project.controller;

import javafx.util.Pair;
import lapr.project.model.Address;
import lapr.project.model.Order;
import lapr.project.model.Platform;
import lapr.project.data.registration.DeliveryRegistration;
import lapr.project.data.registration.OrderRegistration;
import lapr.project.model.UserSession;

import java.util.LinkedList;
import java.util.List;

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
     * Platform class instance
     */
    private Platform m_oPlatform;
    /**
     * Delivery Registration class
     */
    private DeliveryRegistration oDeliveryRegistration;
    /**
     * Order Registration class
     */
    private OrderRegistration oOrderRegistration;
    private ApplicationPOT m_oApplicationPOT;
    private UserSession m_oUserSession;

    public CalculateMostEfficientPathController() {
        this.m_oApplicationPOT = ApplicationPOT.getInstance();
        this.m_oPlatform = this.m_oApplicationPOT.getPlatform();
        this.oDeliveryRegistration = this.m_oPlatform.getDelReg();
        this.oOrderRegistration = this.m_oPlatform.getOrderReg();
    }

    public double getShortestPath() {
        this.m_oUserSession = this.m_oApplicationPOT.getCurrentSession();
        String email = this.m_oUserSession.getCurrentUserEmail();
        Order oOrder = this.oOrderRegistration.getOrderByCourier(email);
        List<Address> list = this.oDeliveryRegistration.getAddressesByDeliveryRunId(email);
        Address a = new Address();
        Address b = new Address();
        Pair<LinkedList<Address>, Double> result = this.oDeliveryRegistration.calculateMostEfficientPath(a, b, list);
        if (result == null) {
            return -1;
        }
        return result.getValue();
    }


}
