package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * DeliveryRun.
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
public class DeliveryRun {

    /**
     * Delivery Run ID
     */
    private Integer mintId;

    /**
     * Delivery Run Courier
     */
    private Courier moCourier;

    /**
     * Delivery Run Vehicle
     */
    private Vehicle moVehicle;

    /**
     * Delivery Run's List of Orders
     */
    private List<Order> mlstOrder;

    /**
     * Delivery Run's status
     */
    private DeliveryStatus moStatus;

    /**
     * Empty constructor of DeliveryRun, which sets all the atributes
     * to default values
     */
    public DeliveryRun() {
        this.mintId = -1;
        this.moCourier = new Courier();
        this.moVehicle = null;
        this.mlstOrder = new ArrayList<>();
        this.moStatus = DeliveryStatus.IDLE;
    }

    /**
     * Constructor of DeliveryRun, which sets all the atributes
     * to the ones given by parameter
     *
     * @param oCourier Courier
     */
    public DeliveryRun(Courier oCourier) {
        this.mintId = -1;
        this.moCourier = oCourier;
        this.moVehicle = null;
        this.mlstOrder = new ArrayList<>();
        this.moStatus = DeliveryStatus.IDLE;
    }

    /**
     * Constructor of DeliveryRun, which sets all the atributes
     * to the ones given by parameter
     *
     * @param oCourier    Courier
     * @param lstDelivery List of Orders
     */
    public DeliveryRun(Courier oCourier, List<Order> lstDelivery) {
        this.mintId = -1;
        this.moCourier = oCourier;
        this.mlstOrder = new ArrayList<>(lstDelivery);
        this.moVehicle = null;
        this.moStatus = DeliveryStatus.IDLE;
    }

    /**
     * Constructor of DeliveryRun, which sets all the atributes
     * to the ones given by parameter
     *
     * @param oCourier    Courier
     * @param lstDelivery List of Orders
     * @param oVehicle    Vehicle
     */
    public DeliveryRun(Courier oCourier, List<Order> lstDelivery, Vehicle oVehicle) {
        this.mintId = -1;
        this.moCourier = oCourier;
        this.mlstOrder = new ArrayList<>(lstDelivery);
        this.moVehicle = oVehicle;
        this.moStatus = DeliveryStatus.IDLE;
    }

    /**
     * Returns the DeliveryRun ID
     *
     * @return DeliveryRun ID
     */
    public Integer getId() {
        return mintId;
    }

    /**
     * Sets the DeliveryRun ID to the one given by parameter
     *
     * @param intId new DeliveryRun ID
     */
    public void setId(Integer intId) {
        this.mintId = intId;
    }

    /**
     * Returns the DeliveryRun Courier
     *
     * @return DeliveryRun Courier
     */
    public Courier getCourier() {
        return moCourier;
    }

    /**
     * Sets the DeliveryRun Courier to the one given by parameter
     *
     * @param oCourier new DeliveryRun Courier
     */
    public void setCourier(Courier oCourier) {
        this.moCourier = oCourier;
    }

    /**
     * Returns the DeliveryRun Vehicle
     *
     * @return DeliveryRun Vehicle
     */
    public Vehicle getVehicle() {
        return moVehicle;
    }

    /**
     * Sets the DeliveryRun Vehicle to the one given by parameter
     *
     * @param oVehicle new DeliveryRun Vehicle
     */
    public void setVehicle(Vehicle oVehicle) {
        this.moVehicle = oVehicle;
    }

    /**
     * Returns the DeliveryRun's List of Orders
     *
     * @return DeliveryRun List of Orders
     */
    public List<Order> getOrderList() {
        return new ArrayList<>(mlstOrder);
    }

    /**
     * Sets the DeliveryRun's List of Orders to the one given by
     * parameter
     *
     * @param lstDelivery new DeliveryRun's List of Orders
     */
    public void setDeliveryList(List<Order> lstDelivery) {
        this.mlstOrder = new ArrayList<>(lstDelivery);
    }

    /**
     * Returns the DeliveryRun Status
     *
     * @return DeliveryRun Status
     */
    public DeliveryStatus getStatus() {
        return moStatus;
    }

    /**
     * Sets the DeliveryRun Status to the one given by parameter
     *
     * @param oStatus new DeliveryRun Status
     */
    public void setStatus(DeliveryStatus oStatus) {
        this.moStatus = oStatus;
    }

    /**
     * Equals method, which compares two 'DeliveryRun's
     *
     * @param o Other Object
     * @return True if both 'DeliveryRun's are equal, false if otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryRun that = (DeliveryRun) o;
        return Objects.equals(mintId, that.mintId);
    }

    /**
     * HashCode
     *
     * @return Integer hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(mintId);
    }

    /**
     * ToString method, which formats a string to
     * print a formatted message
     *
     * @return formatted String
     */
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
