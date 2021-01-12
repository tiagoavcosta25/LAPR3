package lapr.project.controller;

import lapr.project.model.UserSession;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationPOT {

    private UserSession m_currentSession;
    private AddPharmacyProductController m_oAddPharmacyProductController;
    private AvailableChargingSlotController m_oAvailableChargingSlotController;
    private CalculateMostEfficientPathController m_oCalculateMostEfficientPathController;
    private DeliveryAddressController m_oDeliveryAddressController;
    private GenerateInvoiceController m_oGenerateInvoiceController;
    private KnowDeliveryController m_oKnowDeliveryController;
    private LoginController m_oLoginController;
    private MakeAnOrderController m_oMakeAnOrderController;
    private MaxPayloadController m_oMaxPayloadController;
    private NotifyAndRemoveController m_oNotifyAndRemoveController;
    private ProductInformationController m_oProductInformationController;


    private static ApplicationPOT singleton = null;

    public ApplicationPOT() {
        try {
            this.m_oMakeAnOrderController = new MakeAnOrderController();
            Properties properties =
                    new Properties(System.getProperties());
            InputStream input = new FileInputStream("target/classes/application.properties");
            properties.load(input);
            input.close();
            System.setProperties(properties);

        } catch (IOException e) {
            e.printStackTrace();
        }
     }

    public static ApplicationPOT getInstance()
    {
        if(singleton == null)
        {
            synchronized(ApplicationPOT.class)
            {
                singleton = new ApplicationPOT();
            }
        }
        return singleton;
    }

    /**
     * Returns the current session.
     * @return the current session.
     */
    public UserSession getCurrentSession() {
        return m_currentSession;
    }

    /**
     * Modifies the current session.
     * @param session the current session.
     */
    public void setCurrentSession(UserSession session) {
        m_currentSession = session;
    }

    public void clearCurrentSession() { m_currentSession = new UserSession(); }

    /**
     * Returns the current session.
     * @return the current session.
     */
    public MakeAnOrderController getMakeAnOrderController() {
        return m_oMakeAnOrderController;
    }

}
