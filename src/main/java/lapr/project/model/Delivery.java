package lapr.project.model;

import java.util.Date;
import java.util.Objects;

public class Delivery {

    private Order m_oOrder;

    private Date m_dtDeliveryDate;

    private String m_strNotes;

    public Delivery() {
        m_oOrder = null;
        m_dtDeliveryDate = new Date();
        m_strNotes = "";
    }

    public Delivery(Order m_oOrder) {
        this.m_oOrder = m_oOrder;
        this.m_dtDeliveryDate = new Date();
        this.m_strNotes = "";
    }

    public Order getOrder() {
        return m_oOrder;
    }

    public void setOrder(Order oOrder) {
        this.m_oOrder = oOrder;
    }

    public Date getDeliveryDate() {
        return (Date) m_dtDeliveryDate.clone();
    }

    public void setDeliveryDate(Date dtDeliveryDate) {
        this.m_dtDeliveryDate = (Date) dtDeliveryDate.clone();
    }

    public String getNotes() {
        return m_strNotes;
    }

    public void setNotes(String strNotes) {
        this.m_strNotes = strNotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Delivery delivery = (Delivery) o;
        return Objects.equals(m_oOrder, delivery.m_oOrder);
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "m_oOrder=" + m_oOrder +
                ", m_dtDeliveryDate=" + m_dtDeliveryDate +
                ", m_strNotes='" + m_strNotes + '\'' +
                '}';
    }
}
