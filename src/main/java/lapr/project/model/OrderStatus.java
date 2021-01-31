package lapr.project.model;

/**
 * Order Status.
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
public enum OrderStatus {

    /**
     * Order Status.
     */

    TRANFERINGPRODUCTS("Transfering Products"),
    CANCELLED("Cancelled"),
    ORDERED("Ordered"),
    DELIVERING("Delivering"),
    DELIVERED("Delivered");

    /**
     * Designation.
     */
    private String mstrDesignation;

    /**
     * Contructor.
     * @param designation designation.
     */
    OrderStatus(String designation) {
        this.mstrDesignation = designation;
    }

    /**
     * Getter for the desingation.
     * @return desingation.
     */
    public String getDesignation() {
        return mstrDesignation;
    }

    /**
     * Order Status to String Override.
     * @return Order Status Info.
     */
    @Override
    public String toString() {
        return "OrderStatus{" +
                "m_strDesignation='" + mstrDesignation + '\'' +
                '}';
    }
}
