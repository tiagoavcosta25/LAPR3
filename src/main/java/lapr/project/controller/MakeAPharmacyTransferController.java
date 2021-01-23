package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.service.*;

public class MakeAPharmacyTransferController {

    /**
     * Pharmacy Management class
     */
    private PharmacyService moPharmacyService;

    /**
     * Pharmacy Management class
     */
    private PharmacyTransferService moPharmacyTransferService;

    /**
     * Controller to Issue a Transfer Note.
     */
    private IssueTransferNoteController moIssueTransferNoteController;

    /**
     * Pharmacy
     */
    private Pharmacy moPharmacy;

    /**
     * Order
     */
    private PharmacyTransfer moPharmacyTransfer;

    /**
     * An empty constructor of MakeAnOrderController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public MakeAPharmacyTransferController() {
        this.moPharmacyService = new PharmacyService();
        this.moPharmacyTransferService = new PharmacyTransferService();
        this.moIssueTransferNoteController = new IssueTransferNoteController();
    }

    /**
     * The method registers a new Pharmacy Transfer.
     */
    public boolean getStockFromAnotherPharamacy(Order oOrder, Product oProduct, Integer intQuantity) {
        try {
            this.moPharmacy = moPharmacyService.getClosestPharmacyWithStock(oOrder, oProduct, intQuantity);
            this.moPharmacyTransfer = moPharmacyTransferService.newPharmacyTransfer(oOrder, oProduct, intQuantity, this.moPharmacy);
            this.moPharmacyTransferService.registerPharmacyTransfer(this.moPharmacyTransfer);
            return this.moIssueTransferNoteController.issueTransferNote(this.moPharmacyTransfer);
        } catch (Exception ex) {
            return false;
        }
    }

    public void setPharmacy(String strPharmacyEmail){
        this.moPharmacy = this.moPharmacyService.getPharmacy(strPharmacyEmail);
    }
}
