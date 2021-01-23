package lapr.project.model.service;

import lapr.project.controller.ApplicationPOT;
import lapr.project.data.DeliveryDB;
import lapr.project.data.DeliveryRunDB;
import lapr.project.model.*;
import lapr.project.utils.EmailSender;

import java.util.*;

public class DeliveryRunService {

    private DeliveryRunDB moDeliveryRunDB;
    private DeliveryDB moDeliveryDB;

    public DeliveryRunService() {
        moDeliveryRunDB = new DeliveryRunDB();
        moDeliveryDB = new DeliveryDB();
    }

    public boolean registerPath(double dblLatitudeA, double dblLongitudeA, double dblLatitudeB, double dblLongitudeB,
                                String strName, double dblWindSpeed, double dblWindAngle,
                                double dblKineticFrictionCoefficient,VehicleType oVehicleType) {
        return moDeliveryRunDB.addPathToDB(new Path(dblLatitudeA, dblLongitudeA, dblLatitudeB, dblLongitudeB,
                strName, dblWindSpeed, dblWindAngle, dblKineticFrictionCoefficient, oVehicleType));
    }

    public DeliveryRun newDeliveryRun(Courier oCourier, List<Order> oLstOrder) {
        return new DeliveryRun(oCourier, oLstOrder);
    }

    public boolean addNewDeliveryRun(DeliveryRun oDeliveryRun) {
        return moDeliveryRunDB.addNewDeliveryRun(oDeliveryRun);
    }

    public List<Address> getAddressesByDeliveryRunId(String email) {
        return moDeliveryRunDB.getAddressesByDeliveryRunId(email);
    }

    //TODO: Verificar mais tarde
    public LinkedList<Address> calculateMostEfficientPath(Address a, Address b, List<Address> list) {
        return ApplicationPOT.getInstance().getWorldMap().calculateMostEfficientPath(a, b, list);
    }

    public float getMaxPayload(String email) {
        return moDeliveryDB.getMaxPayload(email);
    }


    public Map<String, String> startDeliveryRun(String currentUserEmail) {
        return moDeliveryDB.startDeliveryRun(currentUserEmail);
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
