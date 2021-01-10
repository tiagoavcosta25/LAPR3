package lapr.project.controller;

import lapr.project.model.Pharmacy;
import lapr.project.model.PharmacyManager;
import lapr.project.model.Platform;
import lapr.project.model.Product;
import lapr.project.data.registration.PharmacyRegistration;
import lapr.project.data.registration.ProductRegistration;

public class AddPharmacyProductController {
    /**
     * Platform class instance
     */
    private Platform m_oPlatform;
    /**
     * Pharmacy class instance
     */
    private Pharmacy m_oPharmacy;

    /**
     * Pharmacy's Manager Management class
     */
    private ProductRegistration m_oProductRegistration;

    /**
     * Pharmacy Management class
     */
    private PharmacyRegistration m_oPharmacyRegistration;

    /**
     * Pharmacy's Manager
     */
    private PharmacyManager m_oPharmacyManager;

    /**
     * Product
     */
    private Product m_oProduct;

    /**
     * Product
     */
    private Integer m_intStock;


    /**
     * An empty constructor of MakeAnOrderController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public AddPharmacyProductController() {
        this.m_oPlatform = ApplicationPOT.getInstance().getPlatform();
        this.m_oProductRegistration = m_oPlatform.getProductReg();
        this.m_oPharmacyRegistration = m_oPlatform.getPharmacyReg();
        this.m_oPharmacy = m_oPharmacyRegistration.getPharmacyByManagerEmail(ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail());
    }

    public void addPharmacyProduct(Product oProduct, Integer intStock) {
        try {
            this.m_oProduct = oProduct;
            this.m_intStock = intStock;
        } catch (Exception e) {
            this.m_oPharmacy = null;
        }
    }

    /**
     * The method that adds stock to a pharmacy.
     */
    public void registerPharmacyProduct() {
        this.m_oPharmacyRegistration.registerPharmacyProduct(m_oPharmacy, m_oProduct, m_intStock);
    }

    /**
     * The method that returns every product in the database.
     */
    public void getProducts() {
        this.m_oPharmacyRegistration.registerPharmacy(m_oPharmacy);
    }
}
