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
    private OrderDB oOrderDB;

    /**
     * Courier Management class
     */
    private ClientDB oClientDB;

    /**
     * An empty constructor of RegisterCourierController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public NotifyAndRemoveController(String jdbcUrl, String username, String password) {
        this.oOrderDB = new OrderDB(jdbcUrl, username, password);
        this.oClientDB = new ClientDB(jdbcUrl, username, password);
    }

    public boolean notifyAndRemove(){
        Client client = oClientDB.getClientByEmail(ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail());
        Order order = oOrderDB.getLatestOrder(client);
        return oOrderDB.notifyAndRemove(order);
    }

}

