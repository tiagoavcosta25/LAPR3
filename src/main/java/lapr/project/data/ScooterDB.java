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

    public Scooter getScooter(int id) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getScooter(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(id, 2);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {

                float fltBatteryPerc = rSet.getInt(1);
                String strCharginStatus = rSet.getString(2);
                float fltPotency = rSet.getFloat(3);
                float fltWeight = rSet.getFloat(4);
                int intBatteryCapacity = rSet.getInt(5);
                float fltMaxPayload = rSet.getFloat(6);
                int intId = rSet.getInt(7);
                String strEmail = rSet.getString(8);
                String strPassword = rSet.getString(9);
                Integer intNIF = rSet.getInt(10);
                String strName = rSet.getString(11);
                Double fltLatitude = rSet.getDouble(12);
                Double fltLongitude = rSet.getDouble(13);
                String strStreetName = rSet.getString(14);
                String strDoorNumber = rSet.getString(15);
                String strPostalCode = rSet.getString(16);
                String strLocality = rSet.getString(17);
                String strCountry = rSet.getString(18);

                return new Scooter(fltBatteryPerc, strCharginStatus, fltPotency, fltWeight,
                        intBatteryCapacity, fltMaxPayload, new Pharmacy(strName, new PharmacyManager(intId, strEmail, strPassword, intNIF, strName), new Address(fltLatitude, fltLongitude, strStreetName,
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
                s.getBatteryCapacity(), s.getMaxPayload(), s.getPharmacy());
    }

    private boolean addScooter(float fltBatteryPerc, String strCharginStatus, float fltPotency, float fltWeight,
                               int intBatteryCapacity, float fltMaxPayload, Pharmacy oPharmacy) {
        boolean flag = true;
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call addScooter(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");

            callStmt.setFloat(1, fltBatteryPerc);
            callStmt.setString(2, strCharginStatus);
            callStmt.setFloat(3, fltPotency);
            callStmt.setFloat(4, fltWeight);
            callStmt.setInt(5, intBatteryCapacity);
            callStmt.setFloat(6, fltMaxPayload);
            callStmt.setInt(7, oPharmacy.getId());
            callStmt.setString(8, oPharmacy.getName());
            callStmt.setDouble(9, oPharmacy.getAddress().getLatitude());
            callStmt.setDouble(10, oPharmacy.getAddress().getLongitude());
            callStmt.setString(11, oPharmacy.getAddress().getStreetName());
            callStmt.setString(12, oPharmacy.getAddress().getDoorNumber());
            callStmt.setString(13, oPharmacy.getAddress().getPostalCode());
            callStmt.setString(14, oPharmacy.getAddress().getLocality());
            callStmt.setString(15, oPharmacy.getAddress().getCountry());

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

    public Scooter newScooter(float fltBatteryPerc, String strCharginStatus, float fltPotency, float fltWeight,
                              int intBatteryCapacity, float fltMaxPayload, Pharmacy oPharmacy) {
        return new Scooter(fltBatteryPerc, strCharginStatus, fltPotency, fltWeight, intBatteryCapacity, fltMaxPayload, oPharmacy);
    }

    public boolean registerScooter(Scooter oScooter) {
        return addScooter(oScooter);
    }

    public boolean updateScooterFromDB(int intId, float fltBatteryPerc, String strCharginStatus, float fltPotency, float fltWeight,
                                       int intBatteryCapacity, float fltMaxPayload) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{call updateScooter(?,?,?,?,?,?,?)}");

            callStmt.setInt(1, intId);
            callStmt.setFloat(2, fltBatteryPerc);
            callStmt.setString(3, strCharginStatus);
            callStmt.setFloat(4, fltPotency);
            callStmt.setFloat(5, fltWeight);
            callStmt.setInt(6, intBatteryCapacity);
            callStmt.setFloat(7, fltMaxPayload);

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
                float fltBatteryPerc = rSet.getFloat(2);
                String strCharginStatus = rSet.getString(3);
                float fltPotency = rSet.getFloat(4);
                float fltWeight = rSet.getFloat(5);
                int intBatteryCapacity = rSet.getInt(6);
                float fltMaxPayload = rSet.getFloat(7);

                lstScooter.add(new Scooter(intId, fltBatteryPerc, strCharginStatus, fltPotency, fltWeight,
                        intBatteryCapacity, fltMaxPayload));

                rSet.next();
            }
        } catch (SQLException e) {
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
                String pharmacyName = rSet.getString(3);
                float batteryPerc = rSet.getFloat(4);
                float potency = rSet.getFloat(5);
                float weight = rSet.getFloat(6);
                Integer batteryCapacity = rSet.getInt(7);
                String charginStatus = rSet.getString(8);
                float fltMaxPayload = rSet.getFloat(9);
                // Address
                Address address = addressManager(rSet,10);

                return new Scooter(scooterID,batteryPerc,charginStatus,potency,weight,
                batteryCapacity, fltMaxPayload, new Pharmacy(pharmacyID,pharmacyName,new PharmacyManager(),address));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        throw new IllegalArgumentException("No Charging Slot for Courier: " + email);
    }
}
