package lapr.project.controller;

import lapr.project.model.PharmacyTransfer;
import lapr.project.model.service.PharmacyTransferService;

public class IssueTransferNoteController {

    /**
     * Pharmacy Management class
     */
    private PharmacyTransferService moPharmacyTransferService;

    /**
     * An empty constructor of MakeAnOrderController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public IssueTransferNoteController() {
        this.moPharmacyTransferService = new PharmacyTransferService();
    }

    /**
     * The method registers a new Pharmacy Transfer.
     */
    public boolean issueTransferNote(PharmacyTransfer oPharmacyTransfer) {
        try {
            return this.moPharmacyTransferService.sendEmailWithTransferNote(oPharmacyTransfer);
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
