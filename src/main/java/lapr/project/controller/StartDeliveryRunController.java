package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.service.DeliveryRunService;

import java.util.ArrayList;

public class StartDeliveryRunController {

    /**
     * Courier Management class
     */
    private DeliveryRunService oDeliveryRunService;


    /**
     * A constructor of RegisterCourierController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public StartDeliveryRunController() {
        this.oDeliveryRunService = new DeliveryRunService();
    }

    /**
     * The method receives Courier's email.
     * Initiates the CourierRegistration instance and the Courier instance with the provided data.
     * The method returns the validation of that instance of Courier. True if the data is correct and false if
     * it doesn't.
     *
     */
    public boolean startDeliveryRun(Vehicle vehicle) {
        try {
            ArrayList<String> clientList = oDeliveryRunService.startDeliveryRun(vehicle,ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail());
             return oDeliveryRunService.sendsEmail(clientList);
        }catch (Exception e){
            return false;
        }
    }

}
