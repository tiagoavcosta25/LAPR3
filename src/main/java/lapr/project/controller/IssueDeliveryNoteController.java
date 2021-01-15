package lapr.project.controller;

import lapr.project.model.PharmacyTransfer;
import lapr.project.model.service.PharmacyTransferService;

public class IssueDeliveryNoteController {

    /**
     * Pharmacy Transfer Management class
     */
    private PharmacyTransferService m_oPharmacyTransferService;

    /**
     * Pharmacy Transfer instance
     */
    private PharmacyTransfer m_oPharmacyTransfer;

    /**
     * An empty constructor of IssueTransferNoteController.
     */
    public IssueDeliveryNoteController() {
        this.m_oPharmacyTransferService = new PharmacyTransferService();
    }


    /**
     * The method issues a Delivery note.
     */
    public boolean issueDeliveryNote(int intPharmacyTransferId) {
        try {
            this.m_oPharmacyTransfer = this.m_oPharmacyTransferService.getPharmacyTransfer(intPharmacyTransferId);
            this.m_oPharmacyTransferService.updateStockFromTransfer(intPharmacyTransferId);
            return this.m_oPharmacyTransferService.sendEmailWithDeliveryNote(this.m_oPharmacyTransfer);
        } catch (Exception ex) {
            return false;
        }
    }
}
