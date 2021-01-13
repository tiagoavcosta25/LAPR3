package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.service.*;

public class MakeAPharmacyTransferController {

    /**
     * Pharmacy Management class
     */
    private PharmacyService m_oPharmacyService;

    /**
     * Pharmacy Management class
     */
    private PharmacyTransferService m_oPharmacyTransferService;

    /**
     * Pharmacy
     */
    private Pharmacy m_oPharmacy;

    /**
     * Order
     */
    private PharmacyTransfer m_oPharmacyTransfer;

    /**
     * An empty constructor of MakeAnOrderController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public MakeAPharmacyTransferController() {
        this.m_oPharmacyService = new PharmacyService();
        this.m_oPharmacyTransferService = new PharmacyTransferService();
    }

    /**
     * The method registers a new Pharmacy Transfer.
     */
    public boolean getStockFromAnotherPharamacy(Order oOrder, Product oProduct, Integer intQuantity) {
        try {
            this.m_oPharmacy = m_oPharmacyService.getClosestPharmacyWithStock(oOrder, oProduct, intQuantity);
            this.m_oPharmacyTransfer = m_oPharmacyTransferService.newPharmacyTransfer(oOrder, oProduct, intQuantity, this.m_oPharmacy);
            return this.m_oPharmacyTransferService.registerPharmacyTransfer(this.m_oPharmacyTransfer);
        } catch (Exception ex) {
            return false;
        }
    }
}
