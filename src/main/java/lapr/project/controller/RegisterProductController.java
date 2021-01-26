package lapr.project.controller;

import lapr.project.model.UserSession;
import lapr.project.model.service.ProductService;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Register Product Controller.
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
public class RegisterProductController {
    /**
     * Logger which is used to generate warnings or information, with
     * a custom message
     */
    private static final Logger LOGGER = Logger.getLogger(RegisterProductController.class.getName());

    /**
     * Product Service
     */
    private ProductService moServ;

    /**
     * Empty constructor of RegisterProductController, which instantiates a new
     * Product Service
     */
    public RegisterProductController() {
        this.moServ = new ProductService();
    }

    /**
     * Registers a Product into the Database
     *
     * @param strName          Product name
     * @param strDescription   Product Description
     * @param fltUnitaryPrice  Unitary Price
     * @param fltUnitaryWeight Unitary Weight
     * @return True if Product was registered, false if otherwise
     */
    public boolean registerProduct(String strName, String strDescription, Double fltUnitaryPrice, Double fltUnitaryWeight) {
        try {
            if (ApplicationPOT.getInstance().getCurrentSession().getRole().equals(UserSession.Role.ADMIN))
                return moServ.registerProduct(strName, strDescription, fltUnitaryPrice, fltUnitaryWeight);
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
