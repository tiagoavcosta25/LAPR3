package lapr.project.controller;

import lapr.project.model.Pharmacy;
import lapr.project.model.Product;
import lapr.project.model.service.PharmacyService;
import lapr.project.model.service.ProductService;

import java.util.List;

public class AddPharmacyProductController {

    /**
     * Pharmacy class instance
     */
    private Pharmacy moPharmacy;

    /**
     * Pharmacy's Manager Management class
     */
    private ProductService moProductService;

    /**
     * Pharmacy Management class
     */
    private PharmacyService moPharmacyService;

    /**
     * Product
     */
    private Product moProduct;

    /**
     * Product
     */
    private Integer mintStock;


    public AddPharmacyProductController() {
        this.moProductService = new ProductService();
        this.moPharmacyService = new PharmacyService();
    }

    public boolean addPharmacyProduct(String strPharmacyEmail, Product oProduct, Integer intStock) {
        try {
            if (oProduct == null || intStock < 0 || strPharmacyEmail.isEmpty()) throw new Exception();
            this.moPharmacy = this.moPharmacyService.getPharmacy(strPharmacyEmail);
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
        return this.moPharmacyService.registerPharmacyProduct(moPharmacy, moProduct, mintStock);
    }

    /**
     * The method that returns every product in the database.
     */
    public List<Product> getProducts() {
        return this.moProductService.getProducts();
    }

    /**
     * The method that sets the product.
     */
    public void setProduct(Product oProduct) {
        this.moProduct = oProduct;
    }

    /**
     * The method that sets the Pharmacy.
     */
    public void setPharmacy(Pharmacy oPharmacy) {
        this.moPharmacy = oPharmacy;
    }

    /**
     * The method that sets the stock.
     */
    public void setStock(Integer intStock) {
        this.mintStock = intStock;
    }
}
