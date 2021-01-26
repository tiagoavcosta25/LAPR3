package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.service.InvoiceService;

import java.util.Map;

/**
 * Generate Invoice Controller.
 *
 * Group: Team Lisa [G-021]
 * ______________________________________________________
 * @author António Barbosa <1190404@isep.ipp.pt>
 * @author Ernesto Rodrigues <1190560@isep.ipp.pt>
 * @author Jessica Alves <1190682@isep.ipp.pt>
 * @author Pedro Santos <1190967@isep.ipp.pt>
 * @author Rodrigo Costa <1191014@isep.ipp.pt>
 * @author Tiago Costa <1191460@isep.ipp.pt>
 */
public class GenerateInvoiceController {

    /**
     * Invoice class instance.
     */
    private Invoice moInvoice;

    /**
     * Invoice Management class.
     */
    private InvoiceService moInvoiceService;

    /**
     * An empty constructor of GenerateInvoiceController.
     */
    public GenerateInvoiceController() {
        this.moInvoiceService = new InvoiceService();
    }

    /**
     * Method that Generates the Invoice to a specific Order.
     * @param oOrder Order.
     * @param mapPayments Map Of Payments made.
     * @return true if everything works out, false if it doesn't.
     */
    public boolean generateInvoice(Order oOrder, Map<CreditCard, Double> mapPayments) {
        try {
            this.moInvoice = moInvoiceService.newInvoice(oOrder, mapPayments);
            this.moInvoiceService.registerInvoice(this.moInvoice);
            return this.moInvoiceService.sendInvoiceByEmail(this.moInvoice);
        } catch (Exception ex) {
            this.moInvoice = null;
            return false;
        }
    }

    /**
     * Returns the Invoice.
     * @return Invoice.
     */
    public Invoice getInvoice() {
        return moInvoice;
    }

    /**
     * Sets the Invoice.
     * @param moInvoice Invoice.
     */
    public void setInvoice(Invoice moInvoice) {
        this.moInvoice = moInvoice;
    }

    /**
     * Returns the Invoice Management class.
     * @return Invoice Management class.
     */
    public InvoiceService getInvoiceService() {
        return moInvoiceService;
    }

    /**
     * Sets the Invoice Management class.
     * @param moInvoiceService Invoice Management class.
     */
    public void setInvoiceService(InvoiceService moInvoiceService) {
        this.moInvoiceService = moInvoiceService;
    }
}
