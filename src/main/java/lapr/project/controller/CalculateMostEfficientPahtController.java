package lapr.project.controller;


import lapr.project.graph.map.Graph;
import lapr.project.model.Address;
import lapr.project.model.Platform;
import lapr.project.model.registration.DeliveryRegistration;
import lapr.project.model.registration.OrderRegistration;

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
public class CalculateMostEfficientPahtController {
    /**
     * Platform class instance
     */
    private Platform m_oPlatform;
    /**
     * Delivery Management class
     */
    private DeliveryRegistration oDeliveryRegistration;
    /**
     * Delivery Management class
     */
    private OrderRegistration oOrderRegistration;
    /**
     * The starting point of the path
     */
    private Address oStartingPoint;
    /**
     * The destiny point of the path
     */
    private Address oDestiny;

    //corrigir tipo de dados
    public double getShortestPath() {
        this.m_oPlatform = ApplicationPOT.getInstance().getPlatform();
        this.oDeliveryRegistration = this.m_oPlatform.getDelReg();
        this.oOrderRegistration = this.m_oPlatform.getOrderReg();
        return this.oDeliveryRegistration.getShortestPath(oStartingPoint, oDestiny);
    }


}
