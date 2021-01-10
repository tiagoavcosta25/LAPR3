package lapr.project.controller;

import lapr.project.model.Platform;
import lapr.project.model.Scooter;
import lapr.project.data.registration.DeliveryRegistration;
import lapr.project.data.registration.ScooterRegistration;

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
     * Platform class instance
     */
    private Platform m_oPlatform;
    /**
     * Courier Management class
     */
    private DeliveryRegistration oDeliveryRegistration;
    /**
     * Courier Management class
     */
    private ScooterRegistration oScooterRegistration;

    /**
     * An empty constructor of RegisterCourierController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public SeeSuitableScooterController() {
        this.m_oPlatform = ApplicationPOT.getInstance().getPlatform();
    }

    public Scooter getSuitableScooter(){
        m_oPlatform = ApplicationPOT.getInstance().getPlatform();
        /*UserSession session = ApplicationPOT.getInstance().getCurrentSession();
        String email = session.getCurrentUserEmail();*/
        String email = "user6@gmail.com";
        oDeliveryRegistration = m_oPlatform.getDelReg();
        oScooterRegistration = m_oPlatform.getScooterReg();
        double distance =0;
        float deliveryEnergy = oDeliveryRegistration.getDeliveryEnergy(distance);
        return oScooterRegistration.getSuitableScooter(deliveryEnergy,email);
    }

}

