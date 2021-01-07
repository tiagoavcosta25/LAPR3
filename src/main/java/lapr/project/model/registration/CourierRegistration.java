package lapr.project.model.registration;

import lapr.project.data.DataHandler;
import lapr.project.model.Address;
import lapr.project.model.Courier;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourierRegistration extends DataHandler {


    public Courier getCourier(int id) {

        /* Objeto "callStmt" para invocar a função "getSailor" armazenada na BD.
         *
         * FUNCTION getSailor(id NUMBER) RETURN pkgSailors.ref_cursor
         * PACKAGE pkgSailors AS TYPE ref_cursor IS REF CURSOR; END pkgSailors;
         */
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
                String nif = rSet.getString(3);
                String iban = rSet.getString(4);

                return new Courier(courierID, clientName, nif, iban);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Courier with ID:" + id);
    }

    /**
     * Exemplo de invocação de uma "stored procedure".
     * <p>
     * Adiciona o marinheiro especificado à tabela "Sailors".
     *
     * @param strName o identificador do marinheiro.
     * @param strNif  o nome do marinheiro.
     * @param strIban o "rating" do marinheiro.
     */
    private void addCourierToDB(String strName, String strEmail, String strPassword,String strNif, String strIban) {
        try {
            openConnection();
            /*
             *  Objeto "callStmt" para invocar o procedimento "addSailor" armazenado
             *  na BD.
             *
             *  PROCEDURE addSailor(sid NUMBER, sname VARCHAR, rating NUMBER, age NUMBER)
             *  PACKAGE pkgSailors AS TYPE ref_cursor IS REF CURSOR; END pkgSailors;
             */
            CallableStatement callStmt = getConnection().prepareCall("{ call addCourier(?,?,?,?,?) }");

            callStmt.setString(1, strName);
            callStmt.setString(2, strEmail);
            callStmt.setString(3, strPassword);
            callStmt.setString(4, strNif);
            callStmt.setString(5, strIban);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public Courier newCourier(String strName, String strEmail, String strNIF, String strIBAN) {
        String password = "";
        return new Courier(strName,strEmail,password,strNIF,strIBAN);
    }

    public void registersCourier(Courier oCourier) {
        addCourierToDB(oCourier.getM_name(),oCourier.getStrEmail(),oCourier.getPw(), oCourier.getM_nif(), oCourier.getM_iban());
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
                float latitude = rSet.getFloat(1);
                float longitude = rSet.getFloat(2);
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
}
