package lapr.project.data;

import lapr.project.model.Courier;
import lapr.project.model.Pharmacy;
import lapr.project.utils.*;
import oracle.jdbc.OracleTypes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;

/**
 * Courier Database.
 *
 * Group: Team Lisa [G-021]
 * ______________________________________________________
 * @author António Barbosa <1190404@isep.ipp.pt>
 * @author Ernesto Rodrigues <1190560@isep.ipp.pt>
 * @author Jessica Alves <1190682@isep.ipp.pt>
 * @author Pedro Santos <1190967@isep.ipp.pt>
 * @author Rodrigo Costa <1191014@isep.ipp.pt>
 * @author Tiago Costa <1191460@isep.ipp.pt>
 */
public class CourierDB extends DataHandler {

    /**
     * Logger which is used to generate warnings or information, with
     * a custom message
     */
    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(CourierDB.class.getName());

    /**
     * Recieves the Courier's name, email, password, nif, iban and pharmacies id and registers it on the Database.
     *
     * @param strName Courier's name
     * @param strEmail Courier's email
     * @param strPassword Courier's password
     * @param strNif Courier's nif
     * @param strIban Courier's Iban
     * @param pharmacyId Pharmacy's Id
     * @return true if the Courier is registered on the Database. False otherwise.
     */
    public boolean addCourierToDB(String strName, String strEmail, String strPassword, Integer strNif, String strIban, Integer pharmacyId) {
        boolean flag = true;
        try(CallableStatement callStmt = getConnection().prepareCall("{ call addCourier(?,?,?,?,?,?) }");) {
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
     * Recieves the Courier's email and removes it on the Database
     *
     * @param email Courier's email
     * @return true if the Courier is removed from the Database. False otherwise.
     */
    public boolean removeCourier(String email) {
        boolean flag = true;
        try(CallableStatement callStmt = getConnection().prepareCall("{ call removeCourier(?) }");) {
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

    /**
     * Recieves the Courier's instance and updates it on the Database
     *
     * @param oCourier Courier's instance
     * @return true if the Courier is updated from the Database. False otherwise.
     */
    public boolean updateCourierDB(Courier oCourier) {
        boolean flag = true;
        try(CallableStatement callStmt = getConnection().prepareCall("{ call updateCourier(?,?,?,?,?,?) }");) {
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

    /**
     * Recieves the Courier's email and returns the Courier's instance.
     * @param email Courier's email
     * @return the Courier's instance
     */
    public Courier getCourierByEmail(String email) {
        try(CallableStatement callStmt = getConnection().prepareCall("{ ? = call getCourierByEmail(?) }");) {

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, email);
            callStmt.execute();

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
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Courier: " + email);
    }

    /**
     * Recieves the Scooter's id and sets the Scooter as parked on the Database.
     * @param intId Scooter's id
     * @return 1 if the Scooter was parked, 0 otherwise.
     */
    public int parkScooter(int intId) {
        try(CallableStatement callStmt = getConnection().prepareCall("{ ? = call parkScooter(?) }");) {
            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setInt(2, intId);

            callStmt.execute();
            return callStmt.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            closeAll();
        }
    }

    /**
     * Recieves the Scooter's id and sends the charging status to the email
     * @param intIdScooter Scooter's id
     * @return true if the charging status is sent to the email, false otherwise
     */
    public boolean parkScooterDirectory(Integer intIdScooter) {
        String estimateFileName = DirectoryVerification.verifyFileCreation(Constants.ESTIMATE_FILE_PATH,
                Constants.FILTER, 50);

        if (estimateFileName.equals(""))
            return false;

        double estimate = 0;
        String email = "";

        try (BufferedReader br = new BufferedReader(new FileReader(Constants.ESTIMATE_FILE_PATH + "/" + estimateFileName))) {
            String strCurrentLine;
            if ((strCurrentLine = br.readLine()) != null) {
                String[] aux = strCurrentLine.split(";");
                estimate = Double.parseDouble(aux[0]);
                email = aux[1];
            }
        } catch (IOException e) {
            return false;
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
        String body = String.format("The scooter number %d was parked successfully!\n__________" +
                        "_________________________________________________________\n\n" + "Estimated charging " +
                        "time: %d hours, %d minutes %d seconds.\nEstimated time for full charge: %s.", intIdScooter, time.get(0), time.get(1),
                time.get(2), formattedDateTime);
        EmailSender.sendEmail(email,
                "Scooter Parked", body);
        WriteFile.write("Park_log_" + intIdScooter, body);

        File file = new File(Constants.ESTIMATE_FILE_PATH + estimateFileName);
        try {
            Files.delete(file.toPath());
            file = new File(Constants.ESTIMATE_FILE_PATH + estimateFileName + Constants.FILTER);
            Files.delete(file.toPath());
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "There was an error deleting the file!");
            return false;
        }
        LOGGER.log(Level.INFO, "File handled successfully!");
        return true;
    }
}
