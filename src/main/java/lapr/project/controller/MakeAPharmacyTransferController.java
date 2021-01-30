package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.service.*;

/**
 * Make A Pharmacy Tranfer Controller.
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
public class MakeAPharmacyTransferController {

    /**
     * Pharmacy Management class.
     */
    private PharmacyService moPharmacyService;

    /**
     * Pharmacy Transfer Management class.
     */
    private PharmacyTransferService moPharmacyTransferService;

    /**
     * Controller to Issue a Transfer Note.
     */
    private IssueTransferNoteController moIssueTransferNoteController;

    /**
     * Pharmacy.
     */
    private Pharmacy moPharmacy;

    /**
     * An empty constructor of MakeAPharmacyTransferController.
     */
    public MakeAPharmacyTransferController() {
        this.moPharmacyService = new PharmacyService();
        this.moPharmacyTransferService = new PharmacyTransferService();
        this.moIssueTransferNoteController = new IssueTransferNoteController();
    }

    /**
     * The method registers a new Pharmacy Transfer.
     * @param oOrder Order.
     * @param oProduct Product.
     * @param intQuantity Quantity.
     * @return true if everything works out, false if it doesn't.
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

    /**
     * The method sets the Pharmacy.
     * @param strPharmacyEmail Pharmacy's Email.
     */
    public void setPharmacy(String strPharmacyEmail){
        this.moPharmacy = this.moPharmacyService.getPharmacy(strPharmacyEmail);
    }

    /**
     * The method gets the Pharmacy management class.
     * @return The Pharmacy management class.
     */
    public PharmacyService getPharmacyService() {
        return moPharmacyService;
    }

    /**
     * The method sets the Pharmacy management class.
     * @param oPharmacyService The Pharmacy management class.
     */
    public void setPharmacyService(PharmacyService oPharmacyService) {
        this.moPharmacyService = oPharmacyService;
    }

    /**
     * The method gets the Pharmacy management class.
     * @return The Pharmacy management class.
     */
    public PharmacyTransferService getPharmacyTransferService() {
        return moPharmacyTransferService;
    }

    /**
     * The method sets the Pharmacy Transfer management class.
     * @param oPharmacyTransferService The Pharmacy Transfer management class.
     */
    public void setPharmacyTransferService(PharmacyTransferService oPharmacyTransferService) {
        this.moPharmacyTransferService = oPharmacyTransferService;
    }

    /**
     * The method gets the Controller for a Issuing Transfer Note.
     * @return The Controller for a Issuing Transfer Note.
     */
    public IssueTransferNoteController getIssueTransferNoteController() {
        return moIssueTransferNoteController;
    }

    /**
     * The method sets the Controller for a Issuing Transfer Note.
     * @param oIssueTransferNoteController The Controller for a Issuing Transfer Note.
     */
    public void setIssueTransferNoteController(IssueTransferNoteController oIssueTransferNoteController) {
        this.moIssueTransferNoteController = oIssueTransferNoteController;
    }
}
