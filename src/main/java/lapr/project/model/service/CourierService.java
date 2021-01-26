package lapr.project.model.service;

import lapr.project.controller.ApplicationPOT;
import lapr.project.data.CourierDB;
import lapr.project.model.Courier;
import lapr.project.model.PassGenerator;
import lapr.project.model.Pharmacy;
import lapr.project.utils.Constants;
import lapr.project.utils.DirectoryVerification;
import lapr.project.utils.EmailSender;
import lapr.project.utils.TimeCalculator;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;

public class CourierService {

    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(CourierService.class.getName());

    private CourierDB moCourierDB;


    public CourierService() {
        moCourierDB = new CourierDB();
    }

    public Courier newCourier(String strName, String strEmail, Integer strNIF, String strIBAN, Pharmacy oPharmacy) throws NoSuchAlgorithmException {
        PassGenerator pass = new PassGenerator();
        String password = pass.generatePassword();

        return new Courier(strName, strEmail, password, strNIF, strIBAN, oPharmacy);
    }

    public boolean registersCourier(Courier oCourier) {
        return moCourierDB.addCourierToDB(oCourier.getName(), oCourier.getEmail(), oCourier.getPw(), oCourier.getNif(), oCourier.getM_iban(), oCourier.getM_Pharmacy().getId());
    }

    public Courier getCourierByEmail(String email) {
        return moCourierDB.getCourierByEmail(email);
    }

    public Courier updateCourier(Courier courier, String strName, String strEmail, Integer intNif, String strIban, Pharmacy oPharmacy) {
        if (strName != null) courier.setName(strName);
        if (strEmail != null) courier.setEmail(strEmail);
        if (intNif != null) courier.setNif(intNif);
        if (strIban != null) courier.setM_iban(strIban);
        if (oPharmacy != null) courier.setM_Pharmacy(oPharmacy);
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
        boolean flag = moCourierDB.parkScooter(intIdScooter);
        return moCourierDB.parkScooterDirectory(intIdScooter, flag);
    }

}
