package lapr.project.model;

/**
 * DeliveryStatus.
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
public enum DeliveryStatus {

    /**
     * Possible Delivery Status
     */
    IDLE("Idle"), INPROGRESS("In Progress");

    /**
     * Delivery Status designation
     */
    private String mstrDesignation;

    /**
     *  Constructor of DeliveryStatus, which sets
     *  the designation to the one given by parameter
     *
     * @param designation   DeliveryStatus designation
     */
    DeliveryStatus(String designation) {
        this.mstrDesignation = designation;
    }

    /**
     * Returns the DeliveryStatus designation
     *
     * @return  DeliveryStatus designation
     */
    public String getDesignation() {
        return mstrDesignation;
    }

    /**
     * ToString method, which formats a string to
     * print a formatted message
     *
     * @return formatted String
     */
    @Override
    public String toString() {
        return "DeliveryStatus{" +
                "m_strDesignation='" + mstrDesignation + '\'' +
                '}';
    }
}
