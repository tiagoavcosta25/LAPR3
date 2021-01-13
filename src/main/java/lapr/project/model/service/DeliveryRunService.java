package lapr.project.model.service;

import lapr.project.data.DeliveryRunDB;
import lapr.project.model.*;
import java.util.List;

public class DeliveryRunService {

    private DeliveryRunDB m_oDeliveryRunDB;

    public DeliveryRunService() {
        m_oDeliveryRunDB = new DeliveryRunDB();

    }

    public DeliveryRun newDeliveryRun(Courier oCourier, List<Order> oLstOrder) {
        return new DeliveryRun(oCourier,oLstOrder);
    }

    public boolean addNewDeliveryRun(DeliveryRun oDeliveryRun) {
        return m_oDeliveryRunDB.addNewDeliveryRun(oDeliveryRun);
    }



}
