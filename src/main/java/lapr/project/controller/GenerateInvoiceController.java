package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.data.ClientDB;
import lapr.project.data.InvoiceDB;
import lapr.project.data.OrderDB;
import java.sql.Date;

public class GenerateInvoiceController {

    /**
     * Invoice class instance
     */
    private Invoice m_oInvoice;

    /**
     * Order Management class
     */
    private OrderDB m_oOrderDB;

    /**
     * Client Management class
     */
    private ClientDB m_oClientDB;

    /**
     * Invoice Management class
     */
    private InvoiceDB m_oInvoiceDB;

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
    public GenerateInvoiceController(String jdbcUrl, String username, String password) {
        this.m_oOrderDB = new OrderDB(jdbcUrl, username, password);
        this.m_oInvoiceDB = new InvoiceDB(jdbcUrl, username, password);
        this.m_oClientDB = new ClientDB(jdbcUrl, username, password);
    }

    public boolean newInvoice(Date dtInvoiceDate, float fltTotalPrice) {
        try {
            this.m_oClient = m_oClientDB.getClientByEmail(ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail());
            this.m_oOrder = m_oOrderDB.getLatestOrder(m_oClient);
            this.m_oInvoice = m_oInvoiceDB.newInvoice(dtInvoiceDate, fltTotalPrice, this.m_oOrder);
            return this.registerInvoice();
        } catch (Exception ex) {
            this.m_oInvoice = null;
            return false;
        }
    }

    /**
     * The method registers an order to the database.
     */
    public boolean registerInvoice() {
        return this.m_oInvoiceDB.registerInvoice(this.m_oInvoice);
    }
}
