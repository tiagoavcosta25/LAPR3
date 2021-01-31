package lapr.project.data;

import lapr.project.model.*;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * DeliveryRunDB.
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
public class DeliveryRunDB extends DataHandler {

    /**
     * Adds a Path to the Database
     *
     * @param p Path to be added
     * @return True if the Path was added to the Datebase, false if otherwise
     */
    public boolean addPathToDB(Path p) {
        return addPathToDB(p.getLatitudeA(), p.getLongitudeA(), p.getLatitudeB(), p.getLongitudeB(),
                p.getName(), p.getWindSpeed(), p.getWindAngle(), p.getKineticFrictionCoefficient(), p.getVehicleType());
    }

    /**
     * Adds a Path to the Database
     *
     * @param dblLatitudeA                  Path's first latitude
     * @param dblLongitudeA                 Path's first longitude
     * @param dblLatitudeB                  Path's second latitude
     * @param dblLongitudeB                 Path's second longitude
     * @param strName                       Path's name
     * @param dblWindSpeed                  Path's wind speed
     * @param dblWindAngle                  Path's wind angle
     * @param dblKineticFrictionCoefficient Path's kinetic friction coefficient
     * @param oVehicleType                  Path's Vehicle Type
     * @return True if the Path was added to the Datebase, false if otherwise
     */
    private boolean addPathToDB(double dblLatitudeA, double dblLongitudeA, double dblLatitudeB, double dblLongitudeB,
                                String strName, double dblWindSpeed, double dblWindAngle,
                                double dblKineticFrictionCoefficient, VehicleType oVehicleType) {
        boolean flag = true;
        try (CallableStatement callStmt = getConnection().prepareCall("{ call addPath(?,?,?,?,?,?,?,?,?) }");) {
            callStmt.setDouble(1, dblLatitudeA);
            callStmt.setDouble(2, dblLongitudeA);
            callStmt.setDouble(3, dblLatitudeB);
            callStmt.setDouble(4, dblLongitudeB);
            callStmt.setString(5, strName);
            callStmt.setDouble(6, dblWindSpeed);
            callStmt.setDouble(7, dblWindAngle);
            callStmt.setDouble(8, dblKineticFrictionCoefficient);
            callStmt.setString(9, oVehicleType.getDesignation());

            callStmt.execute();

        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return flag;
    }

    /**
     * Returns all Paths from the Database
     *
     * @return List of all Paths from the Database
     */
    public List<Path> getAllPaths() {
        List<Path> lstPaths = new ArrayList<>();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call getPaths() }");) {
            callStmt.registerOutParameter(1, oracle.jdbc.internal.OracleTypes.CURSOR);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {

                double mdblLatitudeA = rSet.getDouble(1);
                double mdblLongitudeA = rSet.getDouble(2);
                double mdblLatitudeB = rSet.getDouble(3);
                double mdblLongitudeB = rSet.getDouble(4);
                String mstrName = rSet.getString(6);
                double mdblWindSpeed = rSet.getDouble(7);
                double mdblWindAngle = rSet.getDouble(8);
                double mdblKineticFrictionCoefficient = rSet.getDouble(9);
                VehicleType oVehicleType = null;
                if (rSet.getString(5).equals("Scooter")) {
                    oVehicleType = VehicleType.SCOOTER;
                } else oVehicleType = VehicleType.DRONE;

                lstPaths.add(new Path(mdblLatitudeA, mdblLongitudeA, mdblLatitudeB, mdblLongitudeB,
                        mstrName, mdblWindSpeed, mdblWindAngle, mdblKineticFrictionCoefficient, oVehicleType));
            }
            return lstPaths;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Paths Avaliable.");
    }

    /**
     * Returns all Addresses from the Database
     *
     * @return List of all Addresses from the Database
     */
    public List<Address> getAllAddresses() {
        List<Address> lstAddress = new ArrayList<>();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call getAllAddress() }");) {
            callStmt.registerOutParameter(1, oracle.jdbc.internal.OracleTypes.CURSOR);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                lstAddress.add(addressManager(rSet, 1));
            }
            return lstAddress;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Addresses Avaliable.");
    }

    /**
     * Adds a new Delivery Run to the Database
     *
     * @param oDeliveryRun Delivery Run to be added
     * @return True if the Delivery Run was added, otherwise false
     */
    public boolean addNewDeliveryRun(DeliveryRun oDeliveryRun) {
        return addNewDeliveryRun(oDeliveryRun.getCourier(),
                oDeliveryRun.getOrderList(), oDeliveryRun.getStatus(), oDeliveryRun.getVehicle());
    }

    /**
     * Adds a new Delivery Run to the Database
     *
     * @param courier  Delivery Run's Courier
     * @param lst      Delivery Run's Order List
     * @param status   Delivery Run's Status
     * @param oVehicle Delivery Run's Vehicle
     * @return True if the Delivery Run was added, otherwise false
     */
    public boolean addNewDeliveryRun(Courier courier, List<Order> lst, DeliveryStatus status, Vehicle oVehicle) {
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call addNewDeliveryRun(?,?,?) }");
             CallableStatement callStmt2 = getConnection().prepareCall("{ call addOrderToDeliveryRun(?,?) }");) {
            if (courier == null) {
                callStmt.setInt(2, -1);
            } else callStmt.setInt(2, courier.getId());
            callStmt.setString(3, status.getDesignation());
            callStmt.setInt(4, oVehicle.getId());
            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.execute();

            int deliveryRunId = callStmt.getInt(1);

            for (Order o : lst) {
                callStmt2.setInt(1, deliveryRunId);
                callStmt2.setInt(2, o.getId());
                callStmt2.execute();
            }
            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            closeAll();
        }
    }

    /**
     * Returns the most charged Scooter from the Database, regarding a certain model
     * given by parameter
     *
     * @param oVehicleModel Vehicle Model
     * @return Most charged Scooter regarding Vehicle Model
     */
    public Scooter getMostChargedScooterFromModel(VehicleModel oVehicleModel) {
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call getMostChargedScooter(?) }");) {
            callStmt.setString(2, oVehicleModel.getDesignation());
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            if (rSet.next())
                return scooterManager(rSet, 1);
            else
                return null;
        } catch (SQLException e) {
            return null;
        } finally {
            closeAll();
        }
    }

    /**
     * Returns the most charged Drone from the Database, regarding a certain model
     * given by parameter
     *
     * @param oVehicleModel Vehicle Model
     * @return Most charged Drone regarding Vehicle Model
     */
    public Drone getMostChargedDroneFromModel(VehicleModel oVehicleModel) {
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call getMostChargedDrone(?) }");) {
            callStmt.setString(2, oVehicleModel.getDesignation());
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            if (rSet.next())
                return droneManager(rSet, 1);
            return null;
        } catch (SQLException e) {
            return null;
        } finally {
            closeAll();
        }
    }

    /**
     * Checks if there is any valid Charging Slot regarding a Pharmacy Address
     *
     * @param oAddress Pharmacy Address
     * @return True if there is a valid Charging Slot, false if otherwise
     */
    public boolean checkValidChargingSlot(Address oAddress) {
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call checkValidChargingSlot(?,?) }");) {
            callStmt.setDouble(2, oAddress.getLatitude());
            callStmt.setDouble(3, oAddress.getLongitude());
            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.execute();

            return callStmt.getInt(1) != 0;
        } catch (SQLException e) {
            return false;
        } finally {
            closeAll();
        }
    }

    /**
     * Returns the Addresses by pharmacy email
     *
     * @param email Pharmacy Email
     * @return List of Addresses regarding a pharmacy
     */
    public List<Address> getAddressesByDeliveryRunId(String email) {
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call getDeliveryRunIdByCourierEmail(?) }");
             CallableStatement callStmt2 = getConnection().prepareCall("{ ? = call getAddressesByDeliveryRunId(?) }");) {

            callStmt.registerOutParameter(1, OracleTypes.NUMBER);
            callStmt.setString(2, email);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            int delRunId = 0;
            if (rSet.next()) {
                delRunId = rSet.getInt(1);
            }
            callStmt2.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt2.setInt(2, delRunId);

            callStmt2.execute();

            rSet = (ResultSet) callStmt2.getObject(1);
            List<Address> list = new ArrayList<>();
            while (rSet.next()) {
                Address a = addressManager(rSet, 1);
                list.add(a);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Addresses in the delivery run associated with the courier with the following email:" + email);
    }

    /**
     * Starts the Delivery Run
     *
     * @param currentUserEmail Current User's Email (/Courier)
     * @return Map of Strings
     */
    public Map<String, String> startDeliveryRun(String currentUserEmail) {
        Map<String, String> lstClients = new TreeMap<>();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call getClientsEmail(?) }");) {
            callStmt.registerOutParameter(1, oracle.jdbc.internal.OracleTypes.CURSOR);
            callStmt.setString(2, currentUserEmail);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                String clientEmail = rSet.getString(1);
                int orderID = rSet.getInt(2);
                String orderDesc = rSet.getString(3);
                String info = ("Tracking number: " + orderID + "\nOrder description: " + orderDesc);
                lstClients.put(clientEmail, info);
                rSet.next();
            }
            return lstClients;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Scooters Avaliable.");
    }

    /**
     * Finshes a Delivery Run.
     * @param intID ID.
     * @param intBatteryPerc Battery Percentage.
     * @param strCurrentUserEmail Courier's Email.
     * @return true if it finishes a delivery run, false otherwise.
     */
    public boolean finishDeliveryRun(int intID, int intBatteryPerc, String strCurrentUserEmail) {
        try (CallableStatement callStmt = getConnection().prepareCall("{ call finishDeliveryRun(?,?,?) }");) {
            callStmt.setInt(1, intID);
            callStmt.setInt(2, intBatteryPerc);
            callStmt.setString(3, strCurrentUserEmail);
            callStmt.execute();
            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            closeAll();
        }
    }
}
