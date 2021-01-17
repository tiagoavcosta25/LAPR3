package lapr.project.controller;

import lapr.project.model.Address;
import lapr.project.data.CourierDB;

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
public class DeliveryAddressController {
    /**
     * Courier Management class
     */
    private CourierDB m_oCourierDB;

    /**
     * An empty constructor of RegisterCourierController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public DeliveryAddressController() {
        this.m_oCourierDB = new CourierDB();
    }

    public Address getDeliveryAddress(){
        String email = ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail();
        return m_oCourierDB.getDeliveryAddress(email);
    }

}

