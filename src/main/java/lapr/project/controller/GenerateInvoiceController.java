package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.data.ClientDB;
import lapr.project.data.InvoiceDB;
import lapr.project.data.OrderDB;
import lapr.project.model.service.InvoiceService;
import lapr.project.model.service.OrderService;

import java.sql.Date;

public class GenerateInvoiceController {

    /**
     * Invoice class instance
     */
    private Invoice m_oInvoice;

    /**
     * Order Management class
     */
    private OrderService m_oOrderService;

    /**
     * Client Management class
     */
    private ClientDB m_oClientDB;

    /**
     * Invoice Management class
     */
    private InvoiceService m_oInvoiceService;

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
        this.m_oOrderService = new OrderService();
        this.m_oInvoiceService = new InvoiceService();
        this.m_oClientDB = new ClientDB(jdbcUrl, username, password);
    }

    public boolean newInvoice(Date dtInvoiceDate, float fltTotalPrice) {
        try {
            this.m_oClient = m_oClientDB.getClientByEmail(ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail());
            this.m_oOrder = m_oOrderService.getLatestOrder(m_oClient);
            this.m_oInvoice = m_oInvoiceService.newInvoice(dtInvoiceDate, fltTotalPrice, this.m_oOrder);
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
        return this.m_oInvoiceService.registerInvoice(this.m_oInvoice);
    }
}
