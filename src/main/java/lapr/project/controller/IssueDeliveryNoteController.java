package lapr.project.controller;

import lapr.project.model.PharmacyTransfer;
import lapr.project.model.service.PharmacyTransferService;

public class IssueDeliveryNoteController {

    /**
     * Pharmacy Transfer Management class
     */
    private PharmacyTransferService moPharmacyTransferService;

    /**
     * Pharmacy Transfer instance
     */
    private PharmacyTransfer moPharmacyTransfer;

    /**
     * An empty constructor of IssueTransferNoteController.
     */
    public IssueDeliveryNoteController() {
        this.moPharmacyTransferService = new PharmacyTransferService();
    }


    /**
     * The method issues a Delivery note.
     */
    public boolean issueDeliveryNote(int intPharmacyTransferId) {
        try {
            this.moPharmacyTransfer = this.moPharmacyTransferService.getPharmacyTransfer(intPharmacyTransferId);
            this.moPharmacyTransferService.updateStockFromTransfer(intPharmacyTransferId);
            return this.moPharmacyTransferService.sendEmailWithDeliveryNote(this.moPharmacyTransfer);
        } catch (Exception ex) {
            return false;
        }
    }
}
