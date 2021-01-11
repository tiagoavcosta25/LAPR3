package lapr.project.utils;

import lapr.project.model.Product;

public class ValidationProduct {

    private ValidationProduct() {
    }

    public static Product validateInputWithId(int intId, String strName, String strDescription,
                                              float fltUnitaryPrice, float fltUnitaryWeight) {
        Product p = validateInput(strName, strDescription, fltUnitaryPrice, fltUnitaryWeight);
        if(p == null || !validateId(intId)) return null;
        p.setId(intId);
        return p;
    }

    public static Product validateInput(String strName, String strDescription, float fltUnitaryPrice, float fltUnitaryWeight) {
        if ("".equals(strName) || "".equals(strDescription) || fltUnitaryPrice <= 0 || fltUnitaryWeight <= 0) return null;
        return new Product(strName, strDescription, fltUnitaryPrice, fltUnitaryWeight);
    }

    public static boolean validateId(int intId) {
        if(intId <= 0) return false;
        return true;
    }
}
