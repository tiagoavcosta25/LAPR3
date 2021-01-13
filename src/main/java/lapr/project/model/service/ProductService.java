package lapr.project.model.service;

import lapr.project.data.ProductDB;
import lapr.project.model.Product;

public class ProductService {

    private ProductDB pDB;

    public ProductService() {
        this.pDB = new ProductDB();
    }

    public boolean registerProduct(String strName, String strDescription, float fltUnitaryPrice, float fltUnitaryWeight) {
        if(validateInput(strName, strDescription, fltUnitaryPrice, fltUnitaryWeight))
            return pDB.addProductToDB(new Product(strName, strDescription, fltUnitaryPrice, fltUnitaryWeight));
        return false;
    }

    public boolean updateProduct(int intId, String strName, String strDescription, float fltUnitaryPrice, float fltUnitaryWeight) {
        if(validateInputWithId(intId, strName, strDescription, fltUnitaryPrice, fltUnitaryWeight))
            return pDB.updateProductFromDB(intId, strName, strDescription, fltUnitaryPrice, fltUnitaryWeight);
        return false;
    }

    public boolean removeProduct(int intId) {
        if(validateId(intId))
            return pDB.removeProductFromDB(intId);
        return false;
    }

    public Product getProduct(int intId) {
        if(validateId(intId))
            return pDB.getProductFromBD(intId);
        return null;
    }

        public boolean validateInputWithId(int intId, String strName, String strDescription,
                                       float fltUnitaryPrice, float fltUnitaryWeight) {
        return validateInput(strName, strDescription, fltUnitaryPrice, fltUnitaryWeight) && validateId(intId);
    }

    public boolean validateInput(String strName, String strDescription, float fltUnitaryPrice, float fltUnitaryWeight) {
        return ("".equals(strName) || "".equals(strDescription) || fltUnitaryPrice <= 0 || fltUnitaryWeight <= 0);
    }

    public boolean validateId(int intId) {
        return (intId <= 0);
    }
}
