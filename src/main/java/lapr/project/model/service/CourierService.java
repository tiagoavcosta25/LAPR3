package lapr.project.model.service;

import javafx.util.Pair;
import lapr.project.data.CourierDB;
import lapr.project.data.ScooterDB;
import lapr.project.model.*;
import lapr.project.utils.Constants;
import lapr.project.utils.EmailSender;
import lapr.project.utils.WriteFile;

import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;

/**
 * Courier Service.
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
public class CourierService {
    /**
     * Logger which is used to generate warnings or information, with
     * a custom message
     */
    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(CourierService.class.getName());

    /**
     * Subject for email sender and file writer
     */
    private static final String SUBJECT = "Scooter parking automated response.";

    /**
     * Courier Database class
     */
    private CourierDB moCourierDB;

    /**
     * Scooter Database class
     */
    private ScooterDB moScooterDB;

    /**
     * Password.
     */
    private String mstrPassword;

    /**
     * Initializes the Courier Service with an instance of CourierDB and ScooterDB.
     */
    public CourierService() {
        moCourierDB = new CourierDB();
        moScooterDB = new ScooterDB();
    }

    /**
     * The method receives Courier's name, email, nif, iban and Courier's pharmacy.
     * The method generates a password and returns a new Courier.
     *
     * @param strName  Courier's name.
     * @param strEmail Courier's email.
     * @param intNIF   Courier's nif.
     * @param strIBAN  Courier's iban.
     * @param oPharmacy  Courier's Pharmacy.
     */
    public Courier newCourier(String strName, String strEmail, Integer intNIF, String strIBAN, Pharmacy oPharmacy) throws NoSuchAlgorithmException {
        PassGenerator pass = new PassGenerator();
        this.mstrPassword = pass.generatePassword();

        return new Courier(strName, strEmail, this.mstrPassword, intNIF, strIBAN, oPharmacy);
    }

    /**
     * The methods registers a Courier on the Database.
     * @param c Courier instance
     * @return true if the Courier is registered on the Database. False otherwise.
     */
    public boolean registersCourier(Courier c) {
        String body = String.format("Account Information:%n%nName: %s%nNIF: %s%nPassword: %s%n"
                , c.getName(), c.getNif(), this.mstrPassword);
        EmailSender.sendEmail(c.getEmail(), "Account Creation", body
        );
        WriteFile.write("CourierRegistration_" + c.getEmail(), body);
        return moCourierDB.addCourierToDB(c.getName(), c.getEmail(), c.getPw(), c.getNif(), c.getIban(), c.getPharmacy().getId());
    }

    /**
     * The methods recieves the Courier's email and returns the Courier's instance.
     * @param email Courier's email
     * @return Courier's instance
     */
    public Courier getCourierByEmail(String email) {
        return moCourierDB.getCourierByEmail(email);
    }

    /**
     * The method recieves the Courier's instance, name, email, nif, iban and Courier's pharmacy and updates the values.
     *
     * @param courier Courier's instance
     * @param strName Courier's name
     * @param strEmail Courier's email
     * @param intNif Courier's nif
     * @param strIban Courier's iban
     * @param oPharmacy Courier's pharmacy
     * @return the Courier's instance
     */
    public Courier updateCourier(Courier courier, String strName, String strEmail, Integer intNif, String strIban, Pharmacy oPharmacy) {
        if (strName != null) courier.setName(strName);
        if (strEmail != null) courier.setEmail(strEmail);
        if (intNif != null) courier.setNif(intNif);
        if (strIban != null) courier.setIban(strIban);
        if (oPharmacy != null) courier.setPharmacy(oPharmacy);
        return courier;
    }

    /**
     * Recieves a Courier's instance and update's it on the Database.
     * @param oCourier Courier's instance
     * @return true if the Courier is updated on the Database. False otherwise.
     */
    public boolean updateCourierDB(Courier oCourier) {
        return moCourierDB.updateCourierDB(oCourier);
    }

    /**
     * Recieves the Courier's email and removes it from the Database.
     * @param email Courier's email
     * @return true if the Courier is removed. False if otherwise.
     */
    public boolean removeCourier(String email) {
        try {
            return moCourierDB.removeCourier(email);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Recieves the Scooter's Id and sets the Scooter as parked on the Database.
     * @param intIdScooter Scooter's Id
     * @return true if the Scooter is set as parked. False if otherwise.
     */
    public boolean parkScooter(int intIdScooter, String strEmailCourier) {
        //Prepare print
        String temp;
        String fileName = String.format("Park_log_%d", intIdScooter);
        StringBuilder outputBody = new StringBuilder();
        outputBody.append(SUBJECT).append("\n\n");

        //Check if scooter and Courier belong to the same pharmacy
        boolean samePh = moCourierDB.checkIfScooterAndCourierFromSamePh(intIdScooter, strEmailCourier);
        if(!samePh) {
            temp = String.format("Courier with email %s and scooter with id %d are not from the " +
                    "same pharmacy!", strEmailCourier, intIdScooter);
            outputBody.append(temp);
            LOGGER.log(Level.WARNING, temp);
            WriteFile.write(fileName, outputBody.toString());
            EmailSender.sendEmail(strEmailCourier, SUBJECT, outputBody.toString());
            return false;
        }

        //Id of avaiable park
        int parkId = moCourierDB.getFreeParkingSlot(intIdScooter);
        boolean chargingSlot;
        boolean flag;

        //If -1 no park avaiable
        if(parkId == -1) {
            temp = String.format("There are no avaiable parks to park the scooter with id %d or it's already parked!",
                    intIdScooter);
            outputBody.append(temp);
            LOGGER.log(Level.WARNING, temp);
            WriteFile.write(fileName, outputBody.toString());
            EmailSender.sendEmail(strEmailCourier, SUBJECT, outputBody.toString());
            return false;
        }

        //Check if it's charing slot
        else
            chargingSlot = moCourierDB.checkIfChargingSlot(parkId);

        //If it's charging slot ask for file verification
        if(chargingSlot) {
            double currentDouble = moScooterDB.getCurrentPerCharger(parkId);
            String parkCurrent = String.format("Park id: %d - Current: %.2f A", parkId, currentDouble);
            LOGGER.log(Level.INFO, parkCurrent);
            flag = moCourierDB.parkScooterDirectory(intIdScooter);

            //If file verification returns false end with error
            if (!flag) {
                temp = String.format("Scooter with id %d was not locked correctly!",
                        intIdScooter);
                outputBody.append(temp);
                LOGGER.log(Level.WARNING, temp);
                WriteFile.write(fileName, outputBody.toString());
                EmailSender.sendEmail(strEmailCourier, SUBJECT, outputBody.toString());
                return false;
            }

            //If file verification returns true start prints
            List<Pair<String, Scooter>> lst = moScooterDB.getEmailPerChargingScooter(parkId);
            int current = (int) currentDouble;
            for(Pair<String, Scooter> pair : lst) {

                LocalDateTime now = LocalDateTime.now();
                int year = now.getYear();
                int month = now.getMonthValue();
                int day = now.getDayOfMonth();
                int hour = now.getHour();
                int minute = now.getMinute();
                int second = now.getSecond();
                String body = String.format("lock_%d_%02d_%02d_%02d_%02d_%02d.data", year, month, day, hour, minute, second);
                int capacity = pair.getValue().getModel().getBattery().getBatteryCapacity();
                int charge = (int) pair.getValue().getBatteryPerc();
                String email = pair.getKey();
                String fileText = String.format("%d;%d;%d;%s", capacity,current,charge,email);
                try {
                    FileWriter myWriter = new FileWriter(Constants.LOCK_FILE_PATH + body);
                    myWriter.write(fileText);
                    myWriter.close();
                } catch (IOException e) {
                    temp = String.format("Scooter with id %d was not locked successfully!",
                            intIdScooter);
                    outputBody.append(temp);
                    WriteFile.write(fileName, outputBody.toString());
                    EmailSender.sendEmail(strEmailCourier, SUBJECT, outputBody.toString());
                    return false;
                }
                try {
                    FileWriter myWriter = new FileWriter(Constants.LOCK_FILE_PATH + body + Constants.FILTER);
                    myWriter.write(fileText);
                    myWriter.close();
                } catch (IOException e) {
                    temp = String.format("Scooter with id %d was not locked successfully!",
                            intIdScooter);
                    outputBody.append(temp);
                    WriteFile.write(fileName, outputBody.toString());
                    EmailSender.sendEmail(strEmailCourier, SUBJECT, outputBody.toString());
                    return false;
                }
                moCourierDB.parkScooterDirectory(pair.getValue().getId());
            }
        }

        //Check if parked successfully
        boolean parked = moCourierDB.parkScooter(intIdScooter, parkId);

        //If not parked successfully end with error
        if(!parked) {
            temp = String.format("Scooter with id %d was not locked successfully!",
                    intIdScooter);
            outputBody.append(temp);
            WriteFile.write(fileName, outputBody.toString());
            EmailSender.sendEmail(strEmailCourier, SUBJECT, outputBody.toString());
            return false;
        }

        //If scooter didn't need to charge and nothing went wrong, prepare output for non charing spot
        if(!chargingSlot) {
            temp = String.format("Scooter with id %d was parked successfully on a non charging parking spot!",
                    intIdScooter);
            outputBody.append(temp);
            LOGGER.log(Level.WARNING, temp);
            WriteFile.write(fileName, outputBody.toString());
            EmailSender.sendEmail(strEmailCourier, SUBJECT, outputBody.toString());
        }
        return true;
    }

    /**
     * Returns the Courier's Database
     * @return Courier DataBase
     */
    public CourierDB getCourierDB() {
        return moCourierDB;
    }

    /**
     * The methods sets the Courier's Database
     * @param oCourierDB Courier Database
     */
    public void setCourierDB(CourierDB oCourierDB) {
        this.moCourierDB = oCourierDB;
    }

}
