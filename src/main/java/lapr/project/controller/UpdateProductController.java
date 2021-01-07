package lapr.project.controller;

import lapr.project.model.Platform;
import lapr.project.model.Product;
import lapr.project.model.registration.ProductRegistration;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class UpdateProductController {

    private final ApplicationPOT app;
    private final Platform plat;
    private final ProductRegistration pr;

    public UpdateProductController() {
        this.app = ApplicationPOT.getInstance();
        this.plat = app.getPlatform();
        this.pr = plat.getProductReg();
    }

    private boolean verifyString(String str) {
        return str != null && !str.equals("");
    }

    private boolean verifyPositiveDouble(String doub) {
        try {
            return doub != null && !doub.equals("") && doub.matches("^([0-9]+(?:[.,][0-9]+)?)$") && parseDouble(doub) > 0;
        } catch (NumberFormatException e) {
            String newDoub = doub.replace(",", ".");
            return newDoub != null && !newDoub.equals("") && newDoub.matches("^([0-9]+(?:[.,][0-9]+)?)$") && parseDouble(newDoub) > 0;
        }
    }

    public boolean verifyProductId(String productId) {
        return productId != null && !productId.equals("") && productId.matches("^[0-9]*$") && parseInt(productId) > 0;
    }

    public boolean verifyProductName(String productName) {
        return verifyString(productName);
    }

    public boolean verifyProductDescription(String productDescription) {
        return verifyString(productDescription);
    }

    public boolean verifyProductUnitaryPrice(String unitaryPrice) {
        return verifyPositiveDouble(unitaryPrice);
    }

    public boolean verifyProductUnitaryWeight(String unitaryWeight) {
        return verifyPositiveDouble(unitaryWeight);
    }

    public boolean updateProduct(int id, String name, String description, float unitaryPrice, float unitaryWeight) {
        return pr.updateProductFromDB(id, name, description, unitaryPrice, unitaryWeight);
    }
}
