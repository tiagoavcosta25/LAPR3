package lapr.project.controller;

import lapr.project.model.Product;
import lapr.project.model.UserSession;
import lapr.project.model.service.ProductService;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Product Information Controller.
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
public class ProductInformationController {
    /**
     * Logger which is used to generate warnings or information, with
     * a custom message
     */
    private static final Logger LOGGER = Logger.getLogger(ProductInformationController.class.getName());

    /**
     * Product Service
     */
    private ProductService moServ;

    /**
     * Empty constructor of ProductInformationController, which instantiates a new
     * Product Service
     */
    public ProductInformationController() {
        this.moServ = new ProductService();
    }

    /**
     * Returns a Product basing on it's name, given by parameter
     *
     * @param strName   Product's name
     * @return  Product which has the same name as the one given by parameter
     */
    public Product getProduct(String strName) {
        try {
            if(ApplicationPOT.getInstance().getCurrentSession().getRole().equals(UserSession.Role.ADMIN))
                return moServ.getProduct(strName);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "User not logged in!");
        }
        return null;
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
