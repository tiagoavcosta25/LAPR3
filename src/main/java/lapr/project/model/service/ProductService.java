package lapr.project.model.service;

import lapr.project.data.ProductDB;
import lapr.project.model.Product;
import lapr.project.utils.WriteFile;

import java.util.List;

/**
 * Product Service.
 * <p>
 * Group: Team Lisa [G-021]
 * ______________________________________________________
 *
 * @author António Barbosa <1190404@isep.ipp.pt>
 * @author Ernesto Rodrigues <1190560@isep.ipp.pt>
 * @author Jessica Alves <1190682@isep.ipp.pt>
 * @author Pedro Santos <1190967@isep.ipp.pt>
 * @author Rodrigo Costa <1191014@isep.ipp.pt>
 * @author Tiago Costa <1191460@isep.ipp.pt>
 */
public class ProductService {
    /**
     * Product Database
     */
    private ProductDB moProductDB;

    /**
     * Empty constructor of ProductService, which instantiates a new
     * ProductService
     */
    public ProductService() {
        this.moProductDB = new ProductDB();
    }

    /**
     * Returns the Product Database
     *
     * @return Product Database
     */
    public ProductDB getProductDB() {
        return moProductDB;
    }

    /**
     * Sets the Product Database to the one given by parameter
     *
     * @param oProductDB new Product Database
     */
    public void setProductDB(ProductDB oProductDB) {
        this.moProductDB = oProductDB;
    }

    /**
     * Registers a Product into the Database
     *
     * @param strName          Product name
     * @param strDescription   Product description
     * @param dblUnitaryPrice  Product unitary price
     * @param dblUnitaryWeight Product unitary weight
     * @return True if the Product was registered, false if otherwise
     */
    public boolean registerProduct(String strName, String strDescription, Double dblUnitaryPrice, Double dblUnitaryWeight) {
        if (validateInput(strName, strDescription, dblUnitaryPrice, dblUnitaryWeight) &&
                moProductDB.addProductToDB(new Product(strName, strDescription, dblUnitaryPrice, dblUnitaryWeight))) {
            String body = String.format("Product Information:\n\n-Name: %s\n-Description: %s\n-Unitary Price: %.2f€\n" +
                    "-Unitary Weight: %.2fkg", strName, strDescription, dblUnitaryPrice, dblUnitaryWeight);
            return WriteFile.write("ProductRegistration_" + strName, body);
        } else return false;
    }

    /**
     * Updates a Product from the Database
     *
     * @param strProductName   Product original name
     * @param strName          Product new name
     * @param strDescription   Product new description
     * @param dblUnitaryPrice  Product new unitary price
     * @param dblUnitaryWeight Product new unitary weight
     * @return True if Product was updated, false if otherwise
     */
    public boolean updateProduct(String strProductName, String strName, String strDescription, Double dblUnitaryPrice, Double dblUnitaryWeight) {
        if (validateInput(strName, strDescription, dblUnitaryPrice, dblUnitaryWeight) &&
                moProductDB.updateProductFromDB(strProductName, strName, strDescription, dblUnitaryPrice, dblUnitaryWeight)) {
            String body = String.format("Product Update Log\n\nProduct Original Name: %s\n\n" +
                    "Product New Info:\n-Updated Name: %s\n-Updated Description: %s\n-Updated Unitary Price: %.2f€\n-Updated " +
                    "Unitary Weight: %.2fkg", strProductName, strName, strDescription, dblUnitaryPrice, dblUnitaryWeight);
            WriteFile.write("UpdateProduct_" + strProductName, body);
        } else return false;
        return true;
    }

    /**
     * Removes a Product from the Database
     *
     * @param strName Product's name
     * @return True if the Product was removed, false if otherwise
     */
    public boolean removeProduct(String strName) {
        if (moProductDB.removeProductFromDB(strName)) {
            String body = String.format("The product with the corresponding name: '%s' has been removed from our database!",
                    strName);
            return WriteFile.write("RemoveProduct_" + strName, body);
        } else return false;
    }

    /**
     * Returns a Product basing on it's name, given by parameter
     *
     * @param strName Product's name
     * @return Product which has the same name as the one given by parameter
     */
    public Product getProduct(String strName) {
        Product oProduct = moProductDB.getProductFromBD(strName);
        if (oProduct != null) {
            String body = String.format("Product Information\n\n-Id: %d\n-Name: %s\n-Description: %s\n-Unitary Price: %.2f€\n" +
                            "-Unitary Weight: %.2fkg", oProduct.getId(), oProduct.getName(), oProduct.getDescription(), oProduct.getUnitaryPrice(),
                    oProduct.getUnitaryWeight());
            WriteFile.write("ProductInformation_" + strName, body);
            return oProduct;
        } else return null;
    }

    /**
     * Validates the Input
     *
     * @param strName          Product name
     * @param strDescription   Product description
     * @param dblUnitaryPrice  Product unitary price
     * @param dblUnitaryWeight Product unitary weight
     * @return True if the Input is valid, False if otherwise
     */
    public boolean validateInput(String strName, String strDescription, Double dblUnitaryPrice, Double dblUnitaryWeight) {
        return (!("".equals(strName) || "".equals(strDescription) || dblUnitaryPrice <= 0 || dblUnitaryWeight <= 0));
    }

    /**
     * Validates the Product ID
     *
     * @param intId Product ID
     * @return True if Product ID is valid, False if otherwise
     */
    public boolean validateId(int intId) {
        return (intId > 0);
    }

    /**
     * Returns the List of Products in the Database
     *
     * @return List of Products in the Database
     */
    public List<Product> getProducts() {
        return this.moProductDB.getProducts();
    }

    /**
     * Returns the List of Available Products in the Database regarding a certain Pharmacy
     *
     * @param intPharmacyId Pharmacy ID
     * @return List of Products regarding a certain Pharmacy
     */
    public List<Product> getAvailableProducts(int intPharmacyId) {
        return this.moProductDB.getAvailableProducts(intPharmacyId);
    }
}
