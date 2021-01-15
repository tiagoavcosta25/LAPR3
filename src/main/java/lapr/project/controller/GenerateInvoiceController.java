package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.data.ClientDB;
import lapr.project.data.InvoiceDB;
import lapr.project.data.OrderDB;
import lapr.project.model.service.ClientService;
import lapr.project.model.service.InvoiceService;
import lapr.project.model.service.OrderService;

import java.sql.Date;
import java.util.Map;

public class GenerateInvoiceController {

    /**
     * Invoice class instance
     */
    private Invoice m_oInvoice;

    /**
     * Invoice Management class
     */
    private InvoiceService m_oInvoiceService;

    /**
     * An empty constructor of MakeAnOrderController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public GenerateInvoiceController() {
        this.m_oInvoiceService = new InvoiceService();
    }

    public boolean generateInvoice(Order oOrder, Map<CreditCard, Float> mapPayments) {
        try {
            this.m_oInvoice = m_oInvoiceService.newInvoice(oOrder, mapPayments);
            // mandar email
            return this.m_oInvoiceService.registerInvoice(this.m_oInvoice);
        } catch (Exception ex) {
            this.m_oInvoice = null;
            return false;
        }
    }
}
