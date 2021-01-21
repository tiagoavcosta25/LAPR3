package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.service.DeliveryRunService;

import java.util.Map;

public class StartDeliveryRunController {

    /**
     * Courier Management class
     */
    private DeliveryRunService moDeliveryRunService;


    /**
     * A constructor of RegisterCourierController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public StartDeliveryRunController() {
        this.moDeliveryRunService = new DeliveryRunService();
    }

    /**
     * The method receives Courier's email.
     * Initiates the CourierRegistration instance and the Courier instance with the provided data.
     * The method returns the validation of that instance of Courier. True if the data is correct and false if
     * it doesn't.
     *
     */
    public boolean startDeliveryRun() {
        try {
            Map<String,String> clientList = moDeliveryRunService.startDeliveryRun(ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail());
             return moDeliveryRunService.sendsEmail(clientList);
        }catch (Exception e){
            return false;
        }
    }

}
