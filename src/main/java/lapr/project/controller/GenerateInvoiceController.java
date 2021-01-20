package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.service.InvoiceService;

import java.util.Map;

public class GenerateInvoiceController {

    /**
     * Invoice class instance
     */
    private Invoice moInvoice;

    /**
     * Invoice Management class
     */
    private InvoiceService moInvoiceService;

    /**
     * An empty constructor of MakeAnOrderController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public GenerateInvoiceController() {
        this.moInvoiceService = new InvoiceService();
    }

    public boolean generateInvoice(Order oOrder, Map<CreditCard, Float> mapPayments) {
        try {
            this.moInvoice = moInvoiceService.newInvoice(oOrder, mapPayments);
            this.moInvoiceService.registerInvoice(this.moInvoice);
            return this.moInvoiceService.sendInvoiceByEmail(this.moInvoice);
        } catch (Exception ex) {
            this.moInvoice = null;
            return false;
        }
    }
}
