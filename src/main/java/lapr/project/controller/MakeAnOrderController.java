package lapr.project.controller;

import lapr.project.model.Address;
import lapr.project.model.Client;
import lapr.project.model.Order;
import lapr.project.model.Platform;
import lapr.project.model.registration.ClientRegistration;
import lapr.project.model.registration.CourierRegistration;
import lapr.project.model.registration.OrderRegistration;

import java.sql.Date;

public class MakeAnOrderController {
    /**
     * Platform class instance
     */
    private Platform m_oPlatform;
    /**
     * Order class instance
     */
    private Order m_oOrder;

    /**
     * Order Management class
     */
    private OrderRegistration m_oOrderRegistration;

    /**
     * Order Management class
     */
    private ClientRegistration m_oClientRegistration;

    /**
     * Order's Client
     */
    private Client m_oClient;

    /**
     * An empty constructor of MakeAnOrderController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public MakeAnOrderController() {
        this.m_oPlatform = ApplicationPOT.getInstance().getPlatform();
        this.m_oOrderRegistration = m_oPlatform.getOrderReg();
        this.m_oClientRegistration = m_oPlatform.getClientReg();
        //this.m_oClient = m_oClientRegistration.getUserByEmail(ApplicationPOT.getInstance().getCurrentSession().getM_currentUserEmail());
    }

    public void newOrder(float fltAmount, float fltTotalWeight, float fltAdditionalFee, Date dtOrderDate,
                         String strDescription, String strStatus, float latitude, float longitude, String streetName,
                         String doorNumber, String postalCode, String locality, String country) {
        try {
            this.m_oOrder = m_oOrderRegistration.addOrder(fltAmount, fltTotalWeight, fltAdditionalFee, dtOrderDate,
                    strDescription, strStatus, m_oClient, new Address(latitude, longitude, streetName, doorNumber, postalCode, locality, country));
        } catch (RuntimeException ex) {
            this.m_oOrder = null;
        }
    }

    /**
     * The method registers an order to the database.
     */
    public void registersFreelancer() {
        this.m_oOrderRegistration.registerOrder(m_oOrder);
    }
}