package lapr.project.model.service;

import javafx.util.Pair;
import lapr.project.data.DeliveryRunDB;
import lapr.project.model.*;
import lapr.project.utils.EmailSender;
import lapr.project.utils.WriteFile;

import java.util.*;

/**
 * Delivery Run Service.
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
public class DeliveryRunService {

    /**
     * DeliveryRun Database
     */
    private DeliveryRunDB moDeliveryRunDB;

    /**
     * Empty constructor of DeliveryRunService, which instantiates a new
     * DeliveryRun Database
     */
    public DeliveryRunService() {
        moDeliveryRunDB = new DeliveryRunDB();
    }

    /**
     * Returns the DeliveryRun Database
     *
     * @return DeliveryRun Database
     */
    public DeliveryRunDB getDeliveryRunDB() {
        return moDeliveryRunDB;
    }

    /**
     * Sets the DeliveryRun Database to the one given by parameter
     *
     * @param oDeliveryRunDB new DeliveryRun Database
     */
    public void setDeliveryRunDB(DeliveryRunDB oDeliveryRunDB) {
        this.moDeliveryRunDB = oDeliveryRunDB;
    }

    /**
     * Registers a new Path basing on the atributes given by parameter
     *
     * @param dblLatitudeA                  Address Latitude
     * @param dblLongitudeA                 Address Longitude
     * @param dblLatitudeB                  Other Address latitude
     * @param dblLongitudeB                 Other Address longitude
     * @param strName                       Path name
     * @param dblWindSpeed                  Path wind speed
     * @param dblWindAngle                  Path wind angle
     * @param dblKineticFrictionCoefficient Path kinetic friction coefficient
     * @param oVehicleType                  Path Vehicle Type
     * @return True if the Path was registered, false if otherwise
     */
    public boolean registerPath(double dblLatitudeA, double dblLongitudeA, double dblLatitudeB, double dblLongitudeB,
                                String strName, double dblWindSpeed, double dblWindAngle,
                                double dblKineticFrictionCoefficient, VehicleType oVehicleType) {
        return moDeliveryRunDB.addPathToDB(new Path(dblLatitudeA, dblLongitudeA, dblLatitudeB, dblLongitudeB,
                strName, dblWindSpeed, dblWindAngle, dblKineticFrictionCoefficient, oVehicleType));
    }

    /**
     * Creates a new DeliveryRun basing on the atributes given by parameter
     *
     * @param oCourier  Courier
     * @param oLstOrder List of Orders
     * @param oVehicle  Vehicle
     * @return DeliveryRun with the atributes given by parameter
     */
    public DeliveryRun newDeliveryRun(Courier oCourier, List<Order> oLstOrder, Vehicle oVehicle) {
        return new DeliveryRun(oCourier, oLstOrder, oVehicle);
    }

    /**
     * Returns the most charged Scooter basing on a certain Model given by
     * parameter
     *
     * @param oModel Vehicle Model
     * @return most charged Scooter regarding the Model given by parameter
     */
    public Scooter getMostChargedScooter(VehicleModel oModel) {
        return moDeliveryRunDB.getMostChargedScooterFromModel(oModel);
    }

    /**
     * Returns the most charged Drone basing on a certain Model given by
     * parameter
     *
     * @param oModel Vehicle Model
     * @return most charged Drone regarding the Model given by parameter
     */
    public Drone getMostChargedDrone(VehicleModel oModel) {
        return moDeliveryRunDB.getMostChargedDroneFromModel(oModel);
    }

    /**
     * Adds a DeliveryRun to the Database
     *
     * @param oDeliveryRun DeliveryRun to be added to the DB
     * @return True if the DeliveryRun was added, false if otherwise
     */
    public boolean addNewDeliveryRun(DeliveryRun oDeliveryRun) {
        if (moDeliveryRunDB.addNewDeliveryRun(oDeliveryRun)) {
            String body = "";
            for (Order o : oDeliveryRun.getOrderList()) {
                body += (o.getId() + " - ");
            }
            if (body.length()!=0) body = body.substring(0,body.length()-3);
            return WriteFile.write("DeliveryRunRegistration_" + oDeliveryRun.getCourier(),
                    String.format("Delivery Run Information\n\nResponsible Courier: %s\nStatus: %s\n" +
                                    "Vehicle: %s, id-%d\nOrder ID List: %s", oDeliveryRun.getCourier().getName(), oDeliveryRun.getStatus(),
                            oDeliveryRun.getVehicle().getModel().getDesignation(), oDeliveryRun.getVehicle().getId(), body)
            );
        } else {
            return false;
        }
    }

    /**
     * Returns a List of Address basing on the Orders regarding
     * a certain Pharmacy by email
     *
     * @param email Pharmacy email
     * @return List of Address basing on the Orders regarding a certain
     * Pharmacy
     */
    public List<Address> getAddressesByDeliveryRunId(String email) {
        return moDeliveryRunDB.getAddressesByDeliveryRunId(email);
    }

    /**
     * Returns the most efficient Vehicle Model out of two
     *
     * @param lst List of Pairs containing a pair of VehicleModel and the cost, and the List of
     *            Addresses (/path)
     * @return most efficient Vehicle Model
     */
    public VehicleModel getMostEfficientVehicleModel(List<Pair<Pair<VehicleModel, Double>, List<Address>>> lst) {
        if (lst.isEmpty()) return null;

        if (lst.get(0) == null && lst.get(1) == null) {
            return null;
        } else if (lst.get(0) == null) {
            return lst.get(1).getKey().getKey();
        } else if (lst.get(1) == null) {
            return lst.get(0).getKey().getKey();
        } else {
            if (lst.get(0).getKey().getValue() < lst.get(1).getKey().getValue()) return lst.get(0).getKey().getKey();
            else return lst.get(1).getKey().getKey();
        }
    }

    /**
     * Starts a new Delivery Run basing on the Courier's email
     *
     * @param currentUserEmail Current User's email (Courier)
     * @return Map of Strings
     */
    public Map<String, String> startDeliveryRun(String currentUserEmail) {
        return moDeliveryRunDB.startDeliveryRun(currentUserEmail);
    }

    /**
     * Sends an email to the Clients regarding the start
     * of the DeliveryRun
     *
     * @param lstClients List of Clients that will be notified
     * @return True if the email was sent, false if otherwise
     */
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
