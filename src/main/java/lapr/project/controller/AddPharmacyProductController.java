package lapr.project.controller;

import lapr.project.model.Pharmacy;
import lapr.project.model.Product;
import lapr.project.model.service.PharmacyService;
import lapr.project.model.service.ProductService;
import java.util.List;

/**
 * Add Pharmacy Product Controller.
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
public class AddPharmacyProductController {

    /**
     * Pharmacy class instance.
     */
    private Pharmacy moPharmacy;

    /**
     * Product Management class.
     */
    private ProductService moProductService;

    /**
     * Pharmacy Management class.
     */
    private PharmacyService moPharmacyService;

    /**
     * Product that needs to be added to the pharmacy.
     */
    private Product moProduct;

    /**
     * Stock that needs to be added to the pharmacy.
     */
    private Integer mintStock;


    /**
     * Contructor Class For AddPharmacyProductController.
     */
    public AddPharmacyProductController() {
        this.moProductService = new ProductService();
        this.moPharmacyService = new PharmacyService();
    }

    /**
     * Main method to validates and gets the closest pharmacy with stock to the pharmacy that received the order.
     * @param strPharmacyEmail Pharmacy Email.
     * @param oProduct Product.
     * @param intStock Stock that needs to be added to the pharmacy.
     * @return true if everything works out, false if it doesn't.
     */
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
     * @return List of Products.
     */
    public List<Product> getProducts() {
        return this.moProductService.getProducts();
    }

    /**
     * The method that sets the product.
     * @param oProduct Product.
     */
    public void setProduct(Product oProduct) {
        this.moProduct = oProduct;
    }

    /**
     * The method that sets the Pharmacy.
     * @param oPharmacy Pharmacy.
     */
    public void setPharmacy(Pharmacy oPharmacy) {
        this.moPharmacy = oPharmacy;
    }

    /**
     * The method that sets the stock.
     * @param intStock Stock.
     */
    public void setStock(Integer intStock) {
        this.mintStock = intStock;
    }

    /**
     * Returns the Product Management Class.
     * @return The Product Management Class.
     */
    public ProductService getProductService() {
        return moProductService;
    }

    /**
     * Sets the Product Management Class.
     * @param oProductService The Product Management Class.
     */
    public void setProductService(ProductService oProductService) {
        this.moProductService = oProductService;
    }

    /**
     * Returns the Pharmacy Management Class.
     * @return The Pharmacy Management Class.
     */
    public PharmacyService getPharmacyService() {
        return moPharmacyService;
    }

    /**
     * Sets the Pharmacy Management Class.
     * @param oPharmacyService The Pharmacy Management Class.
     */
    public void setPharmacyService(PharmacyService oPharmacyService) {
        this.moPharmacyService = oPharmacyService;
    }
}
