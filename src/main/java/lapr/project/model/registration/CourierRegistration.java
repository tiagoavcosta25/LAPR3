package lapr.project.model.registration;

import lapr.project.data.DataHandler;
import lapr.project.model.*;
import oracle.jdbc.OracleTypes;

import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourierRegistration extends DataHandler {

    public CourierRegistration(String jdbcUrl, String username, String password) {
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
    private boolean addCourierToDB(String strName, String strEmail, String strPassword, Integer strNif, String strIban, Integer pharmacyId) {
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
    public void removeSailor(int intId) {

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
            e.printStackTrace();
        }

    }

    public Courier newCourier(String strName, String strEmail, Integer strNIF, String strIBAN,Pharmacy oPharmacy) throws NoSuchAlgorithmException {
        PassGenerator pass = new PassGenerator();
        String password = pass.generatePassword();

        return new Courier(strName, strEmail, password, strNIF, strIBAN,oPharmacy);
    }

    public boolean registersCourier(Courier oCourier) {
        return addCourierToDB(oCourier.getM_name(), oCourier.getStrEmail(), oCourier.getPw(), oCourier.getM_nif(), oCourier.getM_iban(), oCourier.getM_Pharmacy().getId());
    }

    public Address getDeliveryAddress(String email) {
        /* Objeto "callStmt" para invocar a função "getSailor" armazenada na BD.
         *
         * FUNCTION getSailor(id NUMBER) RETURN pkgSailors.ref_cursor
         * PACKAGE pkgSailors AS TYPE ref_cursor IS REF CURSOR; END pkgSailors;
         */
        CallableStatement callStmt = null;
        try {
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

                return new Address(latitude, longitude, streetName, doorNumber, postalCode, locality, country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Address for Courier:" + email);
    }

    public ChargingSlot getAvailableChargingSlot(String email) {
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
            callStmt.setString(1, email);

            // Executa a invocação da função "getSailor".
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int chargingSlotID = rSet.getInt(1);
                int pharmacyID = rSet.getInt(2);
                String pharmacyName = rSet.getString(3);
                int addressID = rSet.getInt(4);
                Double latitude = rSet.getDouble(5);
                Double longitude = rSet.getDouble(6);
                String doorNumber = rSet.getString(7);
                String streetName = rSet.getString(8);
                String postalCode = rSet.getString(9);
                String locality = rSet.getString(10);
                String country = rSet.getString(11);
                int maxSlotsNumber = rSet.getInt(12);
                float outputPower = rSet.getFloat(13);

                return new ChargingSlot(chargingSlotID,new Park(pharmacyID,maxSlotsNumber,
                        new Pharmacy(pharmacyID,pharmacyName,new PharmacyManager(), new Address(addressID,latitude,longitude,streetName,doorNumber,postalCode,locality,country))), outputPower);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        throw new IllegalArgumentException("No Charging Slot for Courier: " + email);
    }
}
