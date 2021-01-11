package lapr.project.controller;

import lapr.project.model.Client;
import lapr.project.model.Order;
import lapr.project.model.Platform;
import lapr.project.data.registration.ClientRegistration;
import lapr.project.data.registration.OrderRegistration;

/**
 * Register Courier Controller.
 * <p>
 * Group: Team Lisa [G-037]
 * ______________________________________________________
 *
 * @author Ernesto Rodrigues <1190560@isep.ipp.pt>
 * @author Jessica Alves <1190682@isep.ipp.pt>
 * @author Pedro Santos <1190967@isep.ipp.pt>
 * @author Rodrigo Costa <1191014@isep.ipp.pt>
 */
public class NotifyAndRemoveController {
    /**
     * Platform class instance
     */
    private Platform m_oPlatform;
    /**
     * Courier Management class
     */
    private OrderRegistration oOrderRegistration;

    /**
     * Courier Management class
     */
    private ClientRegistration oClientRegistration;

    /**
     * An empty constructor of RegisterCourierController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public NotifyAndRemoveController() {
        this.m_oPlatform = ApplicationPOT.getInstance().getPlatform();
        this.oOrderRegistration = m_oPlatform.getOrderReg();
        this.oClientRegistration = m_oPlatform.getClientReg();
    }

    public boolean notifyAndRemove(){
        Client client = oClientRegistration.getClientByEmail(ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail());
        Order order = oOrderRegistration.getLatestOrder(client);
        return oOrderRegistration.notifyAndRemove(order);
    }

}

