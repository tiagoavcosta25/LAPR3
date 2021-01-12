package lapr.project.model;

import java.util.Date;

public class Delivery {

    private Order m_oOrder;

    private Date m_dtDeliveryDate;

    private String m_strNotes;

    public Delivery(Order m_oOrder, Date m_dtDeliveryDate, String m_strNotes) {
        this.m_oOrder = m_oOrder;
        this.m_dtDeliveryDate = m_dtDeliveryDate;
        this.m_strNotes = m_strNotes;
    }

    public Order getOrder() {
        return m_oOrder;
    }

    public void setOrder(Order oOrder) {
        this.m_oOrder = oOrder;
    }

    public Date getDeliveryDate() {
        return m_dtDeliveryDate;
    }

    public void setDeliveryDate(Date dtDeliveryDate) {
        this.m_dtDeliveryDate = dtDeliveryDate;
    }

    public String getNotes() {
        return m_strNotes;
    }

    public void setNotes(String strNotes) {
        this.m_strNotes = strNotes;
    }
}
