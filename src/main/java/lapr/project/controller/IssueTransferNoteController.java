package lapr.project.controller;

import lapr.project.model.PharmacyTransfer;
import lapr.project.model.service.PharmacyTransferService;

public class IssueTransferNoteController {

    /**
     * Pharmacy Management class
     */
    private PharmacyTransferService m_oPharmacyTransferService;

    /**
     * An empty constructor of MakeAnOrderController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public IssueTransferNoteController() {
        this.m_oPharmacyTransferService = new PharmacyTransferService();
    }

    /**
     * The method registers a new Pharmacy Transfer.
     */
    public boolean issueTransferNote(PharmacyTransfer oPharmacyTransfer) {
        try {
            return this.m_oPharmacyTransferService.sendEmailWithTransferNote(oPharmacyTransfer);
        } catch (Exception ex) {
            return false;
        }
    }
}
