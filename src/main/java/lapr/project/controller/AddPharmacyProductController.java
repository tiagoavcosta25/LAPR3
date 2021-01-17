package lapr.project.controller;

import lapr.project.model.Pharmacy;
import lapr.project.model.Product;
import lapr.project.data.PharmacyDB;
import lapr.project.data.ProductDB;

import java.util.List;

public class AddPharmacyProductController {

    /**
     * Pharmacy class instance
     */
    private Pharmacy m_oPharmacy;

    /**
     * Pharmacy's Manager Management class
     */
    private ProductDB m_oProductDB;

    /**
     * Pharmacy Management class
     */
    private PharmacyDB m_oPharmacyDB;

    /**
     * Product
     */
    private Product m_oProduct;

    /**
     * Product
     */
    private Integer m_intStock;


    public AddPharmacyProductController() {
        this.m_oProductDB = new ProductDB();
        this.m_oPharmacyDB = new PharmacyDB();
    }

    public boolean addPharmacyProduct(Product oProduct, Integer intStock) {
        try {
            if (oProduct == null || intStock < 0) throw new Exception();
            this.m_oProduct = oProduct;
            this.m_intStock = intStock;
            return true;
        } catch (Exception e) {
            this.m_oPharmacy = null;
            return false;
        }
    }

    /**
     * The method that adds stock to a pharmacy.
     */
    public boolean registerPharmacyProduct() {
        this.m_oPharmacy = m_oPharmacyDB.getPharmacyByManagerEmail(ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail());
        return this.m_oPharmacyDB.registerPharmacyProduct(m_oPharmacy, m_oProduct, m_intStock);
    }

    /**
     * The method that returns every product in the database.
     */
    public List<Product> getProducts() {
        return this.m_oProductDB.getProducts();
    }

    /**
     * The method that sets the product.
     */
    public void setProduct(Product oProduct) {
        this.m_oProduct = oProduct;
    }

    /**
     * The method that sets the stock.
     */
    public void setStock(Integer intStock) {
        this.m_intStock = intStock;
    }
}
