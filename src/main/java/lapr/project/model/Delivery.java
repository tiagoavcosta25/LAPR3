package lapr.project.model;

import java.util.Date;
import java.util.Objects;

/**
 * Delivery.
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
public class Delivery {

    /**
     * Order.
     */
    private Order moOrder;

    /**
     * Delivery Date.
     */
    private Date mdtDeliveryDate;

    /**
     * Delivery Notes.
     */
    private String mstrNotes;

    /**
     * Empty Constructor.
     */
    public Delivery() {
        moOrder = null;
        mdtDeliveryDate = new Date();
        mstrNotes = "";
    }

    /**
     * Constructor.
     * @param oOrder
     */
    public Delivery(Order oOrder) {
        this.moOrder = oOrder;
        this.mdtDeliveryDate = new Date();
        this.mstrNotes = "";
    }

    /**
     * Getter for the Order.
     * @return Order.
     */
    public Order getOrder() {
        return moOrder;
    }

    /**
     * Setter for the Order.
     * @param oOrder Order.
     */
    public void setOrder(Order oOrder) {
        this.moOrder = oOrder;
    }

    /**
     * Getter for the Delivery Date.
     * @return Delivery Date.
     */
    public Date getDeliveryDate() {
        return (Date) mdtDeliveryDate.clone();
    }

    /**
     * Setter for the Delivery Date.
     * @param dtDeliveryDate Delivery Date.
     */
    public void setDeliveryDate(Date dtDeliveryDate) {
        this.mdtDeliveryDate = (Date) dtDeliveryDate.clone();
    }

    /**
     * Getter for the Delivery Notes.
     * @return Delivery Notes.
     */
    public String getNotes() {
        return mstrNotes;
    }

    /**
     * Setter for the Delivery Notes.
     * @param strNotes Delivery Notes.
     */
    public void setNotes(String strNotes) {
        this.mstrNotes = strNotes;
    }

    /**
     * Equals Override.
     * @param o Object.
     * @return true if the objects are equal, false they are not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Delivery delivery = (Delivery) o;
        return Objects.equals(moOrder, delivery.moOrder);
    }

    /**
     * toString Override.
     * @return Delivery Information.
     */
    @Override
    public String toString() {
        return "Delivery{" +
                "m_oOrder=" + moOrder +
                ", m_dtDeliveryDate=" + mdtDeliveryDate +
                ", m_strNotes='" + mstrNotes + '\'' +
                '}';
    }
}
