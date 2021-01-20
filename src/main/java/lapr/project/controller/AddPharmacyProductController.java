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
    private Pharmacy moPharmacy;

    /**
     * Pharmacy's Manager Management class
     */
    private ProductDB moProductDB;

    /**
     * Pharmacy Management class
     */
    private PharmacyDB moPharmacyDB;

    /**
     * Product
     */
    private Product moProduct;

    /**
     * Product
     */
    private Integer mintStock;


    public AddPharmacyProductController() {
        this.moProductDB = new ProductDB();
        this.moPharmacyDB = new PharmacyDB();
    }

    public boolean addPharmacyProduct(Product oProduct, Integer intStock) {
        try {
            if (oProduct == null || intStock < 0) throw new Exception();
            this.moProduct = oProduct;
            this.mintStock = intStock;
            return true;
        } catch (Exception e) {
            this.moPharmacy = null;
            return false;
        }
    }

    /**
     * The method that adds stock to a pharmacy.
     */
    public boolean registerPharmacyProduct() {
        this.moPharmacy = moPharmacyDB.getPharmacyByManagerEmail(ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail());
        return this.moPharmacyDB.registerPharmacyProduct(moPharmacy, moProduct, mintStock);
    }

    /**
     * The method that returns every product in the database.
     */
    public List<Product> getProducts() {
        return this.moProductDB.getProducts();
    }

    /**
     * The method that sets the product.
     */
    public void setProduct(Product oProduct) {
        this.moProduct = oProduct;
    }

    /**
     * The method that sets the stock.
     */
    public void setStock(Integer intStock) {
        this.mintStock = intStock;
    }
}
