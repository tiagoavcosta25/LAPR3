package lapr.project.controller;

import lapr.project.model.service.DeliveryRunService;

import java.util.Map;

/**
 * Start Delivery Run Controller.
 * <p>
 * Group: Team Lisa [G-021]
 * ______________________________________________________
 *
 * @author António Barbosa <1190404@isep.ipp.pt>
 * @author Ernesto Rodrigues <1190560@isep.ipp.pt>
 * @author Jessica Alves <1190682@isep.ipp.pt>
 * @author Pedro Santos <1190967@isep.ipp.pt>
 * @author Rodrigo Costa <1191014@isep.ipp.pt>
 * @author Tiago Costa <1191460@isep.ipp.pt>
 */
public class StartDeliveryRunController {

    private DeliveryRunService moDeliveryRunService;


    /**
     * A constructor of RegisterCourierController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public StartDeliveryRunController() {
        this.setMoDeliveryRunService(new DeliveryRunService());
    }

    /**
     * The method receives Courier's email.
     * Initiates the CourierRegistration instance and the Courier instance with the provided data.
     * The method returns the validation of that instance of Courier. True if the data is correct and false if
     * it doesn't.
     */
    public boolean startDeliveryRun() {
        try {
            Map<String, String> clientList = getMoDeliveryRunService().startDeliveryRun(ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail());
            return getMoDeliveryRunService().sendsEmail(clientList);
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * Returns the Delivery Run Service
     *
     * @return Delivery Run Service
     */
    public DeliveryRunService getMoDeliveryRunService() {
        return moDeliveryRunService;
    }

    /**
     * Sets the Delivery Run Service
     *
     * @param moDeliveryRunService Delivery Run Service
     */
    public void setMoDeliveryRunService(DeliveryRunService moDeliveryRunService) {
        this.moDeliveryRunService = moDeliveryRunService;
    }
}
