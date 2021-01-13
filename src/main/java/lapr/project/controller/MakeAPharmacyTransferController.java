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
     * Order
     */
    private Order m_oOrder;

    /**
     * Product
     */
    private Product m_oProduct;

    /**
     * Quantity
     */
    private Integer m_intQuantity;

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
    public MakeAPharmacyTransferController(Order oOrder, Product oProduct, Integer intQuantity) {
        this.m_oPharmacyService = new PharmacyService();
        this.m_oPharmacyTransferService = new PharmacyTransferService();
        this.m_oOrder = oOrder;
        this.m_oProduct = oProduct;
        this.m_intQuantity = intQuantity;
    }

    /**
     * The method registers a new Pharmacy Transfer.
     */
    public boolean getStockFromAnotherPharamacy() {
        try {
            this.m_oPharmacy = m_oPharmacyService.getClosestPharmacyWithStock(m_oOrder, m_oProduct, m_intQuantity);
            this.m_oPharmacyTransfer = m_oPharmacyTransferService.newPharmacyTransfer(this.m_oOrder, this.m_oProduct, this.m_intQuantity, this.m_oPharmacy);
            return this.m_oPharmacyTransferService.registerPharmacy(this.m_oPharmacyTransfer);
        } catch (Exception ex) {
            this.m_oOrder = null;
            return false;
        }
    }
}
