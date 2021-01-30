package lapr.project.controller;

import lapr.project.model.PharmacyTransfer;
import lapr.project.model.service.PharmacyTransferService;

/**
 * Issue transfer Note Controller.
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
public class IssueTransferNoteController {

    /**
     * Pharmacy Transfer Management class.
     */
    private PharmacyTransferService moPharmacyTransferService;

    /**
     * An empty constructor of IssueTransferNoteController.
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

    /**
     * Gets the Pharmacy Transfer Management Class.
     * @return The Pharmacy Transfer Management Class.
     */
    public PharmacyTransferService getPharmacyTransferService() {
        return moPharmacyTransferService;
    }

    /**
     * Sets the Pharmacy Transfer Management Class.
     * @param oPharmacyTransferService The Pharmacy Transfer Management Class.
     */
    public void setPharmacyTransferService(PharmacyTransferService oPharmacyTransferService) {
        this.moPharmacyTransferService = oPharmacyTransferService;
    }
}
