package lapr.project.model.registration;


import lapr.project.data.DataHandler;
import lapr.project.model.Address;
import lapr.project.model.Pharmacy;
import lapr.project.model.Product;
import lapr.project.model.Scooter;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScooterRegistration extends DataHandler {


    public Scooter getScooter(int id) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getScooter(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(id, 1);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {

                int intBatteryPerc = rSet.getInt(1);
                int intCharginStatus = rSet.getInt(2);
                int intPotency = rSet.getInt(3);
                int intWeight = rSet.getInt(4);
                int intBatteryCapacity = rSet.getInt(5);
                int intId = rSet.getInt(6);
                String strName = rSet.getString(7);
                Double fltLatitude = rSet.getDouble(8);
                Double fltLongitude = rSet.getDouble(9);
                String strStreetName = rSet.getString(10);
                String strDoorNumber = rSet.getString(11);
                String strPostalCode = rSet.getString(12);
                String strLocality = rSet.getString(13);
                String strCountry = rSet.getString(14);

                return new Scooter(intBatteryPerc, intCharginStatus, intPotency, intWeight,
                        intBatteryCapacity, new Pharmacy(intId, strName, new Address(fltLatitude, fltLongitude, strStreetName,
                        strDoorNumber, strPostalCode, strLocality, strCountry)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Scooter with ID:" + id);
    }

    /**
     * DATABASE
     */


    public boolean addScooter(Scooter s) {
        return addScooter(s.getBatteryPerc(), s.getCharginStatus(), s.getPotency(), s.getWeight(),
                s.getBatteryCapacity(), s.getPharmacy());
    }

    private boolean addScooter(int intBatteryPerc, int intCharginStatus, int intPotency, int intWeight,
                               int intBatteryCapacity, Pharmacy oPharmacy) {
        boolean flag = true;
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call addScooter(?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");

            callStmt.setInt(1, intBatteryPerc);
            callStmt.setInt(2, intCharginStatus);
            callStmt.setInt(3, intPotency);
            callStmt.setInt(4, intWeight);
            callStmt.setInt(5, intBatteryCapacity);
            callStmt.setInt(6, oPharmacy.getId());
            callStmt.setString(7, oPharmacy.getName());
            callStmt.setDouble(8, oPharmacy.getAddress().getM_latitude());
            callStmt.setDouble(9, oPharmacy.getAddress().getM_longitude());
            callStmt.setString(10, oPharmacy.getAddress().getM_streetName());
            callStmt.setString(11, oPharmacy.getAddress().getM_doorNumber());
            callStmt.setString(12, oPharmacy.getAddress().getM_postalCode());
            callStmt.setString(13, oPharmacy.getAddress().getM_locality());
            callStmt.setString(14, oPharmacy.getAddress().getM_country());

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

    public Scooter newScooter(int intBatteryPerc, int intCharginStatus, int intPotency, int intWeight,
                              int intBatteryCapacity, Pharmacy oPharmacy) {
        return new Scooter(intBatteryPerc, intCharginStatus, intPotency, intWeight, intBatteryCapacity, oPharmacy);
    }

    public void registerScooter(Scooter oScooter) {
        addScooter(oScooter);
    }

    public boolean updateScooterFromDB(int intBatteryPerc, int intCharginStatus, int intPotency, int intWeight,
                                       int intBatteryCapacity) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{call updateScooter(?,?,?,?,?)}");

            callStmt.setInt(1, intBatteryPerc);
            callStmt.setInt(2, intCharginStatus);
            callStmt.setInt(3, intPotency);
            callStmt.setInt(4, intWeight);
            callStmt.setInt(5, intBatteryCapacity);

            callStmt.execute();
            closeAll();

        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public List<Scooter> getScootersList(int intPharmacyId) {
        CallableStatement callStmt = null;
        List<Scooter> lstScooter = new ArrayList<>();
        try {
            callStmt = getConnection().prepareCall("{ ? = call getScootersList() }");

            callStmt.registerOutParameter(1, oracle.jdbc.internal.OracleTypes.CURSOR);
            callStmt.setInt(2, intPharmacyId);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while(rSet.next()){
                int intId = rSet.getInt(1);
                int intBatteryPerc = rSet.getInt(2);
                int intCharginStatus = rSet.getInt(3);
                int intPotency = rSet.getInt(4);
                int intWeight = rSet.getInt(5);
                int intBatteryCapacity = rSet.getInt(6);

                lstScooter.add(new Scooter(intId, intBatteryPerc, intCharginStatus, intPotency, intWeight,
                        intBatteryCapacity));

                rSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Scooters Avaliable.");
    }

    public void removeScooterFromDB(int intId) {
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call removeScooter(?) }");

            callStmt.setInt(1, intId);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
