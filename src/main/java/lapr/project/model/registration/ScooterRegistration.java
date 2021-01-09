package lapr.project.model.registration;


import lapr.project.data.DataHandler;
import lapr.project.model.*;
import oracle.jdbc.OracleTypes;

import java.security.NoSuchAlgorithmException;
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

                float intBatteryPerc = rSet.getInt(1);
                String strCharginStatus = rSet.getString(2);
                float intPotency = rSet.getFloat(3);
                float intWeight = rSet.getFloat(4);
                int intBatteryCapacity = rSet.getInt(5);
                int intId = rSet.getInt(6);
                String strEmail = rSet.getString(7);
                String strPassword = rSet.getString(8);
                Integer intNIF = rSet.getInt(9);
                String strName = rSet.getString(10);
                Double fltLatitude = rSet.getDouble(11);
                Double fltLongitude = rSet.getDouble(12);
                String strStreetName = rSet.getString(13);
                String strDoorNumber = rSet.getString(14);
                String strPostalCode = rSet.getString(15);
                String strLocality = rSet.getString(16);
                String strCountry = rSet.getString(17);

                return new Scooter(intBatteryPerc, strCharginStatus, intPotency, intWeight,
                        intBatteryCapacity, new Pharmacy(strName, new PharmacyManager(intId, strEmail, strPassword, intNIF, strName), new Address(fltLatitude, fltLongitude, strStreetName,
                        strDoorNumber, strPostalCode, strLocality, strCountry)));
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
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

    private boolean addScooter(float intBatteryPerc, String strCharginStatus, float intPotency, float intWeight,
                               int intBatteryCapacity, Pharmacy oPharmacy) {
        boolean flag = true;
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call addScooter(?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");

            callStmt.setFloat(1, intBatteryPerc);
            callStmt.setString(2, strCharginStatus);
            callStmt.setFloat(3, intPotency);
            callStmt.setFloat(4, intWeight);
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

    public Scooter newScooter(float intBatteryPerc, String strCharginStatus, float intPotency, float intWeight,
                              int intBatteryCapacity, Pharmacy oPharmacy) {
        return new Scooter(intBatteryPerc, strCharginStatus, intPotency, intWeight, intBatteryCapacity, oPharmacy);
    }

    public void registerScooter(Scooter oScooter) {
        addScooter(oScooter);
    }

    public boolean updateScooterFromDB(int intId, float intBatteryPerc, String strCharginStatus, float intPotency, float intWeight,
                                       int intBatteryCapacity) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{call updateScooter(?,?,?,?,?)}");

            callStmt.setInt(1, intId);
            callStmt.setFloat(2, intBatteryPerc);
            callStmt.setString(3, strCharginStatus);
            callStmt.setFloat(4, intPotency);
            callStmt.setFloat(5, intWeight);
            callStmt.setInt(6, intBatteryCapacity);

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
                float intBatteryPerc = rSet.getFloat(2);
                String strCharginStatus = rSet.getString(3);
                float intPotency = rSet.getFloat(4);
                float intWeight = rSet.getFloat(5);
                int intBatteryCapacity = rSet.getInt(6);

                lstScooter.add(new Scooter(intId, intBatteryPerc, strCharginStatus, intPotency, intWeight,
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

    public Scooter getSuitableScooter(float deliveryEnergy, String email) {
        /* Objeto "callStmt" para invocar a função "getSailor" armazenada na BD.
         *
         * FUNCTION getSailor(id NUMBER) RETURN pkgSailors.ref_cursor
         * PACKAGE pkgSailors AS TYPE ref_cursor IS REF CURSOR; END pkgSailors;
         */
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getAvailableChargingSlot(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            // Especifica o parâmetro de entrada da função "getSailor".
            callStmt.setString(2, email);

            // Executa a invocação da função "getSailor".
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int scooterID = rSet.getInt(1);
                int pharmacyID = rSet.getInt(2);
                String pharmacyName = rSet.getString(3);
                float batteryPerc = rSet.getFloat(4);
                float potency = rSet.getFloat(5);
                float weight = rSet.getFloat(6);
                Integer batteryCapacity = rSet.getInt(7);
                String charginStatus = rSet.getString(8);
                // Address
                Address address = addressManager(rSet,9);

                return new Scooter(scooterID,batteryPerc,charginStatus,potency,weight,
                batteryCapacity,new Pharmacy(pharmacyID,pharmacyName,new PharmacyManager(),address));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        throw new IllegalArgumentException("No Charging Slot for Courier: " + email);
    }
    }
}
