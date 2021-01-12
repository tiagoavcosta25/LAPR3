package lapr.project.controller;

import lapr.project.model.Scooter;
import lapr.project.data.DeliveryDB;
import lapr.project.data.ScooterDB;

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
public class SeeSuitableScooterController {
    /**
     * Courier Management class
     */
    private DeliveryDB oDeliveryDB;
    /**
     * Courier Management class
     */
    private ScooterDB oScooterDB;

    /**
     * An empty constructor of RegisterCourierController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public SeeSuitableScooterController(String jdbcUrl, String username, String password) {
        oDeliveryDB = new DeliveryDB(jdbcUrl, username, password);
        oScooterDB = new ScooterDB(jdbcUrl, username, password);
    }

    public Scooter getSuitableScooter(Double distance){
        return oScooterDB.getSuitableScooter(distance, ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail());
    }

}

