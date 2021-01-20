package lapr.project.model;

import java.util.Date;
import java.util.Objects;

public class Delivery {

    private Order moOrder;

    private Date mdtDeliveryDate;

    private String mstrNotes;

    public Delivery() {
        moOrder = null;
        mdtDeliveryDate = new Date();
        mstrNotes = "";
    }

    public Delivery(Order m_oOrder) {
        this.moOrder = m_oOrder;
        this.mdtDeliveryDate = new Date();
        this.mstrNotes = "";
    }

    public Order getOrder() {
        return moOrder;
    }

    public void setOrder(Order oOrder) {
        this.moOrder = oOrder;
    }

    public Date getDeliveryDate() {
        return (Date) mdtDeliveryDate.clone();
    }

    public void setDeliveryDate(Date dtDeliveryDate) {
        this.mdtDeliveryDate = (Date) dtDeliveryDate.clone();
    }

    public String getNotes() {
        return mstrNotes;
    }

    public void setNotes(String strNotes) {
        this.mstrNotes = strNotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Delivery delivery = (Delivery) o;
        return Objects.equals(moOrder, delivery.moOrder);
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "m_oOrder=" + moOrder +
                ", m_dtDeliveryDate=" + mdtDeliveryDate +
                ", m_strNotes='" + mstrNotes + '\'' +
                '}';
    }
}
