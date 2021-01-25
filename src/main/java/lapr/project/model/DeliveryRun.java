package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeliveryRun {

    private Integer mintId;

    private Courier moCourier;

    private Vehicle moVehicle;

    private List<Order> mlstOrder;

    private DeliveryStatus moStatus;

    public DeliveryRun() {
        this.mintId = -1;
        this.moCourier = new Courier();
        this.moVehicle = null;
        this.mlstOrder = new ArrayList<>();
        this.moStatus = DeliveryStatus.IDLE;
    }

    public DeliveryRun(Courier m_oCourier) {
        this.mintId = -1;
        this.moCourier = m_oCourier;
        this.moVehicle = null;
        this.mlstOrder = new ArrayList<>();
        this.moStatus = DeliveryStatus.IDLE;
    }

    public DeliveryRun(Courier m_oCourier, List<Order> m_lstDelivery) {
        this.mintId = -1;
        this.moCourier = m_oCourier;
        this.mlstOrder = new ArrayList<>(m_lstDelivery);
        this.moVehicle = null;
        this.moStatus = DeliveryStatus.IDLE;
    }

    public DeliveryRun(Courier m_oCourier, List<Order> m_lstDelivery, Vehicle oVehicle) {
        this.mintId = -1;
        this.moCourier = m_oCourier;
        this.mlstOrder = new ArrayList<>(m_lstDelivery);
        this.moVehicle = oVehicle;
        this.moStatus = DeliveryStatus.IDLE;
    }

    public Integer getId() {
        return mintId;
    }

    public void setId(Integer intId) {
        this.mintId = intId;
    }

    public Courier getCourier() {
        return moCourier;
    }

    public void setCourier(Courier oCourier) {
        this.moCourier = oCourier;
    }

    public Vehicle getVehicle() {
        return moVehicle;
    }

    public void setVehicle(Vehicle oVehicle) {
        this.moVehicle = oVehicle;
    }

    public List<Order> getOrderList() {
        return new ArrayList<>(mlstOrder);
    }

    public void setDeliveryList(List<Order> m_lstDelivery) {
        this.mlstOrder = new ArrayList<>(m_lstDelivery);
    }

    public DeliveryStatus getStatus() {
        return moStatus;
    }

    public void setStatus(DeliveryStatus oStatus) {
        this.moStatus = oStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryRun that = (DeliveryRun) o;
        return Objects.equals(mintId, that.mintId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mintId);
    }

    @Override
    public String toString() {
        return "DeliveryRun{" +
                "m_intId=" + mintId +
                ", m_oCourier=" + moCourier +
                ", m_oVehicle=" + moVehicle +
                ", m_lstOrder=" + mlstOrder +
                ", m_oStatus=" + moStatus +
                '}';
    }
}
