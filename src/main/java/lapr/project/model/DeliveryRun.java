package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeliveryRun {

    private Integer m_intId;

    private Courier m_oCourier;

    private Scooter m_oScooter;

    private List<Address> m_lstDelivery;

    private DeliveryStatus m_oStatus;

    public DeliveryRun() {
        this.m_intId = -1;
        this.m_oCourier = new Courier();
        this.m_oScooter = null;
        this.m_lstDelivery = new ArrayList<>();
        this.m_oStatus = DeliveryStatus.IDLE;
    }

    public DeliveryRun(Courier m_oCourier) {
        this.m_intId = -1;
        this.m_oCourier = m_oCourier;
        this.m_oScooter = null;
        this.m_lstDelivery = new ArrayList<>();
        this.m_oStatus = DeliveryStatus.IDLE;
    }

    public DeliveryRun(Courier m_oCourier, List<Address> m_lstDelivery) {
        this.m_intId = -1;
        this.m_oCourier = m_oCourier;
        this.m_lstDelivery = m_lstDelivery;
        this.m_oScooter = null;
        this.m_oStatus = DeliveryStatus.IDLE;
    }

    public Integer getId() {
        return m_intId;
    }

    public void setId(Integer intId) {
        this.m_intId = intId;
    }

    public Courier getCourier() {
        return m_oCourier;
    }

    public void setCourier(Courier oCourier) {
        this.m_oCourier = oCourier;
    }

    public Scooter getScooter() {
        return m_oScooter;
    }

    public void setScooter(Scooter oScooter) {
        this.m_oScooter = oScooter;
    }

    public List<Address> getDeliveryList() {
        return m_lstDelivery;
    }

    public void setDeliveryList(List<Address> m_lstDelivery) {
        this.m_lstDelivery = m_lstDelivery;
    }

    public DeliveryStatus getStatus() {
        return m_oStatus;
    }

    public void setStatus(DeliveryStatus oStatus) {
        this.m_oStatus = oStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryRun that = (DeliveryRun) o;
        return Objects.equals(m_intId, that.m_intId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_intId);
    }

    @Override
    public String toString() {
        return "DeliveryRun{" +
                "m_intId=" + m_intId +
                ", m_oCourier=" + m_oCourier +
                ", m_oScooter=" + m_oScooter +
                ", m_lstDelivery=" + m_lstDelivery +
                ", m_oStatus=" + m_oStatus +
                '}';
    }
}
