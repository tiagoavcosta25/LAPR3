package lapr.project.model.service;

import lapr.project.data.CourierDB;
import lapr.project.model.Courier;
import lapr.project.model.PassGenerator;
import lapr.project.model.Pharmacy;
import java.security.NoSuchAlgorithmException;

public class CourierService {

    private CourierDB moCourierDB;

    public CourierDB getCourierDB() {
        return moCourierDB;
    }

    public void setCourierDB(CourierDB oCourierDB) {
        this.moCourierDB = oCourierDB;
    }

    public CourierService() {
        moCourierDB = new CourierDB();
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
        boolean flag = moCourierDB.parkScooter(intIdScooter);
        return moCourierDB.parkScooterDirectory(intIdScooter, flag);
    }

}
