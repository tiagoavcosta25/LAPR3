package lapr.project.model.service;

import lapr.project.data.ProductDB;
import lapr.project.model.Product;

import java.util.List;

public class ProductService {

    private ProductDB moProductDB;

    public ProductService() {
        this.moProductDB = new ProductDB();
    }

    public boolean registerProduct(String strName, String strDescription, Double fltUnitaryPrice, Double fltUnitaryWeight) {
        if(validateInput(strName, strDescription, fltUnitaryPrice, fltUnitaryWeight))
            return moProductDB.addProductToDB(new Product(strName, strDescription, fltUnitaryPrice, fltUnitaryWeight));
        return false;
    }

    public boolean updateProduct(int intId, String strName, String strDescription, Double fltUnitaryPrice, Double fltUnitaryWeight) {
        if(validateInputWithId(intId, strName, strDescription, fltUnitaryPrice, fltUnitaryWeight))
            return moProductDB.updateProductFromDB(intId, strName, strDescription, fltUnitaryPrice, fltUnitaryWeight);
        return false;
    }

    public boolean removeProduct(int intId) {
        if(validateId(intId))
            return moProductDB.removeProductFromDB(intId);
        return false;
    }

    public Product getProduct(int intId) {
        if(validateId(intId))
            return moProductDB.getProductFromBD(intId);
        return null;
    }

    public boolean validateInputWithId(int intId, String strName, String strDescription,
                                       Double fltUnitaryPrice, Double fltUnitaryWeight) {
        return validateInput(strName, strDescription, fltUnitaryPrice, fltUnitaryWeight) && validateId(intId);
    }

    public boolean validateInput(String strName, String strDescription, Double fltUnitaryPrice, Double fltUnitaryWeight) {
        return (!("".equals(strName) || "".equals(strDescription) || fltUnitaryPrice <= 0 || fltUnitaryWeight <= 0));
    }

    public boolean validateId(int intId) {
        return (intId > 0);
    }

    public List<Product> getProducts() {
        return this.moProductDB.getProducts();
    }

    public List<Product> getAvailableProducts(int intPharmacyId) {
        return this.moProductDB.getAvailableProducts(intPharmacyId);
    }
}
