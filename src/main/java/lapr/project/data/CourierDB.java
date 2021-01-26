package lapr.project.data;

import lapr.project.model.*;
import lapr.project.model.service.CourierService;
import lapr.project.utils.Constants;
import lapr.project.utils.DirectoryVerification;
import lapr.project.utils.EmailSender;
import lapr.project.utils.TimeCalculator;
import oracle.jdbc.OracleTypes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;

public class CourierDB extends DataHandler {

    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(CourierService.class.getName());

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

        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return flag;
    }

    /**
     * Exemplo de invocação de uma "stored procedure".
     * <p>
     * Remove o marinheiro especificado da tabela "Sailors".
     *
     * @param email o identificador do marinheiro a remover.
     */
    public boolean removeCourier(String email) {
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

            callStmt.setString(1,email);

            callStmt.execute();

        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        }
        finally {
            closeAll();
        }
        return flag;

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
            callStmt.setString(5, oCourier.getIban());
            callStmt.setInt(6, oCourier.getPharmacy().getId());

            callStmt.execute();

        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return flag;
    }

    public Courier getCourierByEmail(String email) {

        /* Objeto "callStmt" para invocar a função "getSailor" armazenada na BD.
         *
         * FUNCTION getSailor(id NUMBER) RETURN pkgSailors.ref_cursor
         * PACKAGE pkgSailors AS TYPE ref_cursor IS REF CURSOR; END pkgSailors;
         */
        CallableStatement callStmt = null;
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{ ? = call getCourierByEmail(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            // Especifica o parâmetro de entrada da função "getSailor".
            callStmt.setString(2, email);

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
                return new Courier(courierID, courierName, courierEmail, password, strNIF, strIban, oPharmacy);
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Courier: " + email);
    }

    public boolean parkScooter(int intId) {
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call parkScooter(?) }");

            callStmt.setInt(1, intId);

            callStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll();
        }
        return true;
    }

    public boolean parkScooterDirectory(Integer intIdScooter, boolean flag) {
            String estimateFileName = DirectoryVerification.verifyFileCreation(Constants.ESTIMATE_FILE_PATH,
                    Constants.ESTIMATE_FILE_FILTER, 50);

            if(estimateFileName.equals(""))
                return false;

            double estimate = 0;

            try (BufferedReader br = new BufferedReader(new FileReader(Constants.ESTIMATE_FILE_PATH + "/" + estimateFileName))) {
                String strCurrentLine;
                if ((strCurrentLine = br.readLine()) != null) {
                    estimate = Double.parseDouble(strCurrentLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            List<Integer> time = TimeCalculator.calculateTime(estimate);

            int hours = time.get(0);
            int minutes = time.get(1);
            int seconds = time.get(2);

            LocalDateTime lt = LocalDateTime.now();
            lt = lt.plusSeconds(seconds);
            lt = lt.plusMinutes(minutes);
            lt = lt.plusHours(hours);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
            String formattedDateTime = lt.format(formatter); //


            if(flag) {
                EmailSender.sendEmail("antoniomsbarros@gmail.com",
                        "Scooter Parked", String.format("The scooter number %d was parked successfully!\n__________" +
                                        "_________________________________________________________\n\n" + "Estimated charging " +
                                        "time: %d hours, %d minutes %d seconds.\nEstimated time for full charge: %s.\n__________" +
                                        "_________________________________________________________\n\n" + "Thank you for " +
                                        "choosing us.\nKing regards,\nPharmacy Service G21.", intIdScooter, time.get(0), time.get(1),
                                time.get(2), formattedDateTime));

                File file = new File(Constants.ESTIMATE_FILE_PATH + "/" + estimateFileName);
                if (file.delete()) {
                    file = new File(Constants.ESTIMATE_FILE_PATH + "/" + estimateFileName + Constants.ESTIMATE_FILE_FILTER);
                    if (file.delete())
                        LOGGER.log(Level.INFO, "File handled successfully!");
                }
            }
            return flag;

    }
}
