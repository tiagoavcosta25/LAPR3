package lapr.project.data;


import lapr.project.data.DataHandler;
import lapr.project.model.*;
import oracle.jdbc.OracleTypes;

import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScooterDB extends DataHandler {

    public ScooterDB(String jdbcUrl, String username, String password) {
        super(jdbcUrl, username, password);
    }

    public ScooterDB() {
        super();
    }

    public Scooter getScooter(int id) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getScooter(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(id, 2);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {

                float fltPotency = rSet.getFloat(1);
                float fltWeight = rSet.getFloat(2);
                float fltMaxPayload = rSet.getFloat(3);
                String strCharginStatus = rSet.getString(4);
                float fltBatteryPerc = rSet.getInt(5);
                int intBatteryCapacity = rSet.getInt(6);
                float fltBatteryVoltage = rSet.getFloat(7);
                Pharmacy oPharmacy = pharmacyManager(rSet,8);

                return new Scooter(fltPotency, fltWeight, fltMaxPayload, strCharginStatus, fltBatteryPerc,
                        intBatteryCapacity, fltBatteryVoltage, oPharmacy);
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
        return addScooter(s.getPotency(), s.getWeight(), s.getMaxPayload(), s.getCharginStatus(),
                s.getBattery(), s.getPharmacy());
    }

    private boolean addScooter(float fltPotency, float fltWeight, float fltMaxPayload, String strCharginStatus,
                               Battery oBattery, Pharmacy oPharmacy) {
        boolean flag = true;
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call addScooter(?,?,?,?,?,?,?,?) }");


            callStmt.setFloat(1, fltPotency);
            callStmt.setFloat(2, fltWeight);
            callStmt.setFloat(3, fltMaxPayload);
            callStmt.setString(4, strCharginStatus);
            callStmt.setFloat(5, oBattery.getBatteryPerc());
            callStmt.setFloat(6, oBattery.getBatteryCapacity());
            callStmt.setFloat(7, oBattery.getBatteryVoltage());
            callStmt.setInt(8, oPharmacy.getId());

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

    public boolean updateScooterFromDB(int intId, float fltBatteryPerc, String strCharginStatus, float fltPotency,
                                       float fltWeight, int intBatteryCapacity, float fltBatteryVoltage,
                                       float fltMaxPayload, int intPharmacyId) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{call updateScooter(?,?,?,?,?,?,?,?)}");

            callStmt.setInt(1, intId);
            callStmt.setFloat(2, fltPotency);
            callStmt.setFloat(3, fltWeight);
            callStmt.setFloat(4, fltMaxPayload);
            callStmt.setString(5, strCharginStatus);
            callStmt.setFloat(6, fltBatteryPerc);
            callStmt.setFloat(7, intBatteryCapacity);
            callStmt.setFloat(8, fltBatteryVoltage);
            callStmt.setInt(9, intPharmacyId);

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
            callStmt = getConnection().prepareCall("{ ? = call getScootersList(?) }");

            callStmt.registerOutParameter(1, oracle.jdbc.internal.OracleTypes.CURSOR);
            callStmt.setInt(2, intPharmacyId);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while(rSet.next()){
                int intId = rSet.getInt(1);
                float fltPotency = rSet.getFloat(2);
                float fltWeight = rSet.getFloat(3);
                float fltMaxPayload = rSet.getFloat(4);
                String strCharginStatus = rSet.getString(5);
                float fltBatteryPerc = rSet.getInt(6);
                int intBatteryCapacity = rSet.getInt(7);
                float fltBatteryVoltage = rSet.getFloat(8);
                Pharmacy oPharmacy = pharmacyManager(rSet,9);

                lstScooter.add(new Scooter(intId, fltPotency, fltWeight, fltMaxPayload, strCharginStatus, fltBatteryPerc,
                        intBatteryCapacity, fltBatteryVoltage, oPharmacy));

                rSet.next();
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Scooters Avaliable.");
    }

    public boolean removeScooterFromDB(int intId) {
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call removeScooter(?) }");

            callStmt.setInt(1, intId);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean registerScooter(Scooter s) {
        return addScooter(s.getPotency(), s.getWeight(), s.getMaxPayload(), s.getCharginStatus(),
                s.getBattery(), s.getPharmacy());
    }

    public Scooter getSuitableScooter(Double distance, String email) {
        /* Objeto "callStmt" para invocar a função "getSailor" armazenada na BD.
         *
         * FUNCTION getSailor(id NUMBER) RETURN pkgSailors.ref_cursor
         * PACKAGE pkgSailors AS TYPE ref_cursor IS REF CURSOR; END pkgSailors;
         */
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getSuitableScooter(?,?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            // Especifica o parâmetro de entrada da função "getSailor".
            callStmt.setDouble(2, distance);
            callStmt.setString(3, email);

            // Executa a invocação da função "getSailor".
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int scooterID = rSet.getInt(1);
                int pharmacyID = rSet.getInt(2);
                float batteryPerc = rSet.getFloat(3);
                float potency = rSet.getFloat(4);
                float weight = rSet.getFloat(5);
                Integer batteryCapacity = rSet.getInt(6);
                String charginStatus = rSet.getString(7);
                float fltMaxPayload = rSet.getFloat(8);
                float fltBatteryVoltage = rSet.getFloat(9);
                Pharmacy oPharmacy = pharmacyManager(rSet,10);

                return new Scooter(scooterID, potency, weight, fltMaxPayload, charginStatus, batteryPerc,
                        batteryCapacity, fltBatteryVoltage, oPharmacy);
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        throw new IllegalArgumentException("No Charging Slot for Courier: " + email);
    }
}
