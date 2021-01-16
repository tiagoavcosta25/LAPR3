package lapr.project.data;

import lapr.project.model.*;
import oracle.jdbc.OracleTypes;

import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourierDB extends DataHandler {

    public CourierDB() {
        super();
    }

    public CourierDB(String jdbcUrl, String username, String password) {
        super(jdbcUrl, username, password);
    }

    /*public Courier getCourier(int id) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getCourier(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            // Especifica o parâmetro de entrada da função "getSailor".
            callStmt.setInt(id, 1);

            // Executa a invocação da função "getSailor".
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int courierID = rSet.getInt(1);
                String clientName = rSet.getString(2);
                String email = rSet.getString(3);
                String strPassword = rSet.getString(4);
                Integer nif = rSet.getInt(5);
                String iban = rSet.getString(6);

                return new Courier(courierID,clientName, email,strPassword,nif,iban);
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Courier with ID:" + id);
    }*/

    /**
     * Exemplo de invocação de uma "stored procedure".
     * <p>
     * Adiciona o marinheiro especificado à tabela "Sailors".
     *
     * @param strName o identificador do marinheiro.
     * @param strNif  o nome do marinheiro.
     * @param strIban o "rating" do marinheiro.
     */
    public boolean addCourierToDB(String strName, String strEmail, String strPassword, Integer strNif, String strIban, Integer pharmacyId) {
        boolean flag = true;
        try {
            openConnection();
            /*
             *  Objeto "callStmt" para invocar o procedimento "addSailor" armazenado
             *  na BD.
             *
             *  PROCEDURE addSailor(sid NUMBER, sname VARCHAR, rating NUMBER, age NUMBER)
             *  PACKAGE pkgSailors AS TYPE ref_cursor IS REF CURSOR; END pkgSailors;
             */
            CallableStatement callStmt = getConnection().prepareCall("{ call addCourier(?,?,?,?,?,?) }");

            callStmt.setString(1, strName);
            callStmt.setString(2, strEmail);
            callStmt.setString(3, strPassword);
            callStmt.setInt(4, strNif);
            callStmt.setString(5, strIban);
            callStmt.setInt(6, pharmacyId);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * Exemplo de invocação de uma "stored procedure".
     * <p>
     * Remove o marinheiro especificado da tabela "Sailors".
     *
     * @param intId o identificador do marinheiro a remover.
     */
    public boolean removeCourier(int intId) {
        boolean flag = true;
        try {
            openConnection();
            /*
             *  Objeto "callStmt" para invocar o procedimento "removeSailor"
             *  armazenado na BD.
             *
             *  PROCEDURE removeSailor(sid NUMBER)
             *  PACKAGE pkgSailors AS TYPE ref_cursor IS REF CURSOR; END pkgSailors;
             */
            CallableStatement callStmt = getConnection().prepareCall("{ call removeCourier(?) }");

            callStmt.setInt(1, intId);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;

    }

    public Address getDeliveryAddress(String email) {
        /* Objeto "callStmt" para invocar a função "getSailor" armazenada na BD.
         *
         * FUNCTION getSailor(id NUMBER) RETURN pkgSailors.ref_cursor
         * PACKAGE pkgSailors AS TYPE ref_cursor IS REF CURSOR; END pkgSailors;
         */
        CallableStatement callStmt = null;
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{ ? = call getDeliveryAddress(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            // Especifica o parâmetro de entrada da função "getSailor".
            callStmt.setString(1, email);

            // Executa a invocação da função "getSailor".
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                Double latitude = rSet.getDouble(1);
                Double longitude = rSet.getDouble(2);
                String streetName = rSet.getString(3);
                String doorNumber = rSet.getString(4);
                String postalCode = rSet.getString(5);
                String locality = rSet.getString(6);
                String country = rSet.getString(7);
                closeAll();
                return new Address(latitude, longitude, streetName, doorNumber, postalCode, locality, country);
            }
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Address for Courier:" + email);
    }

    public ChargingSlot getAvailableChargingSlot(String email, String vehicleType) {
        /* Objeto "callStmt" para invocar a função "getSailor" armazenada na BD.
         *
         * FUNCTION getSailor(id NUMBER) RETURN pkgSailors.ref_cursor
         * PACKAGE pkgSailors AS TYPE ref_cursor IS REF CURSOR; END pkgSailors;
         */
        CallableStatement callStmt = null;
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{ ? = call getAvailableChargingSlot(?,?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            // Especifica o parâmetro de entrada da função "getSailor".
            callStmt.setString(2, email);
            callStmt.setString(3, vehicleType);

            // Executa a invocação da função "getSailor".
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet"
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int chargingSlotID = rSet.getInt(1);
                float outputPower = rSet.getFloat(2);
                closeAll();
                return new ChargingSlot(chargingSlotID, null, outputPower);
            }
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        throw new IllegalArgumentException("No Charging Slot for Courier: " + email);
    }

    public boolean updateCourierDB(Courier oCourier) {
        boolean flag = true;
        try {
            openConnection();
            /*
             *  Objeto "callStmt" para invocar o procedimento "addSailor" armazenado
             *  na BD.
             *
             *  PROCEDURE addSailor(sid NUMBER, sname VARCHAR, rating NUMBER, age NUMBER)
             *  PACKAGE pkgSailors AS TYPE ref_cursor IS REF CURSOR; END pkgSailors;
             */
            CallableStatement callStmt = getConnection().prepareCall("{ call updateCourier(?,?,?,?,?,?) }");

            callStmt.setInt(1, oCourier.getId());
            callStmt.setString(2, oCourier.getName());
            callStmt.setString(3, oCourier.getEmail());
            callStmt.setInt(4, oCourier.getNif());
            callStmt.setString(5, oCourier.getM_iban());
            callStmt.setInt(6, oCourier.getM_Pharmacy().getId());

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

    public Courier getCourierByID(Integer id) {

        /* Objeto "callStmt" para invocar a função "getSailor" armazenada na BD.
         *
         * FUNCTION getSailor(id NUMBER) RETURN pkgSailors.ref_cursor
         * PACKAGE pkgSailors AS TYPE ref_cursor IS REF CURSOR; END pkgSailors;
         */
        CallableStatement callStmt = null;
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{ ? = call getCourierByID(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            // Especifica o parâmetro de entrada da função "getSailor".
            callStmt.setInt(2, id);

            // Executa a invocação da função "getSailor".
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int courierID = rSet.getInt(1);
                String courierName = rSet.getString(2);
                String courierEmail = rSet.getString(3);
                String password = rSet.getString(4);
                Integer strNIF = rSet.getInt(5);
                String strIban = rSet.getString(6);
                Pharmacy oPharmacy = pharmacyManager(rSet, 7);
                closeAll();
                return new Courier(courierID, courierName, courierEmail, password, strNIF, strIban, oPharmacy);
            }
            closeAll();
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        throw new IllegalArgumentException("No Courier: " + id);
    }
}
