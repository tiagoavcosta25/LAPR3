package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.registration.ClientRegistration;
import lapr.project.model.registration.OrderRegistration;
import lapr.project.model.registration.PharmacyRegistration;
import lapr.project.model.registration.ProductRegistration;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public class MakeAnOrderController {
    /**
     * Platform class instance
     */
    private Platform m_oPlatform;
    /**
     * Order class instance
     */
    private Order m_oOrder;

    /**
     * Order Management class
     */
    private OrderRegistration m_oOrderRegistration;

    /**
     * Order Management class
     */
    private ClientRegistration m_oClientRegistration;

    /**
     * Order's Client
     */
    private Client m_oClient;

    /**
     * Pharmacy Management class
     */
    private ProductRegistration m_oProductRegistration;
    /**
     * Order's Pharmacy
     */
    private Product m_oProduct;

    /**
     * Pharmacy Management class
     */
    private PharmacyRegistration m_oPharmacyRegistration;
    /**
     * Order's Pharmacy
     */
    private Pharmacy m_oPharmacy;


    /**
     * An empty constructor of MakeAnOrderController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public MakeAnOrderController() {
        this.m_oPlatform = ApplicationPOT.getInstance().getPlatform();
        this.m_oPharmacyRegistration = m_oPlatform.getPharmacyReg();
        this.m_oProductRegistration = m_oPlatform.getProductReg();
        this.m_oOrderRegistration = m_oPlatform.getOrderReg();
        this.m_oClientRegistration = m_oPlatform.getClientReg();
        this.m_oClient = m_oClientRegistration.getClientByEmail(ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail());
    }

    public void newOrder(float fltAmount, float fltTotalWeight, float fltAdditionalFee, Date dtOrderDate,
                         String strDescription, String strStatus, Double latitude, Double longitude, String streetName,
                         String doorNumber, String postalCode, String locality, String country, Map<Product, Integer> mapProducts) {
        try {
            this.m_oOrder = m_oOrderRegistration.newOrder(fltAmount, fltTotalWeight, fltAdditionalFee, dtOrderDate,
                    strDescription, strStatus, m_oClient, latitude, longitude, streetName, doorNumber, postalCode, locality, country, mapProducts);
        } catch (RuntimeException ex) {
            this.m_oOrder = null;
        }
    }

    /**
     * The method registers an order to the database.
     */
    public void registerOrder() {
        this.m_oOrderRegistration.registerOrder(m_oOrder);
    }

    /**
     * The method returns the list of available products for a pharmacy.
     */
    public List<Pharmacy> getPharmacies() {
        return this.m_oPharmacyRegistration.getPharmacies();
    }

    /**
     * The method returns the list of pharmacies.
     */
    public List<Product> getAvailableProducts(Pharmacy oPharmacy) {
        try {
            this.m_oPharmacy = oPharmacy;
            return this.m_oProductRegistration.getAvailableProducts(oPharmacy.getId());
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
