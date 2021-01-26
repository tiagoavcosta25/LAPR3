package lapr.project.model.service;

import com.sun.javafx.binding.StringFormatter;
import javafx.util.Pair;
import lapr.project.data.CourierDB;
import lapr.project.data.ScooterDB;
import lapr.project.data.VehicleDB;
import lapr.project.model.*;
import lapr.project.utils.Constants;

import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;

public class CourierService {

    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(CourierService.class.getName());

    private CourierDB moCourierDB;

    private ScooterDB moScooterDB;

    public CourierService() {
        moCourierDB = new CourierDB();
        moScooterDB = new ScooterDB();
    }

    public CourierDB getCourierDB() {
        return moCourierDB;
    }

    public void setCourierDB(CourierDB oCourierDB) {
        this.moCourierDB = oCourierDB;
    }

    public Courier newCourier(String strName, String strEmail, Integer strNIF, String strIBAN, Pharmacy oPharmacy) throws NoSuchAlgorithmException {
        PassGenerator pass = new PassGenerator();
        String password = pass.generatePassword();

        return new Courier(strName, strEmail, password, strNIF, strIBAN, oPharmacy);
    }

    public boolean registersCourier(Courier oCourier) {
        return moCourierDB.addCourierToDB(oCourier.getName(), oCourier.getEmail(), oCourier.getPw(), oCourier.getNif(), oCourier.getIban(), oCourier.getPharmacy().getId());
    }

    public Courier getCourierByEmail(String email) {
        return moCourierDB.getCourierByEmail(email);
    }

    public Courier updateCourier(Courier courier, String strName, String strEmail, Integer intNif, String strIban, Pharmacy oPharmacy) {
        if (strName != null) courier.setName(strName);
        if (strEmail != null) courier.setEmail(strEmail);
        if (intNif != null) courier.setNif(intNif);
        if (strIban != null) courier.setIban(strIban);
        if (oPharmacy != null) courier.setPharmacy(oPharmacy);
        return courier;
    }

    public boolean updateCourierDB(Courier oCourier) {
        return moCourierDB.updateCourierDB(oCourier);
    }

    public boolean removeCourier(String email) {
        try {
            return moCourierDB.removeCourier(email);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean parkScooter(int intIdScooter) {
        boolean flag = moCourierDB.parkScooterDirectory(intIdScooter);
        if(!flag) return false;
        int parkId = moCourierDB.parkScooter(intIdScooter);
        if(parkId == -1) {
            return false;
        }
        //lock_2020_12_32_12_34_54.data
        List<Pair<String, Scooter>> lst = moScooterDB.getEmailPerChargingScooter(parkId);
        double currentDouble = moScooterDB.getCurrentPerCharger(parkId);
        String parkCurrent = String.format("Park id: %d - Current: %f A", parkId, currentDouble);
        LOGGER.log(Level.INFO, parkCurrent);
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
                e.printStackTrace();
            }
            try {
                FileWriter myWriter = new FileWriter(Constants.LOCK_FILE_PATH + body + Constants.FILTER);
                myWriter.write(fileText);
                myWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            moCourierDB.parkScooterDirectory(pair.getValue().getId());
        }
        String body = String.format("Scooter with id: %d was parked parked successfully!", intIdScooter);
        LOGGER.log(Level.INFO, body);
        return true;
    }

}
