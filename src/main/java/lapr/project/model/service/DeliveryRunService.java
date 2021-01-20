package lapr.project.model.service;

import javafx.util.Pair;
import lapr.project.controller.ApplicationPOT;
import lapr.project.data.DeliveryDB;
import lapr.project.data.DeliveryRunDB;
import lapr.project.model.*;
import lapr.project.utils.EmailSender;

import java.util.*;

public class DeliveryRunService {

    private DeliveryRunDB m_oDeliveryRunDB;
    private DeliveryDB m_oDeliveryDB;

    public DeliveryRunService() {
        m_oDeliveryRunDB = new DeliveryRunDB();
        m_oDeliveryDB = new DeliveryDB();
    }

    public boolean registerPath(double dblLatitudeA, double dblLongitudeA, double dblLatitudeB, double dblLongitudeB,
                                String strName, double dblWindSpeed, double dblWindAngle,
                                double dblKineticFrictionCoefficient) {
        return m_oDeliveryRunDB.addPathToDB(new Path(dblLatitudeA, dblLongitudeA, dblLatitudeB, dblLongitudeB,
                strName, dblWindSpeed, dblWindAngle, dblKineticFrictionCoefficient));
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
        return ApplicationPOT.getInstance().getWorldMap().calculateMostEfficientPath(a, b, list);
    }

    public float getMaxPayload(String email) {
        return m_oDeliveryDB.getMaxPayload(email);
    }


    public Map<String, String> startDeliveryRun(Vehicle vehicle, String currentUserEmail) {
        return m_oDeliveryDB.startDeliveryRun(vehicle, currentUserEmail);
    }

    public boolean sendsEmail(Map lstClients) {
        boolean flag = true;
        try {
            Iterator it = lstClients.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                EmailSender.sendEmail(pair.getKey().toString(), "Order Status",
                        "Your order has been dispatched!\n" + pair.getValue().toString());
            }
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
}
