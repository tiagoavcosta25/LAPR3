package lapr.project.controller;

import lapr.project.model.UserSession;
import lapr.project.model.service.ProductService;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Update Product Controller.
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
public class UpdateProductController {
    /**
     * Logger which is used to generate warnings or information, with
     * a custom message
     */
    private static final Logger LOGGER = Logger.getLogger(UpdateProductController.class.getName());

    /**
     * Product Service
     */
    private ProductService moServ;

    /**
     * Empty constructor of UpdateProductController, which instantiates a new
     * Product Service
     */
    public UpdateProductController() {
        this.moServ = new ProductService();
    }

    /**
     * Updates a Product from the Database
     * 
     * @param strProductName    Product original name
     * @param strName           Product new name
     * @param strDescription    Product new description
     * @param dblUnitaryPrice   Product new unitary price
     * @param dblUnitaryWeight  Product new unitary weight
     * @return  True if Product was updated, false if otherwise
     */
    public boolean updateProduct(String strProductName, String strName, String strDescription, Double dblUnitaryPrice, Double dblUnitaryWeight) {
        try {
            if(ApplicationPOT.getInstance().getCurrentSession().getRole().equals(UserSession.Role.ADMIN))
                return moServ.updateProduct(strProductName, strName, strDescription, dblUnitaryPrice, dblUnitaryWeight);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "User not logged in!");
        }
        return false;
    }

    /**
     * Returns the Product Service
     *
     * @return Product Service
     */
    public ProductService getMoServ() {
        return moServ;
    }

    /**
     * Sets the Product Service to the one given by parameter
     *
     * @param moServ new Product Service
     */
    public void setMoServ(ProductService moServ) {
        this.moServ = moServ;
    }
}
