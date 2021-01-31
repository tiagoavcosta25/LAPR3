package lapr.project.controller;

import lapr.project.model.service.DeliveryRunService;

import java.util.Map;

/**
 * Finish Delivery Run Controller.
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
public class FinishDeliveryRunController {

    /**
     * Delivery Run Management class
     */
    private DeliveryRunService moDeliveryRunService;


    /**
     * A constructor of Finish Delivery Run Controller.
     */
    public FinishDeliveryRunController() {
        this.moDeliveryRunService = new DeliveryRunService();
    }

    /**
     * The method finishes a delivery run.
     */
    public boolean finishDeliveryRun(int intID, int intBatteryPerc) {
        try {
             return moDeliveryRunService.finishDeliveryRun(intID, intBatteryPerc, ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail());
        }catch (Exception e){
            return false;
        }
    }

    public DeliveryRunService getDeliveryRunService() {
        return moDeliveryRunService;
    }

    public void setDeliveryRunService(DeliveryRunService oDeliveryRunService) {
        this.moDeliveryRunService = oDeliveryRunService;
    }

}
