package lapr.project.controller;

import lapr.project.model.PharmacyTransfer;
import lapr.project.model.service.PharmacyTransferService;

public class IssueDeliveryNoteController {

    /**
     * Pharmacy Transfer Management class
     */
    private PharmacyTransferService moPharmacyTransferService;

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
            PharmacyTransfer oPharmacyTransfer = this.moPharmacyTransferService.getPharmacyTransfer(intPharmacyTransferId);
            this.moPharmacyTransferService.updateStockFromTransfer(intPharmacyTransferId);
            return this.moPharmacyTransferService.sendEmailWithDeliveryNote(oPharmacyTransfer);
        } catch (Exception ex) {
            return false;
        }
    }

    public PharmacyTransferService getPharmacyTransferService() {
        return moPharmacyTransferService;
    }

    public void setPharmacyTransferService(PharmacyTransferService oPharmacyTransferService) {
        this.moPharmacyTransferService = oPharmacyTransferService;
    }
}

