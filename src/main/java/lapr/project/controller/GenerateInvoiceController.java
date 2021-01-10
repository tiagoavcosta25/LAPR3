package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.data.registration.ClientRegistration;
import lapr.project.data.registration.InvoiceRegistration;
import lapr.project.data.registration.OrderRegistration;
import java.sql.Date;

public class GenerateInvoiceController {
    /**
     * Platform class instance
     */
    private Platform m_oPlatform;
    /**
     * Invoice class instance
     */
    private Invoice m_oInvoice;

    /**
     * Order Management class
     */
    private OrderRegistration m_oOrderRegistration;

    /**
     * Client Management class
     */
    private ClientRegistration m_oClientRegistration;

    /**
     * Invoice Management class
     */
    private InvoiceRegistration m_oInvoiceRegistration;

    /**
     * Order's Client
     */
    private Client m_oClient;

    /**
     * Order
     */
    private Order m_oOrder;


    /**
     * An empty constructor of MakeAnOrderController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public GenerateInvoiceController() {
        this.m_oPlatform = ApplicationPOT.getInstance().getPlatform();
        this.m_oOrderRegistration = m_oPlatform.getOrderReg();
        this.m_oInvoiceRegistration = m_oPlatform.getInvoiceReg();
        this.m_oClientRegistration = m_oPlatform.getClientReg();
        this.m_oClient = m_oClientRegistration.getClientByEmail(ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail());
        this.m_oOrder = m_oOrderRegistration.getLatestOrder(m_oClient);
    }

    public void newInvoice(Date dtInvoiceDate, float fltTotalPrice) {
        try {
            this.m_oInvoice = m_oInvoiceRegistration.newInvoice(dtInvoiceDate, fltTotalPrice, this.m_oOrder);
            this.registerInvoice();
        } catch (RuntimeException ex) {
            this.m_oInvoice = null;
        }
    }

    /**
     * The method registers an order to the database.
     */
    public void registerInvoice() {
        this.m_oInvoiceRegistration.registerInvoice(this.m_oInvoice);
    }
}
