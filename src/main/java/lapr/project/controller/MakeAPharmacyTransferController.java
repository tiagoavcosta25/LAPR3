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
            PharmacyTransfer oPharmacyTransfer = moPharmacyTransferService.newPharmacyTransfer(oOrder, oProduct, intQuantity, this.moPharmacy);
            this.moPharmacyTransferService.registerPharmacyTransfer(oPharmacyTransfer);
            return this.moIssueTransferNoteController.issueTransferNote(oPharmacyTransfer);
        } catch (Exception ex) {
            return false;
        }
    }

    public void setPharmacy(String strPharmacyEmail){
        this.moPharmacy = this.moPharmacyService.getPharmacy(strPharmacyEmail);
    }

    public PharmacyService getPharmacyService() {
        return moPharmacyService;
    }

    public void setPharmacyService(PharmacyService oPharmacyService) {
        this.moPharmacyService = oPharmacyService;
    }

    public PharmacyTransferService getPharmacyTransferService() {
        return moPharmacyTransferService;
    }

    public void setPharmacyTransferService(PharmacyTransferService oPharmacyTransferService) {
        this.moPharmacyTransferService = oPharmacyTransferService;
    }

    public IssueTransferNoteController getIssueTransferNoteController() {
        return moIssueTransferNoteController;
    }

    public void setIssueTransferNoteController(IssueTransferNoteController oIssueTransferNoteController) {
        this.moIssueTransferNoteController = oIssueTransferNoteController;
    }
}
