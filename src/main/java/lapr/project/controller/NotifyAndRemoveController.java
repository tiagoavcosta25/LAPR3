package lapr.project.controller;

import lapr.project.model.Client;
import lapr.project.model.Order;
import lapr.project.data.ClientDB;
import lapr.project.data.OrderDB;

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
     * Courier Management class
     */
    private OrderDB moOrderDB;

    /**
     * Courier Management class
     */
    private ClientDB moClientDB;

    /**
     * An empty constructor of RegisterCourierController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public NotifyAndRemoveController() {
        this.moOrderDB = new OrderDB();
        this.moClientDB = new ClientDB();
    }

    public boolean notifyAndRemove(){
        Client client = moClientDB.getClientByEmail(ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail());
        Order order = moOrderDB.getLatestOrder(client);
        return moOrderDB.notifyAndRemove(order);
    }

}

