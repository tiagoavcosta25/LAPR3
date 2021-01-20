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
    }

    /**
     * The method registers a new Pharmacy Transfer.
     */
    public boolean getStockFromAnotherPharamacy(Order oOrder, Product oProduct, Integer intQuantity) {
        try {
            this.moPharmacy = moPharmacyService.getClosestPharmacyWithStock(oOrder, oProduct, intQuantity);
            this.moPharmacyTransfer = moPharmacyTransferService.newPharmacyTransfer(oOrder, oProduct, intQuantity, this.moPharmacy);
            return this.moPharmacyTransferService.registerPharmacyTransfer(this.moPharmacyTransfer);
        } catch (Exception ex) {
            return false;
        }
    }
}
