package lapr.project.data;

import lapr.project.model.*;
import oracle.jdbc.OracleTypes;

import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryRunDB extends DataHandler {

    public boolean addPathToDB(Path p) {
        return addPathToDB(p.getLatitudeA(), p.getLongitudeA(), p.getLatitudeB(), p.getLongitudeB(),
                p.getName(), p.getWindSpeed(), p.getWindAngle(), p.getKineticFrictionCoefficient(),p.getVehicleType());
    }

    private boolean addPathToDB(double dblLatitudeA, double dblLongitudeA, double dblLatitudeB, double dblLongitudeB,
                                String strName, double dblWindSpeed, double dblWindAngle,
                                double dblKineticFrictionCoefficient,VehicleType oVehicleType) {
        boolean flag = true;
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call addPath(?,?,?,?,?,?,?,?,?) }");

            callStmt.setDouble(1, dblLatitudeA);
            callStmt.setDouble(2, dblLongitudeA);
            callStmt.setDouble(3, dblLatitudeB);
            callStmt.setDouble(4, dblLongitudeB);
            callStmt.setString(5, strName);
            callStmt.setDouble(6, dblWindSpeed);
            callStmt.setDouble(7, dblWindAngle);
            callStmt.setDouble(8, dblKineticFrictionCoefficient);
            callStmt.setString(9,oVehicleType.getDesignation());

            callStmt.execute();

        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return flag;
    }

    public List<Path> getAllPaths() {
        CallableStatement callStmt = null;
        List<Path> lstPaths = new ArrayList<>();
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{ ? = call getPaths() }");

            callStmt.registerOutParameter(1, oracle.jdbc.internal.OracleTypes.CURSOR);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {

                double m_dblLatitudeA = rSet.getDouble(1);
                double m_dblLongitudeA = rSet.getDouble(2);
                double m_dblLatitudeB = rSet.getDouble(3);
                double m_dblLongitudeB = rSet.getDouble(4);
                String m_strName = rSet.getString(6);
                double m_dblWindSpeed = rSet.getDouble(7);
                double m_dblWindAngle = rSet.getDouble(8);
                double m_dblKineticFrictionCoefficient = rSet.getDouble(9);
                VehicleType oVehicleType = null;
                if (rSet.getString(5).equals("Scooter")){
                    oVehicleType = VehicleType.SCOOTER;
                }else oVehicleType = VehicleType.DRONE;

                lstPaths.add(new Path(m_dblLatitudeA, m_dblLongitudeA, m_dblLatitudeB, m_dblLongitudeB,
                        m_strName, m_dblWindSpeed, m_dblWindAngle, m_dblKineticFrictionCoefficient,oVehicleType));
            }
            return lstPaths;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Paths Avaliable.");
    }

    //TODO: ARRANJAR
    public List<Address> getAllAddresses() {
        CallableStatement callStmt = null;
        List<Address> lstAddress = new ArrayList<>();
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{ ? = call getAllAddress() }");

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

    public boolean addNewDeliveryRun(DeliveryRun oDeliveryRun) {
        return addNewDeliveryRun(oDeliveryRun.getCourier(),
                oDeliveryRun.getOrderList(),oDeliveryRun.getStatus(),oDeliveryRun.getVehicle());
    }

    public boolean addNewDeliveryRun(Courier courier, List<Order> lst, DeliveryStatus status, Vehicle oVehicle) {
        try {
            openConnection();


            CallableStatement callStmt = getConnection().prepareCall("{ ? = call addNewDeliveryRun(?,?,?) }");

            if (courier == null) {
                callStmt.setInt(2,-1);
            }else callStmt.setInt(2,courier.getId());
            callStmt.setString(3,status.getDesignation());
            callStmt.setInt(4,oVehicle.getId());
            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.execute();

            Integer deliveryRunId = callStmt.getInt(1);

            for (Order o : lst) {
                CallableStatement callStmt2 = getConnection().prepareCall("{ call addOrderToDeliveryRun(?,?) }");
                callStmt2.setInt(1,deliveryRunId);
                callStmt2.setInt(2,o.getId());

                callStmt2.execute();
            }

            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            closeAll();
        }
    }

    public Scooter getMostChargedScooterFromModel(VehicleModel oVehicleModel) {
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ ? = call getMostChargedScooter(?) }");

            callStmt.setString(2,oVehicleModel.getDesignation());
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            if(rSet.next())
                return scooterManager(rSet,1);
            else
                return null;
        } catch (SQLException | NoSuchAlgorithmException e) {
            return null;
        } finally {
            closeAll();
        }
    }

    public Drone getMostChargedDroneFromModel(VehicleModel oVehicleModel) {
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ ? = call getMostChargedDrone(?) }");

            callStmt.setString(2,oVehicleModel.getDesignation());
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            return droneManager(rSet,1);
        } catch (SQLException | NoSuchAlgorithmException e) {
            return null;
        } finally {
            closeAll();
        }
    }

    public boolean checkValidChargingSlot(Address oAddress) {
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ ? = call checkValidChargingSlot(?,?) }");

            callStmt.setDouble(2,oAddress.getLatitude());
            callStmt.setDouble(3,oAddress.getLongitude());
            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.execute();

            return callStmt.getInt(1) != 0;
        } catch (SQLException e) {
            return false;
        } finally {
            closeAll();
        }
    }

    public List<Address> getAddressesByDeliveryRunId(String email) {
        CallableStatement callStmt = null;
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{ ? = call getDeliveryRunIdByCourierEmail(?) }");

            callStmt.registerOutParameter(1, OracleTypes.NUMBER);
            callStmt.setString(2, email);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            int delRunId = 0;
            if (rSet.next()) {
                delRunId = rSet.getInt(1);
            }
            callStmt = getConnection().prepareCall("{ ? = call getAddressesByDeliveryRunId(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, delRunId);

            callStmt.execute();

            rSet = (ResultSet) callStmt.getObject(1);
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


}
