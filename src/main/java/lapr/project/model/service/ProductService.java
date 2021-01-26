package lapr.project.model.service;

import lapr.project.data.ProductDB;
import lapr.project.model.Product;
import lapr.project.utils.WriteFile;

import java.util.List;

public class ProductService {

    private ProductDB moProductDB;

    public ProductDB getProductDB() {
        return moProductDB;
    }

    public void setProductDB(ProductDB oProductDB) {
        this.moProductDB = oProductDB;
    }

    public ProductService() {
        this.moProductDB = new ProductDB();
    }

    public boolean registerProduct(String strName, String strDescription, Double dblUnitaryPrice, Double dblUnitaryWeight) {
        if (validateInput(strName, strDescription, dblUnitaryPrice, dblUnitaryWeight) &&
                moProductDB.addProductToDB(new Product(strName, strDescription, dblUnitaryPrice, dblUnitaryWeight))) {
            String body = String.format("Product Information:\n\n-Name: %s\n-Description: %s\n-Unitary Price: %.2f€\n" +
                    "-Unitary Weight: %.2fkg", strName, strDescription, dblUnitaryPrice, dblUnitaryWeight);
            return WriteFile.write("ProductRegistration_" + strName, body);
        } else return false;
    }

    public boolean updateProduct(String strProductName, String strName, String strDescription, Double dblUnitaryPrice, Double dblUnitaryWeight) {
        if (validateInput(strName, strDescription, dblUnitaryPrice, dblUnitaryWeight) &&
                moProductDB.updateProductFromDB(strProductName, strName, strDescription, dblUnitaryPrice, dblUnitaryWeight)) {
            String body = String.format("Product Update Log\n\nProduct Original Name: %s\n\n" +
                    "Product New Info:\n-Updated Name: %s\n-Updated Description: %s\n-Updated Unitary Price: %.2f€\n-Updated " +
                    "Unitary Weight: %.2fkg",strProductName,strName,strDescription,dblUnitaryPrice,dblUnitaryWeight);
            WriteFile.write("UpdateProduct_" + strProductName,body);
        } else return false;
        return false;
    }

    public boolean removeProduct(String strName) {
        if (moProductDB.removeProductFromDB(strName)) {
            String body = String.format("The product with the corresponding name: '%s' has been removed from our database!",
                    strName);
            return WriteFile.write("RemoveProduct_" + strName, body);
        } else return false;
    }

    public Product getProduct(String strName) {
        Product oProduct = moProductDB.getProductFromBD(strName);
        if (oProduct != null) {
            String body = String.format("Product Information\n\n-Id: %d\n-Name: %s\n-Description: %s\n-Unitary Price: %.2f€\n" +
                    "-Unitary Weight: %.2fkg",oProduct.getId(),oProduct.getName(),oProduct.getDescription(),oProduct.getUnitaryPrice(),
                    oProduct.getUnitaryWeight());
            WriteFile.write("ProductInformation_" + strName,body);
            return oProduct;
        }else return null;
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
