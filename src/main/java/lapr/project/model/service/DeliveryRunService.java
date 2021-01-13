package lapr.project.model.service;

import javafx.util.Pair;
import lapr.project.data.DeliveryDB;
import lapr.project.data.DeliveryRunDB;
import lapr.project.model.*;

import java.util.LinkedList;
import java.util.List;

public class DeliveryRunService {

    private DeliveryRunDB m_oDeliveryRunDB;
    private DeliveryDB m_oDeliveryDB;

    public DeliveryRunService() {
        m_oDeliveryRunDB = new DeliveryRunDB();
        m_oDeliveryDB = new DeliveryDB();
    }

    public DeliveryRun newDeliveryRun(Courier oCourier, List<Order> oLstOrder) {
        return new DeliveryRun(oCourier, oLstOrder);
    }

    public boolean addNewDeliveryRun(DeliveryRun oDeliveryRun) {
        return m_oDeliveryRunDB.addNewDeliveryRun(oDeliveryRun);
    }

    public List<Address> getAddressesByDeliveryRunId(String email) {
        return m_oDeliveryRunDB.getAddressesByDeliveryRunId(email);
    }

    public Pair<LinkedList<Address>, Double> calculateMostEfficientPath(Address a, Address b, List<Address> list) {
        return m_oDeliveryDB.calculateMostEfficientPath(a, b, list);
    }

    public float getMaxPayload(String email) {
        return m_oDeliveryDB.getMaxPayload(email);
    }


}
