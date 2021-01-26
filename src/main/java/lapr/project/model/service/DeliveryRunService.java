package lapr.project.model.service;

import javafx.util.Pair;
import lapr.project.controller.ApplicationPOT;
import lapr.project.data.DeliveryDB;
import lapr.project.data.DeliveryRunDB;
import lapr.project.model.*;
import lapr.project.utils.EmailSender;
import lapr.project.utils.WriteFile;

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

    public DeliveryRun newDeliveryRun(Courier oCourier, List<Order> oLstOrder, Vehicle oVehicle) {
        return new DeliveryRun(oCourier, oLstOrder, oVehicle);
    }

    public Scooter getMostChargedScooter(VehicleModel oModel) {
        return moDeliveryRunDB.getMostChargedScooterFromModel(oModel);
    }

    public Drone getMostChargedDrone(VehicleModel oModel) {
        return moDeliveryRunDB.getMostChargedDroneFromModel(oModel);
    }

    public boolean addNewDeliveryRun(DeliveryRun oDeliveryRun) {
        return moDeliveryRunDB.addNewDeliveryRun(oDeliveryRun);
    }

    public List<Address> getAddressesByDeliveryRunId(String email) {
        return moDeliveryRunDB.getAddressesByDeliveryRunId(email);
    }

    public VehicleModel getMostEfficientVehicleModel(List<Pair<Pair<VehicleModel, Double>, List<Address>>> lst) {
        if (lst.get(0) == null && lst.get(1) == null) {
            return null;
        }else if (lst.get(0) == null) {
            return lst.get(1).getKey().getKey();
        }else if (lst.get(1) == null) {
            return lst.get(0).getKey().getKey();
        }else{
            if (lst.get(0).getKey().getValue() < lst.get(1).getKey().getValue()) return lst.get(0).getKey().getKey();
            else return lst.get(1).getKey().getKey();
        }
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
                WriteFile.write("OrderDispatched_" + pair.getValue().toString(), "Your order has been dispatched!\n" + pair.getValue().toString());
            }
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
}
