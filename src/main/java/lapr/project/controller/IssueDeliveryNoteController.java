package lapr.project.controller;

import lapr.project.model.PharmacyTransfer;
import lapr.project.model.service.PharmacyTransferService;

/**
 * Issue Delivery Note Controller.
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

    /**
     * Returns Pharmacy Transfer Service instance.
     * @return Pharmacy Transfer Service instance.
     */
    public PharmacyTransferService getPharmacyTransferService() {
        return moPharmacyTransferService;
    }

    /**
     * Modifies Pharmacy Transfer Service instance.
     * @param oPharmacyTransferService Pharmacy Transfer Service instance.
     */
    public void setPharmacyTransferService(PharmacyTransferService oPharmacyTransferService) {
        this.moPharmacyTransferService = oPharmacyTransferService;
    }
}

